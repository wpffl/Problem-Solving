import java.util.Scanner;

public class Solution {
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			String s = sc.next();
	
			int rs = 0;
			char chk = '0';
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i) != chk) {
					rs++;
					
					if(chk == '0')
						chk = '1';
					else
						chk = '0';
				}
			}
			System.out.printf("#%d %d\n", t, rs);
		}
	}
}
