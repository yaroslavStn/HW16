public class Factory {
    private static final String[] names = {"vasia", "petya", "jake", "masha"};
    private static final String[] numbers = {"+111", "+222", "+333", "+444", "+555", "+666", "+000"};
    private static final String[] surnames = {"aa", "bb", "cc", "dd", "ee"};

    public Contact newContact (){
        String name = names[(int) (Math.random()*names.length)];
        String surname = surnames[(int) (Math.random()*surnames.length)];
        String phoneNumber =  numbers[(int) (Math.random()*numbers.length)];
        int year = (int) ((Math.random()*47)+1970);
        return new Contact(name,surname,phoneNumber,year);
    }

    public String newString (){
        return names[(int) (Math.random()*names.length)];
    }

}
