package Link_List;
/*
*
*
*
*
* */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "砂浆", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "岩王爷", "钟离");
        HeroNode2 hero4 = new HeroNode2(4, "雷电将军", "雷神");

        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();
        System.out.println("修改后链表");
        HeroNode2 newHeroNode = new HeroNode2(4, "芭芭拉", "奶妈");
        doubleLinkedList.update(newHeroNode);

        doubleLinkedList.list();
        doubleLinkedList.delete(1);
        System.out.println("删除后链表");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }


    //遍历双向链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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


    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
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
        //形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改一个节点的内容
    public void update(HeroNode2 newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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

    //从双向链表删除一个节点
    //说明：
    //1.对于双向链表，我们可以直接找到要删除的节点
    //2.找到后，自我删除即可

    public void delete(int no) {
        //判断当前节点是否为空
        if (head.next == null) {
            System.out.println("链表为空，不能删除");
            return;
        }
        HeroNode2 temp = head.next;//辅助变量
        boolean flag = false;//标志是否找到要删除的节点
        while (true) {

            if (temp == null) {
                break;//到链表的最后节点的最后
            }
            if (temp.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;

            }
            temp = temp.next;//temp后移
        }
        //判断flag是否为真
        if (flag) {
            //可以删除
            temp.pre.next = temp.next;
            //这里代码有问题
            //如果是最后一个节点不需要执行下面这句话
            if (temp.next != null) {
                temp.next.pre = temp.pre;

            }


        } else {
            System.out.printf("没有找到要删除的%d\n", no);

        }

    }


}

//定义HeroNode2，每个HeroNode就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向上一个节点
//构造器
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;

    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}