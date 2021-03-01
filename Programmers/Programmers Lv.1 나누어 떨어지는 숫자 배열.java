import java.util.*;
class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        int[] index = new int[arr.length];
        int cnt=0;
        
        for(int i=0; i<arr.length; i++) {
            if(arr[i] % divisor == 0) {
                index[cnt++] = i;     
            }
        }
        
        if(cnt == 0) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        
        answer = new int[cnt];
        for(int i=0; i<answer.length; i++) {
            answer[i] = arr[index[i]];
        }
        Arrays.sort(answer);
        return answer;
    }
}
