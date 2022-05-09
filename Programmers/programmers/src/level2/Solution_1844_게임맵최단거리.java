package level2;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_1844_게임맵최단거리 {
	public static void main(String[] args) {
		int[][] maps = { { 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1 } };
		int R = maps.length;
		int C = maps[0].length;
		
		System.out.println(solve(maps, R, C));
		
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static int solve(int[][] maps, int R, int C) {
		Queue<Point> queue = new LinkedList<>();
		int[][] check = new int[R][C];
		
		queue.offer(new Point(0, 0));
		check[0][0] = 1;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.x==R-1 && cur.y==C-1) {
				return check[cur.x][cur.y];
			}
			
			for(int i=0; i<4; i++) {
				int x = cur.x + X[i];
				int y = cur.y + Y[i];
				
				if(isValid(R, C, x, y) && check[x][y]==0 && maps[x][y]==1) {
					queue.offer(new Point(x,y));
					check[x][y] = check[cur.x][cur.y]+1;
				}
			}
		}
		return -1;
	}
	
	public static boolean isValid(int R, int C, int r, int c) {
		if(r>=0 && c>=0 && r<R && c<C) return true;
		return false;
	}
}
