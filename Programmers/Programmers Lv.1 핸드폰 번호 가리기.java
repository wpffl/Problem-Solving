class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int i, len = phone_number.length();
        
        for(i=0; i<len-4; i++)
            answer = answer + "*";

        answer = answer + phone_number.substring(len-4);
        
        return answer;
    }
}
