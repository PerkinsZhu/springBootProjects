package com.perkin.mytest;

/**
 * @author: perkins Zhu
 * @date: 2021/6/10 17:07
 * @description:
 **/


//import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import com.perkin.ws.UserServiceImpl;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018-04-16 8:48
 * <p>Title:  haiyang.yu.webservice</p>
 * <p>Description: </p>
 *
 * @author <a href="mailto:991138518@qq.com">yuhaiyang</a>
 * @version 1.0
 */
public class TimeAlarmBellConnection {

    public static void main(String[] args) throws Exception {
//        test01();
//        test02();
        test3();

    }

    //通过命令生成客户端代码方式
    //wsimport -keep http://localhost:8080/demo/hello?wsdl
    private static void test01() {
        //创建WSDL的URL，注意不是服务地址
        URL url = null;
        try {
            url = new URL("http://localhost:8080/demo/hello?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //创建服务名称
        //1.namespaceURI - 命名空间地址
        //2.localPart - 服务视图名
//        QName qname = new QName("http://webservice.yu.haiyang/", "showDate");
//        QName qname = new QName("http://ws.perkin.com/", "UserServiceImplService");
        QName qname = new QName("http://ws.perkin.com/", "UserServiceImplService");

        //创建服务视图
        //参数解释：
        //1.wsdlDocumentLocation - wsdl地址
        //2.serviceName - 服务名称
        Service service = Service.create(url, qname);
        //获取服务实现类
        UserServiceImpl showDate = service.getPort(UserServiceImpl.class);
        //调用查询方法
        showDate.sayHi("KevinBruce");
//            }
//        }, 1, 2, TimeUnit.SECONDS);
    }

    //该方法不用生成代码
    public static void test02() {
        //创建动态客户端
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:8080/demo/hello?wsdl");

        // 需要密码的情况需要加上用户名和密码
        //client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(2000); //连接超时
        httpClientPolicy.setAllowChunking(false); //取消块编码
        httpClientPolicy.setReceiveTimeout(120000); //响应超时
        conduit.setClient(httpClientPolicy);
        //client.getOutInterceptors().addAll(interceptors);//设置拦截器
        try {
            //有返回值可以用这个接受返回结果
            Object[] objects = new Object[0];
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("sayHi", "张三");
            System.out.println("返回数据:" + objects[0]);
            //client.invoke("sayHi", "张三");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //同 test02
    public static void test3() throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/demo/hello?wsdl");
        Object[] objects = client.invoke("sayHi", "jack");//list3方法名 后面是可变参数
        //输出调用结果
        System.out.println(objects[0].getClass());
        System.out.println(objects[0].toString());
    }

}
