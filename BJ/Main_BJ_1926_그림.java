package com.silver;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_1926_그림 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] map = new int[N][M];
		boolean[][] check = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int k=0; k<M; k++) {
				map[i][k] = Integer.parseInt(str[k]);
			}
		}
		
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int k=0; k<M; k++) {
				if(map[i][k]==1 && !check[i][k]) {
					solve(N, M, map, check, i, k);
					count++;
				}
			}
		}
		System.out.println(count);
		System.out.println(max);
	}
	
	public static int max = 0;
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static void solve(int N, int M, int[][] map, boolean[][] check, int r, int c) {
		Queue<Point> queue = new LinkedList<Point>();
		int cnt = 0;
		
		queue.offer(new Point(r, c));
		check[r][c] = true;
		cnt++;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int i=0; i<4; i++) {
				int x = cur.x + X[i];
				int y = cur.y + Y[i];
				
				if(isValid(N, M, x, y) && !check[x][y] && map[x][y]==1) {
					queue.offer(new Point(x,y));
					check[x][y] = true;
					cnt++;
				}
			}
		}
		
		max = Math.max(max, cnt);
	}
	
	public static boolean isValid(int N, int M, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<M) return true;
		return false;
	}
}
