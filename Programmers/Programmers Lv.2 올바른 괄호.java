class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int i, cnt = 0;
        
        for(i=0; i<s.length(); i++){
            if((i == 0) && (s.charAt(i) == ')')) {
                cnt--;
                break;
            }      
            if(cnt < 0)
                break;
            if(s.charAt(i) == '(') 
                cnt++;
            else if(s.charAt(i) == ')')
                cnt--;     
        }
        
        if(cnt != 0)
            answer = false;
     
        return answer;
    }
}
