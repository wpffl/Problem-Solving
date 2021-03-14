import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int p_count = 0, y_count = 0;
        char[] c = s.toCharArray();
        
        for(char ch : c) {
            if(ch == 'p' || ch == 'P')
                p_count++;
            if(ch == 'y' || ch == 'Y')
                y_count++;
        }
        
        if(p_count != y_count)
            answer = false;
        

        return answer;
    }
}
