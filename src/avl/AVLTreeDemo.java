package avl;


//p141
//二叉平衡树
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int arr[] = {4, 3, 6, 5, 7, 8};
//        int arr1[] = {10, 12, 8, 9, 7, 6};
        int arr[] = {10, 11, 7, 6, 8, 9};
        //创建avlTree
        AVLTree avlTree = new AVLTree();
        //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("没有平衡处理前");
        System.out.println("树的高度为" + avlTree.getRoot().height());//4
        System.out.println("树的左子树高度" + avlTree.getRoot().leftHeight());//1
        System.out.println("树的右子树高度" + avlTree.getRoot().rightHeight());//3
        System.out.println("当前根结点为" + avlTree.getRoot());

    }
}

//创建avlTree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return this.root;
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //编写方法
    //node,传入的结点(当做二叉树的根结点)
    //返回以node为根结点的二叉排序树的最小结点的值,删除以node为跟结点的二叉排序树的最小结点
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环查找左结点
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    //删除结点的方法
    public void delNode(int value) {
        if (root != null) {
            //1.查找要删除的结点
            Node targetNode = this.search(value);
            //如果没有找到
            if (targetNode != null) {
                //如果发现targetNode没有父结点
                if (this.root.left == null && this.root.right == null) {
                    this.root = null;
                } else {
                    //找到targetNode的父结点
                    Node parent = this.searchParent(value);
                    //1.如果删除的结点是叶子结点
                    if (targetNode.left == null && targetNode.right == null) {
                        if (parent != null) {

                            //判断targetNode是父结点的左子结点还是右子结点
                            if (parent.left != null && parent.left.value == value) {
                                parent.left = null;
                            } else if (parent.right != null && parent.right.value == value) {
                                parent.right = null;
                            }
                        }

                    } //2.删除有两颗子树结点
                    else if (targetNode.left != null && targetNode.right != null) {
                        int minVal = delRightTreeMin(targetNode.right);
                        targetNode.value = minVal;//注意这里只是改变结点值,所以遍历时可能出现两次
                    } else {//3.删除只有一个子树结点
                        //如果要删除的结点有左子结点
                        if (targetNode.left != null && targetNode.right == null) {
                            //如果targetNode是parent的左子结点
                            if (parent != null) {
                                if (parent.left.value == value) {
                                    parent.left = targetNode.left;
                                } else {
                                    //targetNode是parent的右子结点
                                    if (parent.right.value == value) {
                                        parent.right = targetNode.left;
                                    }
                                }
                            } else {
                                this.root = targetNode.left;
                            }
                        }
                        //如果要删除的结点有右子结点
                        else if (targetNode.right != null && targetNode.left == null) {
                            if (parent != null) {
                                if (parent.left.value == value) {
                                    parent.left = targetNode.right;
                                } else {//targetNode是parent的右子结点
                                    parent.right = targetNode.right;
                                }
                            } else {
                                this.root = targetNode.right;
                            }
                        }
                    }
                }
            }
        }

    }
    //添加结点的方法
    public void add(Node node) {

        if (root == null) {
            root = node;
        } else {
            root.add(node);

        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");

        }
    }
}


//创建结点
class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回当前结点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转的方法
    private void leftRotate() {

        //创建新的结点,以当前结点为根结点创建
        Node newNode = new Node(value);
        //把新的结点左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点右子树设置成当前结点的右子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子结点设置成新的结点
        left = newNode;


    }

    //右旋转
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    //查找要删除的结点
    public Node search(int value) {//value表示要删除的值
        if (value == this.value) {
            return this;
        } else if (value < this.value) {//小于当前结点,向左查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//不小于当前结点
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);

        }


    }

    //查找要删除结点的父结点,返回查找的父结点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找值小于当前结点值，并且当前结点的值的左结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左递归
            } else if (value >= this.right.value && this.right != null) {
                return this.right.searchParent(value);//向右递归
            } else {
                return null;
            }
        }

    }

    //添加结点的方法
    //递归添加
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的结点的值和当前根结点的值比较
        if (node.value < this.value) {//小于根结点的值
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {//大于等于当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //向右递归
                this.right.add(node);
            }

        }
        //当添加完结点后，如果右子树高度-左子树高度 > 1,左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度对于右子树的右子树高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对当前结点的左结点进行左旋转
                rightRotate();

                leftRotate();//左旋转
            } else {
                //直接左旋转
                leftRotate();
            }

            return;//必须要
        }
        //当添加完结点后，如果左子树高度-右子树高度 > 1,左旋转
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点进行左旋转
                left.leftRotate();
                rightRotate();//左旋转
            } else {
                rightRotate();//左旋转
            }
        }

        }




    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

