package Exercise.Day5;

import java.util.Scanner;

public class WeekendDayToNumber {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String day = scn.next();
        switch (day) {
            case "Monday":
                System.out.println("1");
                break;
            case "Tuesday":
                System.out.println("2");
                break;
            case "Wednesday":
                System.out.println("3");
                break;
            case "Thursday":
                System.out.println("4");
                break;
            case "Friday":
                System.out.println("5");
                break;
            case "Saturday":
                System.out.println("6");
                break;
            case "Sunday":
                System.out.println("7");
                break;
            default:
                System.out.println("Wrong input");
        }
    }
}
