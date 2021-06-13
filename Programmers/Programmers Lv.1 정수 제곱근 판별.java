class Solution {
    public long solution(long n) {
        long answer = 0;
        double tmp1 = 0, tmp2 = 0;
        
        tmp1 = Math.sqrt(n);
        tmp2 = Math.round(tmp1);
        if(tmp1 == tmp2)
           answer = (long)Math.pow(tmp1+1, 2);
        else
            answer = -1;
        
        
        return answer;
    }
}
