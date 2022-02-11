/*
 * Name: Barrett A. Williamson
 * Date: 12/2/2021
 * Email: williamsonbarrett@gmail.com
 * Linked List class with all main methods.
 */
package linkedlist;

public class MyLinkedList<E> implements MyList<E> {
	private static class Node<E> {
		private E data;
		private Node<E> next;

		public Node(E data) {
			this(data, null);
		}

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E> head, tail;
	private int size = 0;

	public MyLinkedList() {
	}

	public MyLinkedList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			this.add(objects[i]);
	}

	public E getFirst() {
		if (head != null) {
			return head.data;
		}
		return null;
	}

	public E getLast() {
		if (tail != null) {
			return tail.data;
		}
		return null;
	}

	public void addFirst(E e) {
		Node<E> newNode = new Node<>(e); 
		newNode.next = head; 
		head = newNode; 
		size++; 

		if (tail == null) 
			tail = head;
	}

	public void addLast(E e) {
		Node<E> newNode = new Node<>(e); 

		if (tail == null) {
			head = tail = newNode; 
		} else {
			tail.next = newNode; 
			tail = newNode; 
		}

		size++; 
	}

	@Override 
	public void add(int index, E e) {
		if (index == 0) {
			addFirst(e);
		} else if (index >= size) {
			addLast(e);
		} else {
			Node<E> current = head;
			Node<E> prev = null;
			for (int i = 0; i < index; i++) {
				prev = current;
				current = current.next;
			}
			Node<E> newNode = new Node<>(e);
			prev.next = newNode;
			newNode.next = current;
			size++;
		}
	}

	public E removeFirst() {
		if (size == 0) {
			return null;
		} else {
			E temp = head.data;
			head = head.next;
			size--;
			if (head == null) {
				tail = null;
			}
			return temp;
		}
	}

	public E removeLast() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			E temp = head.data;
			head = tail = null;
			size = 0;
			return temp;
		} else {
			Node<E> current = head;

			for (int i = 0; i < size - 2; i++) {
				current = current.next;
			}

			E temp = tail.data;
			tail = current;
			tail.next = null;
			size--;
			return temp;
		}
	}

	@Override 
	public E remove(int index) {
		if (index < 0 || index >= size) {
			return null;
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node<E> current = head;
			Node<E> prev = null;
			for (int i = 0; i < index; i++) {
				prev = current;
				current = current.next;
			}

			prev.next = current.next;
			size--;
			return current.data;
		}
	}

	@Override 
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.data);
			current = current.next;
			if (current != null) {
				result.append(", "); 
			} else {
				result.append("]"); 
			}
		}

		return result.toString();
	}

	@Override 
	public void clear() {
		size = 0;
		head = tail = null;
	}

	@Override 
	public boolean contains(Object e) {
		return indexOf(e) != -1;

	}

	@Override 
	public E get(int index) {

		int currentIndex = 0;
		Node<E> current = head;
		while (currentIndex != index) {
			current = current.next;
			currentIndex++;
		}
		return current.data;
	}

	@Override 
	public int indexOf(Object e) {
		int index = 0;
		Node<E> current = head;
		while (current != null) {
			if (current.data.equals(e))
				return index;

			index++;
			current = current.next;

		}
		return -1;
	}

	@Override 
	public int lastIndexOf(E e) {
		int index = 0;
		int count = -1;

		Node<E> currentIndex = head;

		while (currentIndex.next != null) {
			if (currentIndex.data.equals(e)) {
				count = index;
			}
			index++;
			currentIndex = currentIndex.next;

			if (currentIndex.data.equals(e)) {
				count = index;

			}
		}

		return count;
	}

	@Override 
	public E set(int index, E e) {
		remove(index);
		add(index,e);
		return e;
		
	}

	@Override 
	public int size() {
		return size;
	}

	@Override 
	public java.util.Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements java.util.Iterator<E> {
		private Node<E> current = head; 

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			E e = current.data;
			current = current.next;
			return e;
		}

		@Override
		public void remove() {
		}
	}

}