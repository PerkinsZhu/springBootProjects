package com.perkins;

import lombok.extern.slf4j.Slf4j;
import org.freeswitch.esl.client.IEslEventListener;
import org.freeswitch.esl.client.inbound.Client;
import org.freeswitch.esl.client.inbound.InboundConnectionFailure;
import org.freeswitch.esl.client.transport.event.EslEvent;

/**
 * @author: perkins Zhu
 * @date: 2021/6/28 18:59
 * @description:
 **/
@Slf4j
public class App {

    private static String host = "127.0.0.1";
    private static int port = 8021;
    private static String password = "ClueCon";

    public static void main(String[] args) {

        Client client = new Client();

        client.addEventListener(new IEslEventListener() {
            @Override
            public void eventReceived(EslEvent event) {
                log.info("Event received [{}]", event);
            }
            @Override
            public void backgroundJobResultReceived(EslEvent event) {
                log.info("Background job result received [{}]", event);
            }

        });

        log.info("Client connecting ..");
        try {
            client.connect(host, port, password, 2);
        } catch (InboundConnectionFailure e) {
            log.error("Connect failed", e);
            return;
        }
        log.info("Client connected ..");
        client.setEventSubscriptions( "plain", "all" );

    }


}
