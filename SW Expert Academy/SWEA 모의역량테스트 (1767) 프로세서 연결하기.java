/*
 * "조합적 문제"
 * 1. 가장자리 코어 배제
 * 2. 부분집합 논리
 *      - 코어 선택 (상, 하, 좌, 우)
 *      - 코어 선택 X
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution {
    static int N, max, totalCnt, min, map[][];
    static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
    static int[] dy = { 0, 0, -1, 1 }; // 상하좌우
    static ArrayList<int[]> list; // 고려해야하는 코어만 담고 있는 리스트(가장자리 코어는 배제)
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N]; // 0: 빈칸, 1: 코어, 2: 전선
            list = new ArrayList<int[]>();
            max = 0;
            min = Integer.MAX_VALUE; // 결과 변수 (전선 길이의 합이 최소인 경우)
            totalCnt = 0; // 총 관리해야하는 코어 개수
 
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
                        continue; // 가장자리는 무시
                    }
                    if (map[i][j] == 1) {
                        list.add(new int[] { i, j }); // core의 위치
                        totalCnt++;
                    }
                }
            }
            go(0, 0);
            System.out.println("#" + t + " " + min);
        }
    }
 
    /* 전선을 놓을 수 있는 경우의 수를 탐색하는 함수 */
    // index: 부분집합에 고려할 코어 인덱스, cCnt: 연결된 코어 개수
    private static void go(int index, int cCnt) {
        /* 기저 조건 */
        if(index == totalCnt) {
            int res = getLength(); // 놓아진 전선의 길이 구하기
             
            if(max < cCnt) { // 코어를 최대한 많이 연결한 경우
                max = cCnt;
                min = res;
            } else if(max == cCnt) { // 연결한 코어의 개수가 같은 경우
                min = Math.min(res, min); // 전선의 길이가 최소인 값 구하기
            } 
            return;
        }
         
        /* 유도 파트 */
        // 코어 선택 전선 놓아보기(4방향으로 놓아보기)
        int[] cur = list.get(index);
        int x = cur[0]; // 행 좌표
        int y = cur[1]; // 열 좌표
 
        for (int d = 0; d < 4; d++) {
            if (isAvailable(x, y, d)) {
                // 전선 놓기
                setStatus(x, y, d, 2);
                // 다음 코어로 넘어가기
                go(index + 1, cCnt + 1);
                // 놓았던 전선 되돌려 놓기
                setStatus(x, y, d, 0);
            }
        }
 
        // 코어 비선택
        go(index + 1, cCnt);
    }
 
    /* 전선을 놓을 수 있는지 검사하는 함수 */
    private static boolean isAvailable(int x, int y, int d) {
        int nx = x, ny = y;
 
        while (true) {
            nx += dx[d];
            ny += dy[d];
 
            // 해당 방향으로 직진을 계속해서 경계를 벗어남 : 전원 연결 가능
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                break;
            }
 
            // 코어나 전선이 놓아져 있어 전원 연결 불가능
            if (map[nx][ny] >= 1) {
                return false;
            }
        }
        return true;
    }
 
    /* 전선을 놓거나 놓았던 전선을 되돌려주는 함수 */
    // s: 상태 값
    private static void setStatus(int x, int y, int d, int s) {
        int nx = x, ny = y;
 
        while (true) {
            nx += dx[d];
            ny += dy[d];
 
            // 해당 방향으로 직진을 계속해서 경계를 벗어남 : 전원 연결 가능
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                break;
            }
 
            // 현재 상태 값으로 배열 바꿈 (s = 0: 놓았던 전선 되돌리기, s = 2: 전선 놓기)
            map[nx][ny] = s;
        }
    }
     
    /* 놓아진 전선의 길이를 구하는 함수 */
    private static int getLength() {
        int lCnt = 0;
         
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 2)
                    lCnt++;
            }
        }
         
        return lCnt;
    }
}
