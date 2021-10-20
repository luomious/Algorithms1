package recursion;

/*
八皇后问题，使用递归
 */
public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    int[] array = new int[max];
    static int count = 0;

    //定义一个数组array，保存皇后放置的结果，比如arr[8]={0,4,7,5,2,6,1,3}表示第几行，第几列
    public static void main(String[] args) {
//测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(count);//一共有92种解法

    }

    //编写一个方法，放置第n个皇后
    private void check(int n) {
        if (n == max) {//n=8时，8个皇后已经放好
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后n，放到该行第一列
            array[n]=i;
            //判断当放置第n个皇后到i列时,是否冲突
            if (judge(n)) {//不冲突
                //接着放第n+1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突,继续执行 array[n]=i;即将第n个皇后，放置在本行的后移一个位置
        }

    }
    //查看当我们放置第n个皇后的时候,就去检测该皇后是否和前面已经摆放的皇后是否冲突
    private boolean judge(int n) {//n表示第n个皇后
        for (int i = 0; i < n; i++) {
            //array[i] == array[n] ,表示判断第n个皇后是否和前面n-1个皇后在同一列
            // Math.abs(n-i)==Math.abs(array[n] - array[i]) ,表示判断n个皇后是否和第i个皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(n-i)==Math.abs(array[n] - array[i])) {
                return false;
            }
        }

        return true;
    }


    //写一个方法，将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
