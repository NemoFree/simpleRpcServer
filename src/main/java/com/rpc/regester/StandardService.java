package com.rpc.regester;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class StandardService {

	//����
	Object bean;
	//�����ķ����б�
	ConcurrentHashMap<Integer , Method> methodCache = new  ConcurrentHashMap<Integer , Method> ();
	//�����ķ�������
	String beanName ;
	
	public StandardService(String beanName){
		this.beanName = beanName;
		_init();
	}
	
	public StandardService(String beanName ,Object bean){
		this.beanName = beanName;
		this.bean =bean;
		_init();
	}
	
	public Object execute(int methodHash , Object ...objects ) throws IllegalArgumentException ,IllegalAccessException,NoSuchMethodException {
		Method m = methodCache.get(methodHash);
		Object rtn = null;
		if(m !=null){
			try {
				rtn = m.invoke(bean, objects);
			} catch (InvocationTargetException e) {
				Throwable  ex = e.getTargetException();
				throw new RuntimeException(ex);
			}
		}else{
			throw new NoSuchMethodException();
		}
		return rtn;
	}
	
	private void _init(){
		
	}
	public void destroy(){
		methodCache.clear();
		bean = null;
	}
	
}
