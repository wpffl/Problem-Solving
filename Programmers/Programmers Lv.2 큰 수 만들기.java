class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        answer = find_max(number, 0, k);
        
        return answer;
    }
    
    public String find_max(String number, int start, int end) {
        int i;
        char max = '0';
        String answer = "";
        int start2 = start, end2 = end + 1;
        
        if(end2 > number.length())
            return "";
        
        if(start == end)
            return number.substring(start);
            
        for(i=start; i<=end; i++) {
            if(number.charAt(i) > max) {
                max = number.charAt(i);
                start2 = i;
            }
        }
        return max + find_max(number, start2+1, end2);
    }
}
