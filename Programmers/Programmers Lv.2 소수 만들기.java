class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        int i, j ,k, cnt = 0;
        int len = nums.length;
        int[] sum = new int[(len*(len-1)*(len-2))/6];
    
        for(i= 0; i<len; i++) {
            for(j=i+1; j<len; j++) {
                for(k=j+1; k<len; k++) {
                    sum[cnt] = nums[i] + nums[j] + nums[k];
                    cnt++;
                }
            }
        }
    
        for(i=0; i<sum.length; i++) {
            for(j=2; j<sum[i]; j++) {
                if(sum[i] % j == 0) {
                    answer++;
                    break;  
                }
            }
        }
    
        answer = sum.length - answer;
        return answer;


    }
}
