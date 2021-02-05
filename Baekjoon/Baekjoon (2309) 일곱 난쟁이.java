import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] arr;
	static int N = 7;
	
	private static void makeCombination(int select, int[] selected, int startIdx) {
		if(select == N) {
			int sum = 0;
			for (int i = 0; i < selected.length; i++) {
				sum += selected[i];
			}
			
			if(sum == 100) {
				Arrays.sort(selected);
				for (int i = 0; i < selected.length; i++) {
					System.out.println(selected[i]);
				}
			}
			return;
		}
		
		for (int i = startIdx; i < arr.length; i++) {
			selected[select] = arr[i];
			makeCombination(select+1, selected, i+1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new int[9];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		makeCombination(0, new int[7], 0);
	}
}
