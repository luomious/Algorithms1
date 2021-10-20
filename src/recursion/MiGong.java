package recursion;

import java.util.Map;

/*
递归算法
 */
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map=new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i]=1;
            map[7][i]=1;

        }
        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;

        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
        //输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
     //使用递归回溯
        setWay2(map, 1, 1);
        //输出新地图,小球走过
        System.out.println("小球走过并标的地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
    }
//使用递归回溯给小球找路
    //说明：map表示地图，i，j表示从地图什么地方出发（1,1）;如果小球能到map[6][5]位置，则说明通路找到，
    // 当map[i][j]=0时表示没有走过，1表示墙，2表示通路可以走，3表示可以走，但是走不通
    //在走迷宫时，要确定一个策略下-右-上-左，如果该点走不通，再回溯

    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已找到
            return true;
        } else {
            if (map[i][j] == 0) {//当前这个点还没有走过
                //按照下-右-上-左走
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左左
                    return true;
                } else {
                    //说明是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j]!=0,可能是1，2,3
                return false;

            }
        }


    }

    //修改找路策略
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已找到
            return true;
        } else {
            if (map[i][j] == 0) {//当前这个点还没有走过
                //按照上-右-下-左走
                map[i][j] = 2;//假定该点可以走通
                if (setWay2(map, i -1, j)) {//向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay2(map, i +1, j)) {//向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j]!=0,可能是1，2,3
                return false;

            }
        }


    }
    //最短路径问题
}
