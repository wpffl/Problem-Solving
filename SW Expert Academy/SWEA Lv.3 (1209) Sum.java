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
 
        for (int t = 1; t <= 10; t++) {
            br.readLine();
            int[][] arr = new int[100][100];
            int sum = 0;
            int max = Integer.MIN_VALUE;
 
            // 배열 init 동시에 행의 합 검사
            for (int i = 0; i < 100; i++) {
                max = Math.max(max, sum);
                sum = 0;
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    sum += arr[i][j];
                }
            }
 
            // 열 검사
            for (int i = 0; i < 100; i++) {
                max = Math.max(max, sum);
                sum = 0;
                for (int j = 0; j < 100; j++) {
                    sum += arr[j][i];
                }
            }
 
            // 대각선 검사 1
            sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += arr[i][i];
            }
            max = Math.max(max, sum);
             
            // 대각선 검사 2
            sum = 0;
            for (int i = 99; i >= 0; i--) {
                sum += arr[i][i];
            }
            max = Math.max(max, sum);
 
            // 최종 max 출력
            bw.write("#" + t + " " + max);
            bw.newLine();
        }
        br.close();
        bw.close();
    }
}
