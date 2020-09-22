package day02;

public class Person {
	private String name,sex;
	
	//无参构造
	public Person() {
		super();
	}
	//有参构造
	public Person(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}
	

	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + "]";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1=new Person();//无参实例化
		Person p2=new Person("Marry", "male");
		p1.name="tom";
		p1.sex="man";
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		
		
	}

}
