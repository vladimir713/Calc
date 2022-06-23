import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
class Calc {
    public static String calc(String input) throws CalcException {
        String res = "";
        String action_reg = "[+\\-*/]";
        String action = getAction(action_reg, input);
        String[] ab = input.split(action_reg);
        if (ab.length == 2) {
            int a = convertABArab(ab[0].trim());
            int b = convertABArab(ab[1].trim());
            int c = convertABRome(ab[0].trim());
            int d = convertABRome(ab[1].trim());
            int resIntArab = resultInt(action, a, b);
            int resIntRome = resultInt(action, c, d);
            if ((a * d > 0) | (b * c > 0)) {
                throw new CalcException("//т.к. используются одновременно разные системы счисления");
            } else if (a * b == 0 & c * d == 0) {
                throw new CalcException("//т.к. операнды должны быть числами от 1 до 10 включительно, " +
                                        "не более");
            } else if (!action.equals("")) {
                if (a * b > 0) {
                    res = Integer.toString(resIntArab);
                } else if (resIntRome > 0) {
                    res = convertAnswerToRome(resIntRome);
                } else {
                    throw new CalcException("//т.к. в римской системе нет отрицательных чисел и 0");
                }
            }
        } else if (ab.length > 2) {
            throw new CalcException("throws Exception //т.к. формат математической операции не удовлетворяет" +
                    " заданию - два операнда и один оператор (+, -, /, *)");
        } else {
            throw new CalcException("throws Exception //т.к. строка не является математической операцией");
        }
        return res;
    }
    static String convertAnswerToRome(int input){
        StringBuilder res = new StringBuilder();
        String[] romeR  = {"C", "L", "X", "V", "I"};
        int[] romeD = {100, 50, 10, 5, 1};
        String[] romeRE = {"IC", "VC", "XC", "IL", "VL", "XL", "IX", "IV"};
        int[] romeDE = {99, 95, 90, 49, 45, 40, 9, 4};
        while(input > 0) {
            for (int ie = 0; ie < romeDE.length; ie++) {
                if (romeDE[ie] == input){
                    res.append(romeRE[ie]);
                    input -= romeDE[ie];
                }
            }
            for (int id = 0; id < romeD.length; id++) {
                if (romeD[id] == input){
                    res.append(romeR[id]);
                    input -= romeD[id];
                }
            }
            for (int i = 0; i < romeD.length - 1; i++) {
                if (romeD[i] > input & romeD[i + 1] < input){
                    res.append(romeR[i + 1]);
                    input -= romeD[i + 1];
                }
            }
        }
        return res.toString();
    }
    static String getAction (String action_reg, String input){
        Pattern pattern = Pattern.compile(action_reg);
        Matcher matcher = pattern.matcher(input);
        boolean found = matcher.find();
        if (found) {
            return matcher.group();
        } else {
            return "";
        }
    }
    static int convertABRome (String input){
        String[] rome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 0; i < rome.length; i++) {
            if (rome[i].equals(input)) {
                return i + 1;
            }
        }
        return 0;
    }
    public static void main (String[]args) throws CalcException {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        System.out.println(calc(expression));
    }
    private static int resultInt (String action,int a, int b){
        switch (action) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                if (b != 0){
                    return a / b;
                } else {
                    return 0;
                }
        }
    }
    private static int convertABArab (String input){
        String[] arab = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (int i = 0; i < arab.length; i++) {
            if (arab[i].equals(input)) {
                return i + 1;
            }
        }
        return 0;
    }
}
