package by.makei.composite.util.impl;

import by.makei.composite.util.BitOperationUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BitOperationImpl implements BitOperationUtil {
    private static final BitOperationImpl instance = new BitOperationImpl();
    private static final Character ZERO = 48;
    private static final Character NINE = 57;

    private BitOperationImpl (){};

    public static BitOperationImpl getInstance(){
        return instance;
    }

    //TODO Maybe it should be just static with closed constructor and no instance?


    @Override
    public String parseAndCalculateBitOperation(String bitOperation) {
        List expression = expressionToRpn(bitOperation);
        return String.valueOf(rpnToAnswer(expression));
    }


    private List<String> expressionToRpn(String expression) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        //divide on tokens
        List<String> tokens = new ArrayList<>();

        int priority;
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            token = new StringBuilder();
            token.append(expression.charAt(i));
            //check if digit?
            if (expression.charAt(i) == '-' || expression.charAt(i) >= ZERO && expression.charAt(i) <= NINE) {
                while (i < expression.length()-1 && expression.charAt(i + 1) >= ZERO && expression.charAt(i + 1) <= NINE) {  //is digit?
                    token.append(expression.charAt(++i));
                }
            } else
                while (i < expression.length()-1 && token.charAt(0) != ')' && token.charAt(0) != '(' &&  token.charAt(0) == expression.charAt(i + 1)) {  //the same mark?
                    token.append(expression.charAt(++i));
                }
            priority = getPriority(token.toString());
            if (priority == 0) {
                tokens.add(token.toString());
            }
            if (priority == 1) {
                stack.push(token.toString());
            }
            if (priority > 1) {
                while (!stack.isEmpty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        tokens.add(stack.pop());
                    } else break;
                }
                stack.push(token.toString());
            }
            if (priority == -1) {
                while (getPriority(stack.peek()) != 1) {
                    tokens.add(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            tokens.add(stack.pop());
        }
        return tokens;
    }

    private int rpnToAnswer(List<String> rpn) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < rpn.size(); i++) {

            if (getPriority(rpn.get(i)) == 0) {
                    stack.push(Integer.parseInt(rpn.get(i)));
            }
            if (getPriority(rpn.get(i)) > 1) {
                String token = rpn.get(i);
                Integer b = stack.pop();
                if(token.equals("~")){
                    stack.push(~b);
                }else {
                    Integer a = stack.pop();
                    switch (token) {
                        case "<<" -> stack.push(a << b);
                        case ">>" -> stack.push(a >> b);
                        case ">>>" -> stack.push(a >>> b);
                        case "&" -> stack.push(a & b);
                        case "|" -> stack.push(a | b);
                        case "^" -> stack.push(a ^ b);
                    }
                }
            }
        }
        return stack.pop();
    }

    private int getPriority(String token) {
        //priority table https://javarush.ru/groups/posts/1925-pobitovihe-operacii
        switch (token){
            case "|" -> {return 2;}
            case "^" -> {return 3;}
            case "&" -> {return 4;}
            case ">>>" -> {return 5;}
            case ">>" -> {return 5;}
            case "<<" -> {return 5;}
            case "~" -> {return 6;}
            case "(" -> {return 1;}
            case ")" -> {return -1;}
            default -> {return 0;}
        }
    }

}
