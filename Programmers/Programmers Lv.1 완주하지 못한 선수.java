import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        int i;
        
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for(i=0; i<completion.length; i++) {
            if(!participant[i].equals(completion[i])) {
                answer = participant[i];
                break;
            }
            else if(i == completion.length-1) {
                answer = participant[completion.length];
            }
        }
        return answer;
    }
}
