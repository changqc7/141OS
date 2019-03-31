import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;


class PrintJobThread extends Thread 
{
    private StringBuffer file_name;

    public PrintJobThread(StringBuffer file)
    {
        this.file_name = file; 
    }

    public void run()
    {
        FileInfo file_info = Main.disk_manager.lookup(file_name);

        int num_printer = Main.printer_resource.request();
        Printer p = Main.Printers[num_printer];
        
        String Message_s = "Printer " + Integer.toString(num_printer+1) + " is printing file " + file_name.toString();
        Main.update(Message_s);

        StringBuffer buffer = new StringBuffer();
        for (int i = 0 ; i < file_info.fileLength ; i++)
        {
            buffer.setLength(0);
            int num_disk = file_info.startingSector + i;
            try 
            {
                Main.Disks[file_info.diskNumber].read(num_disk , buffer);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try
            {
                p.print(buffer);
                Thread.sleep(2750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        String Message_f = "Printer " + Integer.toString(num_printer+1) + " finished printing file " + file_name.toString();
        Main.update(Message_f);

        Main.printer_resource.release(num_printer);
    }
}
