import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] string = br.readLine().toCharArray(); // 문자열
		char[] subString = br.readLine().toCharArray(); // 부분 문자열

		int length = string.length; // 문자열의 길이
		int slength = subString.length; // 부분 문자열의 길이

		/* KMP 알고리즘 */
		// 1. 실패함수 만들기
		int[] fail = new int[slength]; // 되돌아갈 위치 저장
		
		// i:접미사 포인터
		// j:접두사 포인터
		for (int i = 1, j = 0; i < slength; i++) {
			while (j > 0 && subString[i] != subString[j]) {
				j = fail[j - 1];
			}
			if (subString[i] == subString[j])
				fail[i] = ++j;
		}

		// 2. KMP 알고리즘
		int res = 0;
		// i : string 포인터 , j: subString 포인터
		for (int i = 0, j = 0; i < length; ++i) {
			while (j > 0 && string[i] != subString[j]) 
				j = fail[j - 1];

			if (string[i] == subString[j]) { // 두 글자 일치
				if (j == slength - 1) { // j가 패턴의 마지막 인덱스라면 부분문자열을 찾은 경우
					res = 1; 
					break;
				} else {
					j++;
				}
			}
		}

		System.out.println(res);
	}
}
