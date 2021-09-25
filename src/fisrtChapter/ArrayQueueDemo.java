package fisrtChapter;

import java.util.Scanner;

/*
 *
 *
 *
 *
 * */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queues = new ArrayQueue(3);

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
                    System.out.println("输出一个数");
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

//使用数组模拟队列编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize;//队列最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，分析front是指向队列头的前一个位置
        rear = -1;//指向队列尾，指向队列尾的数据（即队列最后一个数据）

    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");

        }
        rear++;
        arr[rear] = n;
    }

    //获取队列数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛异常
            throw new RuntimeException("队列空，不能读取数据");

        }
        front++;
        return arr[front];

    }

    //显示队列所有数据
    public void show() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println("数据arr[" +i+"]"+arr[i]);
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
          throw new RuntimeException("队列为空，没有数据");

        }
        return arr[front + 1];
    }
}