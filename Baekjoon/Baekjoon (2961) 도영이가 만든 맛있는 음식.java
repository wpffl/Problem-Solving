import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Flavor {
	int sour; // 신 맛
	int bitter; // 쓴 맛

	public Flavor(int sour, int bitter) {
		super();
		this.sour = sour;
		this.bitter = bitter;
	}
}

public class Main {
	static Flavor[] f;
	static int N;
	static int MIN; // 신맛과 쓴맛의 최소값

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		f = new Flavor[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			f[i] = new Flavor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		MIN = Integer.MAX_VALUE;
		powerSet(0, new boolean[N]);

		System.out.println(MIN);
	}

	static void powerSet(int cnt, boolean[] selected) {
		if (cnt == N) {
			int totalSour = 1;
			int totalBitter = 0;
			boolean flag = false; // 재료를 하나도 안 뽑은 경우 확인을 위한 변수

			for (int i = 0; i < selected.length; i++) {
				if (selected[i]) {
					if(!flag) flag = true;
					
					totalSour *= f[i].sour;
					totalBitter += f[i].bitter;
				}
			}
			if(!flag) { // 재료 하나도 안뽑았으면 MIN값 변경 X
				return;
			}
			MIN = Math.min(Math.abs(totalSour - totalBitter), MIN);
			return;
		}

		selected[cnt] = true;
		powerSet(cnt + 1, selected);
		selected[cnt] = false;
		powerSet(cnt + 1, selected);
	}
}
