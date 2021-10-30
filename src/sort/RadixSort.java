package sort;

//基数排序,取对应位数放入数组进行排序,是稳定的排序，是桶排序的扩展,最好不要有负数

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        //数组长度要注意，否则会cpu运行内存不足
        int arr[] = {53, 3, 542, 748, 14, 214};

        radixSort1(arr);

    }

    //基数排序的方法
    public static void radixSort(int arr[]) {
        //第一轮排序，针对每个元素的个位排序

        //定义一个二维数组，表示10个桶,每个桶就是一维数组
        //二维数组包含10个一维数组
        //为了防止在放数的时候数据溢出，则每个一维数组大小为arr.length
        //基数排序是使用空间换时间的算法
        int[][] bucket = new int[10][arr.length];//length太大，容易产生内存不足

        //为了记录每个桶实际存放了多少个数据，我们定义一个一维数组来记录每个桶放入的数据个数
        int[] bucketElementCounts = new int[10];//记录bucket每个桶的数据个数
        //对元素个位进行排序
        for (int j = 0; j < arr.length; j++) {
            //取出元素的个位
            int digitOfElement = arr[j] % 10;
            //放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;//记录每个桶中的数据个数，+1
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组)
        int index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入原数组
            if (bucketElementCounts[k] != 0) {
                //循环第k个桶
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }

            }
            //每取一次将 bucketElementCounts[k]清0!!!!!!
            bucketElementCounts[k] = 0;
        }


        System.out.println("第一轮结果"+Arrays.toString(arr));

        //第二轮
        for (int j = 0; j < arr.length; j++) {
            //取出元素的十位
            int digitOfElement = arr[j] / 10 % 10;
            //放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;//记录每个桶中的数据个数，+1
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组)
        index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入原数组
            if (bucketElementCounts[k] != 0) {
                //循环第k个桶
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }

            }
            //每取一次将 bucketElementCounts[k]清0!!!!!!
            bucketElementCounts[k] = 0;
        }


        System.out.println("第二轮结果"+Arrays.toString(arr));


    }
    public static void radixSort1(int arr[]) {
        //最终的基数排序
        //1.得到数组中最大的数的位数
        int max = arr[0];//假设第一个数是最大的数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + " ").length();


        //定义一个二维数组，表示10个桶,每个桶就是一维数组
        //二维数组包含10个一维数组
        //为了防止在放数的时候数据溢出，则每个一维数组大小为arr.length
        //基数排序是使用空间换时间的算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶实际存放了多少个数据，我们定义一个一维数组来记录每个桶放入的数据个数
        int[] bucketElementCounts = new int[10];//记录bucket每个桶的数据个数
        int index = 0;

        //使用循环代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            for (int j = 0; j < arr.length; j++) {
                //取出元素的对应位数
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;//记录每个桶中的数据个数，+1
            }
            //对元素对应位数进行排序
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组)
            index = 0;
            //遍历每一个桶，并将桶中的数据放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，才放入原数组
                if (bucketElementCounts[k] != 0) {
                    //循环第k个桶
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }

                }
                bucketElementCounts[k] = 0;
            }
            System.out.println("第"+i+"轮"+Arrays.toString(arr));
        }




        System.out.println("最终排序结果"+Arrays.toString(arr));


    }

}
