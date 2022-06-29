package study0629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Node클래스에 현재까지 왔을때의 경로를 기록해두는 list를 사용.
*/
public class Main_BJ_11779_최소비용구하기2 {
	public static class Node implements Comparable<Node>{
		int node;
		int w;
		List<Integer> seq = new ArrayList<>();
		
		//인접 리스트 만들때 사용하는 생성자
		public Node(int node, int w) {
			super();
			this.node = node;
			this.w = w;
		}
		
		//큐에 start값 넣을 때 사용하는 생성자
		public Node(int node, int w, int val) {
			super();
			this.node = node;
			this.w = w;
			this.seq.add(val);
		}

		//큐에 start값을 제외한 값을 넣을 때 사용하는 생성자
		public Node(int node, int w, List<Integer> seq) {
			super();
			this.node = node;
			this.w = w;
			for(int i=0; i<seq.size(); i++) {
				this.seq.add(seq.get(i));
			}
			this.seq.add(node);
		}

		@Override
		public int compareTo(Node o) {
			return this.w-o.w;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<Node>[] list = new List[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			String[] str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			int W = Integer.parseInt(str[2]);
			
			list[A].add(new Node(B, W));
		}
		
		String[] str = br.readLine().split(" ");
		int start = Integer.parseInt(str[0]);
		int end = Integer.parseInt(str[1]);

		solve(N, list, start, end);
	}
	
	public static void solve(int N, List<Node>[] list, int start, int end) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		queue.offer(new Node(start, 0, start));
		while(!queue.isEmpty()) {
			Node cur = queue.poll();			
			
			if(visited[cur.node]) continue;
			visited[cur.node] = true;
			
			if(cur.node==end) {
				System.out.println(distance[cur.node]);
				System.out.println(cur.seq.size());
				for(int i=0; i<cur.seq.size(); i++) {
					System.out.print(cur.seq.get(i)+" ");
				}
				return;
			}
			
			for(int i=0; i<list[cur.node].size(); i++) {
				Node next = list[cur.node].get(i);
				
				if(distance[next.node] > cur.w+next.w) {
					distance[next.node] = cur.w+next.w;
					queue.offer(new Node(next.node, cur.w+next.w, cur.seq));
				}
			}
		}
	}
}
