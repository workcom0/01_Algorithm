package com.gold;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_2636_치즈 {
	public static int cheese = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str= br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] map = new int[N][M];	
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int k=0; k<M; k++) {
				map[i][k] = Integer.parseInt(str[k]);
				if(map[i][k]==1) {
					cheese++;
				}
			}
		}
		
		int day = 0;
		while(cheese>0) {
			boolean[][] air = isAir(N, M, map);
			int[][] tmp = copy(N, M, map);
			
			for(int i=0; i<N; i++) {
				for(int k=0; k<M; k++) {
					if(map[i][k]==1) {
						solve(N, M, map, tmp, air, i, k);
					}
				}
			}
			
			map = copy(N, M, tmp);
			day++;
		}
		
		System.out.println(day);
	}
	
	public static void solve(int N, int M, int[][] map, int[][] tmp, boolean[][] air, int r, int c) {
		int count = 0;
		
		for(int i=0; i<4; i++) {
			int x = r + X[i];
			int y = c + Y[i];
			
			if(isValid(N, M, x, y) && air[x][y]) {
				count++;
			}
		}
		
		if(count>=2) {
			tmp[r][c] = 0;
			cheese--;
		}
	}
	
	public static int[][] copy(int N, int M, int[][] map){
		int[][] tmp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int k=0; k<M; k++) {
				tmp[i][k] = map[i][k];
			}
		}
		
		return tmp;
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static boolean[][] isAir(int N, int M, int[][] map) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] air = new boolean[N][M];
		
		queue.offer(new Point(0,0));
		air[0][0] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int i=0; i<4; i++) {
				int r = cur.x + X[i];
				int c = cur.y + Y[i];
				
				if(isValid(N, M, r, c) && map[r][c]==0 && !air[r][c]) {
					queue.offer(new Point(r,c));
					air[r][c] = true;
				}
			}
		}
		
		return air;
	}
	
	public static boolean isValid(int N, int M, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<M) return true;
		return false;
	}
}
