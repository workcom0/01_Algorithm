package study0602;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_2206_벽부수고이동하기 {
	public static class Point{
		int x;
		int y;
		boolean crush;
		
		public Point(int x, int y, boolean crush) {
			super();
			this.x = x;
			this.y = y;
			this.crush = crush;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] map = new int[N+1][M+1];
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split("");
			for(int k=0; k<M; k++) {
				map[i+1][k+1] = Integer.parseInt(str[k]);
			}
		}
		
		solve(N, M, map);
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static void solve(int N, int M, int[][] map) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][][] check = new boolean[N+1][M+1][2];
		int[][][] count = new int[N+1][M+1][2];
		boolean success = false;
		
		queue.offer(new Point(1, 1, false));
		check[1][1][0] = true;
		count[1][1][0] = 1;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.x==N && cur.y==M) {
				if(cur.crush) System.out.println(count[N][M][1]);
				else System.out.println(count[N][M][0]);
				return;
			}
			
			if(cur.crush==false) {
				for(int i=0; i<4; i++) {
					int x = cur.x + X[i];
					int y = cur.y + Y[i];
					
					if(isValid(N, M, x, y) && map[x][y]==0 && !check[x][y][0]) {
						queue.offer(new Point(x, y, false));
						check[x][y][0] = true;
						count[x][y][0] = count[cur.x][cur.y][0] + 1;
					}
					
					if(isValid(N, M, x, y) && map[x][y]==1 && !check[x][y][1]) {
						queue.offer(new Point(x, y, true));
						check[x][y][1] = true;
						count[x][y][1] = count[cur.x][cur.y][0] + 1;
					}
				}
			}else {
				for(int i=0; i<4; i++) {
					int x = cur.x + X[i];
					int y = cur.y + Y[i];
					
					if(isValid(N, M, x, y) && map[x][y]==0 && !check[x][y][1]) {
						queue.offer(new Point(x, y, true));
						check[x][y][1] = true;
						count[x][y][1] = count[cur.x][cur.y][1] + 1;
					}
				}
			}
		}
		
		System.out.println(-1);
	}
	
	public static boolean isValid(int N, int M, int r, int c) {
		if(r>=1 && c>=1 && r<=N && c<=M) return true;
		return false;
	}
}
