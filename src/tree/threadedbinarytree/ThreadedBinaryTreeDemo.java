package tree.threadedbinarytree;
//p107
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
    //测试中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom1");
        HeroNode node2 = new HeroNode(3, "tom3");
        HeroNode node3 = new HeroNode(6, "tom6");
        HeroNode node4 = new HeroNode(8, "tom8");
        HeroNode node5 = new HeroNode(10, "tom10");
        HeroNode node6 = new HeroNode(14, "tom14");

        //二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试
        HeroNode leftNode = node5.getLeft();
        System.out.println(leftNode);

        //当线索化二叉树后，不能使用用来的遍历方法

        System.out.println("使用线索化的方式遍历二叉树");
        threadedBinaryTree.threadedList();

    }
}

//定义ThreadedBinaryTree实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化，需要创建当前节点的前驱结点的指针
    //在递归进行线索化时，保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //遍历中序线索化二叉树方法
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null) {
            //循环找到leftType==1的节点，第一个找到的节点，后面随着遍历的遍历而变化
            //当leftType=1的时候，说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继结点
            while (node.getRightType() == 1) {
                //获取当前节点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替換遍历节点
            node = node.getRight();
        }
    }

    //重载
    public void threadedNodes() {
        this.threadedNodes(root);

    }

    //编写对二叉树进行线索化的方法
    public void threadedNodes(HeroNode node) {
        //如果当前节点为空
        if (node == null) {
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前指针的左指针类型，指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);

        }
        //每处理一个节点后，让当前节点时下一个节点的前驱结点
        pre = node;
        //线索化右子树
        threadedNodes(node.getRight());

    }

    //删除结点
    public void delNode(int no) {
        if (root != null) {
            //先判断root是不是要删除的结点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");

        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return  root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//创建HeroNode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    private int leftType;//leftType=0,表示指向左子树，如果是1表示指向前驱结点
    private int rightType;//rightType=0，表示指向右子树，如果是1表示指向后继结点

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    public void delNode(int no) {

        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //向左递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //前序遍历
    public void preOrder() {

        System.out.println(this);
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {

        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();

        }
    }

    //后序遍历
    public void postOrder() {

        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();

        }
        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {

        System.out.println("进入查找");
        //先比较当前节点
        if (this.no == no) {
            return this;
        }
        //判断当前左子节点是否为空，不为空则递归前序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明左子树上找到了
            return resNode;
        }
        //判断当前右子节点，向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {

        //先判断左子节点
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //
        System.out.println("进入查找");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入查找");
        //如果左右都没有找到，比较当前节点
        if (this.no == no) {
            return this;
        }

        return resNode;
    }
}

