import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WriteMain {
    public static void main(String[] args) {
        WriteMain.run();
    }

    private static void run() {
        try (Scanner scanner = new Scanner(
                new FileInputStream("num.txt"), StandardCharsets.UTF_8.name())) {
            int counter = 0;
            int sum = 0;
            int countPositiveNum = 0;

            while (scanner.hasNextInt() && counter < 100) {
                int i = scanner.nextInt();
                if (i > 0) {
                    sum += i;
                    countPositiveNum++;
                }
                counter++;
            }
            double average = (double) sum / countPositiveNum;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (Scanner scanner = new Scanner(
                new FileInputStream("numWithComma.txt"), StandardCharsets.UTF_8.name())) {
            int sum = 0;
            int countPositiveNum = 0;
            while (scanner.hasNext()) {
                String string = scanner.nextLine();
                String[] nums = string.split(",");
                for (int i = 0; i < 100 && i < nums.length; i++) {
                    int num = Integer.parseInt(nums[i]);
                    if (num > 0) {
                        sum += num;
                        countPositiveNum++;
                    }
                }
            }
            double average = (double) sum / countPositiveNum;
            System.out.println(average);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(
                new FileInputStream("contacts.txt"), StandardCharsets.UTF_8.name())) {
            List<Contact> contacts = new ArrayList<>();
            while (scanner.hasNext()) {
                String contact = scanner.nextLine();
                String[] param = contact.split(" \\| ");
                int year = Integer.parseInt(param[3]);
                contacts.add(new Contact(param[0], param[1], param[2], year));
            }
            contacts.sort(new Comparator<Contact>() {
                @Override
                public int compare(Contact o1, Contact o2) {
                    return Integer.compare(o1.getYear(), o2.getYear());
                }
            });
            for (int i = 0; i < 6; i++) {
                Contact contact = contacts.get(i);
                System.out.println(
                        contact.getName() +
                                " | " +
                                contact.getSurname() +
                                " | " +
                                contact.getPhoneNumber() +
                                " | " +
                                contact.getYear());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
