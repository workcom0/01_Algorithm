package com.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_BJ_1753_최단경로 {
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
		int V = Integer.parseInt(str[0]);
		int E = Integer.parseInt(str[1]);
		
		int K = Integer.parseInt(br.readLine());
		
		List<Node>[] list = new List[V+1];
		
		for(int i=1; i<=V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			str = br.readLine().split(" ");
			int u = Integer.parseInt(str[0]);
			int v = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);
			
			list[u].add(new Node(v,w));
		}
		
		solve(V, K, list);
	}
	
	public static void solve(int V, int K, List<Node>[] list) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] check = new boolean[V+1];
		int[] distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		queue.offer(new Node(K, 0));
		distance[K] = 0;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for(int i=0; i<list[cur.node].size(); i++){
				Node next = list[cur.node].get(i);
				
				if(distance[next.node] > cur.w + next.w) {
					queue.offer(new Node(next.node, cur.w + next.w));
					distance[next.node] = cur.w + next.w;
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
			if(distance[i]==Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(distance[i]);
			}
		}
	}
}
