import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            String postfix = "";
            Stack<Character> stack = new Stack<>();
 
            // 중위 -> 후위
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) { // 숫자인 경우
                    postfix += s.charAt(i);
                } else { // 연산자의 경우
                    if (stack.isEmpty()) { // 스택이 비어 있으면 바로 push
                        stack.push(s.charAt(i));
                    } else { // 스택이 비어있지 않은 경우, 우선순위 검사
                        if (s.charAt(i) == '+') { // +의 경우, 무조건 우선순위가 낮거나 같은 경우 (+ +) or (* +)
                            while (!stack.empty())
                                postfix += stack.pop();
 
                            stack.push(s.charAt(i));
                        } else { // *의 경우
                            if (stack.peek() == '+') { // top이 우선순위가 더 낮은 경우 (+ *)
                                stack.push(s.charAt(i));
                            } else { // top과 우선순위가 같은 경우(* *)
                                postfix += stack.pop();
                                stack.push(s.charAt(i));
                            }
                        }
                    }
                }
            }
 
            while (!stack.isEmpty()) {
                postfix += stack.pop();
            }
             
            //System.out.println("###" + postfix);
            // 후위 표기법으로 변환한 수식으로 연산 수행
            Stack<Integer> stack2 = new Stack<>();
            for (int i = 0; i < postfix.length(); i++) {
                if (postfix.charAt(i) - '0' >= 0 && postfix.charAt(i) - '0' <= 9) { // 피연산자의 경우 stack에 push
                    stack2.push(postfix.charAt(i) - '0');
                } else { // 연산자의 경우 stack에서 두 개 빼서 연산
                    int x = stack2.pop();
                    int y = stack2.pop();
 
                    if (postfix.charAt(i) == '*') {
                        stack2.push(x * y);
                    } else {
                        stack2.push(x + y);
                    }
 
                }
            }
 
            sb.append("#" + t + " " + stack2.pop() + "\n");
        }
        System.out.println(sb.toString());
    }
}
