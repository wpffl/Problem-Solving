import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Conveyor {
		int value; // 컨베이어 칸의 내구도
		boolean isRobot; // 컨베이어 칸에 로봇이 올라가있는지 나타내는 변수
		
		public Conveyor(int value, boolean isRobot) {
			super();
			this.value = value;
			this.isRobot = isRobot;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 컨베이어 벨트의 길이
		int K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸이 K개일 경우 종료
			
		LinkedList<Conveyor> upList = new LinkedList<>(); // 컨베이어 벨트의 위쪽 
		LinkedList<Conveyor> downList = new LinkedList<>(); // 컨베이어 벨트의 아래쪽
		st = new StringTokenizer(br.readLine(), " ");
		
		// 입력받은 컨베이어 벨트의 위쪽 내구도 저장
		for (int i = 0; i < N; i++) {
			upList.addLast(new Conveyor(Integer.parseInt(st.nextToken()), false));
		}
		
		// 입력받은 컨베이어 벨트의 아래쪽 내구도 저장
		for (int i = 0; i < N; i++) {
			downList.addFirst(new Conveyor(Integer.parseInt(st.nextToken()), false));
		}
		
		int res = 0; // 단계 카운트 변수
		int cnt = 0; // 내구도가 0인 칸 카운트하는 변수
		
		// 로봇 옮기는 과정 시작
    // 4. 내구도가 0인 칸이 K개일 경우 종료
		while(cnt < K) {
			// 단계 카운트
			++res;
			
			// 1. 벨트 한 칸 회전
			Conveyor tmp = downList.pollFirst();
			upList.addFirst(tmp);
			
			tmp = upList.pollLast(); 
			tmp.isRobot = false; // 내려가는 위치에 로봇이 있으면 로봇 내림
			downList.addLast(tmp);
			upList.getLast().isRobot = false; // 최종 위치에서 내려가는 위치에 로봇이 있으면 로봇 내림
			
			// 2. 로봇 움직이기
			for (int i = N - 2; i >= 0; i--) {
        // 현재 위치에 로봇이 존재하면서
				if(upList.get(i).isRobot) {
					// 다음 칸의 내구도가 0보다 크고, 다음 칸에 로봇이 없는 경우 이동
					if(upList.get(i + 1).value > 0 && !upList.get(i + 1).isRobot) {
						upList.get(i).isRobot = false;
						if(--upList.get(i + 1).value == 0)
							cnt++;
						upList.get(i + 1).isRobot = true;
					}
				}
			}
			
			// 3. 올라가는 위치에 로봇 없으면 로봇 올리기
			if(!upList.getFirst().isRobot && upList.getFirst().value > 0) {
				if(--upList.getFirst().value == 0)
					++cnt;
				upList.getFirst().isRobot = true;
			}
		}
		
		System.out.println(res);
	}
}
