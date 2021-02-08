
import java.util.Arrays;

@SuppressWarnings("serial")
class EmptyException extends RuntimeException {
	EmptyException() {
		super("비어있습니다.");
	}

	EmptyException(String msg) {
		super(msg);
	}
}

public class MyStack<E> {
	Object[] elements = new Object[2];
	int top = -1;

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == elements.length - 1;
	}

	public void push(E e) {
		if (isFull())
			elements = Arrays.copyOf(elements, elements.length * 2);
		elements[++top] = e;
	}

	@SuppressWarnings("unchecked")
	public E peek() throws EmptyException {
		if (isEmpty())
			throw new EmptyException("데이터가 존재하지 않습니다.");
		return (E) elements[top];
	}

	public E pop() throws EmptyException {
		E e = peek();
		elements[top--] = null;
		return e;
	}

	public String toString() {
		StringBuilder data = new StringBuilder();
		data.append("[");
		if( top == -1) return "[]"; 
		else {
			data.append(elements[0]);
			for(int i = 1; i <= top; i++) {
				data.append(", " + elements[i]);
			}
		}
		data.append("]");
		return data.toString();
	}
}
