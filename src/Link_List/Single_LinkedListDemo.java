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
        singleLinkedList.list();

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
            //将temp后移c
            temp = temp.next;
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
            System.out.printf("要删除的%d\n", no);

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
                '}';
    }
}