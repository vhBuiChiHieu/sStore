package pro.vhbchieu.sStore.sys.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testToSlug() {
        assertEquals("hello-world", StringUtils.toSlug("Hello World"));
        assertEquals("hello-world", StringUtils.toSlug("Hello-World"));
        assertEquals("tieng-viet", StringUtils.toSlug("Tiếng Việt"));
        assertEquals("special-chars", StringUtils.toSlug("Special @#$% Chars"));
        assertEquals("multiple-spaces", StringUtils.toSlug("Multiple   Spaces"));
        assertEquals("trim-dashes", StringUtils.toSlug("-Trim Dashes-"));
    }

    @Test
    public void testIsEmptyOrBlank() {
        assertTrue(StringUtils.isEmptyOrBlank(null));
        assertTrue(StringUtils.isEmptyOrBlank(""));
        assertTrue(StringUtils.isEmptyOrBlank(" "));
        assertTrue(StringUtils.isEmptyOrBlank("\t\n\r"));
        assertFalse(StringUtils.isEmptyOrBlank("text"));
        assertFalse(StringUtils.isEmptyOrBlank(" text "));
    }

    @Test
    public void testGenerateRandomString() {
        assertEquals(10, StringUtils.generateRandomString(10).length());
        assertEquals(20, StringUtils.generateRandomString(20).length());
        assertThrows(IllegalArgumentException.class, () -> StringUtils.generateRandomString(0));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.generateRandomString(-1));
        
        // Test randomness by ensuring different calls produce different results
        String first = StringUtils.generateRandomString(10);
        String second = StringUtils.generateRandomString(10);
        assertNotEquals(first, second);
        
        // Test character set
        String random = StringUtils.generateRandomString(100);
        assertTrue(random.matches("^[A-Za-z0-9]+$"));
    }

    @Test
    public void testAbbreviate() {
        assertNull(StringUtils.abbreviate(null, 5));
        assertEquals("Hello", StringUtils.abbreviate("Hello", 5));
        assertEquals("He...", StringUtils.abbreviate("Hello World", 5));
        assertEquals("Hello...", StringUtils.abbreviate("Hello World", 8));
        assertEquals("Hello World", StringUtils.abbreviate("Hello World", 20));
    }

    @Test
    public void testToCamelCase() {
        assertNull(StringUtils.toCamelCase(null));
        assertEquals("", StringUtils.toCamelCase(""));
        assertEquals("hello", StringUtils.toCamelCase("hello"));
        assertEquals("helloWorld", StringUtils.toCamelCase("hello world"));
        assertEquals("helloWorld", StringUtils.toCamelCase("hello_world"));
        assertEquals("helloWorldAgain", StringUtils.toCamelCase("hello world again"));
        assertEquals("helloWorldAgain", StringUtils.toCamelCase("HELLO_WORLD_AGAIN"));
    }

    @Test
    public void testCountOccurrences() {
        assertEquals(0, StringUtils.countOccurrences(null, "test"));
        assertEquals(0, StringUtils.countOccurrences("test", null));
        assertEquals(0, StringUtils.countOccurrences("test", ""));
        assertEquals(2, StringUtils.countOccurrences("hello hello world", "hello"));
        assertEquals(4, StringUtils.countOccurrences("aaaa", "a"));
        assertEquals(0, StringUtils.countOccurrences("hello", "world"));
        assertEquals(1, StringUtils.countOccurrences("hello world", "world"));
    }

    @Test
    public void testReverse() {
        assertNull(StringUtils.reverse(null));
        assertEquals("", StringUtils.reverse(""));
        assertEquals("olleh", StringUtils.reverse("hello"));
        assertEquals("dlrow olleh", StringUtils.reverse("hello world"));
        assertEquals("!@#$", StringUtils.reverse("$#@!"));
    }

    @Test
    public void testToTitleCase() {
        assertNull(StringUtils.toTitleCase(null));
        assertEquals("", StringUtils.toTitleCase(""));
        assertEquals("Hello", StringUtils.toTitleCase("hello"));
        assertEquals("Hello World", StringUtils.toTitleCase("hello world"));
        assertEquals("Hello World", StringUtils.toTitleCase("HELLO WORLD"));
        assertEquals("Hello   World", StringUtils.toTitleCase("hello   world"));
    }

    @Test
    public void testIsNumeric() {
        assertFalse(StringUtils.isNumeric(null));
        assertFalse(StringUtils.isNumeric(""));
        assertTrue(StringUtils.isNumeric("123"));
        assertFalse(StringUtils.isNumeric("12.3"));
        assertFalse(StringUtils.isNumeric("-123"));
        assertFalse(StringUtils.isNumeric("abc"));
        assertFalse(StringUtils.isNumeric("1a2b3c"));
    }

    @Test
    public void testPadLeft() {
        assertNull(StringUtils.padLeft(null, 5, '0'));
        assertEquals("123", StringUtils.padLeft("123", 2, '0'));
        assertEquals("00123", StringUtils.padLeft("123", 5, '0'));
        assertEquals("***abc", StringUtils.padLeft("abc", 6, '*'));
        assertEquals("hello", StringUtils.padLeft("hello", 5, '0'));
    }

    @Test
    public void testRemoveDuplicates() {
        assertNull(StringUtils.removeDuplicates(null));
        assertEquals("", StringUtils.removeDuplicates(""));
        assertEquals("a", StringUtils.removeDuplicates("aaa"));
        assertEquals("abc", StringUtils.removeDuplicates("abcabc"));
        assertEquals("123", StringUtils.removeDuplicates("11223"));
        assertEquals("helo wrd", StringUtils.removeDuplicates("hello world"));
    }
}
