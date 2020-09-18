package day04;

import java.util.Random;

public class TakeTemperatureThread extends Thread{
	Student stu=new Student();
	
	int str[]=new int[100];
	private static Object o=new Object();
	public void run() {
		for(int i=0;i<5;i++) {
			Random ra =new Random();
			 str[i]=ra.nextInt(100);
			synchronized (o) {
				Thread th=Thread.currentThread();
				String th_name=th.getName();
				System.out.println("学号"+str[i]+"同学"+takeTemperature());
			}
		}
	}
	public String takeTemperature() {
		return "已经测温";
	}

}
