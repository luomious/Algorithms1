package huffmantree;

//p114

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//哈夫曼树
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);

        //测试
        preOrder(root);

    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();

        } else {
            System.out.println("是空树");

        }
    }

    //创建哈夫曼树的方法,返回创建哈夫曼树的根节点
    public static Node createHuffmanTree(int arr[]) {
        //第一步，为了操作方便
        //1.遍历arr数组
        //2.将arr的每个元素构建成一个node
        //3.将node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //排序从小到大
            Collections.sort(nodes);

            System.out.println("nodes=" + nodes);

            //取出权值最小的结点（两颗二叉树）
            Node leftNode = nodes.get(0);
            //取出权值第二小的结点
            Node rightNode = nodes.get(1);

            //构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入nodes
            nodes.add(parent);
        }

        //返回哈夫曼的root结点
        return nodes.get(0);




    }
}

//创建结点类
//为了让Node对象持续排序Collection集合排序
class Node implements Comparable<Node> {
    int value;//结点权值
    char c;//
    Node left;//指向左子结点
    Node right;//指向右子结点

    public Node(int value) {
        this.value = value;
    }

    //写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    @Override
    public String toString() {
        return value+"" ;
    }

    @Override
    public int compareTo(Node node) {
        //表示从小到大排序
        return this.value - node.value;
    }
}