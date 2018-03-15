package com.sinosoft.aspect.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.sinosoft.aspect.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Slf4j
@Configuration
public class SocketIOConfiguration {

    @Value("${socket.io.host}")
    public String SOCKET_IO_HOST;

    @Value("${socket.io.port}")
    public Integer SOCKET_IO_PORT;

    @Bean
    public com.corundumstudio.socketio.Configuration socketConfig(){
        com.corundumstudio.socketio.Configuration socketConfig = new com.corundumstudio.socketio.Configuration();
        socketConfig.setHostname(SOCKET_IO_HOST);
        socketConfig.setPort(SOCKET_IO_PORT);
        return socketConfig;
    }

    @Bean
    public SocketIOServer socketIOServer(){
        SocketIOServer server = new SocketIOServer(socketConfig());
        log.info("Starting SocketIO Server(Port: {} )...",SOCKET_IO_PORT);
        server.start();
        return server;
    }

    //For enable socket.io annotation ( @onConnect, @onEvent,...)
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer server) {
        return new SpringAnnotationScanner(server);
    }

    @PreDestroy
    public void stopSocketIOServer(){
        log.info("Stopping SocketIO Server...");
        socketIOServer().stop();
    }

    @Bean
    public SpringUtil getSpringUtil() {
        return new SpringUtil();
    }
}
