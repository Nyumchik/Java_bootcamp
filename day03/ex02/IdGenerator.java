package ex02;

public class IdGenerator {
    private static IdGenerator instance;
    private Integer id = 0;
    private IdGenerator() {}

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public Integer generateId(){
        return ++id;
    }
}
