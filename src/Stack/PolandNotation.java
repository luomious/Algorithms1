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
        //完成将一个中缀表达式转成后缀表达式
        //因为直接对字符串操作不方便，因此先将字符串的中缀表达式转成对应的List
        String expression = "1+((2+3)x4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
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


    //将中缀表达式转成后缀表达式对应的List
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
