import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

class Read
{
    static String filename;
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Filename: ");
        filename = scan.nextLine();

        try
        {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            System.out.print("\n");
            while(reader.hasNextLine())
            {
                String line = reader.nextLine();
                System.out.println(line); 
            }
            reader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("ERROR: File not found.");
        }

        scan.close();
    }
}