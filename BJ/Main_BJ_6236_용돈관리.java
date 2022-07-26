package study220715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BJ_6236_¿ëµ·°ü¸® {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			min = Math.min(min, arr[i]);
			max = max+arr[i];
		}
		
		while(true) {
			int mid = (min+max)/2;
			
			if(solve(N, M, arr, mid)==1) {
				min = mid+1;
			}else if(solve(N, M, arr, mid)==-1) {
				max = mid-1;
			}
			
			if(min>max) break;
		}
		
		System.out.println(ans);
	}
	
	public static int ans = 0;
	public static int solve(int N, int M, int[] arr, int mid) {
		int count = 0;
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			if(arr[i] > sum) {
				count++;
				sum = mid-arr[i];
			}else {
				sum = sum-arr[i];
			}
			
			if(count>M) return 1;
		}
		
		ans = mid;
		return -1;
	}
}
