import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int i, j = 0;

        while(j < b){
            for(i=0; i<a; i++) {
                System.out.print("*");
            }
            System.out.print("\n");
            j++;
        }
    }
}
