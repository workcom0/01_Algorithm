package com.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_9372_상근이의여행 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int M = Integer.parseInt(str[1]);
			
			int[][] map = new int[N+1][N+1];
			for(int i=0; i<M; i++) {
				str = br.readLine().split(" ");
				int a = Integer.parseInt(str[0]);
				int b = Integer.parseInt(str[1]);
				
				map[a][b] = 1;
				map[b][a] = 1;
			}
			
			System.out.println(solve(N, map, 1));
		}
	}
	
	public static int solve(int N, int[][] map, int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] check = new boolean[N+1];
		int count = 0;
		
		queue.offer(start);
		check[start] = true;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int i=2; i<=N; i++) {
				if(!check[i] && map[cur][i]==1) {
					queue.offer(i);
					check[i] = true;
					count++;
				}
			}
		}
		return count;
	}
}
