package binarysorttree;
//p133
//二叉排序树
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int arr[] = {7, 3, 10, 12, 5, 1, 9, 0};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        binarySortTree.infixOrder();
        //测试删除叶子结点
        binarySortTree.delNode(7);
        System.out.println("删除后");
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
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
                    //如果删除的结点是叶子结点
                    if (targetNode.left == null && targetNode.right == null) {
                        if (parent != null) {

                            //判断targetNode是父结点的左子结点还是右子结点
                            if (parent.left != null && parent.left.value == value) {
                                parent.left = null;
                            } else if (parent.right != null && parent.right.value == value) {
                                parent.right = null;
                            }
                        }

                    } else if (targetNode.left != null && targetNode.right != null) {//删除有两颗子树结点
                        int minVal = delRightTreeMin(targetNode.right);
                        targetNode.value = minVal;//注意这里只是改变结点值,所以遍历时可能出现两次
                    } else {//删除只有一个子树结点
                        //如果要删除的结点有左子结点
                        if (targetNode.left != null && targetNode.right == null) {

                            //如果targetNode是parent的左子结点
                            if (parent != null) {
                                if (parent.left.value == value) {
                                    parent.left = targetNode.left;
                                } else {//targetNode是parent的右子结点
                                    if (parent.right.value == value) {
                                        parent.right = targetNode.left;
                                    }

                                }
                            } else {
                                this.root = targetNode.left;
                            }

                        } else if (targetNode.right != null && targetNode.left == null) {

                            if (parent != null) {
                                if (parent.left.value == value) {
                                    parent.left = targetNode.right;
                                } else {//targetNode是parent的右子结点
                                    parent.right = targetNode.right;
                                }
                            }
                            else {
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

//模板
class BinarySortTree1 {
    private Node root;

    BinarySortTree1() {
    }

    public Node getRoot() {
        return this.root;
    }

    public Node search(int value) {
        return this.root == null ? null : this.root.search(value);
    }

    public Node searchParent(int value) {
        return this.root == null ? null : this.root.searchParent(value);
    }

    public int delRightTreeMin(Node node) {
        Node target;
        for(target = node; target.left != null; target = target.left) {
        }

        this.delNode(target.value);
        return target.value;
    }

    public void delNode(int value) {
        if (this.root != null) {
            Node targetNode = this.search(value);
            if (targetNode != null) {
                if (this.root.left == null && this.root.right == null) {
                    this.root = null;
                } else {
                    Node parent = this.searchParent(value);
                    if (targetNode.left == null && targetNode.right == null) {
                        if (parent.left != null && parent.left.value == value) {
                            parent.left = null;
                        } else if (parent.right != null && parent.right.value == value) {
                            parent.right = null;
                        }
                    } else if (targetNode.left != null && targetNode.right != null) {
                        int minVal = this.delRightTreeMin(targetNode.right);
                        targetNode.value = minVal;
                    } else if (targetNode.left != null) {
                        if (parent != null) {
                            if (parent.left.value == value) {
                                parent.left = targetNode.left;
                            } else {
                                parent.right = targetNode.left;
                            }
                        } else {
                            this.root = targetNode.left;
                        }
                    } else if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        this.root = targetNode.right;
                    }

                }
            }
        }
    }

    public void add(Node node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }

    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }

    }
}

