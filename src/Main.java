import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите первое число:");
        int first = sc.nextInt();

        System.out.println("Введите второе число:");
        int second = sc.nextInt();

        int sum = first + second;
        int diff = first - second;
        int product = first * second;
        double quotient = (double) first / second;

        System.out.println("Сумма: " + sum);
        System.out.println("Разность: " + diff);
        System.out.println("Произведение: " + product);
        System.out.println("Частное: " + quotient);
    }
}