package day04;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorCreatePool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 20, TimeUnit.SECONDS,
				new LinkedBlockingDeque<>(200), new ThreadPoolExecutor.AbortPolicy());
		final CountDownLatch countDownLatch = new CountDownLatch(8);
		for (int i = 0; i < 8; i++) {
			final int currentIndex = i;
			System.out.println("提交第" + i + "个线程");
			threadPoolExecutor.execute(() -> {
				System.out.println(Thread.currentThread().getName() + ", currentIndex is : " + currentIndex);
				countDownLatch.countDown();
			});
		}
		System.out.println("全部提交完毕");
	}

}