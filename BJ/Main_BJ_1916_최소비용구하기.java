package study0604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main_BJ_1916_최소비용구하기 {
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
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<Node>[] list = new List[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<M; i++) {
			String[] str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			int W = Integer.parseInt(str[2]);
			
			list[A].add(new Node(B, W));
		}
		
		String[] str = br.readLine().split(" ");
		int A = Integer.parseInt(str[0]);
		int B = Integer.parseInt(str[1]);
		
		solve(N, list, A, B);
	}
	
	public static void solve(int N, List<Node>[] list, int A, int B) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int[] check = new int[N+1];
		boolean[] visit = new boolean[N+1];
		Arrays.fill(check, Integer.MAX_VALUE);
		
		queue.offer(new Node(A, 0));
		check[A] = 0;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(visit[cur.node]) continue;
			visit[cur.node] = true;
			
			for(int i=0; i<list[cur.node].size(); i++) {
				Node next = list[cur.node].get(i);
				
				if(!visit[next.node] && check[next.node]>cur.w+next.w) {
					queue.offer(new Node(next.node, cur.w+next.w));
					check[next.node] = cur.w+next.w;
				}
			}
		}
		
		System.out.println(check[B]);
	}
}
