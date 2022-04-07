package ws0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_1249_보급로 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String[] str = br.readLine().split("");
				for(int k=0; k<N; k++) {
					map[i][k] = Integer.parseInt(str[k]);
				}
			}
					
			System.out.println("#"+t+" "+solve(N, 0, 0, map));
		}
	
	}
	
	public static class Point implements Comparable<Point>{
		int x, y;
		int W;
		
		public Point(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			W = w;
		}

		@Override
		public int compareTo(Point o) {
			return this.W-o.W;
		}
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static int solve(int N, int start, int end, int[][] map) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		boolean[][] check = new boolean[N][N];
		
		queue.offer(new Point(start, end, 0));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(check[cur.x][cur.y]) continue;
			check[cur.x][cur.y] = true;
			map[cur.x][cur.y] = cur.W;
			
			if(cur.x==N-1 && cur.y==N-1) {			
				return map[cur.x][cur.y];
			}
			
			for(int i=0; i<4; i++) {
				int x = cur.x + X[i];
				int y = cur.y + Y[i];
				
				if(isVaild(N, x, y) && !check[x][y]) {
					queue.offer(new Point(x,y,map[x][y]+map[cur.x][cur.y]));
				}
			}
		}
		return -1;
	}
	
	public static boolean isVaild(int N, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<N) return true;
		return false;
	}
}
