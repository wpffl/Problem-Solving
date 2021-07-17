import java.util.HashMap;

class Solution {
    public int solution(String s) {
		int answer = 0;
		HashMap<String, Integer> map = new HashMap<>();

		map.put("zero", 0);
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		map.put("six", 6);
		map.put("seven", 7);
		map.put("eight", 8);
		map.put("nine", 9);

		int len = s.length();
		StringBuilder sb = new StringBuilder();
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < len; i++) {
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9') { 
				res.append(s.charAt(i));
			} else {
				sb.append(s.charAt(i));
			}
			
			if(map.get(sb.toString()) == null) {
				continue;
			} else {
				res.append(map.get(sb.toString()));
				sb.setLength(0);
			}
		}

		answer = Integer.parseInt(res.toString());
		return answer;
    }
}
