package day04;

public class TicketWindow extends Thread {
	private int tickets = 100;// ������static�������Ա�
	private static Object o = new Object();

	public void run() {
		// ͨ����ѭ����䣬��ӡ���
		for(int i=0;i<5;i++) {
			synchronized (o) {
				if (tickets > 0) {
					// ��ȡ��ǰ�̵߳�����
					Thread th = Thread.currentThread();
					String th_name = th.getName();
					System.out.println(th_name + "��������" + tickets-- + "��Ʊ");
				}
			}
		}
	}
	
}
