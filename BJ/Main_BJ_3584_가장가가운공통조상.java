package com.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_BJ_3584_가장가가운공통조상 {
	public static class Node{
		int node;

		public Node(int node) {
			super();
			this.node = node;
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			List<Node>[] list = new List[N+1];
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<Node>();
			}
			
			for(int i=0; i<N-1; i++) {
				String[] str = br.readLine().split(" ");
				int A = Integer.parseInt(str[0]);
				int B = Integer.parseInt(str[1]);
				
				list[B].add(new Node(A));
			}
			
			String[] str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.offer(A);
			boolean[] check = new boolean[N+1];
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				
				
				check[cur] = true;
				
				
				for(int i=0; i<list[cur].size(); i++) {
					queue.offer(list[cur].get(i).node);
				}
			}
			
			queue.offer(B);
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				
				if(check[cur]) {
					System.out.println(cur);
					break;
				}
				
				for(int i=0; i<list[cur].size(); i++) {
					queue.offer(list[cur].get(i).node);
				}
			}
		}
	}
}
