import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
		HashSet<String> set = new HashSet<>(); // List보다 HashSet이 속도가 빠름
		
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String name = st.nextToken();
			String log = st.nextToken();
			
			if(log.charAt(0) == 'e')
				set.add(name);
			else 
				set.remove(name);
		}
		
		/* 역순 정렬을 위해 HashSet을 List형으로 변환 후, Collection API 사용 */
		List<String> list = new ArrayList<>(set);
		Collections.sort(list, Collections.reverseOrder());
		
		for (String s : list) {
			bw.write(s);
			bw.newLine();
		}
		
		br.close();
		bw.close();	
	}
}
