import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class PrintMain {
    public static void main(String[] args) {
        PrintMain.run();


    }

    private static void run() {
        Factory factory = new Factory();
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream("text.txt")
        )) {
            writer.println("Hello world!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream("num.txt")
        )) {
            for (int i = 0; i < 1001; i++) {
               int n = (int) (Math.random()*1150 - 500);
                writer.println(n);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream("numWithComma.txt")
        )) {
            for (int i = 0; i < 1001; i++) {
                int n = (int) (Math.random()*1150 - 500);
                writer.print(n+",");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new FileOutputStream("contacts.txt"))){
            for (int i = 0; i < 11; i++) {
                Contact contact = factory.newContact();
                writer.print(contact.getName()+
                        " | "+
                        contact.getSurname()+
                        " | "+
                        contact.getPhoneNumber()+
                        " | "+
                        contact.getYear()+
                        '\n');
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
