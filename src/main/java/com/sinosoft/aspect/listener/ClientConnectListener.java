package com.sinosoft.aspect.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.sinosoft.aspect.softphone.facade.SoftPhoneContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientConnectListener{

    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {
        //获取登录用户id，并存入  SoftPhoneContent
        String userId = socketIOClient.getHandshakeData().getSingleUrlParam("userId");
        log.info("用户" + userId + "已连接");
        SoftPhoneContent.addClient(userId, socketIOClient.getSessionId());
    }
}
