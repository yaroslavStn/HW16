import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrintMain {
    public static void main(String[] args) {
        try {
            PrintMain.run();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void run() throws IOException {
        Factory factory = new Factory();

        String string = "hello.";
        int a = 2;
        double d = 3.0;
        float f = 5.25f;

        try (DataOutputStream output = new DataOutputStream(
                new FileOutputStream("data.bin"))) {
            output.writeInt(a);
            output.writeUTF(string);
            output.writeDouble(d);
            output.writeFloat(f);

        }
        Collection<String> collection = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            collection.add(factory.newString());
        }

        try (DataOutputStream outputCollections = new DataOutputStream(new FileOutputStream("Strings.bin"))) {
            outputCollections.writeInt(collection.size());
            for (String s : collection) {
                outputCollections.writeUTF(s);
            }
        }
        List<Contact> contacts = new  ArrayList();
        try (DataOutputStream outputCollections = new DataOutputStream(new FileOutputStream("Contacts.bin"))) {
            int size = 11;
            outputCollections.writeInt(size);
            for (int i = 0; i < size; i++) {
                Contact contact = factory.newContact();
                outputCollections.writeUTF(contact.getName());
                outputCollections.writeUTF(contact.getSurname());
                outputCollections.writeUTF(contact.getPhoneNumber());
                outputCollections.writeInt(contact.getYear());
                contacts.add(contact);
            }
        }
        try (ObjectOutputStream outputCollections = new ObjectOutputStream(new FileOutputStream("ContactsObj.bin"))) {
            outputCollections.writeInt(contacts.size());
            for (Contact c : contacts) {
                outputCollections.writeObject(c);
            }
        }





        /*try (PrintWriter writer = new PrintWriter(
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

*/
    }
}
