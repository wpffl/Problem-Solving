class Solution {
    public int solution(int n) {
        int i, answer = 0;
        
        for(i=1; i<=n; i++){
            if(n%i == 0)
                answer = answer + i;
        }
    
        
        return answer;
    }
}
