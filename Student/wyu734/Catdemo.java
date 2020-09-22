
public class Catdemo {
	private String name;
	private int age;
	public void bark() {
		System.out.println("ίχίχ");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Catdemo [name=" + name + ", age=" + age + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Catdemo cat=new Catdemo();
		cat.setAge(2);
		cat.setName("Π‘°Χ");
		cat.bark();
		System.out.println(cat.toString());
	}

}
