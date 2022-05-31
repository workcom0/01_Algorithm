package com.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_6593_상범빌딩 {
	public static class Point{
		int L;
		int R;
		int C;
		
		public Point(int l, int r, int c) {
			super();
			L = l;
			R = r;
			C = c;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String[] str = br.readLine().split(" ");
			int L = Integer.parseInt(str[0]);
			int R = Integer.parseInt(str[1]);
			int C = Integer.parseInt(str[2]);
			
			if(L==0) break;
			
			char[][][] map = new char[L][R][C];
			Point START = null;
			Point END = null;
			
			for(int l=0; l<L; l++) {
				for(int r=0; r<R; r++) {
					str = br.readLine().split("");
					for(int c=0; c<C; c++) {
						map[l][r][c] = str[c].charAt(0);
						
						if(map[l][r][c]=='S') {
							START = new Point(l, r, c);
						}else if(map[l][r][c]=='E') {
							END = new Point(l, r, c);
						}
					}
				}
				String tmp = br.readLine();
			}
			
			solve(map, START, END, L, R, C);
		}
	}
	
	public static int[] Z = {0, 0, 0, 0, 1, -1};
	public static int[] X = {-1, 0, 1, 0, 0, 0};
	public static int[] Y = {0, 1, 0, -1, 0, 0};	
	public static void solve(char[][][] map, Point START, Point END, int L, int R, int C) {
		Queue<Point> queue = new LinkedList<Point>();
		int[][][] check = new int[L][R][C];
		boolean ans = false;
		
		queue.offer(START);
		check[START.L][START.R][START.C] = 0;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();

			if(map[cur.L][cur.R][cur.C]=='E') {
				ans = true;
				break;
			}
			
			for(int i=0; i<6; i++) {
				int r = cur.R + X[i];
				int c = cur.C + Y[i];
				int l = cur.L + Z[i];
				
				if(isValid(r, c, l, L, R, C) && map[l][r][c]!='#' && check[l][r][c]==0) {
					queue.offer(new Point(l,r,c));
					check[l][r][c] = check[cur.L][cur.R][cur.C] + 1;
				}
			}
		}
		
		if(ans) {
			System.out.println("Escaped in " + check[END.L][END.R][END.C] + " minute(s).");
		}else {
			System.out.println("Trapped!");
		}
	}
	
	public static boolean isValid(int x, int y, int z, int L, int R, int C) {
		if(x>=0 && y>=0 && z>=0 && x<R && y<C && z<L) return true;
		return false;
	}
}
