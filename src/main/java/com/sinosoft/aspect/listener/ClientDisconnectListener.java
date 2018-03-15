package com.sinosoft.aspect.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.sinosoft.aspect.softphone.facade.SoftPhoneContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientDisconnectListener {

    @OnDisconnect
    public void onDisconnect(SocketIOClient socketIOClient) {
        //获取连接用户Id,并从SessionStore中删除
        String userId = socketIOClient.getHandshakeData().getSingleUrlParam("userId");
        SoftPhoneContent.delClient(userId);
        log.info("用户" + userId + "已断开连接！");
    }
}
