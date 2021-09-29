package Link_List;

import com.sun.source.tree.NewArrayTree;

import java.lang.module.FindException;
import java.util.Stack;

/*
 * 1.链表是一节点的方式存储
 * 2.每个节点包含data域，next域，指向下一个节点
 * 3.发现链表的各个节点不一定是连续存放
 * 4.链表分为带头节点的链表和没有头节点的链表，根据实际需要确定
 **************************************************************************************
 *添加（创建）
 * 1.先创建一个head头节点
 * 2.在链表后面添加一个节点
 *
 *
 * */
public class Single_LinkedListDemo {
    public static void main(String[] args) {
            //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "砂浆", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "无用", "无");
        HeroNode hero4 = new HeroNode(4, "流程", "豹子头");
        //创建链表、
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//
        singleLinkedList.add2ByOrder(hero1);
        singleLinkedList.add2ByOrder(hero4);
        singleLinkedList.add2ByOrder(hero2);
        singleLinkedList.add2ByOrder(hero3);
        singleLinkedList.list();

        //显示

        System.out.println("*******************************************");
        HeroNode heroNode = new HeroNode(2, "鲁冠", "qweqweq");
        singleLinkedList.update(heroNode);

        //显示
        singleLinkedList.list();
        System.out.println("*****************************************");
        //删除一个节点
        singleLinkedList.delete(1);
        singleLinkedList.delete(5);
        singleLinkedList.list();
        System.out.println(singleLinkedList.getLength(singleLinkedList.getHead()));
//得到倒数第k个节点
        HeroNode res = singleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 2);
        System.out.println(res);


        System.out.println("测试单链表反转");
        SingleLinkedList.reverseList(singleLinkedList.getHead());

       singleLinkedList.list();
        System.out.println("测试逆序打印单链表");
        singleLinkedList.reversePrint(singleLinkedList.getHead());

    }

}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当找不考虑编号顺序时
    // 1.找到当前链表的最后节点
    //2.将最后的节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp指向链表的最后
        //将最后的节点的next指向新的节点
        temp.next = heroNode;
    }

    //显示链表[遍历]
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移c
            temp = temp.next;
        }
    }

    //查找单链表的倒数第k个节点
    //1.接收head节点，同时接收一个index
    //2.index表示倒数第index个节点
    //3.先把链表从头到尾遍历,得到链表的总长度
    //4.得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
    //5.找到了，返回节点,否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //如果链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一次遍历得到节点个数
        int size = getLength(head);
        //第二次遍历size-index位置，就是我们倒数的第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义给辅助变量,for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表反转
    public static void reverseList(HeroNode head) {
        //如果链表为空，或只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义辅助指针，帮助我们遍历原来链表
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来链表，每遍历一个节点，将其取出，并放在reverseHead的后面
        while (cur != null) {
            next = cur.next;//先暂时保留当前节点的下一个节点
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;
            cur=next;//让next后移
        }
        //将head.next指向reverse.next，实现链表反转
        head.next = reverseHead.next;
    }

    //使用方式2，实现逆序打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压人栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;//先进后出``
        }
        //将节点打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());//stack先进后出
        }


    }



    //修改节点信息，根据no编号来修改，即no编号不能改
    //1.newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表

            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
        }
    }

    public void add2ByOrder(HeroNode heroNode) {
//因为单链表中。temp是位于前一个节点，否则不能插入
        HeroNode temp = head;
        boolean flag = false;//标志添加编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp就在链表最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temp的后面插入
                break;

            } else if (temp.next.no == heroNode.no) {//说明添加的编号已经存在
                flag = true;//说明编号存在

                break;

            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {//不能添加
            System.out.println("准备插入的编号为已经存在，不能加入," + heroNode.no);

        } else {
            //插入到temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //删除节点
    //1.head不能动，需要找到指向前一个节点的temp
    //2.
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {

            if (temp.next == null) {
                break;//到链表的最后
            }
            if (temp.next.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;

            }
            temp = temp.next;//temp后移
        }
        //判断flag是否为真
        if (flag) {
            //可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到要删除的%d\n", no);

        }

    }

    //方法：获取到单链表的节点个数（如果是带头结点的，不考虑头结点）
    /*
    返回有效节点个数

     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量,没有统计头结点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;

    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}