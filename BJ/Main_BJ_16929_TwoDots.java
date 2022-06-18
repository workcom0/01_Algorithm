package study0618;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_16929_TwoDots {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		char[][] map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split("");
			for(int k=0; k<M; k++) {
				map[i][k] = str[k].charAt(0);
			}
		}
		
		outer: for(int i=0; i<N; i++) {
			for(int k=0; k<M; k++) {
				boolean[][] visit = new boolean[N][M];
				visit[i][k] = true;
				search(N, M, map, visit, new Point(i,k), new Point(i,k), 0);
				
				if(success) break outer;
			}
		}
		
		if(success) System.out.println("Yes");
		else System.out.println("No");
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static boolean success = false;
	public static void search(int N, int M, char[][] map, boolean[][] visit, Point start, Point cur, int count)  {		
		if(success) return;
		
		for(int i=0; i<4; i++) {
			int x = cur.x + X[i];
			int y = cur.y + Y[i];
			
			if(count>=3 && isValid(N, M, x, y) &&x==start.x && y==start.y) {
				success = true;
				return;
			}
			
			if(isValid(N, M, x, y) && !visit[x][y] && map[x][y]==map[cur.x][cur.y]) {
				visit[x][y] = true;
				search(N, M, map, visit, start, new Point(x, y), count+1);
				visit[x][y] = false;
				if(success) return;
			}
		}
	}
	
	public static boolean isValid(int N, int M, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<M) return true;
		return false;
	}
}
