package pro.vhbchieu.sStore.sys.utils;

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
}
