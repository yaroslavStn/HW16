public class Contact {

    private String name;
    private String surname;
    private String phoneNumber;
    private int year;

    public Contact(String name, String surname, String phoneNumber, int year) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", year=" + year +
                '}';
    }
}
