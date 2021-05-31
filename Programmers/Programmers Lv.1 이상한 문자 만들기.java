class Solution {
    public String solution(String s) {
        String answer = "";
        int i, j=0, len = s.length();
    
        for(i=0; i<len; i++){
            if(s.charAt(i) == ' ') {
                answer = answer + ' ';
                j = 0;
                continue;
            }
            if(j == 0 || j%2 == 0) {
                answer = answer + Character.toUpperCase(s.charAt(i));
                j++;
            }
            else {
                answer = answer + Character.toLowerCase(s.charAt(i));
                j++;
            }
        
        }
            
        return answer;
    }
}
