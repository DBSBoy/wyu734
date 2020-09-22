package day04;

public class Stutest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TakeTemperatureThread t1=new TakeTemperatureThread();
		TakeTemperatureThread t2=new TakeTemperatureThread();
		TakeTemperatureThread t3=new TakeTemperatureThread();
		TakeTemperatureThread t4=new TakeTemperatureThread();
		TakeTemperatureThread t5=new TakeTemperatureThread();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}

}
