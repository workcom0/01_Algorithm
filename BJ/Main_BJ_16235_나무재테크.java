package bj1152;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_BJ_16235_나무재테크 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int K = Integer.parseInt(str[2]);
		int[][] A = new int[N+1][N+1];
		int[][] ground = new int[N+1][N+1];
		int[][][] tree = new int[250][N+1][N+1];
		boolean[][] isContain = new boolean[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(ground[i], 5);
		}
		
		
		for(int i=1; i<=N; i++) {
			str = br.readLine().split(" ");
			for(int k=0; k<N; k++) {
				A[i][k+1] = Integer.parseInt(str[k]);
			}
		}
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			int z = Integer.parseInt(str[2]);
			
			tree[z][x][y]++; 
			isContain[x][y] = true;
		}
		
		solve(N, M, K, A, ground, tree, isContain);
		
		int count = 0;
		for(int i=1; i<=N; i++) {
			for(int k=1; k<=N; k++) {
				if(isContain[i][k]) {
					for(int j=1; j<250; j++) {
						if(tree[j][i][k]!=0) count+=tree[j][i][k];
					}
				}
			}
		}
		System.out.println(count);
	}
	
	public static int[] X = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static int[] Y = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static void solve(int N, int M, int K, int[][]A, int[][] ground, int[][][] tree, boolean[][] isContain) {
		
		for(int year=1; year<=K; year++) {
			//1. 봄
			for(int i=1; i<=N; i++) {
				for(int k=1; k<=N; k++) {
					int[] tmpAge = new int[250];
					int killtree = 0;
					if(isContain[i][k]) {								
						for(int curAge=1; curAge<250; curAge++) {
							if(tree[curAge][i][k]!=0 && ground[i][k]>=curAge) {
								tree[curAge][i][k]--;
								ground[i][k]-=curAge;
								tmpAge[curAge+1]++;
								curAge--;
							}else if(tree[curAge][i][k]!=0 && ground[i][k]<curAge) {
								tree[curAge][i][k]--;
								killtree+=curAge/2;
								curAge--;
							}
						}
					}
					
					int check=0;
					for(int j=1; j<250; j++) {
						tree[j][i][k] += tmpAge[j];
						check+=tree[j][i][k];
					}
					if(check==0) isContain[i][k]=false;
					
					//2.여름
					ground[i][k] += killtree;
				}
			}
			
			for(int i=1; i<=N; i++) {
				for(int k=1; k<=N; k++) {
					if(isContain[i][k]) {
						int cnt = 0;
						for(int j=1; j<250; j++) {
							cnt = tree[j][i][k];
							if(j%5==0) {
								for(int c=1; c<=cnt; c++) {
									for(int d=0; d<8; d++) {
										int x = i+X[d];
										int y = k+Y[d];
										
										if(isValid(N, x, y)) {
											tree[1][x][y] ++;
											isContain[x][y] = true;
										}
									}
								}
							}
						}
					}
				}
			}
			
			for(int i=1; i<=N; i++) {
				for(int k=1; k<=N; k++) {
					ground[i][k]+=A[i][k];
				}
			}
		}
	}
	
	public static boolean isValid(int N, int r, int c) {
		if(r>=1 && c>=1 && r<=N && c<=N) return true;
		return false;
	}
}
