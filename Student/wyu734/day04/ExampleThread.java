package day04;

public class ExampleThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketWindow t1 = new TicketWindow();
		TicketWindow t2 = new TicketWindow();
		TicketWindow t3 = new TicketWindow();
		TicketWindow t4 = new TicketWindow();
		t1.setName("线程1");
		t2.setName("线程2");
		t3.setName("线程3");
		t4.setName("线程4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
