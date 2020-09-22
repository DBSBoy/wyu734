package day04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
public class CallableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 TestCallable td = new TestCallable();
		 
	        //执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
	        FutureTask<Integer> result = new FutureTask<>(td);
	 
	        new Thread(result).start();
	 
	        //接收线程运算后的结果
	        try {
	            Integer sum = result.get();  //FutureTask 可用于 闭锁 类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
	            System.out.println("所有线程参与运算结果为："+sum);
	            System.out.println("------------------------");
	        } catch (InterruptedException | ExecutionException e) {
	            e.printStackTrace();
	        }
	    }

}
