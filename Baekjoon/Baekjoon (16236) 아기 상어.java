import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Fish {
		int x; // x 좌표
		int y; // y 좌표

		public Fish(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int time;
	static int[][] map;
	static int sharkX, sharkY, sharkSize, eatFishCnt;
	static int[][] distance;
	static int minX, minY, minDistance;

	static int dx[] = { -1, 0, 1, 0 }; // 상좌하우
	static int dy[] = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine()); // 어항의 크기
		map = new int[N][N]; // 어항
		sharkSize = 2; // 아기 상어의 크기

		// 입력받은 어항의 정보 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 9) { // 상어인 경우, 좌표 저장
					sharkX = i;
					sharkY = j;
					map[i][j] = 0; // 탐색 시 빈칸으로 취급
				}
			}
		}

		sol();
		System.out.println(time);
	}

	private static void sol() {
		while (true) {
			distance = new int[N][N]; // 상어와 물고기 사이의 거리 배열
			minX = Integer.MAX_VALUE; // 가장 가까운 물고기의 X좌표
			minY = Integer.MAX_VALUE; // 가장 가까운 물고기의 Y좌표
			minDistance = Integer.MAX_VALUE; // 가장 가까운 물고기와의 거리

			bfs();

			if (minX == Integer.MAX_VALUE && minY == Integer.MAX_VALUE) // 더이상 갈 곳이 없는 경우
				break;
			else {
				++eatFishCnt; // 먹은 물고기 수 증가
				map[minX][minY] = 0; // 물고기 먹은거 표시
				sharkX = minX; // 상어 좌표 업데이트
				sharkY = minY;
				time += distance[minX][minY]; // 상어와 물고기의 거리만큼 시간 증가

				if (eatFishCnt == sharkSize) { // 상어의 크기만큼 물고기 먹은 경우, 상어 크기 1 증가
					++sharkSize;
					eatFishCnt = 0; // 먹은 물고기 수 초기화
				}
			}
		}
	}

	private static void bfs() {
		Queue<Fish> queue = new LinkedList<>();
		queue.offer(new Fish(sharkX, sharkY)); // 상어 큐에 넣기

		while (!queue.isEmpty()) {
			Fish fish = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nx = fish.x + dx[d];
				int ny = fish.y + dy[d];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N) { // 범위 체크
					if (map[nx][ny] <= sharkSize) { // 다음 칸으로 갈 수 있는지 체크
						if (distance[nx][ny] == 0) { // 방문 체크(0: 방문하지 않음)
							distance[nx][ny] = distance[fish.x][fish.y] + 1; // 거리 증가

							if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) { // 상어가 먹을 수 있는 경우
								/* 먹을 수 있으면, min 값 업데이트 */
								if (distance[nx][ny] < minDistance) { // 거리가 더 짧은 경우 min값 업데이트
									minDistance = distance[nx][ny];
									minX = nx;
									minY = ny;
								} else if (distance[nx][ny] == minDistance) { // 거리가 같은 경우 상 우선, 좌 우선
									if (nx < minX) { // x 좌표가 더 작은 경우 먼저 먹음(상 우선)
										minX = nx;
										minY = ny;
									} else if (nx == minX) { // x좌표는 똑같은 경우
										if (ny < minY) { // y 좌표가 더 작은 물고기 먼저 먹음(좌 우선)
											minX = nx;
											minY = ny;
										}
									}
								}
							}
							queue.offer(new Fish(nx, ny));
						}
					}
				}
			}
		}
	}
}
