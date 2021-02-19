import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int N, res;
    static int[] arr;
    static int[] selected;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            res = 0; // 최종 결과값은 테케마다 초기화해주기!!
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            selected = new int[N];
             
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
             
            makePermutation(0, new boolean[N]);
             
             
            sb.append("#" + t + " " + res + "\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
 
    // 순열 만들기
    public static void makePermutation(int select, boolean[] visited) {
        if (select == N) {
            dfs(0, 0, 0);
            return;
        }
 
        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selected[select] = arr[i];
                makePermutation(select+1, visited);
                visited[i] = false;
            }
        }
    }
     
    public static void dfs(int d, int sumL, int sumR) {
        if(d == N) {
            res++;
            return;
        }
         
        // 왼쪽에 추 올려놓기
        dfs(d+1, sumL + selected[d], sumR);
         
        // 오른쪽에 추를 놓아도 왼쪽이 더 크면 오른쪽에 추 놓음
        if(sumR + selected[d] <= sumL)
            dfs(d+1, sumL, sumR + selected[d]);
    }
}
