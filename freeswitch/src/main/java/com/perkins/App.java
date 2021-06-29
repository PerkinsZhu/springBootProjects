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

        //通过外联方式主动向指定的手机号呼叫，并以回显方式接通
//        EslMessage message = client.sendSyncApiCommand("originate", "user/1010 &echo");
        // 以 park 挂起方式接通，接通后另一方无任何反应
//        EslMessage message = client.sendSyncApiCommand("originate", "user/1010 &park");
//      挂起，会播放默认音乐
        EslMessage message = client.sendSyncApiCommand("originate", "user/1010 &hold");
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
