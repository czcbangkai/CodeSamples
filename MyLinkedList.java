
public class MyLinkedList {
	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		list.addLast(0);
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);

		list.print();

		list.remove(2);

		list.print();

		list.pollFirst();

		list.print();

		System.out.println(list.size());

		System.out.println(list.get(3));
		System.out.println(list.get(1));
	}

	private class MyListNode {
		public int 			value;
		public MyListNode 	next;
		public MyListNode 	prev;

		public MyListNode(int value) {
			this.value = value;
		}
	}

	private MyListNode 	head;
	private MyListNode 	tail;
	private int 		size;

	public MyLinkedList() {}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void clear() {
		while (size > 0) {
			pollFirst();
		}
	}

	public void print() {
		String res = new String();
		res += "[";

		MyListNode it = head;
		while (it != null) {
			res += Integer.toString(it.value);
			if (it.next != null) {
				res += ", ";
			}
			it = it.next;
		}

		res += "]";

		System.out.println(res);
	}

	public void addFirst(int element) {
		MyListNode newHead = new MyListNode(element);
		++size;

		if (size == 1) {
			head = tail = newHead;
			return;
		}

		newHead.next = head;
		head.prev = newHead;
		head = newHead;
	}

	public void addLast(int element) {
		if (size == 0) {
			addFirst(element);
			return;
		}

		MyListNode newTail = new MyListNode(element);
		++size;

		tail.next = newTail;
		newTail.prev = tail;
		tail = newTail;
	}

	public void add(int index, int element) {
		if (index < 0 || index > size) {
			// Throw Exception
			return;
		}

		if (index == 0) {
			addFirst(element);
			return;
		}

		if (index == size) {
			addLast(element);
			return;
		}

		MyListNode newNode = new MyListNode(element);

		MyListNode it = head;
		for (int i = 0; i < index - 1; ++i) {
			it = it.next;
		}

		newNode.next = it.next;
		newNode.prev = it;
		it.next.prev = newNode;
		it.next = newNode;

		++size;
	}

	public Integer pollFirst() {
		if (size == 0) {
			return null;
		}

		Integer res = head.value;
		--size;

		if (size == 1) {
			head = tail = null;
			return res;
		} 

		head.next.prev = null;
		head = head.next;

		return res;
	}

	public Integer pollLast() {
		if (size <= 1) {
			return pollFirst();
		}

		Integer res = tail.value;
		--size;

		tail.prev.next = null;
		tail = tail.prev;

		return res;
	}

	public Integer remove(int index) {
		if (index < 0 || index >= size) {
			// Or throw exception
			return null;
		}

		if (index == 0) {
			return pollFirst();
		}

		if (index == size - 1)  {
			return pollLast();
		}

		MyListNode it = head;
		for (int i = 0; i < index; ++i) {
			it = it.next;
		}

		Integer res = it.value;

		it.next.prev = it.prev;
		it.prev.next = it.next;

		--size;

		return res;
	}

	public Integer getFirst() {
		if (size == 0) {
			return null;
		}

		return head.value;
	}

	public Integer getLast() {
		if (size == 0) {
			return null;
		}

		return tail.value;
	}

	public Integer get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}

		MyListNode it = head;
		for (int i = 0; i < index; ++i) {
			it = it.next;
		}

		return it.value;
	}
}