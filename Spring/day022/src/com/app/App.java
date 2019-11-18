package com.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.product.Product;
import com.user.User;

public class App {
	public static void main(String[] args)  {

		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring started");

		// tight coupling
		// spring���� �̸� ��û-> �ش� ��ü�� ������
		Biz<String, User> biz = (Biz<String, User>) factory.getBean("ubiz");
			// size�� Ŭ�������� �־��ִ� ��=�������ش�.
		User user = null;
		
/*
		try {	
			user = biz.select("id00");
			System.out.println(user.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/	 
			
		
		User user1 = new User("id01","pwd01","�̸���");

		
		try {
			biz.insert(user1);
			System.out.println("Inserted Ok From app.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		factory.close();
	
	}

}