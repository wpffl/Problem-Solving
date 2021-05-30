class Solution {
    public String solution(String s, int n) {
        String answer = "";
        
        for(int i=0; i<s.length(); i++) {
            int tmp = (int)s.charAt(i);
            
            if(tmp == 32) { //공백
                answer += " ";
                continue;
            }
            if(tmp >= 97 && tmp <= 122) { //소문자
                tmp += n;
                if(tmp > 122)  // z 넘어간 경우
                    tmp -= 26;
            }    
            else if(tmp >= 65 && tmp <= 90) {
                tmp += n;
                if(tmp > 90)  // Z 넘어간 경우
                    tmp -= 26;
            }
            answer += (char)tmp;
                   
        }
        
        return answer;
    }
}
