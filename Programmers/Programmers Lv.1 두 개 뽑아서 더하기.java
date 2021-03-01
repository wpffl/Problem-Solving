import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        int i, j, num = 0, cnt = 0;
        int[] sol = {};
        ArrayList<Integer> list = new ArrayList<>();
        
        for(i=numbers.length-1; i>0; i--) {
            num += i;
        }

        sol = new int[num];
                
        for(i=0; i<numbers.length-1; i++) {
                for(j=i+1; j<numbers.length; j++) {
                    sol[cnt] = numbers[i] + numbers[j];
                    
                    cnt++;
                }
        }
    
        for(int t : sol) {
            if(!list.contains(t)) {
                list.add(t);
            }
        }

    answer = new int[list.size()];
        
    for (i=0; i < list.size(); i++){
        answer[i] = list.get(i).intValue();
    }
    
    Arrays.sort(answer);
    
        return answer;
    }
}
