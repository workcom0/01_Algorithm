package com.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_BJ_14938_서강그라운드 {
	public static class Node implements Comparable<Node>{
		int index;
		int d;
		
		public Node(int index, int d) {
			super();
			this.index = index;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return this.d-o.d;
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int R = Integer.parseInt(str[2]);
		
		str = br.readLine().split(" ");
		int[] items = new int[N+1];
		for(int i=1; i<=N; i++) {
			items[i] = Integer.parseInt(str[i-1]);
		}
		
		int[][] map = new int[N+1][N+1];	
		for(int i=0; i<R; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int l = Integer.parseInt(str[2]);
			
			map[a][b] = l;
			map[b][a] = l;
		}
		
		for(int i=1; i<=N; i++) {
			solve(N, M, items, map, i);
		}
		System.out.println(max);
	}
	
	public static int max = 0;
	public static void solve(int N, int M, int[] items, int[][] map, int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int[] distance = new int[N+1];
		boolean[] check = new boolean[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		queue.offer(new Node(start, 0));
		distance[start] = 0;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(check[cur.index]) continue;
			check[cur.index] = true;
			distance[cur.index] = cur.d;
			
			for(int i=1; i<=N; i++) {
				if(cur.index==i) continue;
				if(map[cur.index][i]!=0 &&distance[i] > cur.d+map[cur.index][i] && cur.d+map[cur.index][i]<=M) {
					queue.offer(new Node(i, cur.d+map[cur.index][i]));
				}
			}
		}
		
		int sum = 0;
		for(int i=1; i<=N; i++) {
			if(check[i]) sum+=items[i];
		}
		max = Math.max(max, sum);
	}
}
