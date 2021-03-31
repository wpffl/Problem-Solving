import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int M = Integer.parseInt(st.nextToken()); // M 이상
		int N = Integer.parseInt(st.nextToken()); // N 이하
		
		int wordCnt = N - M + 1; // M이상 N이하의 문자 개수
		String[] arrInt = new String[wordCnt]; // 숫자 배열
		String[] arrString = new String[wordCnt]; // 숫자를 문자로 바꾼 배열
		
		// M이상 N이하의 숫자들을 순서대로 배열에 저장
		int tmp = M; // M부터 시작
		for (int i = 0; i < wordCnt; i++) {
			arrInt[i] = String.valueOf(tmp++); // 해당 위치에 맞는 값 넣기
			arrString[i] = ""; // 초기화
			
			// 숫자를 문자로 변환하는 작업
			// String형으로 저장한 숫자를 charAt()을 이용하여 앞부터 하나씩 변환
			for (int j = 0; j < arrInt[i].length(); j++) {
				switch (arrInt[i].charAt(j)) {
				case '0':
					arrString[i] += "zero";
					break;
				case '1':
					arrString[i] += "one";
					break;
				case '2':
					arrString[i] += "two";
					break;
				case '3':
					arrString[i] += "three";
					break;
				case '4':
					arrString[i] += "four";
					break;
				case '5':
					arrString[i] += "five";
					break;
				case '6':
					arrString[i] += "six";
					break;
				case '7':
					arrString[i] += "seven";
					break;
				case '8':
					arrString[i] += "eight";
					break;
				case '9':
					arrString[i] += "nine";
					break;
				}
			}
		}
		
		// HashMap 자료구조를 이용해 문자와 숫자를 묶어서 저장
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < wordCnt; i++) {
			map.put(arrString[i], Integer.parseInt(arrInt[i]));
		}
		
		Arrays.sort(arrString); // 문자로 저장된 배열 오름차순 정렬
		
		// 출력
		for (int i = 0; i < wordCnt; i++) {
			// 10번째 숫자마다 개행
			if(i != 0 && i % 10 == 0)
				sb.append("\n");
			// 정렬된 문자 배열의 원소를 키 값으로 해당 숫자 출력
			sb.append(map.get(arrString[i]) + " ");
		}
		System.out.println(sb.toString());
	}
}
