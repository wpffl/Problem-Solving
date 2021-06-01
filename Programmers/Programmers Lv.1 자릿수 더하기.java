import java.util.*;

public class Solution {
    public int solution(int n) {
        String num = Integer.toString(n);
        int i, len = num.length(), answer = 0;
        
        for(i=0; i<len; i++) {
            answer = answer + Character.getNumericValue(num.charAt(i));             
        }

        return answer;
    }
}
