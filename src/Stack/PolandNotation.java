package Stack;

import java.lang.invoke.SwitchPoint;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 *
 *逆波兰计算器
 *
 *
 * */
public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式
        //因为直接对字符串操作不方便，因此先将字符串的中缀表达式转成对应的List
        //即"1+((2+3)x4)-5"=>ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式为"+infixExpressionList);

        List<String> parseSuffixExpressionList = parseSuffiExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应List"+parseSuffixExpressionList);
        //将得到的中缀表达式的List转成后缀表达式

        //先定义逆波兰表达式,后缀表达式
        //（3+4）x 5-6，  3 4 + 5 x 6 -
        //测试

        /*
        String suffixExpression = "30 4 + 5 x 6 -";
        //1.先将suffixExpression 放在ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

        List<String> list = getListString(suffixExpression);
        System.out.println(list);
        int res = calculate(list);
        System.out.println("计算的结果是=" + res);

         */
    }

    //将一个逆波兰表达式，将运算符和数据依次放在ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建给栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历bl
        for (String item : ls) {
            //这里使用正则表达式
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数运算，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("x")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }

        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }

public static  List<String > parseSuffiExpressionList(List<String> ls){
        //定义两个栈
    Stack<String> s1=new Stack<>();//符号栈
    //说明：因为s2这个栈，在整个转换过程中没有pop操作，后面还需要逆序输出，所以使用List<String> s2
//    Stack<String >s2=new Stack<>();//存储中间结果的栈s2
    List<String >s2=new ArrayList<>();//存储中间结果的栈s2

    //遍历ls
    for (String item : ls) {
        //如果是一个数，加入s2
        if (item.matches("\\d+")) {
            s2.add(item);
        } else if (item.equals("(")) {
            s1.push(item);

        }  else if (item.equals(")")) {
            //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，并将括号丢弃
            while (!s1.peek().equals("(")) {//peek()返回栈顶部元素
                s2.add(s1.pop());
            }
            s1.pop();//将(弹出栈
        } else {
            //当item的优先级小于等于栈顶的运算符，将s1的栈顶运算符弹出并加入s2中，再次s1中新栈的栈顶比较
            while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                s2.add(s1.pop());
            }
            s1.push(item);
        }
    }

        //将s1中剩余的运算符依次弹出并加入s2
    while (s1.size() != 0) {
        s2.add(s1.pop());
    }
    return s2;//存放到list中，因此按顺序输出就是波兰表达式



}
    //将中缀表达式转成后缀表达式对应的List
    //即"1+((2+3)x4)-5"==>ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
    public static List<String> toInfixExpressionList(String s) {//s为中缀表达式
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0;//指针，用于遍历中缀表达式字符串
        String str;//对多位数进行拼接
        char c;//每遍历一个字符，就放到c
        do {
            //如果c是一个非数字，就需要加入到ls中
            if (((c = s.charAt(i)) < 48) || ((c = s.charAt(i)) > 57)) {
                ls.add("" + c);
                i++;//i需要后移

            } else {//如果是一个数，需要考虑多位数
                str = "";//先将str设成""
                while (i < s.length() && ((c = s.charAt(i)) >= 48) && ((c = s.charAt(i)) <= 57)) {
                    str += c;//拼接
                    i++;

                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }
}

//编写一个类，Operation可以返回运算符的优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    //写一个方法，返回对应优先级数字
    public static int getValue(String operation) {
        int result=0;
        switch (operation) {
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }



}

