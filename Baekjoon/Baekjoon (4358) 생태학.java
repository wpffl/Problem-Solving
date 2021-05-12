import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Map<String, Integer> treeList = new HashMap<>(); // 나무 종별 개수를 저장할 map
		int totalCnt = 0; // 총 나무 개수
		
		String treeName = br.readLine();
		while(treeName != null) {
			/* getOrDefault(Object key, V DefaultValue) */
			// key : 값을 가져와야 하는 요소의 키입니다.
			// defaultValue : 지정된 키로 매핑된 값이 없는 경우 반환되어야 하는 기본값입니다.
			// 반환 값 : 찾는 key가 존재하면 해당 key에 매핑되어 있는 값을 반환하고, 그렇지 않으면 디폴트 값이 반환됩니다.
			treeList.put(treeName, treeList.getOrDefault(treeName, 0) + 1); // key 값이 없으면 1, 있으면 기존 value + 1
			++totalCnt;
			
			treeName = br.readLine();
		}
		
		Object[] treeNameList = treeList.keySet().toArray(); // Map의 key값들로 배열 만듦.
		Arrays.sort(treeNameList); // 나무 이름 오름차순 정렬
		
		for (int i = 0; i < treeNameList.length; i++) {
			double rate = (double)treeList.get(treeNameList[i])/totalCnt * 100; // 나무의 점유율 구하기
			
			bw.write(treeNameList[i] + " " + String.format("%.4f", rate) + "\n");
		}
		bw.flush();
	}
}
