import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Printer
{
    private File file;

    public Printer(int i){
        file = new File("./output/PRINTER"+Integer.toString(i));
    }

    void print(StringBuffer data)
    {
        try 
        {
            if (!file.exists()) 
            {
                file.createNewFile();
            }

        FileWriter fw = new FileWriter(file , true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data.toString());
        bw.newLine();

        bw.flush();
        bw.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
        
