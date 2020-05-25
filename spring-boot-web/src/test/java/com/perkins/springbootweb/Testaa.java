package com.perkins.springbootweb;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;

/**
 * @author ：Perkins Zhu
 * @date ：Created in 2020/4/23 12:24
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class Testaa {
    @Test
    public void testTime(){
        System.out.println(System.currentTimeMillis());
        Gson gson = new Gson();
        String str = "{\"type\":\"rec\",\"address\":\"83f9d1f3-6614-40ae-abbf-e7259aa96e3f\",\"body\":{\"code\":1,\"data\":{\"items\":[{\"_id\":\"5ea11164d2f3ac30951e962a\",\"uuid\":\"45a7cc0a-2c18-4707-bcbe-d7884f78459c\",\"address\":\"vts.store.chat.M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"address_id\":\"M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"caller\":null,\"caller_aid\":\"U4dd5c6c0c413388f\",\"caller_channel\":\"APP\",\"called\":\"M11cb6a538c2abc27\",\"called_aid\":null,\"called_channel\":\"STORE\",\"status\":0,\"body\":{\"type\":\"text\",\"context\":\"\u4f60\u597d\"},\"context\":null,\"headers\":\"{\\\"idRandom\\\":82252359289821}\",\"image\":null,\"time\":1587614052797,\"created_at\":1587614052801,\"updated_at\":null},{\"_id\":\"5ea0fe78b43ea41f684cabb8\",\"uuid\":\"da83a2ae-8936-493b-b4b6-4088a30981eb\",\"address\":\"vts.store.chat.M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"address_id\":\"M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"caller\":null,\"caller_aid\":\"U4dd5c6c0c413388f\",\"caller_channel\":\"APP\",\"called\":\"M11cb6a538c2abc27\",\"called_aid\":null,\"called_channel\":\"STORE\",\"status\":0,\"body\":{\"type\":\"text\",\"context\":\"\u5feb\u9012\u8bf4\u5e97\u91cc\u91cd\u65b0\u53d1\u4e00\u5355  \u662f\u4f60\u4eec\u53d1\u7684\u4e48\"},\"context\":null,\"headers\":\"{\\\"idRandom\\\":83622242357712}\",\"image\":null,\"time\":1587609208416,\"created_at\":1587609208423,\"updated_at\":null},{\"_id\":\"5ea0ed30d2f3ac30951e93b7\",\"uuid\":\"dd7ecf99-1aab-405c-95f2-4ffa6302a4ea\",\"address\":\"vts.store.chat.M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"address_id\":\"M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"caller\":null,\"caller_aid\":\"U4dd5c6c0c413388f\",\"caller_channel\":\"APP\",\"called\":\"M11cb6a538c2abc27\",\"called_aid\":null,\"called_channel\":\"STORE\",\"status\":0,\"body\":{\"type\":\"text\",\"context\":\"\uff1f\"},\"context\":null,\"headers\":\"{\\\"idRandom\\\":98861560698103}\",\"image\":null,\"time\":1587604784082,\"created_at\":1587604784084,\"updated_at\":null}],\"hasMore\":false}}}";
        JsonObject obj = gson.fromJson(str,JsonObject.class);
        System.out.println(obj);
    }
}
