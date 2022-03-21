package inc.premzl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static inc.premzl.Files.FileOperations.*;
import static inc.premzl.NGrams.NGrams.getNGrams;
import static inc.premzl.NGrams.NGrams.sortNGrams;

public class LanguageDetection {
    public static void main(String[] args) throws IOException {
        if (Objects.equals(args[0], "learn")) {
            List<Path> paths = Files.walk(Paths.get("assets\\corps"))
                    .filter(Files::isRegularFile).toList();
            String file;
            HashMap<String, Long> occurrences;
            Properties properties;
            int index;

            for (Path path : paths) {
                properties = new Properties();
                occurrences = new HashMap<>();
                index = 0;
                file = readFile(path);

                getNGrams(occurrences, splitWords(file));
                occurrences = sortNGrams(occurrences);

                for (Map.Entry<String, Long> entry : occurrences.entrySet())
                    properties.put(entry.getKey(), index++);

                storeHashmap(properties, path.toString().split("\\\\")[2]);
            }
        } else if (Objects.equals(args[0], "detect")) {
            HashMap<String, Long> corps, input = new HashMap<>();
            Properties properties;
            String language = "";
            long sum, prev = Long.MAX_VALUE, index = 0L;
            List<Path> paths = Files.walk(Paths.get("assets\\solved"))
                    .filter(Files::isRegularFile).toList();

            getNGrams(input, splitWords(readFile(Path.of(args[1]))));
            input = sortNGrams(input);
            for (Map.Entry<String, Long> entry : input.entrySet())
                input.put(entry.getKey(), index++);

            for (Path path : paths) {
                corps = new HashMap<>();
                properties = new Properties();
                sum = 0;
                loadHashmap(properties, corps, String.valueOf(path));

                corps = sortNGrams(corps);

                for (Map.Entry<String, Long> entry : input.entrySet()) {
                    sum += corps.containsKey(entry.getKey()) ?
                            Math.abs(entry.getValue() - corps.get(entry.getKey())) :
                            300;
                }

                if (sum < prev) {
                    language = path.toString().split("\\\\")[2];
                    prev = sum;
                }
            }

            System.out.println(language.substring(0, language.length() - 4));
        }
    }
}
