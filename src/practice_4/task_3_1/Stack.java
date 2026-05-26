package practice_4.task_3_1;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T>{
    private Object[] objects;
    private int size = 0;
    private static final int capacity = 10;
    public Stack(){
        this.objects = new Object[capacity];
    }
    public void push(T element){
        if (size == objects.length){
            resize();
        }
        objects[size++]=element;
    }
    public T pop(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        T element = (T) objects[--size];
        objects[size] = null;
        return element;
    }
    public T peek(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        T element = (T) objects[size-1];
        return element;
    }
    public boolean isEmpty(){return size == 0;}
    public int size() {return size;}
    public void resize(){
        int newCapacity = objects.length * 2;
        objects = Arrays.copyOf(objects, newCapacity);
    }
    @Override
    public String toString() {
        return "Stack{" +
                "objects=" + Arrays.toString(objects) +
                '}';
    }
}

class Test{
    public static void main(String[] args){
        Stack<String> stack1 = new Stack<>();
        stack1.push("hello");
        stack1.push("world");
        stack1.push("bye");
        System.out.println(stack1.peek());
        System.out.println(stack1.pop());

        Stack<Integer> stack2 = new Stack<>();
        for (int i=10;i<=50;i++) {
            stack2.push(i);
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pop() + " ");
        }
    }
}