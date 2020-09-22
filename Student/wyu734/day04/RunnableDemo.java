package day04;

public class RunnableDemo implements Runnable {
	private int tickets = 100;

	public void run() {
		for(int i=0;i<5;i++){
			synchronized (this) {
				if (tickets > 0) {
					// ��ȡ��ǰ�߳�
					Thread th = Thread.currentThread();
					String th_name = th.getName();
					System.out.println(th_name + "���ڷ��۵�" + tickets-- + "��Ʊ");
				}
			}
		}
	}
}
