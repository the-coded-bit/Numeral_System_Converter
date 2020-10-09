package converter;

import java.lang.invoke.StringConcatFactory;
import java.util.Scanner;
import java.lang.Integer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int src_base = scanner.nextInt();
            if (src_base <= 0 || src_base >36 ) throw new Exception("error");
            String src_number = scanner.next();
            int trgt_base = scanner.nextInt();
            if (trgt_base <= 0 || trgt_base >36) throw new Exception("error");
            convert(src_number, src_base, trgt_base);
        } catch (Exception e) {
            System.out.println("error");
        }

    }

    private static void convert(String number, int srcbase, int srctarget) {
        String fin_num, int_num, fraction_num;
        // check if the number is fractional or not
        if (number.indexOf('.') != -1) {
            // means number is fractional
            int_num = number.substring(0, number.indexOf('.'));
            fraction_num = number.substring(number.indexOf('.') + 1);
            fin_num = integerPartConversion(int_num, srcbase, srctarget) + '.' + fractionPartConversion(fraction_num, srcbase, srctarget);
            System.out.println(fin_num);
        } else {
            //integer conversion i.e number is only integer....
            fin_num = integerPartConversion(number, srcbase, srctarget);
            System.out.println(fin_num);
        }

    }

    private static String integerPartConversion(String number, int srcbase, int srctarget) {
        String fin_num = "";
        int n = 0;
        // this is the code for integer conversion of source base to target base
        if (srcbase == 1 && srctarget == 10) {
            System.out.println(number.length());
        } else if (srcbase == 1) {
            fin_num = Integer.toString(Integer.parseInt(Integer.toString(number.length())), srctarget);
        } else if (srctarget == 1 && srcbase == 1) {
            fin_num = number;
        } else if (srctarget == 1 && srcbase != 1) {
            n = Integer.parseInt(number, srcbase);
            while (n != 0) {
                n = n - 1;
                System.out.print(1);
            }
        } else {
            n = Integer.parseInt(number, srcbase);
            fin_num = Integer.toString(n, srctarget);
        }
        return fin_num;
    }

    private static String fractionPartConversion(String number, int srcbase, int srctarget) {
        int a = 1, count = 0;
        double dec = 0.0;
        String fin_num = "";
        for (int i = 0; i < number.length(); i++) {
            dec = dec + Integer.parseInt(Character.toString(number.charAt(i)), 36) / Math.pow(srcbase, a);
            a++;
        }
        while (count != 5) {
            dec = dec * srctarget;
            String s = Double.toString(dec);
            int b = Integer.parseInt(s.substring(0, s.indexOf('.')));
            fin_num = fin_num + Integer.toString(b, srctarget);
            dec = dec - b;
            count++;
        }
        return fin_num;
    }

    private static void printing(int base, String sum) {
        if (base == 2) {
            System.out.println("0b" + sum);
        } else if (base == 8) {
            System.out.println("0" + sum);
        } else {
            System.out.println("0x" + sum);
        }
    }
}
