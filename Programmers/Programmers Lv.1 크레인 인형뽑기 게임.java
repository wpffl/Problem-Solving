import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int i, j, k, answer = 0;
        Stack<Integer> stack = new Stack<>();
        int[][] dolls = new int[board.length][board.length];
        
        for(i=0; i<board.length; i++) { // 배열 뒤집기
            k = 0;
            for(j=board.length-1; j>=0; j--) {         
                   dolls[i][k++] = board[j][i];         
            }
        }
        
        for(i=0; i<moves.length; i++) {
            int tmp = moves[i] - 1; //가져와야 할 배열 index
        
            for(j=dolls.length-1; j>= 0; j--){ 
                if(dolls[tmp][j] != 0){
                    if(stack.empty()) {
                        stack.push(dolls[tmp][j]);
                    }
                    else {
                        if(stack.peek() == dolls[tmp][j]) {
                            answer += 2;
                            stack.pop();
                        } else {
                            stack.push(dolls[tmp][j]);
                        }
                    }
                    dolls[tmp][j] = 0;
                    break;
                }
            }
        }     
        return answer;
    }
}
