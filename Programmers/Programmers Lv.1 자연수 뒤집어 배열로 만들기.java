class Solution {
    public int[] solution(long n) { 
        String num = ""+n;
        int i, len = num.length();
        int[] answer = new int[len];
        
        for(i=0; i < len; i++) {
            answer[i] = (int)(n%10);
            n = n/10;
        }
        
        return answer;
    }
}
