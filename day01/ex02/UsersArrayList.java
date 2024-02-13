package ex02;

public class UsersArrayList implements UsersList {

    private User[] users = new User[10];
    private Integer index = 0;

    public void addUser(User user) {
        if (this.index >= this.users.length) {
            User[] tmp = new User[this.users.length + 10];
            for (int i = 0; i < this.index; i++) {
                tmp[i] = users[i];
            }
            tmp[index] = user;
            index++;
            users = tmp;
        }
        this.users[index] = user;
        this.index++;
    }

    public Integer getNumOfUser() {
        return this.index - 1;
    }

    public User getUserId(Integer id) throws UserNotFoundException {
        for (int i = 0; i < this.index; i++) {
            if (this.users[i].getIdentifier() == id) {
                return this.users[i];
            }
        }
        throw new UserNotFoundException("User is not found");
    }

    public User getUserIndex(Integer index) throws UserNotFoundException{
        if (index < 0 || index > this.index) {
            throw new UserNotFoundException("User is not found");
        }
        return this.users[index];
    }

    public int arraySize(){
        return this.users.length;
    }
}
