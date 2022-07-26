package study220726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_16564_히오스프로게이머 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			int X = Integer.parseInt(br.readLine());
			arr[i] = X;
			
			min = Math.min(min, X);
			max = Math.max(max, X);
		}
		
		max += K;
		
		while(true) {
			int mid = (min+max)/2;
			
			if(solve(N, K, arr, mid)==1) {
				min = mid+1;
			}else if(solve(N, K, arr, mid)==-1) {
				max = mid-1;
			}
			
			if(min>max) break;
		}
		
		System.out.println(ans);
	}
	
	public static int ans = 0;
	public static int solve(int N, int K, int[] arr, int mid) {
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			int tmp = mid - arr[i];
			if(tmp<=0) continue;
			
			sum += tmp;
			
			if(sum>K) return -1;
		}
		
		ans = mid;
		return 1;
	}
}
