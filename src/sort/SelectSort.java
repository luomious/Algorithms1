package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    //p58
    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1,14,13123,12,14151,114};
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println(dateStr);
        selectSort(arr);
        Date date2 = new Date();

        String dateStr1 = simpleDateFormat.format(date2);
        System.out.println(dateStr1);
    }

    //选择排序
    public static void selectSort(int[] arr) {


        for (int i = 0; i < arr.length ; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;//找到最小数的下标
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];//交换两个值的大小
                arr[i] = min;
            }


        }
        System.out.println(Arrays.toString(arr));

    }
}
