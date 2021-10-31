package search;

import java.lang.reflect.Array;
import java.util.ArrayList;

//二分查找,前提是该数组是有序的,顺序，逆序都可以
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89,1000,1000,1000, 1234};
//        int resultIndex = binarySearch(arr, 0, arr.length - 1, 88);

        ArrayList<Integer> resultIndex = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(resultIndex);

        //p80

    }

    //二分查找
    public static int binarySearch(int arr[], int left, int right, int findVal) {
        //当left>right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);

        }
        if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /*
    如果有多个重复的数
    思路：
    1.在找到mid值，不马上返回
    2.向mid索引值的左边扫描，将满足的元素下标加到集合ArrayList
    3.向mid索引值的右边扫描，将满足的元素下标加到集合ArrayList
    4。返回ArrayList

     */
    public static ArrayList<Integer> binarySearch2(int arr[], int left, int right, int findVal) {
        //当left>right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch2(arr, mid + 1, right, findVal);

        }
        if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            /*
             1.在找到mid值，不马上返回
             2.向mid索引值的左边扫描，将满足的元素下标加到集合ArrayList
             3.向mid索引值的右边扫描，将满足的元素下标加到集合ArrayList
             4。返回ArrayList

             */
            ArrayList<Integer> resIndexlist = new ArrayList<>();
            //向mid索引值的左边扫描，将满足的元素下标加到集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则,temp放到集合中
                resIndexlist.add(temp);
                temp -= 1;
            }
            resIndexlist.add(mid);
            //向mid索引值的右边扫描，将满足的元素下标加到集合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length || arr[temp] != findVal) {//退出
                    break;
                }
                //否则,temp放到集合中
                resIndexlist.add(temp);
                temp += 1;//右移

            }
            return resIndexlist;
        }

    }
}
