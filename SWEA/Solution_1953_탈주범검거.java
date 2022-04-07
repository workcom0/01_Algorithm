package ws0407;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_1953_탈주범검거 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int M = Integer.parseInt(str[1]);
			int R = Integer.parseInt(str[2]);
			int C = Integer.parseInt(str[3]);
			int L = Integer.parseInt(str[4]);
			int[][] map = new int[N][M];
			
			for(int i=0; i<N; i++) {
				str = br.readLine().split(" ");
				for(int k=0; k<M; k++) {
					map[i][k] = Integer.parseInt(str[k]);
				}
			}
			
			int ans = solve(N, M, R, C, L, map);
			System.out.println("#"+t+" "+ans);
		}
		
	}
	
	//터널 구조물 타입별로 그대로 매핑 (1~7)
	public static int[][] X = {{0}, {-1, 1, 0, 0}, {-1, 1}, {0, 0}, {-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
	public static int[][] Y = {{0}, {0, 0, -1, 1}, {0, 0}, {-1, 1}, {0, 1}, {0, 1}, {0, -1}, {0, -1}};
	public static int solve(int N, int M, int R, int C, int L, int[][] map) {
		Queue<Point> queue = new LinkedList<Point>();
		int[][] check = new int[N][M]; //방문했는지 체크 and 누적 시간 체크
		int place = 0; //탈주범이 있을 수 있는 장소 count
		
		queue.offer(new Point(R,C));
		check[R][C] = 1;
		place++;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(check[cur.x][cur.y]==L) {
				return place;
			}
			
			for(int i=0; i<X[map[cur.x][cur.y]].length; i++) {
				int x = cur.x + X[map[cur.x][cur.y]][i];
				int y = cur.y + Y[map[cur.x][cur.y]][i];
				
				if(isValid(N, M, x, y) && check[x][y]==0 && map[x][y]!=0 && isLinked(N, M, x, y, cur, map)) {
					queue.offer(new Point(x,y));
					check[x][y] = check[cur.x][cur.y]+1;
					place++;
				}
			}
		}
		return place;
	}
	
	public static boolean isLinked(int N, int M, int x, int y, Point cur, int[][] map) {
		for(int i=0; i<X[map[x][y]].length; i++) {
			int xx = x + X[map[x][y]][i];
			int yy = y + Y[map[x][y]][i];
			
			if(isValid(N, M, xx, yy) && xx==cur.x && yy==cur.y) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValid(int N, int M, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<M) return true;
		return false;
	}
	
}
