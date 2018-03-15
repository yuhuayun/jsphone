package com.sinosoft.aspect.softphone.facade;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.sinosoft.aspect.util.SpringUtil;
import lombok.Data;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class SoftPhoneContent {

    private static Map<String, UserSession> userStore = new ConcurrentHashMap<>();

    public static UserSession getSession(String agentId){
        return userStore.get(agentId);
    }

    public static void addSession(String agentId,UserSession userSession){
        if (userStore.containsKey(agentId)) {
            UserSession session = userStore.get(agentId);

            userSession.setUuid(session.getUuid());
            userStore.put(agentId,userSession);
        }
    }

    public static void remove(String agentId){
        userStore.remove(agentId);
    }

    public static void addClient(String agentId, UUID uuid) {
        UserSession userSession;
        if (userStore.containsKey(agentId)) {
            userSession = userStore.get(agentId);
        }else {
            userSession = new UserSession();
        }

        userSession.setUuid(uuid);
        userSession.setAgentId(agentId);
        userStore.put(agentId,userSession);
    }

    public static void delClient(String agentId) {
        userStore.remove(agentId);
    }

    public static SocketIOClient getClient(String agentId) {
        if (userStore.containsKey(agentId)) {
            UserSession userSession = userStore.get(agentId);
            SocketIOServer server = SpringUtil.getBean(SocketIOServer.class);
            return server.getClient(userSession.getUuid());
        }
        return null;
    }

}
