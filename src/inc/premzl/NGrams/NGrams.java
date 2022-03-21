package inc.premzl.NGrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NGrams {
    public static List<String> getNGram(String word, int length) {
        List<String> nGrams = new ArrayList<>();
        word = "_" + word + "_".repeat(length - 1);

        for (int i = 0; i < word.length() - length + 1; i++)
            nGrams.add(word.substring(i, i + length));

        return nGrams;
    }

    public static void getNGrams(HashMap<String, Long> nGrams, String[] words) {
        long count;
        for (String word : words) {
            if (word.contains("\n")) continue;
            for (int i = 2; i <= 4; i++) {
                if (word.length() < i) continue;
                for (String nGram : getNGram(word, i)) {
                    count = nGrams.containsKey(nGram) ? nGrams.get(nGram) : 0;
                    nGrams.put(nGram, count + 1);
                }
            }
        }
    }

    public static HashMap<String, Long> sortNGrams(HashMap<String, Long> nGrams) {
        return nGrams
                .entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())).limit(300).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}
