import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String path = sc.nextLine();

        int totalLines = 0;
        int minLength = Integer.MAX_VALUE;
        int maxLength = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = reader.readLine()) != null) {

                int length = line.length();

                if (length > 1024) {
                    throw new LongLineException(
                            "Ошибка: строка длиннее 1024 символов (номер строки: "
                                    + (totalLines + 1) + ", длина: " + length + ")"
                    );
                }

                totalLines++;
                minLength = Math.min(minLength, length);
                maxLength = Math.max(maxLength, length);
            }

            if (totalLines == 0) {
                System.out.println("Файл пустой.");
            } else {
                System.out.println("Всего строк: " + totalLines);
                System.out.println("Длина самой короткой строки: " + minLength);
                System.out.println("Длина самой длинной строки: " + maxLength);
            }

        } catch (LongLineException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
