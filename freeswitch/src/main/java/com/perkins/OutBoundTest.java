/*
 * Copyright 2010 david varnes.
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.perkins;

import org.freeswitch.esl.client.outbound.AbstractOutboundClientHandler;
import org.freeswitch.esl.client.outbound.AbstractOutboundPipelineFactory;
import org.freeswitch.esl.client.outbound.SocketClient;
import org.freeswitch.esl.client.outbound.example.SimpleHangupPipelineFactory;
import org.freeswitch.esl.client.transport.SendMsg;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.freeswitch.esl.client.transport.message.EslHeaders;
import org.freeswitch.esl.client.transport.message.EslMessage;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This 'test' is not really a unit test, more an integration test. In order to see
 * any result, configure a FreeSWITCH installation with an extension something like
 * the following:
 * <pre>
 * &lt;extension&gt;
 * &lt;condition field="destination_number" expresssion="444"&gt;
 * &lt;action application="socket" data="192.168.100.88:8084 async full"/&gt;
 * &lt;/condition&gt;
 * &lt;/extension&gt;
 * <pre>
 * Replace the ip address with the host that FreeSWITCH sees that you are running the test on, perhaps
 * localhost.
 * <p/>
 * Run the test, you have 45 seconds to make a call to extension 444 and observe the logs.
 *
 * @author david varnes
 */
public class OutBoundTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /*
     *  Example usage of an 'outbound' socket client.  Of course an application developer would need to
     *  create their own implementation of a handler and pipeline factory, and invoke the SocketClient.
     *
     *  外联 服务端，起 8084端口，监听转发过来的请求
     *  用来请求怎么处理后续动作的
     *
     */
    @Test
    public void run_client() throws InterruptedException {
        log.info("Test starting ...");

        SocketClient client = new SocketClient(8084, getFactory());

        client.start();

        Thread.sleep(145000);

        client.stop();

        log.info("Test ended");
    }

    public AbstractOutboundPipelineFactory getFactory() {
//        return new SimpleHangupPipelineFactory();
        return new MyFactory();
    }


}


class MyFactory extends AbstractOutboundPipelineFactory {

    @Override
    protected AbstractOutboundClientHandler makeHandler() {
        return new AbstractOutboundClientHandler() {
            @Override
            protected void handleConnectResponse(ChannelHandlerContext ctx, EslEvent event) {

                log.info("Received connect response [{}]", event);
                if (event.getEventName().equalsIgnoreCase("CHANNEL_DATA")) {
                    // this is the response to the initial connect
                    log.info("=======================  incoming channel data  =============================");
                    log.info("Event-Date-Local: [{}]", event.getEventDateLocal());
                    log.info("Unique-ID: [{}]", event.getEventHeaders().get("Unique-ID"));
                    log.info("Channel-ANI: [{}]", event.getEventHeaders().get("Channel-ANI"));
                    log.info("Answer-State: [{}]", event.getEventHeaders().get("Answer-State"));
                    log.info("Caller-Destination-Number: [{}]", event.getEventHeaders().get("Caller-Destination-Number"));
                    log.info("=======================  = = = = = = = = = = =  =============================");

                    // now hangup the call
                    hangupCall(ctx.getChannel());
                } else {
                    throw new IllegalStateException("Unexpected event after connect: [" + event.getEventName() + ']');
                }
            }

            @Override
            protected void handleEslEvent(ChannelHandlerContext ctx, EslEvent event) {
                log.info("Received event [{}]", event);
            }

            private void hangupCall(Channel channel) {
                SendMsg hangupMsg = new SendMsg();
                hangupMsg.addCallCommand("execute");
                hangupMsg.addCallCommand("playback");
                hangupMsg.addExecuteAppArg("local_stram://moh");

                /*hangupMsg.addCallCommand("execute");
                hangupMsg.addExecuteAppName("hangup");*/

                EslMessage response = sendSyncMultiLineCommand(channel, hangupMsg.getMsgLines());

                if (response.getHeaderValue(EslHeaders.Name.REPLY_TEXT).startsWith("+OK")) {
                    log.info("Call hangup successful");
                } else {
                    log.error("Call hangup failed: [{}}", response.getHeaderValue(EslHeaders.Name.REPLY_TEXT));
                }
            }
        };
    }


}