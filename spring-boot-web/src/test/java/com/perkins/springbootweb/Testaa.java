package com.perkins.springbootweb;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.perkins.springbootweb.test.AA;
import com.perkins.springbootweb.vo.UserVO;
import org.bson.ByteBuf;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author ：Perkins Zhu
 * @date ：Created in 2020/4/23 12:24
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class Testaa {
    @Test
    public void testTime() {
        System.out.println(System.currentTimeMillis());
        Gson gson = new Gson();
        String str = "{\"type\":\"rec\",\"address\":\"83f9d1f3-6614-40ae-abbf-e7259aa96e3f\",\"body\":{\"code\":1,\"data\":{\"items\":[{\"_id\":\"5ea11164d2f3ac30951e962a\",\"uuid\":\"45a7cc0a-2c18-4707-bcbe-d7884f78459c\",\"address\":\"vts.store.chat.M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"address_id\":\"M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"caller\":null,\"caller_aid\":\"U4dd5c6c0c413388f\",\"caller_channel\":\"APP\",\"called\":\"M11cb6a538c2abc27\",\"called_aid\":null,\"called_channel\":\"STORE\",\"status\":0,\"body\":{\"type\":\"text\",\"context\":\"\u4f60\u597d\"},\"context\":null,\"headers\":\"{\\\"idRandom\\\":82252359289821}\",\"image\":null,\"time\":1587614052797,\"created_at\":1587614052801,\"updated_at\":null},{\"_id\":\"5ea0fe78b43ea41f684cabb8\",\"uuid\":\"da83a2ae-8936-493b-b4b6-4088a30981eb\",\"address\":\"vts.store.chat.M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"address_id\":\"M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"caller\":null,\"caller_aid\":\"U4dd5c6c0c413388f\",\"caller_channel\":\"APP\",\"called\":\"M11cb6a538c2abc27\",\"called_aid\":null,\"called_channel\":\"STORE\",\"status\":0,\"body\":{\"type\":\"text\",\"context\":\"\u5feb\u9012\u8bf4\u5e97\u91cc\u91cd\u65b0\u53d1\u4e00\u5355  \u662f\u4f60\u4eec\u53d1\u7684\u4e48\"},\"context\":null,\"headers\":\"{\\\"idRandom\\\":83622242357712}\",\"image\":null,\"time\":1587609208416,\"created_at\":1587609208423,\"updated_at\":null},{\"_id\":\"5ea0ed30d2f3ac30951e93b7\",\"uuid\":\"dd7ecf99-1aab-405c-95f2-4ffa6302a4ea\",\"address\":\"vts.store.chat.M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"address_id\":\"M11cb6a538c2abc27.U4dd5c6c0c413388f\",\"caller\":null,\"caller_aid\":\"U4dd5c6c0c413388f\",\"caller_channel\":\"APP\",\"called\":\"M11cb6a538c2abc27\",\"called_aid\":null,\"called_channel\":\"STORE\",\"status\":0,\"body\":{\"type\":\"text\",\"context\":\"\uff1f\"},\"context\":null,\"headers\":\"{\\\"idRandom\\\":98861560698103}\",\"image\":null,\"time\":1587604784082,\"created_at\":1587604784084,\"updated_at\":null}],\"hasMore\":false}}}";
        JsonObject obj = gson.fromJson(str, JsonObject.class);
        System.out.println(obj);
    }

    @Test
    public void testChar() {
        char a = '国';
        char b = 'b';
        char c = '3';
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }


    /**
     * 泛型测试
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testT() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
        //通过反射机制加入了String类型
        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testAA() {
        AA aa = new AA();
        System.out.println(aa);
        aa.run();
        AA.run();
    }

    @Test
    public void testTry() {
        try {
            int a = 29 / 0;
        } catch (Exception e) {

        }
    }

    @Test
    public void testNIO() throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:\\zhupingjing\\testFile\\datatest.txt", "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int byteReads = -1;
        do {
            byteBuffer.flip();//切换为读模式
            byteReads = fileChannel.read(byteBuffer);
            System.out.print(new String(byteBuffer.array(), "UTF-8"));
            byteBuffer.clear();
            byteReads = fileChannel.read(byteBuffer);
        } while (byteReads != -1);
        file.close();
    }


    @Test
    public void testNIO2() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("D:\\zhupingjing\\testFile\\datatest.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(2048);
        // read 取出数据则返回 数据长度 已关闭或读取结束返回 -1 ，
        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {
            System.out.println("=====");
            buf.flip();  //make buffer ready for read
            System.out.print(new String(buf.array(), "UTF-8")); // read 1 byte at a time
            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }


    /**
     * 手动校验参数，对于需要进行解密的请求，可以进行手动校验
     */
    @Test
    public void check_person_manually() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        UserVO person = new UserVO();
        person.setName("Man22");
        person.setAge(19);
        person.setEmail("SnailClimb");
        Set<ConstraintViolation<UserVO>> violations = validator.validate(person);
        //output:
        //email 格式不正确
        //name 不能为空
        //sex 值不在可选范围
        for (ConstraintViolation<UserVO> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }
    }
}
