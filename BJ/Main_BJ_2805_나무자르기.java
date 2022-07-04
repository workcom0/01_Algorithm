package study0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2805_나무자르기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int min = 0;
		int max = 0;
		
		int[] tree = new int[N];
		str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			tree[i] = Integer.parseInt(str[i]);
			max = Math.max(max, tree[i]);
		}
		
		
		while(true) {
			int a = solve(N, M, tree, min, max);
			
			if(a==1) {
				min = standard+1;
			}else {
				max = standard-1;
			}
			
			if(min>max) {
				break;
			}
		}
		
		System.out.println(ans);
	}
	
	public static int ans = 0;
	public static int standard = 0;
	public static int solve(int N, int M, int[] tree, int min, int max) {
		int mid = min + ((max-min)/2);
		
		long sum = 0;
		
		for(int i=0; i<N; i++) {
			if(tree[i]>mid) {
				sum+=tree[i]-mid;
			}
		}
		
		if(sum>=M) {
			ans = mid;
			standard = mid;
			return 1;
		}else {
			standard = mid;
			return -1;
		}
		

	}
}
