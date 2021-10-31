package search;

//线性查找
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};//没有顺序的数组
        int value = seqSearch(arr, 11);
        System.out.println(value);
    }

    public static int seqSearch(int arr[], int value) {
        //线性查找是逐一遍历,返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
