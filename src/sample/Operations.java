package sample;

public class Operations {

    public static int toDecimal(String number, int base){

        if(base != 10) {
            int decimal = 0;

            for (int i = number.length() - 1; i >= 0; i--) {
                if (number.charAt(i) == '-') {
                    decimal *= -1;
                } else {
                    decimal += Character.getNumericValue(number.charAt(i)) * (int) Math.pow(base, (number.length() - 1 - i));
                }
            }

            return decimal;
        }else{
            return Integer.parseInt(number);
        }
    }

    public static String fromDecimal(int number, int base){

        if(base != 10) {
            int decimal = number;

            String sign = decimal < 0 ? "-" : "";
            String nonDecimalNumber = "";

            decimal = Math.abs(decimal);

            do {
                nonDecimalNumber = Integer.toString(decimal % base) + nonDecimalNumber;
                if (decimal != 1) {
                    decimal /= base;
                } else {
                    decimal = 0;
                }

            } while (decimal > 0);

            return sign + nonDecimalNumber;
        }else{
            return Integer.toString(number);
        }
    }

    public static String addNumbers(String numberA, String numberB, int base){
        return fromDecimal((toDecimal(numberA, base) + toDecimal(numberB, base)), base);
    }

    public static String subtractNumbers(String numberA, String numberB, int base){
        return fromDecimal((toDecimal(numberA, base) - toDecimal(numberB, base)), base);
    }

    public static String multiplyNumbers(String numberA, String numberB, int base){
        return fromDecimal((toDecimal(numberA, base) * toDecimal(numberB, base)), base);
    }

    public static String divideNumbers(String numberA, String numberB, int base){
        return fromDecimal((toDecimal(numberA, base) / toDecimal(numberB, base)), base);
    }
}
