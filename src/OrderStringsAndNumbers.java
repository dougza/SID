
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author dougz
 */
public class OrderStringsAndNumbers {

    public static void main(String[] args) {
        
        List<String> list = Arrays.asList("001", "Alpha", "Gamma", "Peter", "432", "Beta", "Palindrome", "123.4", "Pit");
        ArrayList<String> withoutP = new ArrayList<>(list);
        withoutP = filterAdd(withoutP, 'P');

        ArrayList<String> onlyP = new ArrayList<>(list);
        onlyP = filterRemove(onlyP, 'P');
        
        ArrayList<String> numbers = new ArrayList<>(list);
        numbers = filterNumbers(numbers);

        Collections.sort(numbers, (String s1, String s2) -> Double.compare(Double.parseDouble(s2), Double.parseDouble(s1)));
          
        Collections.sort(onlyP, (String s1, String s2) -> {
            if (s1.length() != s2.length()) {
                return s1.length() - s2.length();
            } else {
                for (int i = 0; i < s1.length(); i++) {
                    if (s1.charAt(i) != s2.charAt(i)) {
                        return s1.charAt(i) - s2.charAt(i);
                    }
                }
            }
            return 0;
        });

        Collections.sort(withoutP, (String a, String b) -> a.compareTo(b));
        withoutP.addAll(onlyP);
        numbers.addAll(withoutP);
        System.out.println(numbers);
    }

    public static ArrayList<String> filterRemove(ArrayList<String> a, char c) {
        int i = 0;        
        while (i < a.size()) {
            String test = a.get(i);
            if (test.charAt(0) != c) {
                a.remove(i);
            } else {
                i++;
            }
        }
        return a;
    }
    
    public static ArrayList<String> filterAdd(ArrayList<String> a, char c) {
        int i = 0;        
        while (i < a.size()) {
            String test = a.get(i);
            if (test.charAt(0) == c || isNumber(test)) {
                a.remove(i);
            } else {
                i++;
            }
        }
        return a;
    }
    
    public static ArrayList<String> filterNumbers(ArrayList<String> a) {
        int i = 0;        
        while (i < a.size()) {
            String test = a.get(i);
            if (!isNumber(test)) {
                a.remove(i);
            } else {
                i++;
            }
        }
        return a;
    }

    private static boolean isNumber(String s) {
        return s.matches("-?\\d+(\\.\\d+)?");
    }

}
