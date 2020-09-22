package day02;

public class Vetest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car c=new Car(5);
		Truck t=new Truck(10f);
		c.brand="宝马";
		c.color="蓝色";
		//System.out.println("商标"+c.brand+" 颜色"+c.color+" 座位数"+c.seats);
		System.out.println(c.showInfo());
		c.showcar();
		t.brand="奔驰";
		t.color="红色";
		System.out.println(t.showInfo());
		t.showTruck();
	}

}
