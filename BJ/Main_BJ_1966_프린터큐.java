package study0701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_BJ_1966_프린터큐 {
	public static class Paper{
		int index;
		int w;
		
		public Paper(int index, int w) {
			super();
			this.index = index;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int M = Integer.parseInt(str[1]);
			
			str = br.readLine().split(" ");
			List<Integer> list = new ArrayList<>();
			for(int i=0; i<N; i++) {
				list.add(Integer.parseInt(str[i]));
			}
			
			System.out.println(solve(N, M, list));
		}
		
	}
	
	public static int solve(int N, int M, List<Integer> list) {
		Queue<Paper> queue = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			queue.offer(new Paper(i, list.get(i)));
		}
		Collections.sort(list);
		
		int count = 0;
		int max = list.get(list.size()-1);
		while(!queue.isEmpty()) {
			Paper cur = queue.poll();
			
			if(cur.w == max) {
				count++;
				
				if(M==cur.index) {
					return count;
				}
				
				list.remove(list.size()-1);
				max = list.get(list.size()-1);
	
			}else {
				queue.offer(cur);
			}
		}
		
		return -1;
	}
}
