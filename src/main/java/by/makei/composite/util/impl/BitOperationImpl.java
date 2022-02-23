package by.makei.composite.util.impl;

import by.makei.composite.util.BitOperationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BitOperationImpl implements BitOperationUtil {

    //TODO on working
    //https://javarush.ru/groups/posts/1925-pobitovihe-operacii

    @Override
    public String parseAndCalculateBitOperation(String bitOperation) {

        return null;
    }

    public static void main(String[] args) {
        String test = "((1+2)-3)*3-(3+1)";
        System.out.println(expressionToRpn(test));
        System.out.println(rpnToAnswer(expressionToRpn(test)));
    }

    private static String expressionToRpn(String expression) {
        String current = "";
        Stack<Character> stack = new Stack<>();

        //divide on tokens

        List<String> tokens = new ArrayList<>();
        StringBuffer token;

       
        int priority;
        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));

            if (priority == 0) {
                current += expression.charAt(i);
            }
            if (priority == 1) {
                stack.push(expression.charAt(i));
            }

            if (priority > 1) {
                current += ' ';
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        current += stack.pop();
                    } else break;
                }
                stack.push(expression.charAt(i));
            }

            if (priority == -1) {
                current += ' ';
                while (getPriority(stack.peek()) != 1) {
                    current += stack.pop();
                }
                stack.pop();

            }
        }
        while (!stack.empty()) {
            current += stack.pop();

        }
        return current;
    }

    private static double rpnToAnswer(String rpn) {

        String operand = "";
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) {
                        break;
                    }
                    stack.push(Double.parseDouble(operand));
                    operand = "";
                }
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                double a = stack.pop();
                double b = stack.pop();

                if(rpn.charAt(i) == '+'){stack.push(a+b);}
                if(rpn.charAt(i) == '-'){stack.push(a-b);}
                if(rpn.charAt(i) == '*'){stack.push(a*b);}
                if(rpn.charAt(i) == '/'){stack.push(a/b);}

            }
        }


        return stack.pop();
    }

    private static int getPriority(char token) {
        if (token == '*' || token == '/') {
            return 3;
        } else if (token == '+' || token == '-') {
            return 2;
        } else if (token == '(') {
            return 1;
        } else if (token == ')') {
            return -1;
        } else {
            return 0;
        }
    }
}
