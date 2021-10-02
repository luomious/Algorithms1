package Stack;

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
        //先定义逆波兰表达式,后缀表达式
        //（3+4）x 5-6，  3 4 + 5 x 6 -
        //测试ut
        String suffixExpression = "30 4 + 5 x 6 -";
        //1.先将suffixExpression 放在ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

        List<String> list = getListString(suffixExpression);
        System.out.println(list);
        int res = calculate(list);
        System.out.println("计算的结果是=" + res);
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
}
