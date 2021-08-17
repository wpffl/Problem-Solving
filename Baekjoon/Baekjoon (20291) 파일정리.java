import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 파일의 개수
		Map<String, Integer> map = new HashMap<>();
		
		// 입력받은 파일 이름 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), "."); // '.'을 기준으로 파싱
			String title = st.nextToken(); // 파일명
			String type = st.nextToken(); // 확장자
			
			// 해당 확장자가 없으면 1, 있으면 확장자의 개수 + 1
			map.put(type, map.getOrDefault(type, 0) + 1);
		}
		
		// key값 오름차순 정렬
		List<String> keyList = new LinkedList<>(map.keySet());
		Collections.sort(keyList);
		
		// 정렬된 key값으로 출력
		for(String type : keyList) {
			bw.write(type + " " + map.get(type) + "\n");
		}
		bw.flush();
	}
}
