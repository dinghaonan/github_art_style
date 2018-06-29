/**
 * 
 */
package com.neon.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: MapConvertUtil.java
 * @Description: 实体转换工具类
 * @author: dinghaonan
 * @date: 2018年6月10日 上午10:51:59
 * @version V1.0
 */
public class MapConvertUtil {

	/**
	 * 反射实体类赋值
	 * 
	 * @param class1
	 *            用于赋值的实体类
	 * @param class2
	 *            需要待赋值的类型
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T reflectionAttr(final Object class1, final Class class2) {
		if (null == class1) {
			return null;
		}
		Class clazz1 = null;
		Object clazz = null;
		try {
			clazz1 = Class.forName(class1.getClass().getName());
			clazz = class2.newInstance();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} catch (final InstantiationException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		// 获取两个实体类的所有属性
		final Field[] fields1 = clazz1.getDeclaredFields();
		final Field[] fields2 = class2.getDeclaredFields();
		final MapConvertUtil cr = new MapConvertUtil();
		// 遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则赋值
		for (final Field f1 : fields1) {
			if (f1.getName().equals("id")) {
				continue;
			}
			final Object value = cr.invokeGetMethod(class1, f1.getName(), null);
			for (final Field f2 : fields2) {
				if (f1.getName().equals(f2.getName())) {
					final Object[] obj = new Object[1];
					obj[0] = value;
					cr.invokeSetMethod(clazz, f2.getName(), obj);
				}
			}
		}
		return (T) clazz;

	}

	/**
	 * 传入class 获取属性转成数据库字段
	 * 
	 * @param obj
	 *            类
	 * @return
	 */
	public static List<String> paramLowToUpper(final Class obj) {
		final Field[] fields = obj.getDeclaredFields();
		final List<String> fieldNames = new ArrayList<String>();

		for (int i = 0; i < fields.length; i++) {
			String param = "";
			final String paramName = fields[i].getName();
			final String[] splitParamName = paramName.split("(?=[A-Z])");
			for (int j = 0; j < splitParamName.length; j++) {

				if (j == 0) {
					param = param + splitParamName[j].toUpperCase();
				} else {
					param = param + "_" + splitParamName[j].toUpperCase();
				}
			}
			fieldNames.add(param);
		}
		return fieldNames;
	}

	/**
	 * 驼峰转成数据库字段
	 * 
	 * @param column
	 *            字段
	 * @return
	 */
	public static String switchParam(String column) {

		if (column.matches("[a-z]+[A-Z][a-z]+([A-Z][a-z]+)*")) {

			final Pattern pattern = Pattern.compile("[A-Z]");

			final Matcher matcher = pattern.matcher(column);

			while (matcher.find()) {

				final String old = matcher.group();
				final String ne = matcher.group().toLowerCase();

				column = column.replaceAll(old, "_" + ne);

			}

		}
		return column;
	}

	/**
	 * 
	 * 执行某个Field的getField方法
	 * 
	 * @param clazz
	 *            类
	 * @param fieldName
	 *            类的属性名称
	 * @param args
	 *            参数，默认为null
	 * @return
	 */
	private Object invokeGetMethod(final Object clazz, final String fieldName, final Object[] args) {
		final String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Method method = null;
		try {
			method = Class.forName(clazz.getClass().getName()).getDeclaredMethod("get" + methodName);
			return method.invoke(clazz);
		} catch (final Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * 执行某个Field的setField方法
	 * 
	 * @param clazz
	 *            类
	 * @param fieldName
	 *            类的属性名称
	 * @param args
	 *            参数，默认为null
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object invokeSetMethod(final Object clazz, final String fieldName, final Object[] args) {
		final String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Method method = null;
		try {
			final Class[] parameterTypes = new Class[1];
			final Class c = Class.forName(clazz.getClass().getName());
			final Field field = c.getDeclaredField(fieldName);
			parameterTypes[0] = field.getType();
			method = c.getDeclaredMethod("set" + methodName, parameterTypes);
			return method.invoke(clazz, args);
		} catch (final Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
