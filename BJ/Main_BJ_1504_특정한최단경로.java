package study0604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main_BJ_1504_특정한최단경로 {
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
	
	public static int distance1 = 0;
	public static int distance2 = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int E = Integer.parseInt(str[1]);
		
		List<Node>[] list = new List[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<E; i++) {
			str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			int C = Integer.parseInt(str[2]);
			
			list[A].add(new Node(B, C));
			list[B].add(new Node(A, C));
		}
		
		str = br.readLine().split(" ");
		int V1 = Integer.parseInt(str[0]);
		int V2 = Integer.parseInt(str[1]);
		
		distance1 = 0;
		//1 -> 정점1 -> 정점2 -> N
		if(!solve(N, list, 1, V1, 1) || !solve(N, list, V1, V2, 1) || !solve(N, list, V2, N, 1)) {
			distance1 = Integer.MAX_VALUE;
		}

		
		distance2 = 0;
		//1 -> 정점2 -> 정점1 -> N
		if(!solve(N, list, 1, V2, 2) || !solve(N, list, V2, V1, 2) || !solve(N, list, V1, N, 2)) {
			distance2 = Integer.MAX_VALUE;
		}
		
		int ans = Math.min(distance1, distance2);
		
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}	
	}
	
	public static boolean solve(int N, List<Node>[] list, int start, int end, int choice) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] visit = new boolean[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		queue.offer(new Node(start, 0));
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(cur.node==end) {
				if(choice==1) {
					distance1 += cur.w;
				}else {
					distance2 += cur.w;
				}
				return true;
			}
			
			if(visit[cur.node]) continue;
			visit[cur.node] = true;
			
			for(int i=0; i<list[cur.node].size(); i++) {
				Node next = list[cur.node].get(i);

				if(distance[next.node] > cur.w+next.w) {
					queue.offer(new Node(next.node, cur.w+next.w));
					distance[next.node] = cur.w+next.w;
				}
			}
		}
		
		return false;
	}
}
