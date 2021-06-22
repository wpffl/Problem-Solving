import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
	static int totalM; // 파이어볼의 총 질량
	static ArrayList<FireBall>[][] map;
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 8방(상, 상우, 우, 우하, 하, 하좌, 좌, 좌상)
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class FireBall {
		int x;
		int y;
		int m; // 질량
		int s; // 속력
		int d; // 방향
		
		public FireBall(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
		public FireBall(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // map의 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼의 개수
		K = Integer.parseInt(st.nextToken()); // 명령 개수

		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		// 입력받은 파이어볼의 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken()); // 질량
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 방향

			totalM += m; // 총 질량 계산
			map[x][y].add(new FireBall(m, s, d));
		}

		// 명령 개수만큼 수행
		for (int i = 0; i < K; i++) {
			move();
			sumDivide();
		}

		// 결과 출력
		bw.write(String.valueOf(totalM));
		bw.flush();
	}

	private static void move() {
		ArrayList<FireBall> list = new ArrayList<>();

		// 이동할 파이어볼 list에 저장(중복 이동 방지)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() != 0) { // 현재 위치에 파이어볼이 있는 경우
					for (int k = 0; k < map[i][j].size(); k++) {
						FireBall fb = map[i][j].get(k);

						int nx = i + dx[fb.d] * (fb.s % N);
						int ny = j + dy[fb.d] * (fb.s % N);

						if (nx > 0)
							nx %= N;
						if (ny > 0)
							ny %= N;
						if (nx < 0)
							nx = N - Math.abs(nx);
						if (ny < 0)
							ny = N - Math.abs(ny);

						list.add(new FireBall(nx, ny, fb.m, fb.s, fb.d)); // 이동할 파이어볼 리스트에 저장
						map[i][j].remove(k--); // 현재 파이어볼 삭제
					}
				}
			}
		}

		// 실제 이동
		for (FireBall fb : list)
			map[fb.x][fb.y].add(fb);

	}

	private static void sumDivide() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = map[i][j].size();
				
				/* Sum(같은 위치의 파이어볼 하나로 합치기) */
				if(size >= 2) { // 파이어볼이 2개 이상인 경우
					int newM = 0; // 새 질량
					int newS = 0; // 새 속력
					int oddCnt = 0; // 홀수 방향 개수
					int evenCnt = 0; // 짝수 방향 개수
					
					for(FireBall fb : map[i][j]) {
						newM += fb.m;
						newS += fb.s;
						
						if(fb.d % 2 == 0) // 짝수 방향인 경우
							++evenCnt;
						else // 홀수 방향인 경우
							++oddCnt;
					}
					
					totalM -= newM; // 파이어볼의 총 질량 계산
					map[i][j].clear(); // 합쳐진 파이어볼 삭제
					
					/* Divide(파이어볼 4개로 나누기) */
					newM /= 5;
					newS /= size;
					
					if(newM != 0) { // 질량이 0이 아닌 경우
						totalM += (newM * 4); // 파이어볼의 총 질량 계산
						
						if(oddCnt == 0 || evenCnt == 0) { // 모두 홀수, 모두 짝수인 경우 -> 방향 0,2,4,6
							map[i][j].add(new FireBall(newM, newS, 0));
							map[i][j].add(new FireBall(newM, newS, 2));
							map[i][j].add(new FireBall(newM, newS, 4));
							map[i][j].add(new FireBall(newM, newS, 6));
						} else { // 그 외의 경우 -> 방향  1,3,5,7
							map[i][j].add(new FireBall(newM, newS, 1));
							map[i][j].add(new FireBall(newM, newS, 3));
							map[i][j].add(new FireBall(newM, newS, 5));
							map[i][j].add(new FireBall(newM, newS, 7));
						}
					}
				}
			}
		}
	}
}
