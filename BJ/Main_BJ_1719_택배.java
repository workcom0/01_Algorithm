package study0604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main_BJ_1719_택배 {
	public static class Node implements Comparable<Node>{
		int node;
		int w;
		int first;
		
		public Node(int node, int w, int first) {
			super();
			this.node = node;
			this.w = w;
			this.first = first;
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
		
		List<Node>[] list = new List[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			int W = Integer.parseInt(str[2]);
			
			list[A].add(new Node(B, W, 0));
			list[B].add(new Node(A, W, 0));
		}
		
		int[][] ans = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(ans[i], Integer.MAX_VALUE);
		}
		
		for(int i=1; i<=N; i++) {
			solve(N, list, ans, i);
		}
		
		for(int i=1; i<=N; i++) {
			for(int k=1; k<=N; k++) {
				if(ans[i][k]==0) {
					System.out.print("- ");
				}else {
					System.out.print(ans[i][k] + " ");
				}
			}
			System.out.println();
		}
	}
	
	public static void solve(int N, List<Node>[] list, int[][] ans, int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] visit = new boolean[N+1];
		int[] count = new int[N+1];
		Arrays.fill(count, Integer.MAX_VALUE);
		
		queue.offer(new Node(start, 0, 0));
		count[start] = 0;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(visit[cur.node]) continue;
			visit[cur.node] = true;
			ans[start][cur.node] = cur.first;
			
			for(int i=0; i<list[cur.node].size(); i++) {
				Node next = list[cur.node].get(i);
				
				if(!visit[next.node] && count[next.node]>cur.w+next.w) {
					if(cur.node==start) {
						queue.offer(new Node(next.node, cur.w+next.w, next.node));
						count[next.node] = cur.w + next.w;
					}else {
						queue.offer(new Node(next.node, cur.w+next.w, cur.first));
						count[next.node] = cur.w + next.w;
					}					
				}
			}
		}
	}
}
