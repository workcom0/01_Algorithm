package com.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_9012_괄호 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = null;
		
		int T = Integer.parseInt(br.readLine());
		
		outer: for(int t=0; t<T; t++) {
			stack = new Stack<>();
			
			String[] str = br.readLine().split("");
			for(int i=0; i<str.length; i++) {
				char c = str[i].charAt(0);
				
				if(c=='(') {
					stack.add(c);
				}else {
					if(stack.size()==0) {
						sb.append("NO\n");
						continue outer;
					}else if(stack.peek()=='(') {
						stack.pop();
					}
				}
			}
			
			if(stack.size()==0) {
				sb.append("YES\n");
			}else {
				sb.append("NO\n");
			}
		}

		
		
		System.out.println(sb.toString());
	}
}
