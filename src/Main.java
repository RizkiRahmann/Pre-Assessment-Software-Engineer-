import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] arr = {"cook", "save", "taste", "aves", "vase", "state", "map"};
        String[] lowerArr = convertToLowerCase(arr);
        List<List<String>> result = anagrams(lowerArr);

        System.out.println("[");
        for (List<String> listGroups : result) {
            System.out.println("  " + listGroups);
        }
        System.out.println("]");
    }

    public static List<List<String>> anagrams(String[] arr) {
        List<List<String>> listAnagrams = new ArrayList<>();

        for (String word : arr) {
            boolean added = false;

            char[] sortChars = sortChars(word);
            String sortedWord = new String(sortChars);

            for (List<String> stringList : listAnagrams) {
                if (stringList.size() > 0 && isAnagram(stringList.get(0), sortedWord)) {
                    stringList.add(word);
                    added = true;
                    break;
                }
            }

            if (!added) {
                List<String> newStringList = new ArrayList<>();
                newStringList.add(word);
                listAnagrams.add(newStringList);
            }
        }

        return listAnagrams;
    }

    private static char[] sortChars(String word) {
        char[] chars = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            chars[i] = word.charAt(i);
        }

        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - i - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
        return chars;
    }

    private static String[] convertToLowerCase(String[] words) {
        String[] lowerCaseWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            lowerCaseWords[i] = toLowerCase(words[i]);
        }
        return lowerCaseWords;
    }

    private static String toLowerCase(String word) {
        char[] chars = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            chars[i] = word.charAt(i);
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char) (chars[i] + ('a' - 'A'));
            }
        }
        return new String(chars);
    }

    private static boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] charCount = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            charCount[word1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < word2.length(); i++) {
            charCount[word2.charAt(i) - 'a']--;
        }

        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}