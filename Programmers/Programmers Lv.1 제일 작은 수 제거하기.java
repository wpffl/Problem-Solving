class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        int min=arr[0];
        int min_idx = 0;
        int cnt = 0;
        
        if(arr.length == 1) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        
        for(int i=1; i<arr.length; i++) {
            if(min > arr[i]) {
                min = arr[i];
                min_idx = i;
            }
        }
        answer = new int[arr.length-1];
        
        for(int i=0; i<arr.length; i++) {
            if(i == min_idx) {
                continue;
            }
            answer[cnt++] = arr[i];
        }
        
        return answer;
    }
}
