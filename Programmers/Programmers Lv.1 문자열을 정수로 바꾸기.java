class Solution {
    public int solution(String s) {
        int answer = 0;
        
        if(s.charAt(0) == '+'){
            s = s.substring(1);
            answer = Integer.parseInt(s);
        }
        else if(s.charAt(0) == '-') {
            s = s.substring(1);
            answer = Integer.parseInt(s);
            answer = answer * -1;
            
        }   
        else 
            answer = Integer.parseInt(s);
        
        return answer;
    }
}
