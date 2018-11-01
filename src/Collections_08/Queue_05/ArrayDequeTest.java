package Collections_08.Queue_05;

import java.util.ArrayDeque;

//队列 先进先出(FIFO),栈 后进先出(LIFO)
public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque stack = new ArrayDeque();
        //一次将三个 元素推入 栈
        stack.push("111");
        stack.addFirst("222");
        stack.push("333");
        System.out.println(stack);

        //访问第一个对象而不pop出
        System.out.println(stack.peek());
        System.out.println(stack.element());
    }
}
