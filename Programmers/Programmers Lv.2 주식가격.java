import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int i, j;
        
        for(i=0; i<prices.length; i++) {
            for(j=i+1; j < prices.length; j++) {
                answer[i]++;
                if(prices[i] > prices[j])
                    break;
            }
        }
        return answer;
    }
}
