package huffmanCode;

import org.w3c.dom.NodeList;
//p123

import java.util.*;

//哈夫曼编码
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);//长度40

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果" + Arrays.toString(huffmanCodeBytes));

        //测试byteToBitString()方法





        //分布过程
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println(nodes);
//
//        //测试
//        System.out.println("哈夫曼树");
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
//        System.out.println("前序遍历");
//        huffmanTreeRoot.preOrder();
//        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
//        System.out.println("~生成的哈夫曼表" + huffmanCodes);
//
//        //测试
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
//

    }

    //完成数据的解压
    //思路
    //1.将huffmanCodeBytes[99, -12, 126, -113, -44, 89, -121, -89, 49, -6, 81, 12]转成二进制的字符串
    private static String byteToBitString(boolean flag, byte b) {
        //flag表示是否需要补高位，true表示要补高位，
        //这里没有考虑最后一位为负数的情况，需要改进,但是不确定

        //使用变量保存b
        int temp = b;//把b转成int

        //如果是正数，需要补高位
        if (flag) {
            temp |= 256;//按为或运算
        }

        String str = Integer.toBinaryString(temp);//返回二进制补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }

    //2.编写一个方法完成对压缩数据的解码
    //huffmanCodes哈夫曼编码表map
    //huffmanBytes哈夫曼编码得到的字节数组
    //返回原来的字符串对应的数组
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //先得到huffmanBytes对应的二进制的字符串,形如10101000010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            StringBuilder.append(byteToBitString(!flag, b);)
        }
        System.out.println(stringBuilder.toString());
        return null;
    }


    //使用一个方法将前面的方法封装起来，便于调用
    //输入原始字符串,输出哈夫曼编码
    private static byte[] huffmanZip(byte[] bytes) {
        //1.生成node节点
        List<Node> nodes = getNodes(bytes);
        //2根据node创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //3.对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //4.根据哈夫曼编码进行压缩,得到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;

    }


    //编写一个方法,将字符串对应的byte[]数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]
    //bytes原始字符串数组i like like like java do you like a java,返回压缩后的bytes[]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes将bytes转成哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("测试stringBuilder="+stringBuilder.toString());

        //将“0110001。。。”转成byte[]

        //统计返回byte[] huffmanBytes长度
        //一句话int len=(stringBuilder.length()+7)/8

        int len=0;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index=0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {//每8位对应一个byte
            String strBytes;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strBytes = stringBuilder.substring(i);
            } else {
                strBytes = stringBuilder.substring(i, i + 8);
            }



            //将strBytes转成一个byte，放到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strBytes, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //为了方便调用
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        } else {
            //处理root左子树
            getCodes(root.left, "", stringBuilder);
            //处理右子树
            getCodes(root.right, "", stringBuilder);
            return huffmanCode;
        }
    }
    //生成哈夫曼树对应的哈夫曼编码
    //思路
    //1.哈夫曼编码表存放在Map<Byte,String>形式 ,如 =01,97->100,d=11000,u=11001,e=1110,v=11011,i=101,y=11010,k=1111,l=000,o=0011
    static Map<Byte, String> huffmanCode = new HashMap<>();
    //2.在生成哈夫曼编码表示，需要拼接路径，对应StringBuilder存储每个叶子结点路径
    static StringBuilder stringBuilder = new StringBuilder();

    //将传入的node结点的所有叶子结点的所有哈夫曼编码得到，放到huffmanCode集合中
    //node,传入结点,code：路径，0表示左子结点，1表示右子结点，stringBuilder用于拼接路径
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder中
        stringBuilder1.append(code);
        if (node != null) {//如果node=null不处理
            //判断当前node是否为叶子结点
            if (node.data == null) {//非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder1);
                //向右递归
                getCodes(node.right, "1", stringBuilder1);
            } else {//说明是叶子结点
                //表示找到每个叶子结点的最后
                huffmanCode.put(node.data, stringBuilder1.toString());
            }

        }
    }


    //前序遍历方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空");
        }
    }

    private static List<Node> getNodes(byte[] bytes) {
        //1.创建ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes,统计每个byte出现的出现->map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {//Map还没有这个数据字符，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每个键值对转成一个Node对象，并加入到nodes集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }


    //通过List创建对应哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //取出第一和第二的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建新的二叉树,它的根节点没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将处理过的二叉树从nodes中移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //添加新的二叉树
            nodes.add(parent);
        }
        //nodes最后的节点就是哈夫曼树的根节点
        return nodes.get(0);
    }

}


//创建Node，放数据和权值
class Node implements  Comparable<Node>{
    Byte data;//存放数据本身,比如'a'=>97  ' '=>32
    int weight;//权值,表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        //从小到大排序
        return this.weight - node.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
