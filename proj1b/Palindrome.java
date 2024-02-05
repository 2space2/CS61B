public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    private boolean isPali(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (word.length() == 2) {
            return word.charAt(0) == word.charAt(word.length() - 1);
        } else if (word.charAt(0) == word.charAt(word.length() - 1)) {
            return isPali(word.substring(1, word.length() - 1));
        }
        return false;
    }
    public boolean isPalindrome(String word) {
        return isPali(word);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        for (int i = 0; i < word.length() /2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - i -1))) {
                return false;
            }
        }
        return true;
    }
}
