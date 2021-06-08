import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class WriteMain {
    static int counter = 0;

    public static void main() {
        String[] args = new String[2];
        args[0] = "d:\\торрент\\[HTML Academy] Профессиональный HTML и CSS, уровень 2\\01 Вводный\\Лекция\\Лекция 1 - Знакомство.mp4";
        args[1] = "d:\\торрент\\[HTML Academy] Профессиональный HTML и CSS, уровень 2\\01 Вводный\\Лекция\\Лекция 21 - Знакомство.mp4";

        try {
            WriteMain.run();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        WriteMain copyMain = new WriteMain();
        try {
            copyMain.copy(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int fileCounter = fileCounter(new File("D:\\Java\\lesson16"));


    }

    private static int fileCounter(File file) {

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                fileCounter(f);
            }
        } else {
            counter++;
        }
        return counter;
    }

    private static void run() throws IOException, ClassNotFoundException {
        try (DataInputStream inputStream = new DataInputStream(
                new FileInputStream("data.bin")
        )) {
            /*byte [] bytes = new byte[4];
            inputStream.read(bytes,0,4);
            ByteBuffer wrapped = ByteBuffer.wrap(bytes);
            int i = wrapped.getInt();*/
            int i = inputStream.readInt();
            String s = inputStream.readUTF();
            double d = inputStream.readDouble();
            float f = inputStream.readFloat();
        }

        try (DataInputStream inputStream = new DataInputStream(
                new FileInputStream("Strings.bin")
        )) {
            Collection<String> collection = new ArrayList<>();
            int size = inputStream.readInt();
            for (int i = 0; i < size; i++) {
                String s = inputStream.readUTF();
                collection.add(s);
            }
        }
        List<Contact> contacts = new ArrayList<>();
        try (DataInputStream inputStream = new DataInputStream(
                new FileInputStream("Contacts.bin")
        )) {
            int size = inputStream.readInt();
            for (int i = 0; i < size; i++) {
                String name = inputStream.readUTF();
                String surName = inputStream.readUTF();
                String phone = inputStream.readUTF();
                int year = inputStream.readInt();
                contacts.add(new Contact(name, surName, phone, year));
            }
        }
        contacts.clear();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("ContactsObj.bin"))) {
            int size = inputStream.readInt();
            for (int i = 0; i < size; i++) {
                Contact contact = (Contact) inputStream.readObject();
                contacts.add(contact);
            }
        }


        /*try (Scanner scanner = new Scanner(
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
*/
    }

    private void copy(String[] args) throws IOException {

        Copy byteCopy = new Copy() {
            @Override
            public void copy(String[] args) throws IOException {
                try (InputStream source = new FileInputStream(args[0]);
                     OutputStream destination = new FileOutputStream(args[1])) {
                    while (true) {
                        int read = source.read();
                        if (read == -1) break;
                        destination.write(read);
                    }
                }
            }

        };


        Copy buffCopy = new Copy() {
            @Override
            public void copy(String[] args) throws IOException {

                try (InputStream source = new FileInputStream(args[0]);
                     OutputStream destination = new FileOutputStream(args[1])) {
                    byte[] buffer = new byte[4000];
                    int lengthRead;
                    while ((lengthRead = source.read(buffer)) > 0) {
                        destination.write(buffer, 0, lengthRead);
                        destination.flush();
                    }
                }

            }
        };
        Copy byteCopyWithBuffered = new Copy() {
            @Override
            public void copy(String[] args) throws IOException {
                try (InputStream source = new BufferedInputStream(new FileInputStream(args[0]), 4000);
                     OutputStream destination = new BufferedOutputStream(new FileOutputStream(args[1]), 4000)) {
                    while (true) {
                        int read = source.read();
                        if (read == -1) break;
                        destination.write(read);
                    }
                }
            }

        };

        Copy buffCopyWithBuffered = new Copy() {
            @Override
            public void copy(String[] args) throws IOException {
                try (InputStream source = new BufferedInputStream(new FileInputStream(args[0]), 4000);
                     OutputStream destination = new BufferedOutputStream(new FileOutputStream(args[1]), 4000)) {
                    byte[] buff = new byte[4000];

                    while (source.available() > 0) {
                        int read = source.read(buff);
                        if (read == -1) break;
                        destination.write(buff);
                        destination.flush();
                    }
                }
            }
        };


        Copy[] methods = {};
        //byteCopy, buffCopy, byteCopyWithBuffered, buffCopyWithBuffered

        for (Copy method : methods) {
            Instant start = Instant.now();
            method.copy(args);
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            time /= 1000;
            System.out.println(time);
        }
    }
}
