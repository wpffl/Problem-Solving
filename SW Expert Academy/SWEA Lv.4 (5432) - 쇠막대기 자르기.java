import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
         
        int T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
            String s = br.readLine();
            int res = 0;
             
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '(') { 
                    stack.push('(');
                } else { 
                    stack.pop();
                    if(s.charAt(i-1) == '(') { // "()의 경우" -> 레이저
                        res += stack.size(); // stack 안에 있는 막대기들(아직 안끝난 막대기들)을 더한다! 
                    }
                    else { // "))의 경우" -> 끝난 막대기
                        res++;
                    }
                }
            }
             
            sb.append("#" + t + " " + res + "\n");
        }
        System.out.println(sb.toString());
    }
}
