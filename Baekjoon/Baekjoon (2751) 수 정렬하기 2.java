import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      /*
        -1000000 ~ 1000000
        기준점 0 = index 100000 으로 생각
      */

      int size = 2000001;
      int val = 1000000;

      int N = Integer.parseInt(br.readLine()); // 수의 개수	
      boolean[] arr = new boolean[size];	

      // 입력 받은 정수에 1,000,000 더해서 표시
      for(int i = 0; i < N; i++) 
        arr[Integer.parseInt(br.readLine()) + val] = true;

      // 출력
      for(int i = 0; i < size; i++) {
        if(arr[i]) 
          bw.write((i - val) + "\n");
      }
      bw.flush();
    }
}
