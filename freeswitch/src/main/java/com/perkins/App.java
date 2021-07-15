package com.perkins;

import com.sun.deploy.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.freeswitch.esl.client.IEslEventListener;
import org.freeswitch.esl.client.inbound.Client;
import org.freeswitch.esl.client.inbound.InboundConnectionFailure;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.freeswitch.esl.client.transport.message.EslMessage;
import org.jboss.netty.util.internal.StringUtil;

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
        client.setEventSubscriptions("plain", "all");

//        simpleTest(client);
        callTest(client);

    }

    private static void callTest(Client client) {
        EslMessage message = client.sendSyncApiCommand("originate", "user/1010 &park");
        showMessage(message);
        EslMessage message2 = client.sendSyncApiCommand("originate", "user/1009 &park");
        showMessage(message2);

        EslMessage show = client.sendSyncApiCommand("show","channels");
        showMessage(show);

//        uuid_bridge b1aa8210-c0d3-4fa3-a4c6-ec81c538897f 0ef19345-077e-4fe9-bd8e-e47e1f9d8987

    }

    private static void showMessage(EslMessage message) {
        StringBuilder header = new StringBuilder();
        message.getHeaders().forEach((k, v) -> {
            header.append(k + ": " + v+"\r\n");
        });
        log.info("================Headers=========================\r\n{}", header);

        String body = StringUtils.join(message.getBodyLines(), "\r\n");
        log.info("================Body=========================\r\n{}", body);
    }

    private static void simpleTest(Client client) {
        //通过外联方式主动向指定的手机号呼叫，并以回显方式接通
        EslMessage message = client.sendSyncApiCommand("originate", "user/1010 &echo");
        // 以 park 挂起方式接通，接通后另一方无任何反应
//        EslMessage message = client.sendSyncApiCommand("originate", "user/1010 &park");
//      挂起，会播放默认音乐
//        EslMessage message = client.sendSyncApiCommand("originate", "user/1010 &hold");
        // record  录音
//        EslMessage message = client.sendSyncApiCommand("originate", "user/1010 &record(D:/tmp/fs/20210629_voice.wav)");
//        转接给 1009
//        EslMessage message = client.sendSyncApiCommand("originate", "user/1010 &bridge(user/1009)");
        log.info(StringUtils.join(message.getBodyLines(), "\r\n"));


       /* EslMessage response = client.sendSyncApiCommand("sofia status", "");
        log.info("sofia status = [{}]", response.getBodyLines().get(3));

*/
    }


}
