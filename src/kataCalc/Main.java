package kataCalc;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println(calc(sc.nextLine()));


    }

    private static int arabicNumberCount = 0;

    public static String calc(String input) throws Exception {
        String[] list = input.split("\s+");
        if (list.length != 3) {
            throw new Exception();
        }
        int num1 = romanNumeralsArabic(list[0]);
        int num2 = romanNumeralsArabic(list[2]);
        if (arabicNumberCount == 1 || num1 > 10 || num2 > 10) {
            throw new Exception();
        }
        int result = 0;
        switch (list[1]) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new Exception();
        }
        if (arabicNumberCount == 2) {
            return String.valueOf(result);
        }
        return arabicNumeralsRoman(result);
    }

    private static int romanNumeralsArabic(String num) {
        String[] numbers = num.split("");
        int result = 0;
        int number = 0;
        int pastNumber = 0;
        for (String i : numbers) {
            switch (i) {
                case "I" -> {
                    number = 1;
                }
                case "V" -> {
                    number = 5;
                }
                case "X" -> {
                    number = 10;
                }
                case "L" -> {
                    number = 50;
                }
                case "C" -> {
                    number = 100;
                }
                case "D" -> {
                    number = 500;
                }
                case "M" -> {
                    number = 1000;
                }
                default -> {
                    arabicNumberCount++;
                    return Integer.parseInt(String.join("", numbers));
                }
            }
            if (pastNumber != 0 && pastNumber < number) {
                result += number - 2 * pastNumber;
            } else {
                result += number;
            }
            pastNumber = number;
        }
        return result;
    }

    private static String arabicNumeralsRoman(int num) throws Exception {
        StringBuilder result = new StringBuilder();
        if (num < 0) {
            throw new Exception();
        }
        Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        while (num > 0) {
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getKey() <= num) {
                    num -= entry.getKey();
                    result.append(entry.getValue());
                    break;
                }
            }
        }

        return result.toString();

    }

    private enum Roman {

    }

}


