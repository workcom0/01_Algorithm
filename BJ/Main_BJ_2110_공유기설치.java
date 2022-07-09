package study0709;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BJ_2110_공유기설치 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int C = Integer.parseInt(str[1]);
		
		int min=1;
		int max=0;
		
		int[] map = new int[N];
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, map[i]);
		}
		Arrays.sort(map);
		
		while(true) {
			int decision = solve(N, C, min, max, map);
			
			if(min>=max) break;
			
			if(decision==-1) {
				max = (min+max)/2-1;
			}else if(decision==1) {
				min = (min+max)/2+1;
			}
		}
		
		System.out.println(ans);
	}
	
	public static int ans = 0;
	public static int solve(int N, int C, int min, int max, int[] map) {
		int mid = (min+max)/2;
		
		int tmp=0;
		int cnt=0;
		for(int i=0; i<N; i++) {
			if(i==0) {
				tmp=map[i];
				cnt++;
				continue;
			}
			
			if(map[i]-tmp>=mid) {
				cnt++;
				tmp=map[i];
			}else {
				continue;
			}
			
			if(cnt==C) {
				ans = mid;
				return 1;
			}
		}
		
		return -1;
	}
}
