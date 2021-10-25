package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

//希尔排序,对插入排序的改进
public class ShellSort {
    public static void main(String[] args) {
        int[] arr=new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前" + dateStr);

        shellSort2(arr);

        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后" +dateStr2);
    }
//交换式希尔排序
    public static void ShellSort(int arr[]) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历个组中的所有元素（共5组，每组两个元素），步长为5
                for (int j = i - gap; j >= 0; j -= gap) {

                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;

                    }
                }
            }
//            System.out.println("希尔排序中的数组" + Arrays.toString(arr));
        }

        //希尔排序第一轮
        //因为第一轮排序将10个数据分成5组
//        for (int i = 5; i < arr.length; i++) {
        //第二轮
//        for (int i = 2; i < arr.length; i++) {
        //第三轮
//        for (int i = 1; i < arr.length; i++) {
//            //遍历个组中的所有元素（共5组，每组两个元素），步长为5
//            for (int j = i - 1; j >= 0; j -= 1) {
//                //如果当前元素大于加上步长后的那个元素，声说明交换
//                if (arr[j] > arr[j + 1]) {
//                     temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//
//                }
//            }
//        }
        System.out.println("希尔排序后的数组" + Arrays.toString(arr));
    }

    //移位式希尔排序
    public static void shellSort2(int arr[]) {
        //增量gap,并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环后，就把temp的值给插入的位置
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }

}
