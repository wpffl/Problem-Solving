import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    private final static int MIN = 0;
    private final static int MAX = 100;
    private static int[] boxH = new int[MAX];
     
    // dump하는 함수
    private static void dump() {
        Arrays.sort(boxH);
        boxH[MIN] += 1;
        boxH[MAX-1] -= 1;   
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        for (int t = 1; t <= 10; t++) {
            int cnt = sc.nextInt();
             
            // 입력 받은 박스 높이 배열에 저장
            for (int i = 0; i < MAX; i++) {
                boxH[i] = sc.nextInt();
            }
             
            for (int i = 0; i < cnt; i++) {
                dump();
            }
             
            Arrays.sort(boxH);
             
            System.out.println("#" + t + " " + (boxH[MAX-1]-boxH[MIN]));
        }
    }
}
