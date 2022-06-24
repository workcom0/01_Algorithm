package study0624;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_9466_텀프로젝트 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] list = new int[N+1];
			
			String[] str = br.readLine().split(" ");
			for(int i=1; i<=N; i++) {
				list[i] = Integer.parseInt(str[i-1]);
			}
			
			visited = new int[N+1];
			success = false;
			index = 0;
			count = N;
			for(int i=1; i<=N; i++) {
				if(visited[i]==0) {
					solve(N, list, i);
				}
			}
			
			System.out.println(count);
		}
	}
	
	public static int[] visited;
	public static boolean success;
	public static int index;
	public static int count;
	public static void solve(int N, int[] list, int cur) {
		if(visited[cur]==0) {
			visited[cur] = 1;
			solve(N, list, list[cur]);
			visited[cur] = 2;
			if(success) {
				if(cur==index) success = false;
				count--;
			}
		}else if(visited[cur]==1) {
			index = cur;
			success = true;
			return;
		}else {
			success = false;
			return;
		}
	}
}
