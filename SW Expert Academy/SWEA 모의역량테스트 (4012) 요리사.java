import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr;
	static boolean[] checked;
	static int N;
	static int A, B, MIN;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			checked = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			MIN = Integer.MAX_VALUE;
			makeCombination(0, 0);
			sb.append("#" + t + " " + MIN + "\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	
	public static void makeCombination(int select, int startIdx) {
		if(select == N/2) {
			int ret = calculate();
			MIN = Math.min(MIN, ret); // 최소값 찾기
			return;
		}
		
		for (int i = startIdx; i < N; i++) {
			checked[i] = true;
			makeCombination(select+1, i+1);
			checked[i] = false;
		}
	}
	
	public static int calculate() {
		ArrayList<Integer> listA = new ArrayList<>();
		ArrayList<Integer> listB = new ArrayList<>();
		
		// 재료 N/2개씩 분배
		for (int i = 0; i < N; i++) {
			if(checked[i])
				listA.add(i);
			else
				listB.add(i);
		}
		
		// 두 음식간 맛의 차이 계산
		A = 0; B = 0;
		for (int i = 0; i < listA.size()-1; i++) {
			for (int j = i+1; j < listA.size(); j++) {
				A += arr[listA.get(i)][listA.get(j)];
				A += arr[listA.get(j)][listA.get(i)];
				B += arr[listB.get(i)][listB.get(j)];
				B += arr[listB.get(j)][listB.get(i)];
			}
		}
		
		return Math.abs(A - B);
	}
}
