import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int tmp, answer = 0;
        
        Arrays.sort(arr);
        for(int i = 0; i<arr.length-1; i++) {
            tmp = gcd(arr[i], arr[i+1]);
            tmp = (arr[i] * arr[i+1])/tmp;
            arr[i+1] = tmp;      
        }
        answer = arr[arr.length-1];
        
        return answer;
    }
    
    public int gcd(int big, int small) {
        if(big%small == 0)
            return small;
        return gcd(small, big%small);
    }
}
