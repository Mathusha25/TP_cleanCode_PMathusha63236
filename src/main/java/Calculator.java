import java.util.Stack;

public class Calculator {
    public double evaluateMathExpression(String expression) {
        // Initial condition that will allow to check entry
        if (expression ==null || expression.trim().isEmpty()){
            throw new IllegalArgumentException("Expression should contain something.");
        }

        //Removing spaces
        expression =expression.replaceAll("\\s+","");

        //Initialisation Variables
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();

        //Read the expression character by character
        for (int i=0;i<expression.length();i++) {
            char c = expression.charAt(i);

            //If it is a number or decimal point
            StringBuilder numBuilder = null;
            if (Character.isDigit(c) || c == '.') {
                numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i))) || expression.charAt(i) == '.') {
                    numBuilder.append(expression.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(numBuilder.toString()));
            }

            //If there is opening brackets
            else if (c=='(') {
                operations.push(c);
            }

            //If there is closing brackets
            else if (c==')') {
                while (operations.peek() != '('){
                    numbers.push(applyOperation(operations.pop(), numbers.pop(),numbers.pop()));
                }
                operations.pop();
            } else if (isOperator(c)) {
                while (!operations.isEmpty() && hasPrecedence(c,operations.peek())){
                    numbers.push(applyOperation(operations.pop(),numbers.pop(),numbers.pop()));
                }
                operations.push(c);
            }
        }

        while (!operations.isEmpty()){
            numbers.push(applyOperation(operations.pop(),numbers.pop(),numbers.pop()));
        }

        if (numbers.size() != 1){
            throw new IllegalArgumentException("Expression invalide");
        }
        return numbers.pop();
    }

    private boolean isOperator(char c){
        return c=='+' || c=='-' || c=='*' || c=='/'  ;
    }

    private boolean hasPrecedence(char op1, char op2){
        if (op2 =='(' || op2 ==')'){
            return false;
        }
        if ((op1 == '*' || op1 =='/') && (op2 == '+' || op2 == '-')) {return false;
        return true;
    }

    private double applyOperation(char op, double  a, double b){
        switch (op){
            case '+' : return a + b;
            case '-' : return a - b;
            case '*' : return a * b;
            case '/' :
                if (b==0) {throw new ArithmeticException("Attention division par 0");}
                return a/b;
            default: throw new IllegalArgumentException("Inconnu : "+ op);
        }
    }
}
