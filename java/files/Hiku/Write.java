import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Write
{
    static String filename;
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Filename: ");
        filename = scan.nextLine();

        File file = new File(filename);

        try
        {
            file.createNewFile();
        }
        catch(IOException e)
        {
            System.out.print("ERROR: could not create file");
        }

        Boolean loop = true;

        System.out.println("\nType '!q' to exit.\n");
        while(loop)
        {
            try
            {
                FileWriter filewriter = new FileWriter(filename, true);

                String line = scan.nextLine();
                if(line.equals("!q") ||  line.equals("!Q"))
                {
                    loop = false;
                }
                else
                {
                    filewriter.append(line);
                    filewriter.append(String.format("%n"));
                    filewriter.close();
                }
            }
            catch(IOException e)
            {
                System.out.println("failed to type to file");
                loop = false;
            }
        }
        scan.close();
    }
}