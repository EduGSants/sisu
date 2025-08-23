import java.io.BufferedReader;
import java.io.FileReader;

public class leitura {
    public static void main(String[] args) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(args[0]));
            String categorias = input.readLine();
            for(int i = 1; i < 5610; i++) {
                String line = input.readLine();
                String[] parts = line.split(",", 6);
                Pessoa a = new Pessoa(parts[0],parts[1], parts[2], Double.parseDouble(parts[4]), parts[3], Integer.parseInt(parts[5]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
