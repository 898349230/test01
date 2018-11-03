package com.ab.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) {
		try {
//			Car car = Car.class.newInstance();
//			car.setBound("红旗");
//			car.setPrice(200000);
			//System.out.println(car);
			
			Class<?> clazz = Class.forName("com.ab.reflect.Car");
			System.out.println("=====================测试class==============================");
//			System.out.println("class.getName() :\t"+clazz.getName());  // com.ab.reflect.Car
//			System.out.println("clazz.getSimpleName() :\t"+clazz.getSimpleName());// Car
//			System.out.println("clazz.getTypeName() :\t"+clazz.getTypeName());// com.ab.reflect.Car
//			System.out.println("clazz.getModifiers() :\t"+clazz.getModifiers()); // 1
			System.out.println("=====================测试 method=============================");
			Method[] methods = clazz.getMethods();
		//	Object result = methods[0].invoke(clazz.newInstance(), 100,"abc");
//			System.out.println(result);
//			for (Method method : methods) {
//				System.out.println("name : "+method.getName()+
//									"\tDeclaringClass : "+method.getDeclaringClass()+
//									"\tgetParameterTypes ： "+method.getParameterTypes()[1].getSimpleName()+
//									"\tgetModifiers : "+method.getModifiers()+
//									"\tgetReturnType : "+method.getReturnType()+
//									"\tgetGenericParameterTypes : "+method.getGenericParameterTypes()[1].getTypeName()+
//									"\tgetParameters.length : "+method.getParameters().length);
//			}
			
			
			// 通过   方法名称和    参数列表 获取   run 方法 
			Method method = clazz.getMethod("run", int.class,String.class);
			Method method2 = clazz.getMethod("run", int.class); //  int 不能转换为 Integer
			Object result = method.invoke(clazz.newInstance(),200,"奔驰");
			Object result2 = method2.invoke(clazz.newInstance(),200);
			
			Constructor<?> constructor = clazz.getConstructor(clazz,String.class,int.class);
			Car car2 = (Car) constructor.newInstance("法拉第",5555);
			System.out.println(" 构造函数 ： "+car2);
			
			System.out.println("=================测试field==================================");
			
		//	Field[] fields = clazz.getFields();   // 只获取  public 修饰的 
			Field[] fields = clazz.getDeclaredFields(); // 获取所有 权限修饰符 修饰的  包括private
			
			for (Field field : fields) {
				System.out.println(" field : getName : "+field.getName());
				System.out.println(" field : getModifiers : "+field.getModifiers());
				System.out.println(" field :  getType : "+field.getType());
			}
			
			
			Car instance = (Car) clazz.newInstance();
			Field bound = clazz.getDeclaredField("bound");
			bound.setAccessible(true);
			Field price = clazz.getDeclaredField("price");
			price.setAccessible(true);
			bound.set(instance, "玛莎拉蒂");
			price.set(instance, 100001);
			System.out.println(" car bound ： "+instance.getBound());
			
		} catch (InstantiationException | 
				IllegalAccessException | 
				ClassNotFoundException | 
				IllegalArgumentException | 
				InvocationTargetException |
				SecurityException | 
				NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
