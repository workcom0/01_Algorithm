package com.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BJ_16496_큰수만들기 {
	public static class Number implements Comparable<Number>{
        int x;
        String str;
        
        public Number(int x){
            this.x = x;
            this.str = String.valueOf(x);
        }
        
        @Override
        public int compareTo(Number o){
            String str1 = this.str + o.str;
            String str2 = o.str + this.str;
            return -str1.compareTo(str2);
        }
    }
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(str[i]);
		}
		System.out.println(solution(numbers));
	}
	
	public static String solution(int[] numbers) {
        PriorityQueue<Number> queue = new PriorityQueue<>();
        for(int i=0; i<numbers.length; i++){
            queue.offer(new Number(numbers[i]));
        }
        
        String answer = "";
        while(!queue.isEmpty()){
            answer += queue.poll().str;
        }
        
        if(answer.length()>1 && answer.charAt(0)=='0')
            return "0";
        return answer;
    }
}
