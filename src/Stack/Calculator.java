package Stack;
/*
* 用栈来模拟计算器
*
*
* */
public class Calculator {
    public static void main(String[] args) {
    //根据思路完成表达式的运算
        String expression = "70+20*6-4";//186
        //创建数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";//用于拼接
        //开始循环扫描expression
        while (true) {
            //循环得到expression的每个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch,然后做相应处理
            if (operStack.isOper(ch)) {//如果是运算符
                //判断符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈不为空，比较优先级，如果当前符号优先级小于栈中操作符优先级，就需要从数栈pop出两个数，得到结果后将数据push数栈，符号进入oper栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //符号入符号栈
                        operStack.push(ch);

                    } else {
                        //如果当前操作符大于栈顶操作符，直接输入符号栈
                        operStack.push(ch);

                    }
                } else {
                    //符号栈为空,直接入栈
                    operStack.push(ch);


                }


            } else {//如果为数，直接入栈,此处存放为字符，需要转换为int
                //当发现一个数时可能是多位数，在处理数时，需要在expression的index后再看一位，如果是符号，则停止，并拼接数字

//                numStack.push(ch-48);//
                //处理多位数
                keepNum += ch;
                //如果ch为expression的最后一位，直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));

                } else{
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位为运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的！！！！,keepNum清空
                        keepNum = "";
                    }
                }
                    //判断下一位是否为数字，是则继续扫描，如果为运算符则入栈



            }
            //让index+1，判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序从符号栈和数栈中pop出对应的值，并计算
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有最后的结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);

        }
        //将数栈最后的数pop出来
        int res2 = numStack.pop();
        System.out.println(expression+"的结果是" + res2);
    }
}


//先创建一个栈，直接使用前面创建好的,需要扩展功能
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据放在数组
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //返回栈顶的值
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //判断是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈]，遍历时需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级,优先级是程序员决定的，优先级用数字表示，数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假设目前表达式只有+，-，*，/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res=0;//用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 -num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
