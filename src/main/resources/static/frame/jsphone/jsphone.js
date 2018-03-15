/**
 * 软电话类
 * */
function JsPhone(){
	this.agentId = null;//工号
	this.password = null;//密码
	this.extension = null;//分机
	this.sessionId = null;//一次连接登录的会话ID
	this.agentIndex = null;
	this.notReadyReason = null;//示忙原因
	this.platformType = null;//平台类型
	this.platServiceUrl = null;//软电话中间件服务基地址
	this.pollingTimer = null;//轮循事件的定时器
	this.statusTimer = null;//软电话状态定时器
	this.durationTime = 0;//软电话状态持续时长
	this.historyDialNums = new Array(10);//保留最新的10个外呼号码记录
	this.callId = null;//保存一次通话的标识
	$phone = this;//获得本类的引用
};
var $phone = null; //本类的引用
/**
 * 外拔历史记录
 * */
function historyDialNum(dialNum,dialTime){
	this.dialNum = dialNum;//外拔号码
	this.dialTime = dialTime;//外拔时间
};
/**
 * Button类
 * */
function jbutton(name,i18n,className,isShow){
	this.name = name;//按钮名称
	this.i18n = i18n;//按钮国际化的KEY,也是按钮的ID
	this.className = className;//按钮的样式名称
	this.isShow = isShow;//初始化时是否显示
};
var buttonArr = new Array();
buttonArr[0] = new jbutton("接话","Answer","answer",true);
buttonArr[1] = new jbutton("挂断","Wrapup","wrapup",false);
buttonArr[2] = new jbutton("拔号","Dial","dial",true);
buttonArr[3] = new jbutton("保持","Hold","hold",true);
buttonArr[4] = new jbutton("收回","CancelHold","hold",false);
buttonArr[5] = new jbutton("咨询","Consult","consult",true);
buttonArr[6] = new jbutton("转接","Transfer","transfer",true);
buttonArr[7] = new jbutton("会议","Conference","conference",true);
buttonArr[8] = new jbutton("小休","Rest","rest",true);
buttonArr[9] = new jbutton("示忙","Busy","busy",true);
buttonArr[10] = new jbutton("示闲","Idle","idle",true);
buttonArr[11] = new jbutton("处理","ACW","acw",true);
buttonArr[12] = new jbutton("监控","Monitor","monitor",true);

var logingStatus = "签入中";
var logonStatus = "已签入|Rest|Busy|Idle"; //签入后，点亮四个按钮
var logoutStatus = "已签出";//签出后，没有按钮可用
var notReadyStatus = "示忙中|Dial|Idle|Monitor";
var idleStatus = "示闲中|Rest|Busy";
var dialingStatus = "拔号中|Wrapup"; //拔号中，点亮挂断按钮
var activeStatus = "通话中|Wrapup|Hold|Rest|Busy|";
var acwStatus = "后处理|Rest|Busy|ACW";
var alertingStatus = "震铃|Answer|Wrapup";
var holdingStatus = "保持中|CancelHold|Consult";
/**
 * 加载CSS文件
 * */
JsPhone.prototype.loadCss = function(webRoot){
	var head = document.getElementsByTagName('head')[0];
	var link = document.createElement('link');
	link.href = webRoot + "/frame/jsphone/css/UI.css";
	link.rel = 'stylesheet';
	link.type = 'text/css';
	head.appendChild(link);
};
/**
 * 初始化软电话、画界面
 * */
JsPhone.prototype.init = function(container,webRoot){
	this.loadCss(webRoot);
	var html = '<table width="580" height="46" border="0">';
	html += '<tr>';
	$.each(buttonArr,function(i, button){
		var isShow = button.isShow;
		var style = "display:;";
		if(!isShow){
			style = "display:none;";
		}
		html += '<td id="'+button.i18n+'" title="'+button.i18n+'" class="jbutton '+button.className+'Gray" style="'+style+'">';
		html += button.name;
		html += '</td>';
	});
	html += '<td width="*">';
	html += '<div class="phoneDiv">对方号码：<input type="text" id="cusPhone" style="width:100px;height:16px;"></div>';
	html += '<div class="phoneDiv">座席分机：<span id="extension"></span></div>';
	html += '<div class="phoneDiv">状态：<span id="statusName">未签入</span> <span id="statusDuration">00:00:00</span> <span id="statusImg"> &nbsp; &nbsp; &nbsp;</span></div>';
	html += '</td>';
	html += '</tr>';
	html += '</table>';
	container.innerHTML = html;
	//绑定按钮事件
	$("#Answer").click(function(){
		$phone.answerCall();
	});
	$("#Dial").click(function(){
		$phone.prepareDial();//点拔号时，弹出拔号框
	});
	$("#Hold").click(function(){//点保持按钮
		$phone.hold();
	});
	$("#CancelHold").click(function(){//取消保持
		$phone.cancelHold();
	});
	$("#Consult").click(function(){//咨询
		$phone.prepareConsult();
	});
	$("#Rest").click(function(){
		$phone.doRest();
	});
	$("#Busy").click(function(){
		$phone.setNotReady();//点示忙时，弹出示忙原因
	});
	$("#Idle").click(function(){
		$phone.setIdle();//设置空闲
	});
	$("#Wrapup").click(function(){
		$phone.wrapup(); //挂断电话
	});
	$("#ACW").click(function(){ //结束后处理
		$phone.doAcw();
	});
	//开始显示状态持续时间
	this.showStatusDuration();
};


/**
 * 初始化软电话的参数
 */
JsPhone.prototype.initParams = function(agentId, password, extension, platformType, platServiceUrl){
	this.agentId = agentId;
	this.password = password;
	this.extension = extension;
	this.platformType = platformType;
	this.platServiceUrl = platServiceUrl;
};
/**
 * 切换软电话UI展现
 * */
JsPhone.prototype.showPhoneByEvent = function(statusName){
	var statusArr = new Array();
	statusArr = statusName.split("|");
	$("#statusName")[0].innerHTML = statusArr[0];
	$.each(buttonArr,function(i, button){
		if(statusArr.contains(button.i18n)){
			$("#"+button.i18n).removeClass(button.className+"Gray").addClass(button.className);
		}else{
			$("#"+button.i18n).removeClass(button.className).addClass(button.className+"Gray");
		}
	});
};
/**
 * 计算并显示状态持续时长
 * */
JsPhone.prototype.showStatusDuration = function(){
	this.statusTimer = window.setInterval(function(){
		$phone.durationTime ++;
		var second = parseInt($phone.durationTime);//秒
		var minute = 0;	//分
		var hour = 0;	//小时
		if(second >= 60) {
			minute = parseInt(second/60);
			second = parseInt(second%60);
			if(minute >= 60) {
				hour = parseInt(minute/60);
				minute = parseInt(minute%60);
			}
		}
		var result = "00:00:"+parseInt(second);
		second = parseInt(second);
		if(parseInt(second) < 10){
			result = "00:00:0"+parseInt(second);
			second = "0" + parseInt(second)
		}
		if(minute > 0) {
			result = "00:" + parseInt(minute) + ":" + second;
			minute = parseInt(minute);
		}
		if(parseInt(minute) < 10){
			result = "00:0" + parseInt(minute) + ":" + second;
			minute = "0" + parseInt(minute);
		}
		if(hour > 0) {
			result = ""+parseInt(hour) + ":" + minute + ":" + second;
		}
		if(parseInt(hour) < 10){
			result = "0"+parseInt(hour) + ":" + minute + ":" + second;
		}
		$("#statusDuration")[0].innerHTML = result;
	},1000);
};
/**
 * 统一的向软电话中间件服务器发送请求的方法
 * */
JsPhone.prototype.sendRequest = function(method,arg1,arg2,arg3){
	var url = $phone.platServiceUrl;
	var param = "";
	if(method == "getSession"){
		param += "GetSession"+"?agentId="+this.agentId+"&password="+this.password+"&station="+this.extension;
	}else if(method == "logon"){

		param += "ActionLogon?agentId="+this.agentId+"&sessionid="+this.sessionId;

	}else if(method == "logoff"){
		param += "ActionLogoff?agentId="+this.agentId;
	}else if(method == "notReady"){
		param += "ActionSetNotReady?agentId="+this.agentId+"&notreadyreason="+arg1;
	}else if(method == "idle"){
		param += "ActionSetIdle?agentId="+this.agentId;
	}else if(method == "dial"){
		param += "ActionMakecall?agentId="+this.agentId+"&phonenumber="+arg1;
	}else if(method == "wrapup"){
		param += "ActionReleaseCall?agentId="+this.agentId+"&callid="+arg1;
	}else if(method == "answer"){
		param += "ActionAnswerCall?agentId="+this.agentId;
	}else if(method == "hold"){
		param += "ActionHoldCall?agentId="+this.agentId+"&callid="+arg1;
	}else if(method == "cancelHold"){
		param += "ActionRetrieveCall?agentId="+this.agentId+"&callid="+arg1;
	}else if(method == "acw"){
		param += "ActionCompleteCall?agentId="+this.agentId+"&callid="+arg1+"&agentIndex="+arg2;
	}

	$.ajax({
		type : 'get',
		url : url + param,
		data: null,
		//dataType : 'json',
		//jsonp : "jsoncallback",
		success : function(data){
			if(data.result == "successful"){ //登录成功
				if(method == "getSession"){
					var sessionId = data.sessionid;
					$phone.sessionId = sessionId;
					$phone.sendRequest("logon");
				}else if(method == "logon"){
					//签入成功后，开始轮遁事件
					$phone.pollingEvent();
				}
			}else if(data.result == "fail"){
				var errorcode = data.errorcode;
				alert("操作失败，错误原因：" + errorcode);
				if(method == "logon"){ //处理登录失败
					logonFail(); //这句是测试时用的，生产环境要取消掉，或在集成的页面上实现这个方法
				}
			}
		},
		error : function(){
			alert('fail');
		}
	});
};

function SendSamples_callBack(r){
    if(r.constructor.toString().indexOf("function Error()") != -1)
        alert("ERROR\r\n\r\n" + r.description + "\r\n\r\n[" + r.number + "]");
    else
        alert(r);
}

/**
 * 签入
 */
JsPhone.prototype.logon = function(){
	$phone.sendRequest("getSession");
	$phone.showPhoneByEvent(logingStatus);
	$phone.durationTime = 0;
};
/**
 * 签入成功
 * */
JsPhone.prototype.logonSuccess = function(){
	$phone.showPhoneByEvent(logonStatus);
	$phone.durationTime = 0;
	logonSuccess();//这一句所调的方法，集成的页面要实现
};
/**
 * 签出
 */
JsPhone.prototype.logoff = function(){
	$phone.sendRequest("logoff");
};
/**
 * 签出成功
 * */
JsPhone.prototype.logoffSuccess = function(){
	$phone.showPhoneByEvent(logoutStatus);
	$phone.durationTime = 0;
	window.clearInterval(this.pollingTimer);
};
/**
 * 示忙
 * */
JsPhone.prototype.setNotReady = function(){
	//点示忙时，先弹出一个示忙原因的选择框
	var popBox = $('#pop-box');
	if(!popBox.length){
		var boxStr = '<div id="pop-box" class="pop-box">';
		boxStr += '<div class="pop-box-title">示忙原因</div><div class="pop-box-body">';
		$.each(this.notReadyReason,function(i, notReady){
			boxStr += '<div class="notReadyItem" id="'+notReady.key+'">'+notReady.key+'-'+notReady.value+'</div>';
		});
		boxStr += '</div></div>';
		popBox = $(boxStr);
        $('body').append(popBox);
        $(".notReadyItem").click(function(){
        	$phone.notReady(this);
        });
        $(".notReadyItem").mouseover(function(){
        	this.style.background = "#dff3fc";
        });
        $(".notReadyItem").mouseout(function(){
        	this.style.background = "#fff";
        });
	}else{
		popBox.css({display: ""});
	}
    var pageX = document.body.scrollLeft + event.clientX - 5,
    pageY = document.body.scrollLeft + event.clientY - 5;
    popBox.css({top: pageY,left: pageX});
};
/**
 * 设置示忙发请求
 * */
JsPhone.prototype.notReady = function(obj){
	var key = obj.id;
	$phone.sendRequest("notReady",key);
	var popBox = $('#pop-box');
	popBox.css({display: "none"});
};
/**
 * 发送小休请求
 * */
JsPhone.prototype.doRest = function(){
	$phone.sendRequest("notReady","0");
};
/**
 * 示闲发请求
 * */
JsPhone.prototype.setIdle = function(){
	$phone.sendRequest("idle");
};
/**
 * 呼入时点接话
 * */
JsPhone.prototype.answerCall = function(){
	$phone.sendRequest("answer");
};
/**
 * 弹出拔号框，准备拔号
 * */
JsPhone.prototype.prepareDial = function(){
	//点拔号时，先弹出一个拔号框
	var dialBox = $('#dialBox');
	if(!dialBox.length){
		var boxStr = '<div id="dialBox" class="pop-box">';
		boxStr += '<div class="pop-box-title">拔号</div><div class="pop-box-body">';
		boxStr += '<table id="dialTbl" style="border-collapse: collapse;">';
		boxStr += '<tr>';
		boxStr += '<td id="historyDialNumTd" width="200">';
		boxStr += '</td>';
		boxStr += '<td width="110" class="numTd">';
		boxStr += '<input type="button" class="numBtn" value="1" onclick="$phone.clickNumBtn(this);">';
		boxStr += '<input type="button" class="numBtn" value="2" onclick="$phone.clickNumBtn(this);">';
		boxStr += '<input type="button" class="numBtn" value="3" onclick="$phone.clickNumBtn(this);"><br>';
		boxStr += '<input type="button" class="numBtn" value="4" onclick="$phone.clickNumBtn(this);">';
		boxStr += '<input type="button" class="numBtn" value="5" onclick="$phone.clickNumBtn(this);">';
		boxStr += '<input type="button" class="numBtn" value="6" onclick="$phone.clickNumBtn(this);"><br>';
		boxStr += '<input type="button" class="numBtn" value="7" onclick="$phone.clickNumBtn(this);">';
		boxStr += '<input type="button" class="numBtn" value="8" onclick="$phone.clickNumBtn(this);">';
		boxStr += '<input type="button" class="numBtn" value="9" onclick="$phone.clickNumBtn(this);"><br>';
		boxStr += '<input type="button" class="numBtn" value="0" onclick="$phone.clickNumBtn(this);">';
		boxStr += '<input type="button" class="bignumBtn" value="Backspace" id="backspace"><br>';
		boxStr += '<input type="text" id="dialPhoneNum" maxlength="12" style="width:100px;">';
		boxStr += '<input type="button" class="bignumBtn" id="doDial" value="拔号" onclick="$phone.doDialNum();">';
		boxStr += '<input type="button" class="bignumBtn" id="cancelDial" value="取消" onclick="$phone.cancelDialNum();">';
		boxStr += '</td>';
		boxStr += '</tr></table>';
		boxStr += '</div></div>';
		dialBox = $(boxStr);
        $('body').append(dialBox);
        $("#historyDialNumTd")[0].innerHTML = $phone.loadDialHistory();
	}else{
		dialBox.css({display: ""});
		//重新加载历史外拔记录
		$("#historyDialNumTd")[0].innerHTML = $phone.loadDialHistory();
	}
    var pageX = document.body.scrollLeft + event.clientX - 5,
    pageY = document.body.scrollLeft + event.clientY - 5;
    dialBox.css({top: pageY,left: pageX});
};
/**
 * 加载拔号盘上的历史记录
 * */
JsPhone.prototype.loadDialHistory = function(){
	var boxStr = "";
	boxStr += '<table class="historyDial">';
	$.each(this.historyDialNums,function(i, history){//加载历史外拔号
		if(history != null){
			boxStr += '<tr><td width="10">'+(i+1)+'</td><td width="120">'+history.dialNum+'</td><td width="60">'+history.dialTime+'</td></tr>';
		}else{
			boxStr += '<tr><td width="10">'+(i+1)+'</td><td width="120">&nbsp;</td><td width="60">&nbsp;</td></tr>';
		}
	});
	boxStr += '</table>';
	return boxStr;
};
/**
 * 点击拔号盘上的数字键
 * */
JsPhone.prototype.clickNumBtn = function(obj){
	var str = "";
	str = $("#dialPhoneNum")[0].value;
	str += obj.value;
	$("#dialPhoneNum")[0].value = str;
};
/**
 * 点击拔号盘上的拔号按钮
 * */
JsPhone.prototype.doDialNum = function(){
	var dialNum = $("#dialPhoneNum")[0].value;
	if(dialNum == ""){
		alert("请输入您要拔打的电话号码");
		return false;
	}
	$phone.sendRequest("dial",dialNum);
	//把已拔打号码，加入拔打历史Array中
	var history = new historyDialNum(dialNum,new Date().getHMS());
	for(var i=0;i<this.historyDialNums.length;i++){
		if(this.historyDialNums[i] == null){
			this.historyDialNums[i] = history;
			break;
		}
	}
	$phone.cancelDialNum();
};
/**
 * 点击拔号盘上的取消按钮
 * */
JsPhone.prototype.cancelDialNum = function(){
	var popBox = $('#dialBox');
	popBox.css({display: "none"});
};
/**
 * 挂断电话的方法
 * */
JsPhone.prototype.wrapup = function(){
	var callId = $phone.cassId;
	$phone.sendRequest("wrapup",callId);
};
/**
 * 点击”保持“按钮的方法
 * */
JsPhone.prototype.hold = function(){
	var callId = $phone.cassId;
	$phone.sendRequest("hold",callId);
};
/**
 * 点击“收回”按钮的方法，即取消保持
 * */
JsPhone.prototype.cancelHold = function(){
	var callId = $phone.cassId;
	$phone.sendRequest("cancelHold",callId);
};
/**
 * 准备咨询，弹出咨询对话框
 * */
JsPhone.prototype.prepareConsult = function(){
	//点拔号时，先弹出一个拔号框
	var consultBox = $('#consultBox');
	if(!consultBox.length){//如果还没有这个对话框，就新画一个
		var boxStr = '<div id="consultBox" class="pop-box">';
		boxStr += '<div class="pop-box-title">咨询</div><div class="pop-box-body">';
		boxStr += '<table id="consultTbl" style="border-collapse: collapse;">';
		boxStr += '<tr>';
		boxStr += '<td id="consultOrganTd" width="200">';
		boxStr += '</td>';
		boxStr += '<td width="110" class="numTd">';
		boxStr += '<input type="text" id="consultAgentId" maxlength="12" style="width:100px;">';
		boxStr += '<input type="button" class="bignumBtn" id="doDial" value="咨询" onclick="$phone.doConsult();">';
		boxStr += '<input type="button" class="bignumBtn" id="cancelDial" value="取消" onclick="$phone.cancelConsult();">';
		boxStr += '</td>';
		boxStr += '</tr></table>';
		boxStr += '</div></div>';
		dialBox = $(boxStr);
        $('body').append(dialBox);
        $("#consultOrganTd")[0].innerHTML = $phone.loadConsultOrgan();
	}else{
		dialBox.css({display: ""});
		//重新加载被咨询的组和人
		$("#consultOrganTd")[0].innerHTML = $phone.loadConsultOrgan();
	}
    var pageX = document.body.scrollLeft + event.clientX - 5,
    pageY = document.body.scrollLeft + event.clientY - 5;
    dialBox.css({top: pageY,left: pageX});
};
/**
 * 放弃咨询的方法，即在咨询面板上点“取消”按钮
 * */
JsPhone.prototype.cancelConsult = function(){
	var consultBox = $('#consultBox');
	consultBox.css({display: "none"});
};
/**
 * 加载可咨询组的方法
 * */
JsPhone.prototype.loadConsultOrgan = function(){
	var boxStr = "";
	boxStr += '<table class="historyDial">';
	boxStr += '<tr><td width="240" id="consultOrgan">';
	for(var i=0;i<5;i++){
		boxStr += '<div>可咨组'+(i+1)+'</div>';
	}
	boxStr += '</td>';
	boxStr += '<td width="480">';
	boxStr += '<table class="historyDial">';
	boxStr += '<tr><td width="160">工号</td><td width="160">分机</td><td width="160">状态</td></tr>';
	for(var i = 2001;i<2011;i++){
		boxStr += '<tr><td>'+i+'</td><td>'+(3000+i)+'</td><td>通话中</td></tr>';
	}
	boxStr += '</table>';
	boxStr += '</td></tr>';
	boxStr += '</table>';
	return boxStr;
};
/**
 * 结束后处理的方法
 * */
JsPhone.prototype.doAcw = function(){
	var callId = $phone.cassId;
	var agentIndex = $phone.agentIndex;
	$phone.sendRequest("acw",callId,agentIndex);
};
/**
 * 轮循事件的方法
 * */
JsPhone.prototype.pollingEvent = function(){
	this.pollingTimer = window.setInterval(function(){
		var url = $phone.platServiceUrl;
		var param = "/";
		param += "ActionPollingEvt?agentid="+$phone.agentId+"&feature=keepalive";
		$.ajax({
			type : 'get',
			url : url + param,
			data: null,
			dataType : 'json',
			//jsonp : "jsoncallback",
			success : function(data){
				if(data != null){
					if(data.eventtype == "EvtDidConnCTI"){ 			//1-连接CTI成功

					}else if(data.eventtype == "EvtDidLogon"){		//2-登录成功
						$phone.logonSuccess();
						var notReadyReason = data.notreadyreason;
						if(notReadyReason != null){
							$phone.notReadyReason = notReadyReason;
						}
					}else if(data.eventtype == "EvtDidIdle"){		//3-示闲成功
						$phone.showPhoneByEvent(idleStatus);
						$phone.durationTime = 0;
					}else if(data.eventtype == "EvtDidNotReady"){	//4-示忙成功
						$phone.showPhoneByEvent(notReadyStatus);
						$phone.durationTime = 0;
					}else if(data.eventtype == "EvtDidWrapup"){		//5-挂断后进入后处理事件
						$phone.showPhoneByEvent(acwStatus);
						$phone.durationTime = 0;
						//隐藏挂断按钮，恢复拔号按钮
						var dialBtn = $('#Dial');
						dialBtn.css({display: ""});
						var wrapupBtn = $('#Wrapup');
						wrapupBtn.css({display: "none"});
					}else if(data.eventtype == "EvtDidAlerting"){	//6-震铃事件
						$phone.showPhoneByEvent(alertingStatus);
						$phone.durationTime = 0;
						//把拔号按钮换成挂断按钮
						var dialBtn = $('#Dial');
						dialBtn.css({display: "none"});
						var wrapupBtn = $('#Wrapup');
						wrapupBtn.css({display: ""});
						//解析弹屏数据，里面有主被叫、callid
						var popdata = data.popdata;
						if(popdata != null){
							var ani = popdata.ani;
							var dnis = popdata.dnis;
							var callId = popdata.callid;
							//显示主被叫、设置callId
							$("#cusPhone")[0].value = ani;
							$phone.callId = callId;
						}

					}else if(data.eventtype == "EvtDidActive"){		//7-通话中的事件
						$phone.showPhoneByEvent(activeStatus);
						$phone.durationTime = 0;
						//隐藏收回按钮，并显示保持按钮
						var holdBtn = $('#Hold');
						holdBtn.css({display: ""});
						var cancelHoldBtn = $('#CancelHold');
						cancelHoldBtn.css({display: "none"});
					}else if(data.eventtype == "EvtDidDial"){		//8-外拔号时，拔号中事件
						$phone.showPhoneByEvent(dialingStatus);
						$phone.durationTime = 0;
						//取出并保存callid
						var callId = data.callid;
						$phone.callId = callId;
						//隐藏拔号按钮，并显示挂断按钮
						var dialBtn = $('#Dial');
						dialBtn.css({display: "none"});
						var wrapupBtn = $('#Wrapup');
						wrapupBtn.css({display: ""});
					}else if(data.eventtype == "EvtDidHold"){		//9-保持
						$phone.showPhoneByEvent(holdingStatus);
						$phone.durationTime = 0;
						//隐藏保持按钮，并显示收回按钮
						var holdBtn = $('#Hold');
						holdBtn.css({display: "none"});
						var cancelHoldBtn = $('#CancelHold');
						cancelHoldBtn.css({display: ""});
					}else if(data.eventtype == "EvtConsultDial"){	//10-咨询拔号

					}else if(data.eventtype == "EvtDidConsult"){	//11-咨询

					}else if(data.eventtype == "EvtDidConference"){	//12-会议

					}else if(data.eventtype == "EvtDidLogoff"){		//13-签出
						$phone.logoffSuccess();
					}else if(data.eventtype == "EvtDidDisConnCTI"){	//14-断开连接

					}else if(data.eventtype == "EvtDidError"){		//15-执行方法异常

					}else if(data.eventtype == "EvtDidMonitor"){	//16-监控

					}
				}
			},
			error : function(){
				alert('fail');
			}
		});
	},1000);
};
/**
 * 给数据扩展的一个方法，数组中是否包含某个值
 * */
Array.prototype.contains = function(needle){
	for(i in this){
		if(this[i] == needle) return true;
	}
	return false;
};
/**
 * 获得时间的时分秒字符串
 * */
Date.prototype.getHMS = function(){
	var hour = this.getHours();
	var minute = this.getMinutes();
	var second = this.getSeconds();
	if(hour < 10)hour = "0"+hour;
	if(minute < 10)minute = "0"+minute;
	if(second < 10)second = "0"+second;
	return hour + ":" + minute + ":" + second;
};