import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Volunteer {
	int sRank; // 서류심사 성적 순위
	int mRank; // 면접심사 성적 순위

	public Volunteer(int sRank, int mRank) {
		super();
		this.sRank = sRank;
		this.mRank = mRank;
	}	
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			int N = Integer.parseInt(br.readLine()); // 지원자 인원 수
			ArrayList<Volunteer> list = new ArrayList<>();
			
			/* 지원자 성적 순위 입력 */
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				list.add(new Volunteer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			// 서류 성적 순위로 정렬
			Collections.sort(list, new Comparator<Volunteer>() {
				@Override
				public int compare(Volunteer o1, Volunteer o2) {
					return o1.sRank - o2.sRank;
				}
			});
			
			int res = 1; // 서류 1등은 무조건 뽑힘.
			// 면접 순위 검사
			int max_mRank = list.get(0).mRank;
			for (int i = 1; i < N; i++) {
				if(list.get(i).mRank < max_mRank) {
					res++;
					max_mRank = list.get(i).mRank;
				}
			}
			
			System.out.println(res);
		}
	}
}
