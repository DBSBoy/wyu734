package day04;

public class Runnabletest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunnableDemo r=new RunnableDemo();
		new Thread(r,"通道1").start();
		new Thread(r,"通道2").start();
		new Thread(r,"通道3").start();
		
	}

}
