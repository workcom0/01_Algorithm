package study0709;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2343_기타레슨 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		int start=0;
		int end=0;
		int[] list = new int[N];
		str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(str[i]);
			
			start = Math.max(start, list[i]);
			end += list[i];
		}
		
		while(true) {
			int decision = solve(N, M, start, end, list);
			
			if(start>=end) break;
			
			if(decision==-1) {
				end = (start+end)/2-1;
			}else if(decision==1) {
				start = (start+end)/2+1;
			}
		}
		
		System.out.println(ans);
		
	}
	
	public static int ans = 0;
	public static int solve(int N, int M, int start, int end, int[] list) {
		int mid = (start+end)/2;
				
		int sum = 0;
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(sum+list[i] > mid) {
				cnt++;
				sum=0;
				i--;
			}else {
				sum += list[i];
			}
			
			if(cnt>=M) {
				return 1;
			}
		}
		
		ans = mid;
		return -1;
	}
}
