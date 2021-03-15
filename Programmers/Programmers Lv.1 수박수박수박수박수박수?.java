class Solution {
    public String solution(int n) {
        String answer = "";
        String su = "수";
        String bak = "박";
        int i;
        
        for(i = 1; i <= n; i++) {
            if(i%2 == 1)
                answer = answer + su;
            else
                answer = answer + bak;
        }
        return answer;
    }
}
