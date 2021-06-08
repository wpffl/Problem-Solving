class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int num = 0, tmp = x;
        
        while(tmp != 0){
            num = num + (tmp % 10);
            tmp = tmp/10;
        }
        
        if(x%num == 0)
            answer = true;
        else
            answer = false;
        
        return answer;
    }
}
