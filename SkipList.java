import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * This is a simple Java implementation of the Skip List data structure that
 * supports a few of the typical List methods.
 * 
 * @author Juan Leaniz Pittamiglio Student ID: 215241433
 * 
 */

public class SkipList<E> implements List<E> {

	private int size = 0;
	private int listHeight = 1;
	private Node<E> head;
	private Random random;
	private final double p = 0.5;
	private final int MAX_LEVEL = 10;

	/**
	 * This class is for Skip list nodes
	 *
	 */
	private static class Node<E> {
		E item;
		Node<E> forward;
		Node<E> down;
		int distance;

		/**
		 * Node constructor
		 * 
		 * @param E element contained in the node
		 */
		public Node(E element) {
			this.item = element;
		}
	}

	/**
	 * Constructor function. This creates an empty SkipList
	 * 
	 */
	public SkipList() {
		this.head = new Node<>(null);
		this.head.distance = 1;
		this.random = new Random();
	}

	/**
	 * This function is used to generate a new level. This is a probabilistic
	 * method.
	 * 
	 * @return lvl - new level value
	 */
	private int randomLevel() {
		int lvl = 1;
		while (random.nextDouble() < p && lvl < MAX_LEVEL)
			lvl += 1;
		return lvl;
	}

	/**
	 * This method adds an Object to the list in the specified index position.
	 * 
	 * This method implementation is based on William Pugh's "A SkipList Cookbook"
	 * 
	 * @param index: the index where the element will be added
	 * @param element: the object to be added to the list
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}

		int lvl = randomLevel();

		if (lvl > listHeight) {
			for (int i = lvl; i > listHeight; i--) {
				Node<E> node = new Node<>(null);
				node.down = head;
				node.forward = null;
				node.distance = size + 1;
				head = node;
			}
			listHeight = lvl;
		}

		int pos = 0; // pos = pos(x) - the element after which we insert
		int currentLevel = listHeight;
		Node<E> lastInserted = null; // the subsequent, lower new copies will be attached to this

		// for (Node<E> x = head; x != null && currentLevel > 0; currentLevel--) {
		for (Node<E> x = head; x != null && currentLevel > 0; x = x.down) {
			while (pos + x.distance <= index) {
				pos = pos + x.distance;
				if (x.forward != null) {
					x = x.forward;
				}
			}
			if (currentLevel > lvl)
				x.distance = x.distance + 1; // do not insert: just update distances
			else {
				// insert an item at this level
				Node<E> y = new Node<>(element);
				lastInserted = x.forward;
				y.forward = lastInserted;
				x.forward = y;
				y.distance = pos + x.distance - index;
				x.distance = index + 1 - pos;
				if (lastInserted != null)
					lastInserted.down = y;
				lastInserted = y;
			}
			currentLevel--;
		}
		size++;
	}

	/**
	 * This methods returns the number of elements in the SkipList.
	 * 
	 * @return int Returns the number of elements in the list.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * This method adds an Object to the list. This skiplist class allows the
	 * insertion of null elements.
	 * 
	 * @param e an Object to be added to the SkipList
	 * @return true if Object is added, false otherwise
	 */
	@Override
	public boolean add(E e) {
		try {
			if (this.size == 0) {// empty list
				this.add(0, e);
				return true;
			} else {
				this.add(this.size, e);
				return true;
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Cannot add element to list.");
			return false;
		}
	}

	/**
	 * This method returns an Object in the list at a specified index.
	 * 
	 * This method implementation is based on William Pugh's "A SkipList Cookbook"
	 * 
	 * Get(0) will return the head node.
	 * 
	 * @throws IndexOutOfBoundsException
	 * @param index: the index to retrieve the element from
	 * @return E: the object that was retrieved
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size)
			throw new IndexOutOfBoundsException("Index must be > 0 and <= " + listHeight);

		Node<E> x = this.head;

		int pos = 0;
		for (int lvl = listHeight; lvl > 0; lvl--) {
			while (pos + x.distance <= index) {
				pos = pos + x.distance;
				x = x.forward;
			}
		}
		return x.item;
	}

	/**
	 * This method prints the Skip List values
	 * 
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("[");

		// traverse the head nodes until reaching base level
		Node<E> node = this.head;
		for (int lvl = listHeight - 1; lvl > 0; lvl--) {
			node = node.down;
		}

		// count forward links
		int links = 0;
		Node<E> temp = node;
		while (temp != null) {
			temp = temp.forward;
			if (temp != null)
				links++;
		}

		// traverse forward links at the first level and get their values
		for (int i = 0; i < links; i++) {
			node = node.forward;
			sb.append(node.item);
			if (node.forward != null)
				sb.append(", ");
		}

		sb.append("]");
		return sb.toString();

		/*
		 * test code provided by Prof. Pavlovych
		 *
		 * StringBuffer sb = new StringBuffer();
		 * sb.append("toString() SkipList of height: " + listHeight + " Size: "+ size
		 * +"\n"); for (Node<E> node = head; node != null; node = node.down){ for
		 * (Node<E> node2 = node; node2 != null; node2 = node2.forward) sb.append("(" +
		 * node2.item + ")-" + node2.distance + "- "); sb.append("\n"); } return
		 * sb.toString();
		 */
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();

	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();

	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();

	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();

	}

	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException();

	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();

	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();

	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();

	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();

	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();

	}

}
