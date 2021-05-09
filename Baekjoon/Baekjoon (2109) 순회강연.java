import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Lecture implements Comparable<Lecture> {
		int price; // 강연료
		int day; // 기다려줄 수 있는 날짜
		
		public Lecture(int price, int day) {
			super();
			this.price = price;
			this.day = day;
		}

		@Override
		public int compareTo(Lecture o) {	
			return o.price - this.price; // 강연료 기준으로 내림차순 정렬
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine()); // 강연 요청 건수
		boolean[] checked = new boolean[10001]; // 해당 날짜에 강연이 잡혀있는지 나타내는 배열
		int res = 0; // 결과 변수(최대로 벌 수 있는 돈)
		
		// 입력받은 강연 정보 저장
		PriorityQueue<Lecture> pqueue = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int price = Integer.parseInt(st.nextToken()); // 강연료
			int day = Integer.parseInt(st.nextToken()); // 기다려줄 수 있는 날짜
			
			pqueue.offer(new Lecture(price, day));
		}
	
		while(!pqueue.isEmpty()) {
			Lecture lec = pqueue.poll();
			
			// lec.day부터 1일까지 스케줄 확인하면서 강의할 수 있는지 체크
			for (int i = lec.day; i >= 1; i--) {
				if(!checked[i]) { // 해당 날짜에 강연이 잡혀있는지 확인 -> 없으면 강연 잡기
					checked[i] = true; 
					res += lec.price;
					break;
				}
			}	
		}
		
		System.out.println(res);
	}
}
