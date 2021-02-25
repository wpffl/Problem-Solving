import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        for (int t = 1; t <= 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
 
            int length = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            List<Integer> list = new LinkedList<Integer>();
 
            list.add(s.charAt(0) - '0');
 
            for (int i = 1; i < length; i++) {
                if (list.size() > 0) {
                    if (list.get(list.size() - 1) == s.charAt(i) - '0') {
                        list.remove(list.size() - 1);
                    } else {
                        list.add(s.charAt(i) - '0');
                    }
                } else {
                    list.add(s.charAt(i) - '0');
                }
            }
 
            bw.write("#" + t + " ");
            for (int i : list) {
                bw.write(String.valueOf(i));
            }
            bw.newLine();
        }
        br.close();
        bw.close();
    }
}
