package study0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_9935_문자열폭발 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<str1.length; i++) {
			char ch = str1[i];
			stack.add(ch);
			
			if(stack.size()>=str2.length && stack.peek()==str2[str2.length-1]) {
				Stack<Character> tmp = new Stack<>();
				boolean success = false;
				
				for(int k=str2.length-1; k>=0; k--) {
					char tmpCh = stack.pop();
					if(tmpCh==str2[k]) {
						tmp.add(tmpCh);
						success = true;
					}else {
						tmp.add(tmpCh);
						success = false;
						break;
					}
				}
				
				if(!success) {
					int count = tmp.size();
					for(int k=0; k<count; k++) {
						stack.add(tmp.pop());
					}
				}
			}
		}
		
		if(stack.size()==0) System.out.println("FRULA");
		else {
			int count = stack.size();
			for(int i=0; i<count; i++) {
				sb.append(stack.pop());
			}
			System.out.println(sb.reverse().toString());
		}
	}
}
