package org.example.annotation.demo.annotations;

import com.sun.tools.internal.jxc.ap.Const;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class utils {

    /**
     * 序列化
     */

    public static String toString(Object obj) {
        Class<?> cls = obj.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName() + "#");
        for (Field field :
                cls.getDeclaredFields()) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                if (field.getType() == Date.class){
                    String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(field.get(obj));
                    sb.append(field.getName() + "=" + dateStr + "/");
                }else {
                    sb.append(field.getName() + "=" + field.get(obj) + "/");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 反序列化
     */

    public static Object fromString(String str) {
         String[] os = str.split("#");
         if (os.length < 1) {
             throw new IllegalArgumentException(str);
         }
        try {
            Class<?> cls = Class.forName(os[0]);
            Object obj = cls.newInstance();
            String[] ot = os[1].split("/");
            if(ot.length > 1){
                for (String s:
                     ot) {
                    String[] fv = s.split("=");
                    if(fv.length !=2){
                        throw new IllegalArgumentException(s);
                    }
                    Field f  = cls.getDeclaredField(fv[0]);
                    if (!f.isAccessible()){
                        f.setAccessible(true);
                    }
                    setFieldValue(f, obj, fv[1]);
                }
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void setFieldValue(Field f, Object obj, String value) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, ParseException {
        Class<?> type = f.getType();
        if (type == int.class) {
            f.setInt(obj, Integer.parseInt(value));
        } else if (type == byte.class) {
            f.setByte(obj, Byte.parseByte(value));
        } else if (type == short.class) {
            f.setShort(obj, Short.parseShort(value));
        } else if (type == long.class) {
            f.setLong(obj, Long.parseLong(value));
        } else if (type == float.class) {
            f.setFloat(obj, Float.parseFloat(value));
        } else if (type == double.class) {
            f.setDouble(obj, Double.parseDouble(value));
        } else if (type == char.class) {
            f.setChar(obj, value.charAt(0));
        } else if (type == boolean.class) {
            f.setBoolean(obj, Boolean.parseBoolean(value));
        }else if (type == Date.class) {
            f.set(obj, new SimpleDateFormat("yyyy-MM-dd").parse(value));
        }else {
            Constructor<?> constructor = type.getConstructor(new Class[]{String.class});
            f.set(obj, constructor.newInstance(value));
        }
    }

    /**
     * 字段格式输出
     */
    public static String format(Object obj) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Class<?> cls = obj.getClass();

        for (Field f : cls.getDeclaredFields()
        ) {
            if (!f.isAccessible()) {
                f.setAccessible(true);
            }
            Label label = f.getAnnotation(Label.class);
            String name = label != null ? label.value() : f.getName();
            Object value = f.get(obj);
            if (value != null && f.getType() == Date.class) {
                value = formatDate(f, value);
            }
            sb.append(name + ":" + value + "\n");
        }
        return sb.toString();
    }

    private static Object formatDate(Field field, Object value) {
        Format format = field.getAnnotation(Format.class);
        if (format != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format.pattern());
            sdf.setTimeZone(TimeZone.getTimeZone(format.timezone()));
            return sdf.format(value);
        }
        return value;
    }

    /**
     * 对象实例
     */
    public static <T> T getInstance(Class<T> cls) {
        try {
            T obj = cls.newInstance();
            Field[] fields = cls.getDeclaredFields();
            for (Field f :
                    fields) {
                if (f.isAnnotationPresent(SimpleInject.class)) {
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    Class<?> fieldCls = f.getType();
                    f.set(obj, getInstance(fieldCls));
                }
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象单例
     */

    private static Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    public static <T> T getSingletonInstance(Class<T> cls) {
        try {
            if (!cls.isAnnotationPresent(SimpleSingleton.class)) {
                return CreateInstance(cls);
            }
            Object obj = instances.get(cls);

            if (obj != null) {
                return (T) obj;
            }
            synchronized (cls) {
                obj = instances.get(cls);
                if (obj == null) {
                    obj = CreateInstance(cls);
                    instances.put(cls, obj);
                }
            }

            return (T) obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T CreateInstance(Class<T> cls) throws IllegalAccessException, InstantiationException {
        T obj = cls.newInstance();
        Field[] fields = cls.getDeclaredFields();
        for (Field f :
                fields) {
            if (f.isAnnotationPresent(SimpleInject.class)) {
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                }
                Class<?> fieldCls = f.getType();
                f.set(obj, getSingletonInstance(fieldCls));
            }
        }
        return obj;
    }

}
