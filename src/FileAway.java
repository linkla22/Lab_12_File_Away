import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileAway {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            System.out.println("File selected: " + selectedFile.getName());

            try (Scanner fileScanner = new Scanner(selectedFile)) {
                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    lineCount++;
                    wordCount += countWords(line);
                    charCount += line.length();
                }

                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of words: " + wordCount);
                System.out.println("Number of characters: " + charCount);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("No file selected. Exiting...");
        }
    }

    private static int countWords(String line) {
        String[] words = line.split("\\s+");
        return words.length;
    }
}
