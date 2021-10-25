package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    //p54
    //冒泡排序
    public static void main(String[] args) {
        int arr[] = {3, 8, 9, 10, -2};
        //第一趟排序,将最大排到最后面

//        for (int j = 0; j < arr.length - 1; j++) {
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//
//        }
//        System.out.println("第一趟排序" + Arrays.toString(arr));
//        //第二趟排序
//        for (int j = 0; j < arr.length - 2; j++) {
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//
//        }
//        System.out.println("第二趟排序" + Arrays.toString(arr));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间" + dateStr);
        bubbleSort(arr);
        Date date2 = new Date();

        String dateStr1 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间" + dateStr);


    }

    //将前面的冒泡排序方法封装成一个方法
    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;//标识变量，表示是否进行交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag == false) {//一次都没有交换过
                break;
            } else {
                flag = false;//重置flag,进行下次判断
            }
        }
        System.out.println(Arrays.toString(arr));

    }

}
