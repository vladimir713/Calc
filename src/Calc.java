import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
class Calc {
    public static String calc(String input) {
         int a = 1;
         int b = 1;
         String action = "";
         boolean running = true;
         boolean arab_rome = true;
         String res = "";
         String action_reg = "[\\+\\-\\*\\/]";
         String[] ab = input.split(action_reg);
         if (ab.length == 2){
             try{
                 a = convertAB(ab[0].trim());
                 b = convertAB(ab[1].trim());
             }catch(NumberFormatException e){
                 res = "Строка не является математической операцией";
                 running = false;
                 arab_rome = false;
             }
             if (!arab_rome){
                 ab = convertABRome(ab);
             }
             Pattern pattern = Pattern.compile(action_reg);
             Matcher matcher = pattern.matcher(input);
             boolean found = matcher.find();
             if (found){
                 action = matcher.group();
             }
             else {
                 running = false;
             }
             if (a > 0 & a < 11 & b > 0 & b < 11 & running){
                 res = Integer.toString(resultInt(action, a, b));
             }
             else {
                 res = "числа должны быть от 1 до 10 включительно, не более";
                 running = false;
             }
         }
         else{
             res = "формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
             running = false;
         }
         return res;
    }
    String[] convertABRome(String[] ab){
        String[] rome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (String numb:rome) {
            if (numb == ab[0]) {
                int a = rome.indexOf(numb);
            }
        }
    }
    public static void main(String[] args){
         Scanner in = new Scanner(System.in);
         String expression = in.nextLine();
         System.out.println(calc(expression));
    }
    private static int resultInt(String action, int a, int b) {
        switch(action) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                return a / b;
        }
    }
    private static int convertAB(String inputStr) {
        return Integer.parseInt(inputStr);

    }
}