package inc.premzl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LanguageDetection {
    public static void main(String[] args) throws IOException {
        System.out.println(Files
                .readString(
                        Path.of("assets/corps/swedish-Havsboken.txt")));
    }
}
