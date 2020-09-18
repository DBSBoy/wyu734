package day02;

public class Phone {
	private float screenSize;
	private String brand;
	private long  cpu;
	private int memory;
	
	
	public float getScreenSize() {
		return screenSize;
	}


	public void setScreenSize(float screenSize) {
		this.screenSize = screenSize;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public long getCpu() {
		return cpu;
	}


	public void setCpu(long cpu) {
		this.cpu = cpu;
	}


	public int getMemory() {
		return memory;
	}


	public void setMemory(int memory) {
		this.memory = memory;
	}


	@Override
	public String toString() {
		return "Phone [screenSize=" + screenSize + ", brand=" + brand + ", cpu=" + cpu + ", memory=" + memory + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Phone p1=new Phone();
		p1.setBrand("ะกรื");
		p1.setCpu(765);
		p1.setMemory(128);
		p1.setScreenSize(6.5f);
		System.out.println(p1.toString());
		
	}

}
