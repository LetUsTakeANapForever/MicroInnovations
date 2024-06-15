import java.util.Map;
import java.util.HashMap;

public class MainProgram {
    private Map<Character, Integer> alph_lower, alph_upper;
    private Map<Integer, Character> alph_lower_num, alph_upper_num;
    private char a = 'a';
    private char A = 'A';
    private char[] char_arr;
    private char char_value;
    private int decoded_value;
    private String result = "";

    public MainProgram() {
        alph_lower = new HashMap<>();
        alph_lower_num = new HashMap<>();
        alph_upper = new HashMap<>();
        alph_upper_num = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            alph_lower.put((char) ((int) a + i), i);
            alph_lower_num.put(i, (char) ((int) a + i));
            alph_upper.put((char) ((int) A + i), i);
            alph_upper_num.put(i, (char) ((int) A + i));
        }
    }

    public String decodeString(int shift, String st) {
        char_arr = st.toCharArray();
        for (char c : char_arr) {
            if (c == ' ') {
                char_value = ' ';
                result += String.valueOf(char_value);
                continue;
            }
            if (Character.isLowerCase(c)) {
                int value = alph_lower.get(c);
                if ((value - shift) < 0)
                    decoded_value = (value - shift) + 26;
                else
                    decoded_value = (value - shift) % 26;
                char_value = alph_lower_num.get(decoded_value);
            } else {
                int value = alph_upper.get(c);
                if ((value - shift) < 0)
                    decoded_value = (value - shift) + 26;
                else
                    decoded_value = (value - shift) % 26;
                char_value = alph_upper_num.get(decoded_value);
            }
            result += String.valueOf(char_value);
        }
        return result;
    }

    public String encodeString(int shift, String st) {
        char[] char_arr = st.toCharArray();
        for (char c : char_arr) {
            if (c == ' ') {
                char_value = ' ';
                result += String.valueOf(char_value);
                continue;
            }
            if (Character.isLowerCase(c)) {
                int value = alph_lower.get(c);
                int encoded_value = (value + shift) % 26;
                char_value = alph_lower_num.get(encoded_value);
            } else {
                int value = alph_upper.get(c);
                int encoded_value = (value + shift) % 26;
                char_value = alph_upper_num.get(encoded_value);
            }
            result = result + String.valueOf(char_value);
        }
        return result;
    }
}
