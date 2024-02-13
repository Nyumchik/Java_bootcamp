package ex01;

public class Program {
    public static void main(String[] args) {
        User userJohn = new User(0, "John", 1000);
        User userMike = new User(25, "Mike", 1000);
        User userMike2 = new User(7, "Mike", 1000);

        System.out.println("User 1: " + userJohn.getIdentifier() + " Name: " + userJohn.getName());
        System.out.println("User 2: " + userMike.getIdentifier() + " Name: " + userMike.getName());
        System.out.println("User 3: " + userMike2.getIdentifier() + " Name: " + userMike2.getName());
    }
}
