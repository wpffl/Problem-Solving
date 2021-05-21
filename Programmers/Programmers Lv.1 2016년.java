class Solution {
    public String solution(int a, int b) {
        String answer = "";
        String[] day = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int[] days = {31,29,31,30,31,30,31,31,30,31,30,31};
        int i, total = 0;
        
        for(i=0; i < a-1; i++)
            total = total + days[i];
        
        total = total + b;
        
        answer = day[total%7];
        
        return answer;
    }
}
