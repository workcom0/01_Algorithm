package study0626;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_11047_동전0 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		
		int[] coin = new int[N+1];
		for(int i=1; i<=N; i++) {
			int A = Integer.parseInt(br.readLine());
			
			coin[i] = A;
		}
		
		int count = 0;
		int index = N;
		while(true) {
			if(index==0) break;
			
			if(coin[index] > K) {
				index--;
				continue;
			}
			
			int cnt = K/coin[index];
			count += cnt;
			K = K-coin[index]*cnt;
			index--;
		}
		
		System.out.println(count);
	}
}
