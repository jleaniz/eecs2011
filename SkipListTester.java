
public class SkipListTester {

	public static void main(String[] args) {
		SkipList<Object> list = new SkipList<Object>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);
		
		Object i = list.get(0);
		System.out.println("Got: "+i);
		System.out.println(list.toString());
	}

}
