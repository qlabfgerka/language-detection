package inc.premzl.Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Properties;

public class FileOperations {
    public static String readFile(Path path) throws IOException {
        return Files.readString(path);
    }

    public static String removeSpecialCharacters(String text) {
        text = text.replaceAll("[-)(<>\"'„\\.!?,*\\[\\]{}\\\\\\|\\/»«0-9;:—_“”\\n]", "");
        text = text.replaceAll("[ ]+", " ");
        return text;
    }

    public static String[] splitWords(String text) {
        text = removeSpecialCharacters(text);
        return text.split(" ");
    }

    public static void storeHashmap(Properties properties, String filename) throws IOException {
        properties.store(new OutputStreamWriter(
                        new FileOutputStream("assets\\solved\\" + filename),
                        StandardCharsets.UTF_8),
                null);
    }

    public static void loadHashmap(Properties properties, HashMap<String, Long> nGrams, String path) throws IOException {
        properties.load(new InputStreamReader(new FileInputStream(String.valueOf(path)), StandardCharsets.UTF_8));

        for (String key : properties.stringPropertyNames())
            nGrams.put(key, Long.valueOf(properties.get(key).toString()));
    }
}
