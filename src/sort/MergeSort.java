package sort;

//归并排序
//p71
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {
    public static void main(String[] args) {
        //p70
        int arr[] = new int[80];
        int temp[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前" + dateStr);

        mergeSort(arr, 0, arr.length - 1, temp);
        Date date2 = new Date();

        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后" +dateStr2);

        System.out.println("归并排序后的数组" + Arrays.toString(arr));
    }

    //分+合方法
    public static void mergeSort(int arr[], int left, int right, int temp[]) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid+1, right, temp);
            //到合并
            merge(arr, left, mid, right, temp);
        }
    }
    /*
    int arr[] 原始数组
    int left 左边有序序列的初始索引
    int mid  中间索引
    int right 右边索引
    int temp[] 中转数组
     */
    //合并的方法
    public static void merge(int arr[], int left, int mid, int right, int temp[]) {
        int i = left;//初始化,左边序列的初始索引
        int j = mid + 1;//初始化,右边序列的初始索引
        int t = 0;//指向temp的数组的当前索引

        //先把左右两边的数据规则填充到temp数组
        //直到左右两边的序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边的有序序列的当前元素，小于等于右边的有序序列的当前元素
            //即将左边的当前元素拷贝到temp数组
            //然后t++，i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {//反之，将右边序列的当前元素填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;

            }
        }

        //把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) {//左边的有序序列还有剩余的元素，就全部填充到temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {//右边的有序序列还有剩余的元素，就全部填充到temp中
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //把temp数组的元素拷贝到arr
        //注意，并不是每次拷贝所有
        t = 0;
        int tempLeft = left;//
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
