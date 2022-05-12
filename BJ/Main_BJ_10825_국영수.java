package com.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BJ_10825_국영수 {
	public static class Grade implements Comparable<Grade>{
		String name;
		int kor;
		int eng;
		int mat;
		
		public Grade(String name, int kor, int eng, int mat) {
			super();
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.mat = mat;
		}

		@Override
		public int compareTo(Grade o) {
			return this.kor!=o.kor ? o.kor-this.kor: (this.eng!=o.eng? this.eng-o.eng: (this.mat!=o.mat? o.mat-this.mat: this.name.compareTo(o.name)));
		}
		
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Grade> queue = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			String name = str[0];
			int kor = Integer.parseInt(str[1]);
			int eng = Integer.parseInt(str[2]);
			int mat = Integer.parseInt(str[3]);
			
			queue.offer(new Grade(name, kor, eng, mat));
		}
		
		for(int i=0; i<N; i++) {
			sb.append(queue.poll().name).append("\n");
		}
		System.out.println(sb.toString());
	}
}
