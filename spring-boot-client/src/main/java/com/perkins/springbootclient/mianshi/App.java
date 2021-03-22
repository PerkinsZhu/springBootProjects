package com.perkins.springbootclient.mianshi;

import org.apache.dubbo.common.utils.StringUtils;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws ObjectAccessException {
        new App().test9();
    }





    public void test9() throws ObjectAccessException {
        Layer1 layer1 = new Layer1();
        layer1.layer2 = new Layer2();
        layer1.layer2.layer3 = new ArrayList<>();
        Layer3 l3 = new Layer3();
        l3.val = 1;
        layer1.layer2.layer3.add(l3);
        Layer3 layer3 = (Layer3) getPath(layer1, "layer2.layer3[0]");
        System.out.println("layer3=" + layer3);
        Integer layer3_0_val = (Integer) getPath(layer1, "layer2.layer3[0].val");
        System.out.println("layer3_0_val=" + layer3_0_val);
    }

    public Object getPath(Object root, String path) throws ObjectAccessException { /* 实现这个方法 */
        if (root == null || StringUtils.isBlank(path)) {
            return null;
        }
        Class clazz = root.getClass();
        String headPath = path;
        String tailPath = "";
        if (headPath.contains(".")) {
            int subIndex = path.indexOf(".");
            headPath = path.substring(0, subIndex);
            tailPath = path.substring(subIndex + 1);
        }
        Object currentObject = null;
        try {
            boolean isArray = checkIsArray(headPath);
            String newHead = headPath;
            if (isArray) {
                currentObject = getArrayValue(clazz, root, headPath);
            } else {
                Field field = clazz.getDeclaredField(newHead);
                field.setAccessible(true);
                currentObject = field.get(root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object nextValue = getPath(currentObject, tailPath);
        if (nextValue == null) {
            return currentObject;
        } else {
            return nextValue;
        }

    }

    private Object getArrayValue(Class clazz, Object root, String headPath) throws Exception {
        int temp = headPath.indexOf("[");
        String newHead = headPath.substring(0, temp);
        Field field = clazz.getDeclaredField(newHead);
        field.setAccessible(true);
        Object currentObject = field.get(root);
        String arrayStr = headPath.substring(temp + 1);
        return parseArray(currentObject, arrayStr);
    }

    private Object parseArray(Object currentObject, String arrayStr) {
        if (currentObject == null || StringUtils.isBlank(arrayStr)) {
            return null;
        }
        int temp = arrayStr.indexOf("]");
        String b = arrayStr.substring(0, temp);
        int arrayIndex = Integer.valueOf(b);
        Object newObject = null;
        if (currentObject instanceof List) {
            newObject = ((List) currentObject).get(arrayIndex);
        } else {
            newObject = ((Object[]) currentObject)[arrayIndex];
        }

        String nextPath = arrayStr.substring(temp + 1);
        //TODO 这里对多维数组进行解析，需要测试
        Object nextValue = parseArray(newObject, nextPath);
        if (nextValue == null) {
            return newObject;
        } else {
            return nextValue;
        }
    }

    private boolean checkIsArray(String headPath) {
        //TODO 这里需要修改判断逻辑
        return headPath.contains("[") && headPath.contains("]");
    }
}

class Layer1 {
    public Layer2 layer2;
}

class Layer2 {
    public List<Layer3> layer3;
}

class Layer3 {
    public Integer val;

    @Override
    public String toString() {
        return "Layer3{" +
                "val=" + val +
                '}';
    }
}

class ObjectAccessException extends Exception {
}

