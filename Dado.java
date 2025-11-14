import java.util.Random;

public class Dado {
    private Random random = new Random();

    public int tirar() {
        return random.nextInt(6) + 1;
    }
}