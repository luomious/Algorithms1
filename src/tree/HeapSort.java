package tree;

import java.util.Arrays;

//p110
//堆排序,前提是完全二叉树,大顶堆
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排列
        int arr[] = {4, 6, 8, 5, 9, -1, 90, 89, 56, -999, 10};
        heapSort(arr);
    }

    public static void heapSort(int arr[]) {
        int temp = 0;
//        System.out.println("堆排序！！!");
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次" + Arrays.toString(arr));
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第二次" + Arrays.toString(arr));
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //将堆顶元素与末尾元素交换，重新调整结构，使其满足堆定义，然后继续交换堆顶元素与末尾元素，反复执行
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println("数组" + Arrays.toString(arr));

    }


    //完成的功能：将以i对应的叶子节点的数调整成大顶堆
    //将一个数组(二叉树),调整成大顶堆
    public static void adjustHeap(int arr[], int i, int length) {//i表示非叶子节点的索引,length对多少个元素进行调整，length在逐渐减少
        int temp = arr[i];
        //开始调整
        //k = i * 2 + 1指向i的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子结点小于右子结点
                k++;///k指向右子节点
            }
            if (arr[k] > temp) {//如果子节点大于父节点
                arr[i] = arr[k];//把大的值赋值给当前节点
                i = k;           //i指向k，继续循环比较
            } else {
                break;//!!
            }
        }
        //当for循环结束后，以i为父节点的树的最大值，放在了堆顶
        arr[i] = temp;//将temp值放到调整后的位置
    }

}
