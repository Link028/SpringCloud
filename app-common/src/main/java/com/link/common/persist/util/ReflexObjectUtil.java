package com.link.common.persist.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflexObjectUtil
{
	/**
     * 单个对象的所有键值
     * 
     * @param object
     *            单个对象
     * 
     * @return Map<String, Object> map 所有 String键 Object值 ex：{pjzyfy=0.00,
     *         xh=01, zzyl=0.00, mc=住院患者压疮发生率, pjypfy=0.00, rs=0, pjzyts=0.00,
     *         czydm=0037, lx=921, zssl=0.00}
     */
	public static Map<String, Object> getKeyAndValue(Object obj) {
		
		try
		{
			Class<? extends Object> userCla = (Class<? extends Object>) obj.getClass();
			Field[] fs = userCla.getDeclaredFields();
			//Field[] fs = userCla.getFields(); //获取所有public字段,包括父类字段
			Map<String, Object> map = new HashMap<String, Object>();
			
			for (int i = 0; i < fs.length; i++)
			{
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = new Object();

				val = f.get(obj);
				// 得到此属性的值
				map.put(f.getName(), val);// 设置键值

				/*
				 * String type = f.getType().toString();//得到此属性的类型 if
				 * (type.endsWith("String")) {
				 * System.out.println(f.getType()+"\t是String"); f.set(obj,"12") ;
				 * //给属性设值 }else if(type.endsWith("int") ||
				 * type.endsWith("Integer")){
				 * System.out.println(f.getType()+"\t是int"); f.set(obj,12) ; //给属性设值
				 * }else{ System.out.println(f.getType()+"\t"); }
				 */
			}
			//获取父类属性
			Class<? > clzz = userCla.getSuperclass();
			if( clzz != null && ! clzz.getName().equals(Object.class.getName() ))
			{
				Field[] sfs = clzz.getDeclaredFields();
				for (int i = 0; i < sfs.length; i++)
				{
					Field f = sfs[i];
					f.setAccessible(true); 
					Object val = new Object();

					val = f.get(obj);
					map.put(f.getName(), val);
				}
			}
			
			return map;
		}
		catch (Exception e)
		{
			 throw new RuntimeException("获取属性值失败:"+e.getMessage(),e);
		}
	}

    /**
     * 单个对象的某个键的值
     * 
     * @param object
     *            对象
     * 
     * @param key
     *            键
     * 
     * @return Object 键在对象中所对应得值 没有查到时返回空字符串
     */
    public static Object getValueByKey(Object obj, String key) {
        // 得到类对象
        Class<? extends Object> userCla = (Class<? extends Object>) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            try {
            
                if (f.getName().endsWith(key)) {
                    return f.get(obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // 没有查到时返回空字符串
        return "";
    }

    /**
             * 多个（列表）对象的所有键值
     * 
     * @param object
     * @return List<Map<String,Object>> 列表中所有对象的所有键值 ex:[{pjzyfy=0.00, xh=01,
     *         zzyl=0.00, mc=住院患者压疮发生率, pjypfy=0.00, rs=0, pjzyts=0.00,
     *         czydm=0037, lx=921, zssl=0.00}, {pjzyfy=0.00, xh=02, zzyl=0.00,
     *         mc=新生儿产伤发生率, pjypfy=0.00, rs=0, pjzyts=0.00, czydm=0037, lx=13,
     *         zssl=0.00}, {pjzyfy=0.00, xh=03, zzyl=0.00, mc=阴道分娩产妇产伤发生率,
     *         pjypfy=0.00, rs=0, pjzyts=0.00, czydm=0037, lx=0, zssl=0.00},
     *         {pjzyfy=0.00, xh=04, zzyl=0.75, mc=输血反应发生率, pjypfy=0.00, rs=0,
     *         pjzyts=0.00, czydm=0037, lx=0, zssl=0.00}, {pjzyfy=5186.12,
     *         xh=05, zzyl=0.00, mc=剖宫产率, pjypfy=1611.05, rs=13, pjzyts=7.15,
     *         czydm=0037, lx=13, zssl=0.00}]
     */
    public static List<Map<String, Object>> getKeysAndValues(List<Object> object) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Object obj : object) {
            Class<? extends Object> userCla;
            // 得到类对象
            userCla = (Class<? extends Object>) obj.getClass();
            /* 得到类中的所有属性集合 */
            Field[] fs = userCla.getDeclaredFields();
            Map<String, Object> listChild = new HashMap<String, Object>();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true); // 设置些属性是可以访问的
                Object val = new Object();
                try {
                    val = f.get(obj);
                    // 得到此属性的值
                    listChild.put(f.getName(), val);// 设置键值
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.add(listChild);// 将map加入到list集合中
        }

        return list;
    }

    /**
     * 多个（列表）对象的某个键的值
     * 
     * @param object
     * @param key
     * @return List<Object> 键在列表中对应的所有值 ex:key为上面方法中的mc字段 那么返回的数据就是： [住院患者压疮发生率,
     *         新生儿产伤发生率, 阴道分娩产妇产伤发生率, 输血反应发生率, 剖宫产率]
     */
    public static List<Object> getValuesByKey(List<Object> object, String key) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : object) {
            // 得到类对象
            Class<? extends Object> userCla = (Class<? extends Object>) obj.getClass();
            /* 得到类中的所有属性集合 */
            Field[] fs = userCla.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true); // 设置些属性是可以访问的
                try {
                    if (f.getName().endsWith(key)) {
                        list.add(f.get(obj));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

	/**
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @return
	  @throws
	 *@Description 设置 属性值
	 * @author liguanghui
	 * @date 2018/10/18-10:41
	 */
	public static void setValue(Object obj, String fieldName, Object value) {
	    try {
	        Field field = obj.getClass().getDeclaredField(fieldName);
	        field.setAccessible(true);
	        field.set(obj, value);
	    } catch (Exception e) {
	        throw new RuntimeException("失败", e);
	    }
	}
}
