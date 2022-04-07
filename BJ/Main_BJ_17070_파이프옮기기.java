package aTest;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_17070_파이프옮기기 {
	public static class Pipe{
		Point left;
		Point right;
		int direction;
		
		public Pipe(Point left, Point right) {
			super();
			this.left = left;
			this.right = right;
			
			if(left.x==right.x) this.direction=0;
			else if(left.y==right.y) this.direction=1;
			else if(left.x!=right.x && left.y!=right.y) this.direction=2;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			for(int k=0; k<N; k++) {
				map[i][k] = Integer.parseInt(str[k]);
			}
		}
		
		solve(N, map, new Pipe(new Point(0,0), new Point(0,1)));
		System.out.println(count);
	}
	
	public static int count=0;
	public static int[] X = {1, 0, 1};
	public static int[] Y = {0, 1, 1};
	public static void solve(int N, int[][] map, Pipe pipe) {
		if(pipe.right.x==N-1 && pipe.right.y==N-1) {
			count++;
			return;
		}
		
		if(pipe.direction==0) {
			for(int i=0; i<3; i++) {
				if(i==0) continue;

				int x = pipe.right.x + X[i];
				int y = pipe.right.y + Y[i];
				
				if(i==1) {
					if(isVaild(N, x, y) && map[x][y]==0) {
						Pipe tmp = new Pipe(new Point(pipe.right.x, pipe.right.y), new Point(x,y));
						solve(N, map, tmp);
					}
				}else if(i==2) {
					if(isVaild(N, x, y) && isVaild(N, x-1, y) && isVaild(N, x, y-1) && map[x][y]==0 && map[x-1][y]==0 && map[x][y-1]==0) {
						Pipe tmp = new Pipe(new Point(pipe.right.x, pipe.right.y), new Point(x,y));
						solve(N, map, tmp);
					}
				}
			}
		}
		
		if(pipe.direction==1) {
			for(int i=0; i<3; i++) {
				if(i==1) continue;

				int x = pipe.right.x + X[i];
				int y = pipe.right.y + Y[i];
				
				if(i==0) {
					if(isVaild(N, x, y) && map[x][y]==0) {
						Pipe tmp = new Pipe(new Point(pipe.right.x, pipe.right.y), new Point(x,y));
						solve(N, map, tmp);
					}
				}else if(i==2) {
					if(isVaild(N, x, y) && isVaild(N, x-1, y) && isVaild(N, x, y-1) && map[x][y]==0 && map[x-1][y]==0 && map[x][y-1]==0) {
						Pipe tmp = new Pipe(new Point(pipe.right.x, pipe.right.y), new Point(x,y));
						solve(N, map, tmp);
					}
				}
			}
		}
		
		if(pipe.direction==2) {
			for(int i=0; i<3; i++) {
				int x = pipe.right.x + X[i];
				int y = pipe.right.y + Y[i];
				
				if(i==0 || i==1) {
					if(isVaild(N, x, y) && map[x][y]==0) {
						Pipe tmp = new Pipe(new Point(pipe.right.x, pipe.right.y), new Point(x,y));
						solve(N, map, tmp);
					}
				}else if(i==2) {
					if(isVaild(N, x, y) && isVaild(N, x-1, y) && isVaild(N, x, y-1) && map[x][y]==0 && map[x-1][y]==0 && map[x][y-1]==0) {
						Pipe tmp = new Pipe(new Point(pipe.right.x, pipe.right.y), new Point(x,y));
						solve(N, map, tmp);
					}
				}
			}
		}
		
		return;
	}
	
	public static boolean isVaild(int N, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<N) return true;
		return false;
	}
}
