package com.sinosoft.aspect.softphone.soap;

import com.sinosoft.aspect.softphone.soap.agent.AgentPortalException;
import com.sinosoft.aspect.softphone.soap.agent.Client.UDAgent;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Stream;

/**
 * Web Service 接口公共抽象类
 * 实现 服务器 连接、发包、解析返回值功能
 * @author wangjunhua
 * @since 1.0
 *
 */
@Slf4j
public abstract class Stub {

	//发送消息编码
	private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	//请求的 PORTAL 地址全路径
	protected URL cachedEndpoint;
	//服务器连接超时时间
	protected Integer connectTimeout;
	//服务器读超时时间
	protected Integer readTimeout;
	//软电话注册的 SESSIONID
	protected String sessionId;

	//SOAP 消息头
	private static final String header = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n<soapenv:Body>\n";
	//SOAP 包的结束
	private static final String endEnvelope = "</soapenv:Body>\n</soapenv:Envelope>";

	/**
	 * 调用接口
	 * @param action 执行方法
	 * @param params 请求参数
	 * @return 接口返回结果
	 */
	protected Object invoke(String action ,Object[] params) throws AgentPortalException{

		try {
			//组装参数
			StringBuilder message = new StringBuilder()
					.append(header)
					.append(buildParm(action,params))
					.append(endEnvelope);

			return send(action,message);

		}catch (SocketTimeoutException e){
			throw new AgentPortalException(action + ": Socket Read timed out ");
		}catch (IOException e){
			throw new AgentPortalException("网络连接超时！");
		}
	}

	private StringBuilder buildParm(String methodName,Object[] params){
		StringBuilder content = new StringBuilder();
		content.append(startPortName(methodName));

		for (int i = 0; i < params.length; i++) {
			content.append(setParamsHref(params[i], i));
		}

		content.append(endPortName(methodName));

		for (int i = 0; i < params.length; i++) {
			content.append(setParamsValue(params[i], new Object[]{i}));
		}

		return content;
	}

	private String setParamsHref(Object param,int index){
		Class type = param.getClass();
		String name = type.getName();
		if (name.equals("java.lang.String")) {
			return param.toString();
		} else {
			return setParams(param, new Object[]{index});
		}
	}

	private String setParams(Object param,Object[] index){

		Class type = param.getClass();
		try {
			Method method = type.getMethod("getHref", Integer.class);
			return (String) method.invoke(param, index);

		} catch (SecurityException e) {
			log.error("setParams :异常Security");
		} catch (NoSuchMethodException e) {
			log.error("setParams :Class={}, 异常NoSuchMethod",type);
		} catch (IllegalArgumentException e) {
			log.error("setParams :异常IllegalArgument");
		} catch (IllegalAccessException e) {
			log.error("setParams :异常IllegalAccess");
		} catch (InvocationTargetException e) {
			log.error("setParams :异常InvocationTarget");
		}

		return "";
	}

	private String setParamsValue(Object param,Object[] index){
		try {
			Class type = param.getClass();
			String name = type.getName();
			if (!"java.lang.String".equalsIgnoreCase(name)) {
				Method method = type.getMethod("toString", Integer.class);
				return (String) method.invoke(param, index);
			}
		} catch (SecurityException e) {
			log.error("setParams :异常Security");
		} catch (NoSuchMethodException e) {
			log.error("setParams :异常NoSuchMethod");
		} catch (IllegalArgumentException e) {
			log.error("setParams :异常IllegalArgument");
		} catch (IllegalAccessException e) {
			log.error("setParams :异常IllegalAccess");
		} catch (InvocationTargetException e) {
			log.error("setParams :异常InvocationTarget");
		}

		return "";
	}

	/**
	 * 访问的接口名称  开始
	 */
	private String startPortName(String portName){
		return "<" + portName + ">";
	}

	/**
	 * 访问的接口名称  结束
	 */
	private String endPortName(String portName){
		return "</" + portName + ">";
	}


	//向服务器端发送SOAP包
	private Object send(String action,StringBuilder message) throws IOException {

		log.info("send = {}", message);

		HttpURLConnection httpConn = createConnection();
		httpConn.setRequestProperty("Content-Length",String.valueOf(message.length()));

		// 注册成功以后每次发送 SESSIONID 维持登陆状态
		if (sessionId != null) {
			httpConn.setRequestProperty("Cookie", sessionId);
			log.info("sessionId={}", sessionId);
		}

		httpConn.connect();

		OutputStream out = httpConn.getOutputStream();
		out.write(message.toString().getBytes(UTF8_CHARSET));
		out.flush();

		if (httpConn.getResponseCode() != 200) {
			log.info("HTTP response: " + httpConn.getResponseCode()
					+ " :: " + httpConn.getResponseMessage());
		}

		// Aspect 会在注册以后返回 SESSIONID 用来维持登陆状态，否则坐席 session 会过期
		if ("register".equalsIgnoreCase(action)) {
			sessionId = httpConn.getHeaderField("Set-Cookie");
			log.info("登陆成功!获取到 sessionId ：{}" + sessionId);
		}

		if ("unRegister".equalsIgnoreCase(action)) {
			sessionId = null;
		}

		Object res = null;
		if (httpConn.getResponseCode() == 200) {
			StringBuffer buff = new StringBuffer();
			InputStream in = httpConn.getInputStream();
			//调试时使用
			InputStreamReader is = new InputStreamReader(in, UTF8_CHARSET);
			BufferedReader r = new BufferedReader(is);

			while (true) {
				String line = r.readLine();
				if (line == null) {
					break;
				}
				buff.append(line).append('\n');
			}

			log.info("Response：{}", buff);

			res = parserSoap(buff.toString());

			r.close();
			is.close();
			in.close();
		}

		httpConn.disconnect();

		return res;
	}

	private HttpURLConnection createConnection() throws IOException{

		HttpURLConnection httpConn = _openConnection();

		httpConn.setConnectTimeout(this.connectTimeout);
		httpConn.setReadTimeout(this.readTimeout);
		httpConn.setDoInput(true);
		httpConn.setDoOutput(true);
		httpConn.setRequestProperty("Host", getHost());
		httpConn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		httpConn.setRequestProperty("Accept", "application/soap+xml, application/dime, multipart/related, text/*");
		httpConn.setRequestProperty("User-Agent", "Axis/1.2RC2");
		httpConn.setRequestProperty("SOAPAction", "");
		httpConn.setRequestProperty("Connection", "Keep-Alive");
		httpConn.setRequestProperty("Cache-Control", "no-cache");
		httpConn.setRequestProperty("Pragma", "no-cache");
		httpConn.setRequestMethod("POST");

		return httpConn;
	}

	/**
	 * 连接 Aspect 服务器
	 * @return HttpURLConnection http 链接
	 * @throws IOException 网络异常
	 */
	private HttpURLConnection _openConnection() throws IOException {
		 try
	      {
	        URLConnection connection = this.cachedEndpoint.openConnection();
	        return (HttpURLConnection)connection;
	      } catch (IOException e) {
			 log.error("SUBb _openConnection: IOException opening connection: " + e.toString());
	          throw e;
		 }
	}

	/**
	 * @note 获得Aspect 服务器地址端口
	 * @return
	 */
	private String getHost() {
		StringBuffer _address = new StringBuffer();
		return _address.append(cachedEndpoint.getHost()).append(":").append(cachedEndpoint.getPort()).toString();
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = new Integer(connectTimeout);
	}

	public void setReadTimeout(Integer readTimeout) {
		this.readTimeout = readTimeout;
	}

	/**
	 * 解析Soap 消息
	 * @return
	 */
	private Object parserSoap(String in){

		DocumentBuilderFactory dbf = null;
		try {
			System.setProperty("com.sun.org.apache.xml.internal.dtm.DTMManager", "com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault");

            //调试时使用输出返回soap  String in
			StringReader read = new StringReader(in);

			InputSource source = new InputSource(read);
			try{
				System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
			 dbf = DocumentBuilderFactory.newInstance();
			}catch (Exception e) {
				try{
					System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
					 dbf = DocumentBuilderFactory.newInstance();
				}catch (Exception e1) {

				}
			}
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(source);

			Element docElement = doc.getDocumentElement();

			NodeList root = docElement.getElementsByTagName("multiRef");
			//只返回一个字符串
			if (root.getLength() == 0) {
//				String res = docElement.getNodeValue();
//				if(res.trim().length() > 0){
//					return res;
//				}
				NodeList list = docElement.getElementsByTagName("registerAgentEventSubscriberReturn");
				if(list != null && list.getLength() > 0){
					Node res = list.item(0).getChildNodes().item(0);
					return res.getNodeValue();//docElement.getTextContent();
				}
			}

			Map ids = readSoapNode(root);
			Node body = docElement.getFirstChild();
			Node response = body.getFirstChild();
			if(response.getNodeName().indexOf("Response") > 0){
				Node infoReturn = response.getFirstChild();

				//返回值为一个对象的
				if(infoReturn != null){
					Node href = infoReturn.getAttributes().getNamedItem("href");
					if(href != null){
						if(!ids.isEmpty()){
							SoapNode soap = (SoapNode) ids.get("id0");
							soap.setSoapNodeValue(soap,ids);
							return soap.getObject();
						}
					}
					//返回值为数组的
					Node classType = infoReturn.getAttributes().getNamedItem("xsi:type");
					if(classType != null){
						String type = classType.getNodeValue();
						if(type.equals("soapenc:Array")){
							if(!ids.isEmpty()){

								List arrays = new ArrayList();
								for(Iterator it = ids.keySet().iterator();it.hasNext();){
									String key = (String) it.next();
									SoapNode soap = (SoapNode) ids.get(key);
									soap.setSoapNodeValue(soap,ids);
									arrays.add(soap.getObject());

								}
								return arrays.toArray();
							}
						}
					}
				}


			}

		} catch (Exception e) {
			log.error("parserSoap: 解析soap异常,{}",e);
		}

		return null;
	}

	private Map readSoapNode(NodeList root){
		Map<String,SoapNode> objList = new HashMap<>();
		for(int i=0;i<root.getLength();i++){
			Node node = root.item(i);
			SoapNode soap = new SoapNode(node);
			objList.put(soap.getId(), soap);
		}
		return objList;
	}

}
