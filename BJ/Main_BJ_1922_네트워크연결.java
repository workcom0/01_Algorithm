package com.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_BJ_1922_네트워크연결 {
	
	public static class Point implements Comparable<Point>{
		int node;
		int w;
		
		public Point(int node, int w) {
			super();
			this.node = node;
			this.w = w;
		}

		@Override
		public int compareTo(Point o) {
			return this.w-o.w;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<Point>[] list = new List[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			String[] str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			int C = Integer.parseInt(str[2]);
			
			if(A==B) continue;
			
			list[A].add(new Point(B, C));
			list[B].add(new Point(A, C));
		}
		
		solve(N, list, 1);
	}
	
	public static void solve(int N, List<Point>[] list, int start) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		boolean[] check = new boolean[N+1];
		int sum = 0;
		
		queue.offer(new Point(start, 0));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(check[cur.node]) continue;
			check[cur.node] = true;
			sum += cur.w;
			
			for(int i=0; i<list[cur.node].size(); i++) {
				if(!check[list[cur.node].get(i).node]) {
					queue.add(new Point(list[cur.node].get(i).node, list[cur.node].get(i).w));
				}
			}
		}
		
		System.out.println(sum);
	}
}
