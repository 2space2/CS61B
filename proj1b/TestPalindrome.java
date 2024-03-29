import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {

        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("hello"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("afda"));
    }

    @Test
    public void testisPalinddrome2() {
        CharacterComparator offbyone = new OffByOne();
        assertTrue(palindrome.isPalindrome("", offbyone));
        assertTrue(palindrome.isPalindrome("aabb", offbyone));
        assertFalse(palindrome.isPalindrome("aaa", offbyone));
        assertFalse(palindrome.isPalindrome("abcd", offbyone));
    }
}
