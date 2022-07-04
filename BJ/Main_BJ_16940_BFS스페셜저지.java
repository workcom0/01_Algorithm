package study0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_BJ_16940_BFS스페셜저지 {
	public static class Node{
		int node;
		int parent;
		
		public Node(int node, int parent) {
			super();
			this.node = node;
			this.parent = parent;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		List<Integer>[] list = new List[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<N-1; i++) {
			String[] str = br.readLine().split(" ");
			int A = Integer.parseInt(str[0]);
			int B = Integer.parseInt(str[1]);
			
			list[A].add(B);
			list[B].add(A);
		}
		
		int[] ans = new int[N];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			int node = Integer.parseInt(str[i]);
			
			ans[i] = node;
		}
		
		if(solve(N, list, ans)) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
	
	public static boolean solve(int N, List<Integer>[] list, int[] ans) {
		Set<Integer> set = new HashSet<>();
		boolean[] visit = new boolean[N+1];
		
		
		set.add(1);
		visit[1] = true;
		int count = 1;
		int parent = 0;
		int index = 0;
		while(index<N) {
			for(int i=0; i<count; i++) {
				if(set.contains(ans[index])) {
					set.remove(ans[index]);
					index++;
				}else return false;
			}

			int node = ans[parent++];		
			
			count = 0;
			for(int i=0; i<list[node].size(); i++) {
				if(!visit[list[node].get(i)]) {
					set.add(list[node].get(i));
					visit[list[node].get(i)] = true;
					count++;
				}
			}
		}
		return true;
	}
}
