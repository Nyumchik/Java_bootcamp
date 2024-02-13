package ex02;

public class Program {
    public static void main(String[] args) {
        User userJohn = new User("John", 1000);
        User userMike = new User("Mike", 1000);
        User userQ = new User("Q", 1000);
        User userW= new User("W", 1000);
        User userE= new User("E", 1000);
        User userR= new User("R", 1000);
        User userT= new User("T", 1000);
        User userY= new User("Y", 1000);
        User userU= new User("U", 1000);
        User userI= new User("I", 1000);
        User userO= new User("O", 1000);

        UsersArrayList users = new UsersArrayList();

        users.addUser(userJohn);
        users.addUser(userMike);
        users.addUser(userQ);
        users.addUser(userW);
        users.addUser(userE);
        users.addUser(userR);
        users.addUser(userT);
        users.addUser(userY);
        users.addUser(userU);

        System.out.println("arraySize: " + users.arraySize());
        
        users.addUser(userI);
        users.addUser(userO);

        System.out.println("arraySize: " + users.arraySize());
        System.out.println("numOfUsers: " + users.getNumOfUser());
        
        try {
            System.out.println("getUserIndex: " + users.getUserIndex(0).getName());
            System.out.println("getUserId: " + users.getUserId(2).getName());
        }
        catch (UserNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        try {
            System.out.println("getUserId: " + users.getUserId(50).getName());
        }
        catch (UserNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        try {
            System.out.println("getUserIndex: " + users.getUserIndex(50).getName());
        }
        catch (UserNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
