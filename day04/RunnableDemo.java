package day04;

public class RunnableDemo implements Runnable {
	private int tickets = 100;

	public void run() {
		for(int i=0;i<5;i++){
			synchronized (this) {
				if (tickets > 0) {
					// 获取当前线程
					Thread th = Thread.currentThread();
					String th_name = th.getName();
					System.out.println(th_name + "正在发售第" + tickets-- + "张票");
				}
			}
		}
	}
}
