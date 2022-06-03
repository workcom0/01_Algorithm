package study0603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_BJ_11725_트리의부모찾기 {
	public static class Node{
		int node;

		public Node(int node) {
			super();
			this.node = node;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		List<Node>[] list = new List[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		
		for(int i=0; i<N-1; i++) {
			String[] str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			
			list[A].add(new Node(B));
			list[B].add(new Node(A));
		}
		
		solve(N, list);
	}
	
	public static void solve(int N, List<Node>[] list) {
		Queue<Node> queue = new LinkedList<Node>();
		int[] parent = new int[N+1];
		
		//1이 트리의 루트니까 시작점으로 함
		queue.offer(new Node(1));
		parent[1] = -1;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			//현재 cur노드와 연결되어있으면서 아직 방문하지않은 노드(cur의 자식)를 찾아서 부모노드 저장함
			for(int i=0; i<list[cur.node].size(); i++) {
				Node next = list[cur.node].get(i);
				
				if(parent[next.node]==0) {
					queue.offer(next);
					parent[next.node] = cur.node;
				}
			}
		}
		
		for(int i=2; i<=N; i++) {
			System.out.println(parent[i]);
		}
	}
}
