import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application
{
    private static int NUMBER_OF_USERS=4;
    private static int NUMBER_OF_DISKS=2;
    private static int NUMBER_OF_PRINTERS=3;

    static Disk[] Disks = new Disk[NUMBER_OF_DISKS];

    static Printer[] Printers = new Printer[NUMBER_OF_PRINTERS];

    static UserThread[] Users = new UserThread[NUMBER_OF_USERS];

    static DiskManager disk_manager = new DiskManager();
    static ResourceManager disk_resource = new ResourceManager(NUMBER_OF_DISKS);
    static ResourceManager printer_resource = new ResourceManager(NUMBER_OF_PRINTERS);
    
    static TextArea textArea = new TextArea();
    Label statusLabel = new Label("Click start");
    Button startButton = new Button("Start");
    Button exitButton = new Button("Exit");
    
    public void start(Stage stage)
    {
    	stage.setTitle("141 OS");
    	startButton.setOnAction(click -> 
    	{
    		for (int i = 0 ; i < NUMBER_OF_USERS ; i++)
    		{
    			Users[i].start();
    		}
    	});
    	
    	exitButton.setOnAction(click ->
    	{
    		stage.close();
    	});
    	
    	HBox buttonBox = new HBox(5, startButton, exitButton);
    	VBox root = new VBox(10, statusLabel, buttonBox, textArea);
    	
    	Scene scene = new Scene(root,400,300);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public static void update(String message)
    {
    	Platform.runLater(new Runnable() 
    	{
    		@Override
    		public void run() 
    		{
    			textArea.appendText(message +"\n");
    		}
    	});
    }

    public static void main(String[] args)
    {
        for (int i = 0 ; i < NUMBER_OF_DISKS ; i++)
        {
            Disks[i] = new Disk(i+1);
        }

        for (int i = 0 ; i < NUMBER_OF_PRINTERS ; i++)
        {
            Printers[i] = new Printer(i+1);
        }

        for (int i = 0 ; i < NUMBER_OF_USERS ; i++)
        {
            Users[i] = new UserThread(i+1);
        }
        
        launch(args);
    }
}
