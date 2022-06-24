package study0623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main_BJ_16943_숫자재배치 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		char[] A = str[0].toCharArray();
		Arrays.sort(A);
		
		int B = Integer.parseInt(str[1]);
		
		int N = str[0].length();
		String ans = "";
		boolean[] visit = new boolean[N];
		solve(N, A, B, ans, visit, 0);
		
		if(success) {
			System.out.println(answer);
		}else {
			System.out.println(-1);
		}
	}
	
	public static boolean success = false;
	public static String answer = "";
	public static void solve(int N, char[] A, int B, String ans, boolean[] visit, int cnt) {
		if(success) return;
		
		if(cnt==N) {
			int a = Integer.parseInt(ans);

			if(a<B) {
				success=true;
				answer = ans;
			}
			
			return;
		}
		
		for(int i=N-1; i>=0; i--) {
			if(cnt==0 && A[i]=='0') continue;
			if(visit[i]) continue;
			
			visit[i] = true;
			solve(N, A, B, ans+A[i], visit, cnt+1);
			visit[i] = false;
		}
		
		return;
	}
}
