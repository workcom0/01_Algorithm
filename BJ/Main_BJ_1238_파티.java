package com.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_BJ_1238_파티 {
	public static class Node implements Comparable<Node>{
		int node;
		int w;
		public Node(int node, int w) {
			super();
			this.node = node;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.w-o.w;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int X = Integer.parseInt(str[2]);
		int[][] map = new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			int W = Integer.parseInt(str[2]);
			map[A][B] = W;
		}
		solve(N, X, map);
	}
	
	public static void solve(int N, int X, int[][] map) {
		PriorityQueue<Node> queue = new PriorityQueue<>();		
		int[] ans = new int[N+1];
				
		for(int start=1; start<=N; start++) {
			int[] distance = new int[N+1];
			boolean[] check = new boolean[N+1];
			Arrays.fill(distance, Integer.MAX_VALUE);
			
			queue.offer(new Node(start, 0));
			while(!queue.isEmpty()) {
				Node cur = queue.poll();
				
				if(check[cur.node]) continue;
				check[cur.node] = true;
				distance[cur.node] = cur.w;
				
				if(cur.node==X) {
					ans[start] = distance[cur.node];
					queue.clear();
					break;
				}
				
				for(int i=1; i<=N; i++) {
					if(map[cur.node][i]!=0 && distance[i] > distance[cur.node]+map[cur.node][i]) {
						queue.offer(new Node(i, distance[cur.node]+map[cur.node][i]));
					}
				}
			}
		}
		
		int[] distance = new int[N+1];
		boolean[] check = new boolean[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		queue.offer(new Node(X, 0));
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(check[cur.node]) continue;
			check[cur.node] = true;
			distance[cur.node] = cur.w;
			
			for(int i=1; i<=N; i++) {
				if(map[cur.node][i]!=0 && distance[i] > distance[cur.node]+map[cur.node][i]) {
					queue.offer(new Node(i, distance[cur.node]+map[cur.node][i]));
				}
			}
		}
		
		//System.out.println(Arrays.toString(ans));
		int max=0;
		for(int i=1; i<=N; i++) {
			max = Math.max(max,ans[i]+distance[i]);
		}
		System.out.println(max);
	}
}
