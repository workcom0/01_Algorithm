package com.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_BJ_2660_회장뽑기 {
	public static class Candidate implements Comparable<Candidate>{
		int no;
		int w;
		
		public Candidate(int no, int w) {
			super();
			this.no = no;
			this.w = w;
		}
		
		@Override
		public int compareTo(Candidate o) {
			return this.w!=o.w? this.w-o.w:this.no-o.no;
		}
	}
	
	public static List<Candidate> list = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());		
		int[][] map = new int[N+1][N+1];
		
		while(true) {
			String[] str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			
			if(A==-1 && B==-1) break;
			
			map[A][B] = 1;
			map[B][A] = 1;
		}
		
		for(int i=1; i<=N; i++) {
			solve(N, map, i);
		}
		
		Collections.sort(list);
		
		System.out.println(min + " " + count);
		for(int i=0; i<count; i++) {
			System.out.print(list.get(i).no + " ");
		}
	}
	
	public static int min = Integer.MAX_VALUE;
	public static int count = 0;
	public static void solve(int N, int[][] map, int start) {
		Queue<Candidate> queue = new LinkedList<>();
		boolean[] check = new boolean[N+1];
		int cnt = 0;
		
		queue.offer(new Candidate(start, 0));
		check[start] = true;
		cnt = 0;
		while(!queue.isEmpty()) {
			Candidate cur = queue.poll();
			
			for(int i=1; i<=N; i++) {
				if(!check[i] && map[cur.no][i]==1) {
					queue.offer(new Candidate(i, cur.w+1));
					check[i] = true;
					cnt = Math.max(cnt, cur.w+1);
				}
			}
		}
		
		if(min > cnt) {
			min = cnt;
			count = 1;
		}else if(min < cnt) {
			
		}else {
			count++;
		}
		
		list.add(new Candidate(start, cnt));
	}
	
}
