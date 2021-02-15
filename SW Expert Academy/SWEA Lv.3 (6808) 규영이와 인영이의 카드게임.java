import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int[] arr1, arr2;
    static int point1, point2;
    static int win, lose;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr1 = new int[9]; // 규영이 카드 패(순서 그대로)
            arr2 = new int[9]; // 인영이 카드 패
            boolean[] checked = new boolean[18]; // 규영이가 들고 있는 카드 체크 배열
 
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
                checked[arr1[i]-1] = true;
            }
 
            /* 규영이가 가지고 있는 숫자 제외하고 1-18까지 중 9개 선별 */
            int cnt = 0;
            for (int i = 0; i < 18; i++) {
                if (!checked[i]) {
                    arr2[cnt++] = i + 1;
                }
            }
 
            win = 0; // 규영이가 이기는 경우의 수
            lose = 0; // 규영이가 지는 경우의 수
            makePermutation(0, new int[9], new boolean[9]);
 
            sb.append("#" + t + " " + win + " " + lose + "\n");
        }
        System.out.println(sb.toString());
 
    }
 
    static void makePermutation(int select, int[] selected, boolean[] visited) {
        if (select == 9) {
            point1 = 0; // 규영이 포인트
            point2 = 0; // 인영이 포인트
            for (int i = 0; i < 9; i++) {
                if (arr1[i] > selected[i])
                    point1 += arr1[i] + selected[i];
                else
                    point2 += arr1[i] + selected[i];
            }
 
            if (point1 > point2)
                win++; 
            else
                lose++; 
            return;
        }
 
        for (int i = 0; i < arr2.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[select] = arr2[i];
                makePermutation(select + 1, selected, visited);
                visited[i] = false;
            }
        }
    }
}
