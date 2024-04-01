import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class SimulatorOne {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input file:");
        String file = scanner.nextLine();
        Travel.readFile(file);
    }
    class Travel
    {
        public static String readFile(String file)
        {
            Scanner fileIN;
            try {
                fileIN = new Scanner(new FileInputStream(file));
                int nodes = fileIN.nextInt();

            }
            catch (FileNotFoundException e)
            {
                System.out.println("Reenter file");
            }
         return "";
        }
    }
}
