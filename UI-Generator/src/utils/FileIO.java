package utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO {
    private FileIO(){}
    public static String openFile(String path, String charset){
        StringBuilder sb = new StringBuilder();
        // Specify the encoding of the file.
        Charset encoding = Charset.forName(charset);
        // Path to the file
        Path file = Paths.get(path);

        // Try with resources to close reader at the end.
        try(BufferedReader br = Files.newBufferedReader(file, encoding)) {
            // The line read in.
            String line;

            // Combine assignment with null check (null being EOF).
            // Could put the assignment on its on line if you'd prefer.
            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public static boolean saveFile(String dir, String filename, String text){
        BufferedWriter writer = null;
        try {
            //create a temporary file
            File logFile = new File(dir+"\\"+filename);

            // This will output the full path where the file will be written to...
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
