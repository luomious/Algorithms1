package sort;

import java.util.Arrays;
//p62
//插入排序
public class InsertSort {
    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1};
        insertSort(arr);

    }

    public static void insertSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1;//即arr[1]的前面的下标

            //给insert找到插入的位置
            //insertIndex >= 0,保证insertVal找插入位置时，不至于越界
            //insertVal < arr[insertIndex],表示待插入的数还没有找到插入位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                //将 arr[insertIndex]后移
                arr[insertIndex + 1] = arr[insertIndex];//arr[insertIndex]
                insertIndex--;

            }
            //判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
            //当退出循环，说明找到插入的位置，insertIndex+1


        }
        System.out.println(Arrays.toString(arr));

    }
}
