import java.util.*;

class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        int i, len = s.length(); 
        
       if(!(len == 4 || len == 6))
           answer = false;
        
        for(i = 0; i < len; i++) {
            if(Character.isDigit(s.charAt(i)) == false) 
                answer = false;
        }
    
        return answer;
    }
}
