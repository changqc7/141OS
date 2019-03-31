import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;

class UserThread extends Thread
{
    private int id;
    private BufferedReader file;

    public UserThread(int user)
    {
    	this.id = user;
        File new_file = new File("./input/USER" + Integer.toString(user));
        try
        {
            file = new BufferedReader(new FileReader(new_file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        try
        {
        	String Message = "User " + Integer.toString(id) + " start to do something";
        	Main.update(Message);
        	
            String line = file.readLine();
            while (line != null)
            {
                if (line.startsWith(".save"))
                {
                    FileInfo file_info = new FileInfo();
                    StringBuffer file_name = new StringBuffer();
                    file_name.append(line.substring(6));

                    int num_disk = Main.disk_resource.request();
                    file_info.diskNumber = num_disk;
                    file_info.startingSector = Main.Disks[num_disk].get_sector();
                    file_info.fileLength = 0;
                    String Message_s = "Disk " + Integer.toString(num_disk+1) + " is writing file " + file_name.toString();
                    Main.update(Message_s);

                    line = file.readLine();

                    while (!line.startsWith(".end"))
                    {
                        StringBuffer buffer_line = new StringBuffer();
                        buffer_line.append(line);
                        try 
                        {
                            Main.Disks[num_disk].write(file_info.startingSector+file_info.fileLength , buffer_line);
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        file_info.fileLength += 1;
                        line = file.readLine();
                    }
                    
                    String Message_f = "Disk " + Integer.toString(num_disk+1) + " finishes writing file " + file_name.toString();
                    Main.update(Message_f);

                    Main.disk_manager.enter(file_name , file_info);
                    Main.disk_resource.release(num_disk);
                    line = file.readLine();
                }

                else if (line.startsWith(".print"))
                {
                    String sub = line.substring(7);
                    StringBuffer file_print = new StringBuffer();
                    file_print.append(sub);
                    PrintJobThread p = new PrintJobThread(file_print);
                    p.start();
                    line = file.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

