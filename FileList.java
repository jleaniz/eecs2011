
/**
 * EECS 2011 - Assignment 1]
 * Implementation of the java List interface as per
 * the instructor specifications
 * 
 * @author Juan Leaniz Pittamiglio
 * @version 1.00, 11 Jan 2019
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class FileList<E> extends FileContainer implements List<E> {

	private int elementCount = 0;
	private static FileWriter fr;

	/**
	 * 
	 * Simple constructor to create an empty list. It also writes the empty list to
	 * a file on disk using a default filename.
	 */
	public FileList() {
		this.fileName = System.currentTimeMillis() + ".list";
		File file = new File(this.getFileName());
		try {
			fr = new FileWriter(file);
			fr.flush();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Constructor that takes a file name as a parameter. Checks if the file
	 * contains a valid list and loads it, otherwise it creates a new (empty) file.
	 * 
	 * @param fileName
	 */
	public FileList(String fileName) {
		this.fileName = fileName;
		File file = new File(fileName);
		try {
			if (file.exists() && file.length() > 0) {
				Scanner sc = new Scanner(file);
				String last = "";
				while (sc.hasNextLine()) {
					last = sc.nextLine();
				}
				sc.close();
				this.elementCount = Integer.parseInt(last.split(":")[0]) + 1;
			} else {
				fr = new FileWriter(file, true);
				fr.write("");
				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * The method returns the current file name.
	 * 
	 * @return the current file name
	 */
	@Override
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * 
	 * The method returns the correct file size associated with this list
	 * 
	 * @return file size
	 */
	@Override
	public long getFileSize() {
		return (new File(this.getFileName()).length());
	}

	private int getelementCount() {
		return this.elementCount;
	}

	private boolean isNumber(E e) {
		if (e instanceof Number)
			return true;
		else
			return false;
	}

	@SuppressWarnings({ "unchecked" })
	private E readObject(String[] data) {
		Object e = null;
		if (data[1].equals("Integer"))
			e = Integer.parseInt(data[2]);
		else if (data[1].equals("Short"))
			e = Short.parseShort(data[2]);
		else if (data[1].equals("Byte"))
			e = Byte.parseByte(data[2]);
		else if (data[1].equals("Long"))
			e = Long.parseLong(data[2]);
		else if (data[1].equals("Double"))
			e = Long.parseLong(data[2]);
		else if (data[1].equals("Float"))
			e = Float.parseFloat(data[2]);
		return (E) e;
	}

	@Override
	public boolean add(E e) {
		if (e != null && isNumber(e)) {
			File file = new File(this.getFileName());
			String type = e.getClass().getSimpleName();

			try {
				fr = new FileWriter(file, true);
				if (type.equals("Integer")) {
					fr.write(elementCount + ":Integer:" + e.toString() + "\n");
					this.elementCount += 1;
				} else if (e instanceof Short) {
					fr.write(elementCount + ":Short:" + e.toString() + "\n");
					this.elementCount += 1;
				} else if (e instanceof Byte) {
					fr.write(elementCount + ":Byte:" + e.toString() + "\n");
					this.elementCount += 1;
				} else if (e instanceof Double) {
					fr.write(elementCount + ":Double:" + Double.doubleToLongBits((Double) e) + "\n");
					this.elementCount += 1;
				} else if (e instanceof Float) {
					fr.write(elementCount + ":Float:" + e.toString() + "\n");
					this.elementCount += 1;
				} else if (e instanceof Long) {
					fr.write(elementCount + ":Long:" + e.toString() + "\n");
					this.elementCount += 1;
				}
				fr.flush();
				fr.close();
				return true;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("add(): Error adding object. Null or invalid object.");
		}
		return false;
	}

	@Override
	public void add(int index, E e) {
		if (e != null && isNumber(e) && index >= 0 && index <= this.elementCount) {
			File file = new File(this.getFileName());
			File tempfile = new File("temp");
			int currIndex = 0;

			if (index == this.elementCount) {
				this.add(e);
			} else {
				try {
					Scanner sc = new Scanner(file);
					fr = new FileWriter(tempfile, true);
					while (sc.hasNextLine()) {
						String line = sc.nextLine();
						String data[] = line.split(":");
						currIndex = Integer.parseInt(data[0]);
						int newIndex = currIndex + 1;

						if (currIndex == index) {
							// add new element
							if (e instanceof Integer) {
								fr.write(index + ":Integer:" + e.toString() + "\n");
								this.elementCount += 1;
							} else if (e instanceof Short) {
								fr.write(index + ":Short:" + e.toString() + "\n");
								this.elementCount += 1;
							} else if (e instanceof Byte) {
								fr.write(index + ":Byte:" + e.toString() + "\n");
								this.elementCount += 1;
							} else if (e instanceof Double) {
								// fr.write(index + ":Double:" + e.toString() + "\n");
								fr.write(index + ":Double:" + Double.doubleToLongBits((Double) e) + "\n");
								this.elementCount += 1;
							} else if (e instanceof Float) {
								fr.write(index + ":Float:" + e.toString() + "\n");
								this.elementCount += 1;
							} else if (e instanceof Long) {
								fr.write(index + ":Long:" + e.toString() + "\n");
								this.elementCount += 1;
							}
							// re-add element in this position but with the new index
							if (data[1].equals("Integer")) {
								Integer i = Integer.parseInt(data[2]);
								fr.write(newIndex + ":Integer:" + i.toString() + "\n");
							} else if (data[1].equals("Short")) {
								Short i = Short.parseShort(data[2]);
								fr.write(newIndex + ":Short:" + i.toString() + "\n");
							} else if (data[1].equals("Byte")) {
								Byte i = Byte.parseByte(data[2]);
								fr.write(newIndex + ":Byte:" + i.toString() + "\n");
							} else if (data[1].equals("Long")) {
								Long i = Long.parseLong(data[2]);
								fr.write(newIndex + ":Long:" + i.toString() + "\n");
							} else if (data[1].equals("Double")) {
								Long i = Long.parseLong(data[2]);
								fr.write(newIndex + ":Double:" + i.toString() + "\n");
							} else if (data[1].equals("Float")) {
								Float i = Float.parseFloat(data[2]);
								fr.write(newIndex + ":Float:" + i.toString() + "\n");
							}
						} else if (currIndex < index) {
							fr.write(line + "\n");
						} else {
							if (data[1].equals("Integer")) {
								Integer i = Integer.parseInt(data[2]);
								fr.write(newIndex + ":Integer:" + i.toString() + "\n");
							} else if (data[1].equals("Short")) {
								Short i = Short.parseShort(data[2]);
								fr.write(newIndex + ":Short:" + i.toString() + "\n");
							} else if (data[1].equals("Byte")) {
								Byte i = Byte.parseByte(data[2]);
								fr.write(newIndex + ":Byte:" + i.toString() + "\n");
							} else if (data[1].equals("Long")) {
								Long i = Long.parseLong(data[2]);
								fr.write(newIndex + ":Long:" + i.toString() + "\n");
							} else if (data[1].equals("Double")) {
								Long i = Long.parseLong(data[2]);
								fr.write(newIndex + ":Double:" + i.toString() + "\n");
							} else if (data[1].equals("Float")) {
								Float i = Float.parseFloat(data[2]);
								fr.write(newIndex + ":Float:" + i.toString() + "\n");
							}
						}
					}
					fr.flush();
					fr.close();
					sc.close();
					tempfile.renameTo(file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} else {
			if (e == null)
				System.out.println("add(index): Checks failed. Attempting to add null element.");
			else
				System.out.println("add(index): Checks failed. Index out of bounds.");
		}
	}

	@Override
	public void clear() {
		this.elementCount = 0;
		File file = new File(this.getFileName());
		try {
			fr = new FileWriter(file);
			fr.write("");
			fr.flush();
			fr.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public E remove(int index) {
		Object e = null;
		File file = new File(this.getFileName());
		File tempfile = new File("temp");
		int currIndex = 0;

		try {

			if (this.size() < 1) {
				return null; // empty list
			}

			else if (index < 0 || index > this.size() - 1) { // invalid index
				System.out.println("remove(index): Index out of bounds");
			}

			// removing first element from a list with 1 element
			else if (index == 0 && this.size() == 1) {
				this.clear();
			}

			// removing the last element of a list
			else if (index != 0 && index == this.size() - 1) {
				Scanner sc = new Scanner(file);
				fr = new FileWriter(tempfile, true);
				int c = 0;
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					if (c < this.elementCount - 1) {
						fr.write(line + "\n");
					}
					c++;
				}
				fr.flush();
				fr.close();
				sc.close();
				tempfile.renameTo(file);
				this.elementCount -= 1;
			}

			// common case
			else {
				Scanner sc = new Scanner(file);
				fr = new FileWriter(tempfile, true);
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					String data[] = line.split(":");
					currIndex = Integer.parseInt(data[0]);

					if (currIndex < index) {
						fr.write(line + "\n");
					} else if (currIndex == index) {
						e = this.readObject(data);
					} else {
						fr.write(currIndex - 1 + ":" + data[1] + ":" + data[2] + "\n");
					}
				}
				fr.flush();
				fr.close();
				sc.close();
				tempfile.renameTo(file);
				this.elementCount -= 1;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return (E) e;
	}

	@Override
	public boolean remove(Object o) {
		if (o != null && o instanceof Number) {
			String type = null;
			String value = null;

			type = o.getClass().getSimpleName();
			if (type.equals("Integer")) {
				value = o.toString();
			}
			if (type.equals("Short")) {
				value = o.toString();
			}
			if (type.equals("Byte")) {
				value = o.toString();
			}
			if (type.equals("Double")) {
				value = o.toString();
			}
			if (type.equals("Float")) {
				value = o.toString();
			}
			if (type.equals("Long")) {
				value = o.toString();
			}

			/*
			 * Read the data file looking for an entry with the same type and value, if
			 * found, get the index and call remove(int index)
			 */
			File file = new File(this.getFileName());
			int currIndex = 0;
			try {
				Scanner sc = new Scanner(file);
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					String data[] = line.split(":");
					currIndex = Integer.parseInt(data[0]);
					if (type != null && value != null && data[1].equals(type) && data[2].equals(value)) {
						this.remove(currIndex);
						return true;
					}
				}
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String toString() {
		File file = new File(this.getFileName());
		String s = "[";
		if (this.elementCount == 0) {
			return "[]";
		}
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String data[] = sc.nextLine().split(":");
				int index = Integer.parseInt(data[0]);
				if (data[1].equals("Integer")) {
					Integer i = Integer.parseInt(data[2]);
					s += i.toString();
				} else if (data[1].equals("Short")) {
					Short i = Short.parseShort(data[2]);
					s += i.toString();
				} else if (data[1].equals("Byte")) {
					Byte i = Byte.parseByte(data[2]);
					s += i.toString();
				} else if (data[1].equals("Long")) {
					Long i = Long.parseLong(data[2]);
					s += i.toString();
				} else if (data[1].equals("Double")) {
					Double i = Double.longBitsToDouble(Long.parseLong(data[2]));
					s += i.toString();
				} else if (data[1].equals("Float")) {
					Float i = Float.parseFloat(data[2]);
					s += i.toString();
				}
				if (index != this.elementCount - 1)
					s += ", ";
				else
					s += "]";
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return s;
	}

	@Override
	public int size() {
		return this.elementCount;
	}

	/**
	 * The following methods are currently unsupported
	 */

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
	public E get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
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
