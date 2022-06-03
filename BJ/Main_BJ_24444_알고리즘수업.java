package com.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_BJ_24444_알고리즘수업 {
	public static class Node {
		int node;

		public Node(int node) {
			super();
			this.node = node;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int R = Integer.parseInt(str[2]);
		
		List<Node>[] list = new List[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			list[a].add(new Node(b));
			list[b].add(new Node(a));
		}
		
		for(int i=1; i<=N; i++) {
			Collections.sort(list[i], new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return o1.node - o2.node;
				}
			});
		}
		
		solve(N, list, R);
	}
	
	public static void solve(int N, List<Node>[] list, int start) {
		Queue<Node> queue = new LinkedList<Node>();
		int[] check = new int[N+1];
		int count = 1;
		StringBuilder sb = new StringBuilder();
		
		queue.offer(new Node(start));
		check[start] = count++;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for(int i=0; i<list[cur.node].size(); i++) {
				Node next = list[cur.node].get(i);
				
				if(check[next.node]==0) {
					queue.offer(new Node(next.node));
					check[next.node] = count++;
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			sb.append(check[i] + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
