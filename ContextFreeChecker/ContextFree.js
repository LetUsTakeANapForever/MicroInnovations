import java.util.Stack;

// L(G) = {a^nb^n : n >= 0}

public class ContextFree {
    public static void main(String[] args) {

        String[] testingtArray = {"aaab", "aab", "", "bab", "baba", "abba", "aaabbb", "aaabbba", "aabb"};

        System.out.println("-------------------------------------------");
        System.out.println("L(G) = {a^nb^n : n >= 0}");
        System.out.println("-------------------------------------------");
        for (String st : testingtArray){
            boolean isContext = CheckIfIsConText(st);
            System.out.printf("%s\t\t:\t%s", st,  isContext);
            if (isContext){
                System.out.print(GetNumOfAAndB(st));
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------");
    }

    public static boolean CheckIfIsConText(String st) {
        if (st.isEmpty())
            return true;

        Stack<Character> stack = new Stack<>();

        for (Character c : st.toCharArray()) {
            // System.out.println("curr_char : " + c);
            if (c.equals('a'))
                stack.push('(');
            else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }

            // System.out.println("curr_stack : " + stack + stack.isEmpty());
        }

        return stack.isEmpty();
    }

    public static String GetNumOfAAndB(String s){
        int numOfA = 0, numOfB = 0;

        for (Character c : s.toCharArray()){
            if (c.equals('a'))
                numOfA++;
            else
                numOfB++;
        }

        return String.format("\t(a^%db^%d)", numOfA, numOfB);
    }
}
