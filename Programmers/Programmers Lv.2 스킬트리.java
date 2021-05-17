import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        ArrayList<String> skillTrees = new ArrayList<String>(Arrays.asList(skill_trees));
        Iterator<String> ite = skillTrees.iterator();
        
        while(ite.hasNext()) {
            if(skill.indexOf(ite.next().replaceAll("[^" + skill + "]", "")) != 0)
                ite.remove();
        }
        answer = skillTrees.size();
        return answer;
    }
}
