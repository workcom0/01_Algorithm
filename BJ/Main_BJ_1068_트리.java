package study0604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_BJ_1068_트리 {
	public static class Node{
		int data;		
		List<Node> list = new ArrayList<Node>();		

		public Node(int data) {
			super();
			this.data = data;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Node[] tree = new Node[N];
		for(int i=0; i<N; i++) {
			tree[i] = new Node(i);
		}

		int root = -1;
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			int parent = Integer.parseInt(str[i]);
			
			if(parent==-1) {
				root = i;
				continue;
			}
			
			tree[parent].list.add(tree[i]);
		}
		
		int DEL = Integer.parseInt(br.readLine());
		if(DEL == root) {
			System.out.println(0);
		}else {
			delete(N, root, tree, DEL);
			solve(N, root, tree);
		}
		
	}
	
	public static void solve(int N, int root, Node[] tree) {
		Queue<Integer> queue = new LinkedList<>();
		
		int count = 0;
		queue.offer(root);
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(tree[cur].list.size()==0) count++;
			else {
				for(int i=0; i<tree[cur].list.size(); i++) {
					int next = tree[cur].list.get(i).data;
					queue.offer(next);
					
				}
			}		
		}
		
		System.out.println(count);
	}
	
	public static void delete(int N, int root, Node[] tree, int DEL) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(root);
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int i=0; i<tree[cur].list.size(); i++) {
				int next = tree[cur].list.get(i).data;
				if(next==DEL) {
					tree[cur].list.remove(i);
					return;
				}else {
					queue.offer(next);
				}
			}
		}
	}
}
