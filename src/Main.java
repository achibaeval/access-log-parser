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
        int yandexCount = 0;
        int googleCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = reader.readLine()) != null) {

                int length = line.length();
                if (length > 1024) {
                    throw new LongLineException("Строка длиннее 1024 символов (номер строки: "
                            + (totalLines + 1) + ", длина: " + length + ")");
                }

                totalLines++;

                String agent = extractAgentFromUserAgent(line); // "Googlebot" / "YandexBot" / null

                if ("YandexBot".equals(agent)) {
                    yandexCount++;
                } else if ("Googlebot".equals(agent)) {
                    googleCount++;
                }
            }

            if (totalLines == 0) {
                System.out.println("Файл пустой.");
                return;
            }

            double yandexShare = (yandexCount * 100.0) / totalLines;
            double googleShare = (googleCount * 100.0) / totalLines;

            System.out.println("Всего запросов (строк): " + totalLines);
            System.out.println("YandexBot: " + yandexCount + " (" + String.format("%.2f", yandexShare) + "%)");
            System.out.println("Googlebot: " + googleCount + " (" + String.format("%.2f", googleShare) + "%)");

        } catch (LongLineException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    // Возвращает "Googlebot" / "YandexBot" / null
    private static String extractAgentFromUserAgent(String line) {


        int open = line.indexOf('(');
        int close = line.indexOf(')', open + 1);
        if (open == -1 || close == -1) {
            return null;
        }

        String inBrackets = line.substring(open + 1, close);
        String[] parts = inBrackets.split(";");
        if (parts.length < 2) {
            return null;
        }


        String second = parts[1].trim();


        int slash = second.indexOf('/');
        if (slash == -1) {
            return second;
        }

        return second.substring(0, slash);
    }
}
