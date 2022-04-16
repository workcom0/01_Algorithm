package com.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_5567_결혼식 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1];
		
		int M = Integer.parseInt(br.readLine());	
		for(int i=0; i<M; i++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		solve(N, map, 1);
	}
	
	public static void solve(int N, int[][] map, int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] check = new boolean[N+1];
		int[] cnt = new int[N+1];
		int count = 0;
		
		queue.offer(start);
		check[start] = true;	
		cnt[start] = 0;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(cnt[cur]==2) continue;
			
			for(int i=1; i<=N; i++) {
				if(!check[i] && map[cur][i]!=0) {
					queue.offer(i);
					check[i] = true;	
					cnt[i] += cnt[cur]+1;
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
}
