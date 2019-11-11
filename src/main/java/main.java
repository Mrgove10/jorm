import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        System.out.println("Press 1 to import a file");
        System.out.println("Press 2 to export from database");
        int choice = Integer.parseInt(reader.readLine());
        switch (choice){
            case 1:
                System.out.println("upload to db");
                break;
            case 2:
                System.out.println("Export from db");
                break;
            default:
                System.out.println("wrong option selected");
        }
    }
}
