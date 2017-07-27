package com.ywc.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用反射进行操作的一个工具类
 *
 * @author licho
 */
public class ReflectUtil {
    /**
     * 利用反射获取指定对象的指定属性
     *
     * @param obj       目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 利用反射获取指定对象里面的指定属性
     *
     * @param obj       目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
                .getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                // 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }
        return field;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     *
     * @param obj        目标对象
     * @param fieldName  目标属性
     * @param fieldValue 目标值
     */
    public static void setFieldValue(Object obj, String fieldName,
                                     String fieldValue) {
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 两者属性名一致时，拷贝source里的属性到dest里
     *
     * @param dest   目标对象
     * @param source 源对象
     * @throws IllegalAccessException    非法进入异常
     * @throws IllegalArgumentException  非法参数异常
     * @throws InvocationTargetException 非法调用异常
     */
    public static void copyPorperties(Object dest, Object source) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Class srcCla = source.getClass();
        Field[] fsF = srcCla.getDeclaredFields();

        for (Field s : fsF) {
            String name = s.getName();
            Object srcObj = invokeGetterMethod(source, name);
            try {
                BeanUtils.setProperty(dest, name, srcObj);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 调用Getter方法.
     *
     * @param target       目标对象
     * @param propertyName 属性名称
     * @return 属性get方法调用结果
     * @throws IllegalAccessException    非法进入异常
     * @throws IllegalArgumentException  非法参数异常
     * @throws InvocationTargetException 非法调用异常
     */
    public static Object invokeGetterMethod(Object target, String propertyName) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(target, getterMethodName, new Class[]{},
                new Object[]{});
    }

    /**
     * 直接调用对象方法, 无视private/protected修饰符.
     *
     * @param object         目标对象
     * @param methodName     方法名称
     * @param parameterTypes 参数类型
     * @param parameters     参数
     * @return 方法调用结果
     * @throws IllegalAccessException    非法进入异常
     * @throws IllegalArgumentException  非法参数异常
     * @throws InvocationTargetException 非法调用异常
     */
    public static Object invokeMethod(final Object object,
                                      final String methodName, final Class<?>[] parameterTypes,
                                      final Object[] parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method ["
                    + methodName + "] parameterType " + parameterTypes
                    + " on target [" + object + "]");
        }

        method.setAccessible(true);
        return method.invoke(object, parameters);
    }

    /**
     * 循环向上转型, 获取对象的DeclaredMethod.
     * 如向上转型到Object仍无法找到, 返回null.
     *
     * @param object         目标对象
     * @param methodName     方法名称
     * @param parameterTypes 参数类型
     * @return 目标对象的DeclaredMethod
     */
    protected static Method getDeclaredMethod(Object object, String methodName,
                                              Class<?>[] parameterTypes) {
        Assert.notNull(object, "object不能为空");

        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {    // NOSONAR
                // Method不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 根据指定注解获得属性值
     *
     * @param bean            目标bean
     * @param annotationClass 注解Class
     * @return 属性值
     */
    public static String getFiledValueByAnnotation(Object bean, Class<? extends Annotation> annotationClass) {
        Class<?> beanClass = bean.getClass();
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(annotationClass)) {
                try {
                    return BeanUtils.getProperty(bean, field.getName());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 根据指定注解设置属性值
     *
     * @param bean            目标bean
     * @param annotationClass 注解Class
     * @param value           值
     */
    public static void setFiledValueByAnnotation(Object bean, Class<? extends Annotation> annotationClass, Object value) {
        Class<?> beanClass = bean.getClass();
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(annotationClass)) {
                try {
                    BeanUtils.setProperty(bean, field.getName(), value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Bean转Map
     * @param obj Bean
     * @return Map
     */
    public static Map<String, Object> transBean2Map(Object obj) {

        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    /**
     * Map转Bean
     * @param map map
     * @param obj Bean
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        if (map == null || obj == null) {
            return;
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            System.out.println("transMap2Bean2 Error " + e);
        }
    }

    /**
     * 检查对象所有属性是否为空
     *
     * @param obj 对象
     * @param excludeField 排除检查的字段
     * @return 全部为空返回 true 只要有不为空则为false
     */
    public static boolean checkBeanAllFiledIsNull(Object obj,String ... excludeField){
        if (obj==null){return true;}
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null || isInFields(field,excludeField)) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                    continue;
                } else {
                  return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 是否为需要排除的属性
     * @param field 属性
     * @param FieldNames 排除的属性名
     * @return
     */
    private static boolean isInFields(Field field, String ...FieldNames){
        if(FieldNames.length==0){return false;}
        for (String filedName : FieldNames) {
            return field.getName().equals(filedName);
        }
        return false;
    }
}

