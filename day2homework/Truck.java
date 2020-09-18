package day02;

public class Truck extends Vehicles2 {
	 float load;

	public Truck(float load) {
		super();
		this.load = load;
	}
	public Truck() {
		// TODO Auto-generated constructor stub
	}
	public void showTruck() {
		System.out.println("дижи"+load);
	}
}
