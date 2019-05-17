import java.util.ArrayList;
import java.util.List;

public class FunctionalityCheck {

	enum Position {beginning, end, random, byValue};

	public static void main(String[] args) {
		List [] lists = {new ArrayList<Number>(), new FileList<Number>()};
		for (List <Number> currentList : lists){
			currentList.add(64);
			currentList.add(-3.0);
			currentList.add(-1L);
			currentList.add(45f);

			System.out.println(currentList.getClass().getSimpleName());
			System.out.println("toString()");
			System.out.println(currentList);
			System.out.println("Expected:");
			System.out.println("[64, -3.0, -1, 45.0]");
			System.out.println();

			System.out.println("size(): " + currentList.size());
			System.out.println("Expected:");
			System.out.println("size(): 4");
			System.out.println();

			System.out.println("Removing two items");
			currentList.remove(1);
			currentList.remove(45.0f);
			System.out.println(currentList);
			System.out.println("Expected:");
			System.out.println("[64, -1]");
			System.out.println();

			System.out.println("size(): " + currentList.size());
			System.out.println("Expected:");
			System.out.println("size(): 2");
			System.out.println();

			System.out.println("Clear");
			currentList.clear();
			System.out.println(currentList);
			System.out.println("size(): " + currentList.size());
			System.out.println("Expected:");
			System.out.println("[]");
			System.out.println("size(): 0");
			System.out.println();

			System.out.println("Adding two items");
			currentList.add(1);
			currentList.add(0, 45.0f);
			System.out.println(currentList);
			System.out.println("size(): " + currentList.size());
			System.out.println("Expected:");
			System.out.println("[45.0, 1]");
			System.out.println("size(): 2");
			System.out.println();
		}
		
		System.out.println("FileList name: " + ((FileList)lists[1]).getFileName());
		System.out.println("FileList file size: " + ((FileList)lists[1]).getFileSize() + " B");
	}

}
