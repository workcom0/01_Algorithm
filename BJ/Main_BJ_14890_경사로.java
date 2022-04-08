package bj1152;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_14890_경사로 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int L = Integer.parseInt(str[1]);
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int k=0; k<N; k++) {
				map[i][k] = Integer.parseInt(str[k]);
			}
		}
		
		int count = 0;
		for(int i=0; i<N; i++) {
			count+= solve1(N, L, arr(N, map[i]));
		}
		
		for(int i=0; i<N; i++) {
			int[] arr = new int[N];
			for(int k=0; k<N; k++) {
				arr[k] = map[k][i];
			}
			count+=solve1(N, L, arr);
		}
		System.out.println(count);
	}
	
	public static int[] arr(int N, int[] map) {
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = map[i];
		}
		
		return arr;
	}
	
	public static int solve1(int N, int L, int[] map) {
		int count = 0;
		
		int standardIndex = 0;
		int standardVal = map[0];
		int len = 0;
		boolean check = true;
		for(int i=0; i<N; i++) {
			if(standardVal==map[i]) {
				standardIndex = i;
				standardVal = map[i];
				len++;
			}
			else if(standardVal+1==map[i]) {
				if(len>=L) {
					standardIndex = i;
					standardVal = map[i];
					i = i-1;
					len = 0;
				}else {
					check = false;
					break;
				}
			}else if(standardVal-1==map[i]) {
				if(i+L-1<N) {
					standardVal = map[i];
					for(int j=i; j<=i+L-1; j++) {
						if(standardVal!=map[j]) {
							check = false;
							break;
						}
					}
					
					if(check) {
						standardIndex = i+L-1;
						standardVal = map[i];
						i=i+L-1;
						len = 0;
					}else {
						break;
					}
					
				}else {
					check=false;
					break;
				}
			}else if(Math.abs(standardVal-map[i]) > 1) {
				check = false;
				break;
			}
			
		}
		
		if(check==false) {
			return 0;
		}else {
			return 1;
		}
		
	}
}
