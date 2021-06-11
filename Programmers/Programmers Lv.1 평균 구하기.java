class Solution {
    public double solution(int[] arr) {
        double answer = 0, sum = 0;
        int i;
        
        for(i=0; i<arr.length; i++)
            sum = sum + arr[i];
        answer = sum/arr.length;
        return answer;
    }
}
