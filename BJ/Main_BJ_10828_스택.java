package com.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_10828_스택 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			String command = str[0];		
			
			if(command.equals("push")) {
				String x =str[1];
				stack.push(x);
				
			}else if(command.equals("pop")) {
				if(stack.size()==0) {
					sb.append("-1\n");
				}else {
					sb.append(stack.pop()).append("\n");
				}
				
			}else if(command.equals("size")) {
				sb.append(stack.size()).append("\n");
				
			}else if(command.equals("empty")) {
				if(stack.isEmpty()) {
					sb.append("1").append("\n");
				}else {
					sb.append("0").append("\n");
				}
			}else if(command.equals("top")) {
				if(stack.size()==0) {
					sb.append("-1\n");
				}else {
					sb.append(stack.peek()).append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
}
