import java.util.Scanner;

public class Main {
	static String start = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
	static String r1 = "\"재귀함수가 뭔가요?\"";
	static String r2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
	static String r3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
	static String r4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	static String r5 = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	static String end = "라고 답변하였지.";
	static String underbar ="____";
	static int N;
	
	public static void recursive(int n) {
		String bar = "";
		for(int i=0; i<n; i++) {
			bar += underbar; 
		}
		if(n < N) { // 반복
			System.out.println(bar + r1);
			System.out.println(bar + r2);
			System.out.println(bar + r3);
			System.out.println(bar + r4);
			
			recursive(n+1);
			System.out.println(bar + end);
			
		}else { // 마지막 경우
			System.out.println(bar + r1);
			System.out.println(bar + r5);
			System.out.println(bar + end);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		System.out.println(start);
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		System.out.println(r4);
		
		recursive(1);
		
		System.out.println(end);
		
		sc.close();
	}
}
