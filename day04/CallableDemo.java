package day04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
public class CallableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 TestCallable td = new TestCallable();
		 
	        //ִ�� Callable ��ʽ����Ҫ FutureTask ʵ�����֧�֣����ڽ�����������
	        FutureTask<Integer> result = new FutureTask<>(td);
	 
	        new Thread(result).start();
	 
	        //�����߳������Ľ��
	        try {
	            Integer sum = result.get();  //FutureTask ������ ���� ������CountDownLatch�����ã������е��߳�û��ִ�����֮�������ǲ���ִ�е�
	            System.out.println("�����̲߳���������Ϊ��"+sum);
	            System.out.println("------------------------");
	        } catch (InterruptedException | ExecutionException e) {
	            e.printStackTrace();
	        }
	    }

}
