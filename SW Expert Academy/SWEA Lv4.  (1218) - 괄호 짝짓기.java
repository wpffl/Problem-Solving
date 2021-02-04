import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        for (int t = 1; t <= 10; t++) {
            int num = Integer.parseInt(br.readLine());
            String s = br.readLine();
            boolean flag = false;
            Stack<Character> stack = new Stack<>(); // [], {}, (), <> 괄호의 여는 괄호를 담을 stack
 
            for (int i = 0; i < num; i++) {
                if (flag)
                    break;
                else {
 
                    switch (s.charAt(i)) {
                    case '[':
                        stack.push(s.charAt(i));
                        break;
 
                    case '{':
                        stack.push(s.charAt(i));
                        break;
 
                    case '(':
                        stack.push(s.charAt(i));
                        break;
 
                    case '<':
                        stack.push(s.charAt(i));
                        break;
 
                    case ']':
                        if (!stack.isEmpty() && stack.peek() == '[')
                            stack.pop();
                        else
                            flag = true;
                        break;
 
                    case '}':
                        if (!stack.isEmpty()  && stack.peek() == '{')
                            stack.pop();
                        else
                            flag = true;
                        break;
 
                    case ')':
                        if (!stack.isEmpty()  && stack.peek() == '(')
                            stack.pop();
                        else
                            flag = true;
                        break;
 
                    case '>':
                        if (!stack.isEmpty()  && stack.peek() == '<')
                            stack.pop();
                        else
                            flag = true;
                        break;
                    }
                }
            }
 
            if (!flag && stack.isEmpty())
                sb.append("#" + t + " 1\n");
            else
                sb.append("#" + t + " 0\n");
        }
        System.out.println(sb.toString());
    }
}
