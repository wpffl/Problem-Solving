import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] num = new String[numbers.length];
        int i;
        
        for(i=0; i<num.length;i++)
            num[i] = numbers[i] + "";
        
        Arrays.sort(num, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        
        for(i=0; i<numbers.length; i++)
            answer = answer + num[i];
        
        if(answer.charAt(0) == '0')
            return "0";
        else
            return answer;
    }
}
