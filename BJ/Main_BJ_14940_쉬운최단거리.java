package com.gold;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_14940_쉬운최단거리 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] map = new int[N][M];
		boolean[][] check = new boolean[N][M];
		int[][] ans = new int[N][M];
		
		Point start = null;
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int k=0; k<M; k++) {
				map[i][k] = Integer.parseInt(str[k]);
				if(map[i][k]==2) {
					start = new Point(i,k);
				}
			}
		}
		
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(start);
		check[start.x][start.y] = true;
		ans[start.x][start.y] = 0; 
		while(!queue.isEmpty()) {
			Point cur = queue.poll();			
			
			for(int i=0; i<4; i++) {
				int x = cur.x + X[i];
				int y = cur.y + Y[i];
				if(isValid(x, y, N, M) && map[x][y]==1 && !check[x][y]) {
					check[x][y] = true;
					ans[x][y] = ans[cur.x][cur.y]+1; 
					queue.offer(new Point(x,y));
				}else if(isValid(x, y, N, M)&& map[x][y]==0 && !check[x][y]) {
					check[x][y] = true;
					ans[x][y] = 0; 
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int k=0; k<M; k++) {
				if(!check[i][k] && map[i][k]==1) {
					ans[i][k] = -1;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int k=0; k<M; k++) {
				System.out.print(ans[i][k] + " ");
			}
			System.out.println();
		}
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	
	public static boolean isValid(int x, int y, int N, int M) {
		if(x>=0 && x<N && y>=0 && y<M) return true;
		return false;
	}
}
