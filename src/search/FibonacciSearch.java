package search;

import java.util.Arrays;

//斐波那契算法
public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {

        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int res = fibSearch(arr, 89);
        System.out.println(res);
    }


    //因为后面mid=low+F(k-1)-1,需要使用斐波那契数列
    //非递归方法得到斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //使用非递归方式
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标k
        int mid = 0;//存放mid的值
        int f[] = fib();//获取斐波那契数列
        //获取斐波那契分割数值的下标k
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]值可能对于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a[]
        //不足的部分用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需求使用a数组最后填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //使用While循环处理，找到key
        while (low <= high) {//只要满足，就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//向数组左边查找
                high = mid - 1;
                //说明
                //1.全部元素=前面的元素+后面的元素
                //2.f[k]=f[k-1]+f[k-2]
                //因为前面有f[k-1]个元素，可以继续拆分f[k-1]=f[k-2]+f[k-3]
                k--;

            } else if (key > temp[mid]) {//向数组右边查找
                low = mid + 1;
                //说明为什么是k -= 2;
                //1.全部元素=前面的元素+后面的元素
                //2.f[k]=f[k-1]+f[k-2]
                //3.因为后面有f[k-2]个元素，可以继续拆分f[k-1]=f[k-3]+f[k-4]
                //4.在f[k-2]的前面进行查找k-=2
                //5.即下次循环mid=f[k-1-2]-1
                k -= 2;
            } else {//找到
                //需要确定,返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }

            }

        }
        return -1;
    }


}
