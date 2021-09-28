package Link_List;

import com.sun.source.tree.NewArrayTree;

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

    }

}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

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
            //将temp后移
            temp = temp.next;
        }
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
                ", next=" + next +
                '}';
    }
}