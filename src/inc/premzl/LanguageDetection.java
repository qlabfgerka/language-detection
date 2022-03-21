package inc.premzl;

import java.io.IOException;

import static inc.premzl.Files.FileOperations.readFile;

public class LanguageDetection {
    public static void main(String[] args) throws IOException {
        String file = readFile(args[1]);
        String[] words;

        file = file.replaceAll("[-)(<>\"'„\\.!?,*\\[\\]{}\\\\\\|\\/»«0-9;:—_“”\\n]", "");
        file = file.replaceAll("[ ]+", " ");
        //System.out.println(file);
        words = file.split( " ");

        for(String word : words) {
            System.out.println(word);
        }
    }
}
