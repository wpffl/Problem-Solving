import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int[][] arr;
    static boolean[][] visited;
    static int N;
    static int idx;
    static int max;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
 
    public static void dfs(int start, int depth, int i, int j) {
        visited[i][j] = true;
 
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
 
            if (x >= 0 && x < N && y >= 0 && y < N) {
                if ((arr[i][j] + 1) == arr[x][y] && visited[x][y] == false) {
                    dfs(start, depth + 1, x, y);
                }
            }
        }
         
        if (depth > max) {
            max = depth;
            idx = start;
        } else if (depth == max) {
            if (start < idx) {
                idx = start;
            }
        }
        visited[i][j] = false;
    }
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
 
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            visited = new boolean[N][N];
            idx = 0;
            max = 0;
 
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dfs(arr[i][j], 1, i, j);
                }
            }
            sb.append("#" + t + " " + idx + " " + max + "\n");
        }
        System.out.println(sb.toString());
    }
}
