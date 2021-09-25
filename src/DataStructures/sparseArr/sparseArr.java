package DataStructures.sparseArr;
/*
 * 稀疏数组：
 * 二维数组转为稀疏数组，
 * 1.遍历原始数组，得到有效数据个数sum
 * 2.根据sum就可以创建稀疏数组sparseArr int[sum+1][3]
 * 3.将二维数组的有效数据存入到稀疏数组
 *
 *稀疏数组转为二维数组:
 * 1.读取稀疏数组的第一行数据，创建原始二维数组
 * 2.在读取稀疏数组后几行数据，并付给原始二维数组即可
 *
 * */
public class sparseArr {
    public static void main(String[] args) {
        //创建一个11*11的二维数组
        //0表示没有旗子，1表示黑，2表示蓝
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始数组
        System.out.println("原始二维数组~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t",data);

            }
            System.out.println();
        }
        //将二维数组转化为稀疏数组
        //1.遍历数组的得到非0的值
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);
        //2.创建对应稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到sparseArr中
        int count = 0;//计数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {

                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println("*********************************");
        System.out.println("得到的稀疏数组为");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();
        //稀疏数组返回二维数组
        //1.读取稀疏数组的第一行数据，创建原始二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.在读取稀疏数组后几行数据，并付给原始二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];

        }
        //输出恢复后的二维数组
        System.out.println("*************************");
        System.out.println("恢复后的二维数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t",data);

            }
            System.out.println();
        }

    }
}
