package hashtab;

import javafx.css.Size;

import java.util.Scanner;
//89
//哈希表
public class HashTabDemo {
    public static void main(String[] args) {
    //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单菜单
        String key = " ";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.out.println(0);
                default:
                    break;
            }
        }


    }
}

//创建HashTab管理多链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;//表示有多少条链表

    public HashTab(int size) {
        //初始化empLinkedListArray
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //不要忘了分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }

    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工id，得到该员工应当添加到哪个链表
        int empLinkedKListNo = hashFun(emp.id);
        //将emp添加到对应链表中
        empLinkedListArray[empLinkedKListNo].add(emp);

    }

    //编写一个散列函数,使用简单的取模法
    public int hashFun(int id) {
        return id % size;
    }

    //遍历hashtab
    public void list() {
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].list();
        }
    }

}
//表示雇员
class Emp {
    public int id;
    public String name;
    public Emp next;//默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;

    }
}

//创建一个EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针,执行第一个Emp，因此这个链表的head是直接指向第一个Emp
    private Emp head;//默认为null

    //添加雇员到链表
    //说明
    //假定id是自动增长的,即直接加入本链表的最后即可
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {//说明到链表最后
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时直接将emp加入链表
        curEmp.next = emp;
    }


    //遍历链表雇员信息
    public void list(int no) {
        if (head == null) {//说明链表为空
            System.out.println("第" + no + "链表为空");
            return;
        }
        System.out.println("当前链表信息为");
        Emp curEmpp = head;//辅助指针
        while (true) {
            System.out.printf("=>id=%d name=%s\t",curEmpp.id,curEmpp.name);
            if (curEmpp.name == null) {//说明curEmp已经到最后节点
                break;
            }
            curEmpp = curEmpp.next;
        }
        System.out.println();
    }
}
