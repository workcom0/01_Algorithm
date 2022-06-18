package study0618;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BJ_2668_숫자고르기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] list = new List[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=1; i<=N; i++) {
			list[i].add(Integer.parseInt(br.readLine()));
		}
		
		boolean[] visit = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			if(!visit[i]) {
				boolean[] tmp = new boolean[N+1];
				List<Integer> tmp_list = new ArrayList<>();
				success = false;
				solve(N, list, visit, tmp, i, i, tmp_list);
			}
		}
		
		System.out.println(count);
		for(int i=1; i<=N; i++) {
			if(visit[i]) System.out.println(i);
		}
	}
	
	public static boolean success = false;
	public static int count = 0;
	public static void solve(int N, List<Integer>[] list, boolean[] visit, boolean[] tmp, int start, int cur, List<Integer> tmp_list) {
		if(visit[cur]) return;
		
		if(tmp[cur] && cur==start) {
			success = true;
			for(int i=0; i<tmp_list.size(); i++) {
				int a = tmp_list.get(i);
				visit[a] = true;
				count++;
			}
			return;
		}
		
		if(tmp[cur] && cur!=start) {
			return;
		}
		
		if(success) return;
		
		tmp[cur] = true;
		tmp_list.add(cur);
		solve(N, list, visit, tmp, start, list[cur].get(0), tmp_list);
	}
}
