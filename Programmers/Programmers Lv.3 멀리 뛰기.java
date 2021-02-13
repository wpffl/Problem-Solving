class Solution {
    public long solution(int n) {
        long answer = 0;
        
        int[] a = new int[2000];
        a[0] = 1;
        a[1] = 2;
        
        for(int i=2; i<=n; i++){
            a[i] = (a[i-1]%1234567) + (a[i-2]%1234567);
        }
        
        answer = a[n-1]%1234567;
        
        return answer;
    }
}
