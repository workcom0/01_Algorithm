package study220805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BJ_16401_과자나눠주기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int M = Integer.parseInt(str[0]);
		int N = Integer.parseInt(str[1]);
		List<Integer> list = new ArrayList<>();
		int min = 1;
		int max = 0;
		
		str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(str[i]));
			max = Math.max(max, list.get(i));
		}
		
		while(true) {
			int mid = (min+max)/2;
			
			if(solve(M, N, list, mid)==1) {
				min = mid+1;
			}else if(solve(M, N, list, mid)==-1) {
				max = mid-1;
			}
			
			if(min > max) break;
		}
		
		System.out.println(ans);		
	}
	
	public static int ans = 0;
	public static int solve(int M, int N, List<Integer> list, int mid) {
		
		int count = 0;
		for(int i=0; i<list.size(); i++) {
			if(list.get(i)<mid) continue;
			
			count += list.get(i)/mid;
			
			if(count>=M) {
				ans = mid;
				return 1;
			}
		}
		
		return -1;
	}
}
