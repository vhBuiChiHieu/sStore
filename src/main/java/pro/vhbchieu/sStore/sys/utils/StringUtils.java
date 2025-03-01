package pro.vhbchieu.sStore.sys.utils;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

    public static String toSlug(String input) {
        String lowerCaseString = input.toLowerCase();
        //convert vietnamese char
        String normalizedString = Normalizer.normalize(lowerCaseString, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String withoutDiacritics = pattern.matcher(normalizedString).replaceAll("");

        //
        String halfFinal = withoutDiacritics.replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-");

        //remove -
        return halfFinal.replaceAll("^-|-$", "");
    }

    /**
     * Checks if a string is empty or blank.
     * @param str The input string
     * @return true if the string is null, empty, or contains only whitespace
     */
    public static boolean isEmptyOrBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Generates a random alphanumeric string of specified length.
     * @param length The length of the random string
     * @return The generated random string
     */
    public static String generateRandomString(int length) {
        if (length <= 0) throw new IllegalArgumentException("Length must be positive");
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Abbreviates a string to the specified maximum length, appending "..." if truncated.
     * @param str The input string
     * @param maxLength The maximum length of the abbreviated string (including ellipsis)
     * @return The abbreviated string
     */
    public static String abbreviate(String str, int maxLength) {
        if (str == null) return null;
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * Converts a string to camelCase.
     * @param input The input string (space or underscore separated)
     * @return The camelCase converted string
     */
    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) return input;
        String[] words = input.split("[\\s_]+");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                result.append(word.toLowerCase());
            } else {
                result.append(word.substring(0, 1).toUpperCase());
                result.append(word.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * Counts the occurrences of a substring within a string.
     * @param str The string to search
     * @param sub The substring to count
     * @return The number of occurrences
     */
    public static int countOccurrences(String str, String sub) {
        if (str == null || sub == null || sub.isEmpty()) return 0;
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }
}
