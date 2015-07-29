import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author test
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = "eg.pgn";
		String input = "";
		try {
			Scanner sc = new Scanner(new File(filename));
			while (sc.hasNextLine()) {

				String line = sc.nextLine();

				if (line.matches("\\[.*\\]"))
					continue;

				// line.replaceAll("\\{.*\\}", "");

				input += (" " + line);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		PGNReader pgnReader = new PGNReader();

		for (String string : input.split("(\\d)+\\.")) {

			if ((string.trim()).length() != 0) {
				pgnReader.move(string.trim());

				// System.err.println(string);
				pgnReader.printBoard();
			}
		}
		// pgnReader.move("Bb5 a6");
		pgnReader.printBoard();

	}

}
