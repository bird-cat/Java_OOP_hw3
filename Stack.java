package ntu.r09922114.container;

public class Stack {
    private Object[] container;
    private int top;

    public Stack() {
        container = new Object[15];
        top = 0;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(Object item) {
        container[top++] = item;
    }

    public void pop() {
        top--;
    }

    public Object front() {
        return container[top - 1];
    }
}
