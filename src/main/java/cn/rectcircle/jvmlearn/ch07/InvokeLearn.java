package cn.rectcircle.jvmlearn.ch07;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 
 *
 * @author sunben
 * @date 2018-07-25
 * @version 0.0.1
 */
public class InvokeLearn {
	static class ClassA {
		public void println(String s){
			System.out.println(s);
		}
	}

	public static void main(String[] args) throws Throwable {
		Object obj = System.currentTimeMillis() % 2 == 0 ? System.out:new ClassA();
		MethodHandle mh =  getPrintlnMethodHandle(obj);
		mh.invokeExact("Hello World!");
	}

	private static MethodHandle getPrintlnMethodHandle(Object obj) throws NoSuchMethodException, IllegalAccessException {
		MethodType mt = MethodType.methodType(void.class, String.class);
		return MethodHandles.lookup().findVirtual(obj.getClass(), "println", mt).bindTo(obj);
	}

}