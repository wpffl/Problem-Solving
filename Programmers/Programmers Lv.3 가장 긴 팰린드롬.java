import java.util.*;
class Solution
{
    public int solution(String s)
    {
       int left = 0, right = 0;
        int result = 1;

        if(s.length() != 1) {
            for (int center = 1; center < s.length() - 1; center++) {
                left = center - 1;
                right = center + 1;
                while (left >= 0 && right <= s.length() - 1) {
                    if (s.charAt(left) != s.charAt(right))
                        break;

                    result = right - left + 1 > result ? right - left + 1 : result;
                    left--;
                    right++;
                }
            }

            for (int center = 0; center <= s.length() - 2; center++) {
                left = center;
                right = center + 1;
                while (left >= 0 && right <= s.length() - 1) {
                    if (s.charAt(left) != s.charAt(right))
                        break;
                    result = right - left + 1 > result ? right - left + 1 : result;
                    left--;
                    right++;
                }
            }
        }

        return result;
    }
}
