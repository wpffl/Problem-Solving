import java.util.Arrays;

class Solution {
    public String solution(String s) {
        String answer = "";
        
        char[] sol = s.toCharArray();
        Arrays.sort(sol);
        
        answer = new String(sol);
    
        return (new StringBuffer(answer)).reverse().toString();
        
        
    }
}
