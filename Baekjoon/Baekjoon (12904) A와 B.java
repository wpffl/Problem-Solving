import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 문자열 S를 T로 만들 수 있는지
		// 가능한 동작
		// 1. 문자열의 뒤에 A를 추가한다.
		// 2. 문자열을 뒤집고 뒤에 B를 추가한다.
		String S = br.readLine(); // 원본 문자열
		String T = br.readLine(); // 바꿀 문자열
		int res = 0; // 결과 변수(바꿀 수 있으면 1, 없으면 0)

		int sLen = S.length(); // 문자열 S의 길이
		int tLen = T.length(); // 문자열 T의 길이
		StringBuilder sb = new StringBuilder(T);
		
		while(tLen > sLen) { // tLen == sLen이면 종료
			if(sb.charAt(tLen - 1) == 'A') { // 맨 끝이 A인 경우
				/* A 삭제*/
				sb.deleteCharAt(tLen - 1); 
				--tLen;
				
			} else { // 맨 끝이 B인 경우
				/* B 삭제  및  문자열 뒤집기*/
				sb.deleteCharAt(tLen - 1); 
				--tLen;
				sb.reverse();
			}
		}
		
		if(S.equals(sb.toString())) // S로 T를 만들 수 있는 경우
			res = 1;
		System.out.println(res);
	}
}
