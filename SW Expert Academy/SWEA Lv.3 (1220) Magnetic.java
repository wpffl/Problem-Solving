import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        int[] dx = { -1, 1 }; // 상하
        int[] dy = { 0, 0 };
 
        for (int t = 1; t <= 10; t++) {
            br.readLine();
            int[][] arr = new int[100][100]; // 1: N극, 2: S극
            int cnt = 0; // 결과 변수(교착상태의 개수)
 
            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            boolean ch = false;
            for (int j = 0; j < 100; j++) {
                ch = false;
                for (int i = 0; i < 100; i++) {
                    if (ch == false && arr[i][j] == 1) {
                        ch = true;
                        continue;
                    }
                    if (ch == true && arr[i][j] == 2) { // N극을 만나고 S극을 만난 경우
                        ++cnt;
                        ch = false;
                        continue;
                    }
                }
            }
 
            bw.write("#" + t + " " + cnt);
            bw.newLine();
        }
        br.close();
        bw.close();
    }
}
