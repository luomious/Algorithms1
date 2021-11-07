package huffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCodeModule{
    static Map<Byte, String> huffmanCodes = new HashMap();
    static StringBuilder stringBuilder = new StringBuilder();

    public HuffmanCodeModule() {
    }

    public static void main(String[] args) {
        String zipFile = "d://Uninstall.zip";
        String dstFile = "d://Uninstall2.xml";
        unZipFile(zipFile, dstFile);
        System.out.println("解压成功!");
    }

    public static void unZipFile(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;

        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[])ois.readObject();
            Map<Byte, String> huffmanCodes = (Map)ois.readObject();
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception var16) {
            System.out.println(var16.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception var15) {
                System.out.println(var15.getMessage());
            }

        }

    }

    public static void zipFile(String srcFile, String dstFile) {
        OutputStream os = null;
        ObjectOutputStream oos = null;
        FileInputStream is = null;

        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);
        } catch (Exception var15) {
            System.out.println(var15.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception var14) {
                System.out.println(var14.getMessage());
            }

        }

    }

    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < huffmanBytes.length; ++i) {
            byte b = huffmanBytes[i];
            boolean flag = i == huffmanBytes.length - 1;
            stringBuilder.append(byteToBitString(!flag, b));
        }

        Map<String, Byte> map = new HashMap();
        Iterator var13 = huffmanCodes.entrySet().iterator();

        while(var13.hasNext()) {
            Map.Entry<Byte, String> entry = (Map.Entry)var13.next();
            map.put((String)entry.getValue(), (Byte)entry.getKey());
        }

        List<Byte> list = new ArrayList();


        for(int i = 0; i < stringBuilder.length(); i += i) {
            i = 1;
            boolean flag = true;
            Byte b = null;

            while(flag) {
                String key = stringBuilder.substring(i, i + i);
                b = (Byte)map.get(key);
                if (b == null) {
                    ++i;
                } else {
                    flag = false;
                }
            }

            list.add(b);
        }

        byte[] b = new byte[list.size()];

        for(int i = 0; i < b.length; ++i) {
            b[i] = (Byte)list.get(i);
        }

        return b;
    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag || temp <0) {
            temp = b | 256;
        }

        String str = Integer.toBinaryString(temp);
        return flag ? str.substring(str.length() - 8) : str;
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] var6 = bytes;
        int index = bytes.length;

        for(int var4 = 0; var4 < index; ++var4) {
            byte b = var6[var4];
            stringBuilder.append((String)huffmanCodes.get(b));
        }

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanCodeBytes = new byte[len];
        index = 0;

        for(int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            ++index;
        }

        return huffmanCodeBytes;
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        } else {
            getCodes(root.left, "0", stringBuilder);
            getCodes(root.right, "1", stringBuilder);
            return huffmanCodes;
        }
    }

    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }

    }

    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList();
        Map<Byte, Integer> counts = new HashMap();
        byte[] var6 = bytes;
        int var5 = bytes.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            byte b = var6[var4];
            Integer count = (Integer)counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        Iterator var9 = counts.entrySet().iterator();

        while(var9.hasNext()) {
            Map.Entry<Byte, Integer> entry = (Map.Entry)var9.next();
            nodes.add(new Node((Byte)entry.getKey(), (Integer)entry.getValue()));
        }

        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while(nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = (Node)nodes.get(0);
            Node rightNode = (Node)nodes.get(1);
            Node parent = new Node((Byte)null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }

        return (Node)nodes.get(0);
    }
}

class Node1 implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node1(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public String toString() {
        return "Node [data = " + this.data + " weight=" + this.weight + "]";
    }

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
