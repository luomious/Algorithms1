package Link_List;
/*
* 构建一个单向环形链表
* 1.先创建第一个节点，让first指向该节点，并形成环形
* 2.后面当我们创建一个新的节点，就把该节点加入已有环形链表即可
* 遍历环形链表
* 1.先让一个辅助指针curBoy，指向first节点
* 2.然后通过一个while循环遍历该环形链表即可curBoy=first结束
*
*
*
*
*
*
*
* */
public class Josepfu {
    public static void main(String[] args) {
        //测试环形链表遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        System.out.println("****************************");
        //测试小孩出圈
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

//创建一个环形单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点构建一个环形链表
    public void addBoy(int nums) {
        //nus做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让 curBoy 指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }


        }
    }

    //遍历当前环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，我们需要使用辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号为%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完
                break;

            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入，计算小孩出圈的顺序
    public void countBoy(int startNo,int countNum, int nums) {//startNo表示从第几个小孩开始，countNums表示数几下,num表示多少小孩在圈中
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums || startNo > nums) {
            System.out.println("输入有误，请重新输入");
            return;
        }
        //创建辅助指针
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后小孩节点
                break;

            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出去
        //进行循环操作，直到只有一个节点
        while (true) {
            if (helper == first) {//说明圈中只有一个节点
                break;

            }
            //让first和helper指针同时移动countNum-1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是出圈的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.println("最后留在圈中的小孩编号为" + first.getNo());


    }
}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
