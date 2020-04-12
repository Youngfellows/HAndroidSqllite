package com.pandora.handroidsqllite.base;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseModel implements Serializable {

    /**
     * 打印对象属性值
     **/
    public String objectToString(Object obj) {
        List<Map<String, Object>> FiledInfos = getFiledsInfo(obj);
        StringBuffer buffer = new StringBuffer();
        if (!FiledInfos.isEmpty()) {
            for (Map<String, Object> map : FiledInfos) {
                String tag = map.get("name").toString();
                if (!"$change".equals(tag) && !"serialVersionUID".equals(tag)) {
                    String str = map.get("name") + "=" + map.get("value") + "  ";
                    buffer.append(str);
                }
            }
        } else {
            if (obj != null) {
                String className = obj.getClass().getSimpleName();
                buffer.append(className);
            } else {
                buffer.append("objectToString方法调用参数为null");
            }
        }
        return buffer.toString();
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     */
    private List getFiledsInfo(Object obj) {
        List<Map<String, Object>> list = new ArrayList();
        if (obj != null) {
            Field fields[] = obj.getClass().getDeclaredFields();
            String fieldNames[] = new String[fields.length];
            Map mapInfo = null;
            for (int i = 0; i < fields.length; i++) {
                Object o = getFieldValueByName(fields[i].getName(), obj);
                mapInfo = new HashMap();
                mapInfo.put("type", fields[i].getType().toString());
                mapInfo.put("name", fields[i].getName());
                mapInfo.put("value", getFieldValueByName(fields[i].getName(), obj));
                list.add(mapInfo);
            }
        }
        return list;
    }

    /**
     * 根据属性名获取属性值
     */
    private Object getFieldValueByName(String fieldName, Object obj) {
        Object value = null;
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = obj.getClass().getMethod(getter, new Class[]{});
            value = method.invoke(obj, new Object[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取属性名数组
     */
    private String[] getFiledName(Object obj) {
        Field fields[] = obj.getClass().getDeclaredFields();
        String fieldNames[] = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /***
     * 获取对象的所有属性值，返回一个对象数组
     */
    private Object[] getFiledValues(Object obj) {
        String fieldNames[] = this.getFiledName(obj);
        Object value[] = new Object[fieldNames.length];
        for (int i = 0; i < fieldNames.length; i++) {
            value[i] = this.getFieldValueByName(fieldNames[i], obj);
        }
        return value;
    }
}
