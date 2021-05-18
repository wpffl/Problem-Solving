import java.util.*;

class Solution
{
    public int solution(int[] A, int[] B)
    {
        int answer = 0;
        Integer[] iB = new Integer[B.length];
        
        for(int i=0; i<iB.length; i++)
            iB[i] = B[i];

        Arrays.sort(A);
        Arrays.sort(iB, new Comparator<Integer> () {
            public int compare(Integer i1, Integer i2){
                return i2.compareTo(i1);
            }
        });
 
        for(int i=0; i<A.length; i++)
            answer = answer + (A[i] * iB[i]);

        return answer;
    }
}
