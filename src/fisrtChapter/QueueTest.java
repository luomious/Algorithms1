package fisrtChapter;

import com.sun.tools.javac.Main;

import java.util.Scanner;

/*
 * 1.front变量的含义做调整：front指向队列第一个元素，arr[front]就是队列第一个元素，初始值为0
 * 2.rear变量含义：指向队列最后一个元素的后一个位置，因为希望空一个元素为约定，初始值为0
 * 3.当队列满的时候，条件是（rear+1）%maxSize==front；满
 * 4.当队列为空的时候，rear==front
 * 5.队列有效数据个数为(rear+maxSize-front)%maxSize
 * 6.这样可以得到环形队列
 *
 *
 *
 *
 *
 * */
public class QueueTest {
    public static void main(String[] args) {
        System.out.println("测试环形队列");
        CircleArray queues = new CircleArray(7);//声明最大长度为6

        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queues.show();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queues.addQueue(value);
                    break;
                case 'g':
                    System.out.println("取出数据");
                    try {
                        int res = queues.getQueue();
                        System.out.printf("取出的数据是%d\n", res);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头
                    try {
                        int res = queues.headQueue();
                        System.out.println("队列头的数据是：" + res);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出成功");

    }
    }




class CircleArray {
    private int maxSize;//队列最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }
    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        //加入数据
        arr[rear] = n;
        //将rear后移
        rear = (rear + 1) % maxSize;

    }

    public int getQueue() {
        if (isEmpty()) {
            //通过抛异常
            throw new RuntimeException("队列空，不能读取数据");
        }
        //将front保留给临时保留
        //将front后移,考虑取模
        //返回临时变量的值
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }

        }

    public int size() {
        return (rear - front + maxSize) % maxSize;
    }
    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}
