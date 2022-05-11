package level2;

import java.util.HashMap;

public class Solution_12981_영어끝말잇기 {
	public static void main(String[] args) {
		int n = 3;
		String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
		int turn = 1;
		int who = 1;
		HashMap<String, Integer> map = new HashMap<>();
		String beforeWord = " ";
		
		for(int i=0; i<words.length; i++) {
			if(map.containsKey(words[i])) {
				break;
			}else if(i>=1 && beforeWord.charAt(beforeWord.length()-1)!=words[i].charAt(0)) {
				break;
			}else {
				map.put(words[i], 0);
				beforeWord = words[i];
				
				if((i+1)%n==0) {
					turn ++;
				}
				
				who = (who)%(n)+1;
			}
		}
		
		int[] answer = new int[2];
		if(words.length == map.size()) {
			answer[0] = 0;
			answer[1] = 0;
		}else {
			answer[0] = who;
			answer[1] = turn;
		}
		System.out.println(who + " " + turn);
	}
}
