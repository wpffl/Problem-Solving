import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        int cnt=0;
        ArrayList<Long> list = new ArrayList<>();
        
        while(n > 0) {
            list.add((long)n%10);
            n = (long)n/10;   
            cnt++;
        }
        
        Collections.sort(list, Collections.reverseOrder());
       
        for (int i=0; i < list.size(); i++){
            answer += Math.pow(10, --cnt) * list.get(i).intValue();
        }
        
        return answer;
    }
}
