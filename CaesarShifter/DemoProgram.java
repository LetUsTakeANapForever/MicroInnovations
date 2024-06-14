import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class DemoProgram{
    private Scanner input;
    private Map <Character, Integer> alph_lower, alph_upper;
    private Map <Integer, Character> alph_lower_num, alph_upper_num;
    private char a = 'a';
    private char A = 'A';
    public DemoProgram(){
        alph_lower = new HashMap<>();
        alph_lower_num = new HashMap<>();
        alph_upper = new HashMap<>();
        alph_upper_num = new HashMap<>();
        for(int i = 0; i < 26; i++){
            alph_lower.put((char)((int)a+i), i);
            alph_lower_num.put(i, (char)((int)a+i));
            alph_upper.put((char)((int)A+i), i);
            alph_upper_num.put(i, (char)((int)A+i));
        }
    }
    public void mainMune(){
        input = new Scanner(System.in);
        System.out.println("1.Encode");
        System.out.println("2.Decode");
        System.out.print("Select : ");
        while(true){
            switch (Integer.parseInt(input.nextLine())) {
                case 1:
                    encodeString();break;
                case 2:
                    decodeString();break;
                default:
                    System.out.println("Try again");break;
            }
            input.close();return;
        }
    }
    public void decodeString(){
        System.out.print("Input shift : ");
        int shift = Integer.parseInt(input.nextLine());
        System.out.print("Input string : ");
        String st = input.nextLine();
        char[] st_arr = st.toCharArray();
        char char_value;
        int decoded_value;
        for(char str : st_arr){
            if(Character.isLowerCase(str)){
                int value = alph_lower.get(str);
                if ((value-shift) < 0)
                    decoded_value = (value-shift)+26;
                else decoded_value = (value-shift)%26;
                char_value = alph_lower_num.get(decoded_value);
            }else{
                int value = alph_upper.get(str);
                if ((value-shift) < 0)
                    decoded_value = (value-shift)+26;
                else decoded_value = (value-shift)%26;
                char_value = alph_upper_num.get(decoded_value);
            }
            System.out.print(char_value);
        }
        System.out.println();
    }
    public void encodeString(){
        System.out.print("Input shift : ");
        int shift = Integer.parseInt(input.nextLine());
        System.out.print("Input string : ");
        String st = input.nextLine();
        char[] st_arr = st.toCharArray();
        char char_value;
        for(char str : st_arr){
            if(Character.isLowerCase(str)){
                int value = alph_lower.get(str);
                int encoded_value = (value+shift)%26;
                char_value = alph_lower_num.get(encoded_value);
            }else{
                int value = alph_upper.get(str);
                int encoded_value = (value+shift)%26;
                char_value = alph_upper_num.get(encoded_value);
            }
            System.out.print(char_value);
        }
        System.out.println();
    }
}
