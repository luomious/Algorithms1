package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
//p153
//图
public class Graph {
    private ArrayList<String> vertexList;//存储顶点
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目

    //定义数组boolean[],记录某个结点是否被访问
    private boolean[] isVisited ;
    public static void main(String[] args) {
        //测试
        int n = 8;//结点个数
//        String Vertex[] = {"A", "B", "C", "D", "E"};
        String Vertex[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String Vertexvalue : Vertex) {
            graph.insertVertex(Vertexvalue);
        }
        //添加边
        //AB,AC,BC,BD,BE
//        graph.insertEdge(0, 1, 1);//AB
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);



        //显示
        graph.showGraph();
        //测试dfs
        System.out.println("深度遍历");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先");
        graph.bfs();

    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;

    }

    //得到第一个邻接结点的下标v
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] >0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i可以看做A,B,C,D,E
    public void dfs(boolean[] isVisited, int i) {
        //首先访问该结点,输出
        System.out.print(getValueByIndex(i) + "->");
        //将该结点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点
        int w = getFirstNeighbor(i);
        while (w != -1) {//说明存在
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w结点被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载,遍历所有结点进行dfs
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有结点,进行dfs（回溯）
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //对一个节点进行广度优先遍历方法
    private void bfs(boolean[] isVisited,int i) {
        int u;//表示队列的头结点
        int w;//邻接结点的下标
        //需要一个队列,记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.add(i);
        while (!queue.isEmpty()) {
            //取出队列头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标
            w = getFirstNeighbor(u);
            while (w != -1) {//说明找到
                //判断是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已访问过
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //访问过,以u为前驱点，找w后面的下一个邻接结点
                w = getNextNeighbor(u, w);
            }
        }

    }

    //遍历所有的结点，都进行广度优先
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }




    //常用方法
    //返回结点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i(下标)对应的数据0-"A"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int link[] : edges) {
            System.out.println(Arrays.toString(link));

        }
    }

    //插入结点
    public void insertVertex(String vertex) {

        vertexList.add(vertex);
    }

    //添加边
    //v1，v2表示点的下标,就是第几个顶点"A"-"B"
    //weight表示结点之间是否连接
    public void insertEdge(int v1, int v2, int weight) {

        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;

    }
}
