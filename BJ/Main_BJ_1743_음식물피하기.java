package com.silver;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_1743_음식물피하기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int R = Integer.parseInt(str[0]);
		int C = Integer.parseInt(str[1]);
		int K = Integer.parseInt(str[2]);
		int[][] map = new int[R+1][C+1];
		boolean[][] check = new boolean[R+1][C+1];
		
		for(int i=0; i<K; i++) {
			str = br.readLine().split(" ");
			int r = Integer.parseInt(str[0]);
			int c = Integer.parseInt(str[1]);
			
			map[r][c] = 1;
		}
		
		for(int i=1; i<=R; i++) {
			for(int k=1; k<=C; k++) {
				if(map[i][k]==1 && !check[i][k]) {
					solve(R, C, map, check, i, k);
				}
			}
		}
		System.out.println(max);
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static int max = 0;
	public static void solve(int R, int C, int[][] map, boolean[][] check, int r, int c) {
		Queue<Point> queue = new LinkedList<Point>();
		int count = 0;
		
		queue.offer(new Point(r,c));
		check[r][c] = true;
		count++;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int i=0; i<4; i++) {
				int x = cur.x+X[i];
				int y = cur.y+Y[i];
				
				if(isValid(R, C, x, y) && !check[x][y] && map[x][y]==1) {
					queue.offer(new Point(x,y));
					check[x][y] = true;
					count++;
				}
			}
		}
		
		max = Math.max(max, count);
	}
	
	public static boolean isValid(int R, int C, int r, int c) {
		if(r>=1 && c>=1 && r<=R && c<=C) return true;
		return false;
	}
}
