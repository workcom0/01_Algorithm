package study0603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_1600_말이되고픈원숭이 {
	public static class Point{
		int x;
		int y;
		int k;
		
		public Point(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int W = Integer.parseInt(str[0]);
		int H = Integer.parseInt(str[1]);
		
		int[][] map = new int[H][W];
		for(int i=0; i<H; i++) {
			str = br.readLine().split(" ");
			for(int k=0; k<W; k++) {
				map[i][k] = Integer.parseInt(str[k]);
			}
		}
		
		solve(K, W, H, map);
		
	}
	
	//원숭이가 움직이는 거
	public static int[] X1 = {-1, 0, 1, 0};
	public static int[] Y1 = {0, 1, 0, -1};
	
	//말이 움직이는 거
	public static int[] X2 = {-2, -2, -1, 1, 2, 2, 1, -1};
	public static int[] Y2 = {-1, 1, 2, 2, 1, -1, -2, -2};
	
	public static void solve(int K, int W, int H, int[][] map) {
		Queue<Point> queue = new LinkedList<Point>();
		int[][][] visit = new int[H][W][K+1]; //방문처리하는 배열
		
		queue.offer(new Point(0, 0, 0));
		visit[0][0][0] = 1;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.x==H-1 && cur.y==W-1) {
				System.out.println(visit[cur.x][cur.y][cur.k]-1); //처음에 시작했을 때의 방문처리를 1로 해줘서 마지막에 1 빼줘야 함
				return;
			}
			
			if(cur.k<K) {
				//아직 K번 사용 안해서 (원숭이4방탐색 + 말8방탐색) 가능함
				for(int i=0; i<4; i++) {
					int x = cur.x + X1[i];
					int y = cur.y + Y1[i];
					int k = cur.k;
					
					if(isValid(W, H, x, y) && visit[x][y][k]==0 && map[x][y]==0) {
						queue.offer(new Point(x,y,k));
						visit[x][y][k] = visit[cur.x][cur.y][cur.k]+1;
					}
				}
				
				for(int i=0; i<8; i++) {
					int x = cur.x + X2[i];
					int y = cur.y + Y2[i];
					int k = cur.k+1;
					
					if(isValid(W, H, x, y) && visit[x][y][k]==0 && map[x][y]==0) {
						queue.offer(new Point(x,y,k));
						visit[x][y][k] = visit[cur.x][cur.y][cur.k]+1;
					}
				}
			}else {
				//K번 다 사용 했기 때문에 (원숭이4방탐색) 가능함
				for(int i=0; i<4; i++) {
					int x = cur.x + X1[i];
					int y = cur.y + Y1[i];
					int k = cur.k;
					
					if(isValid(W, H, x, y) && visit[x][y][k]==0 && map[x][y]==0) {
						queue.offer(new Point(x,y,k));
						visit[x][y][k] = visit[cur.x][cur.y][cur.k]+1;
					}
				}
			}
		}
		
		System.out.println("-1");
		return;
	}
	
	public static boolean isValid(int W, int H, int r, int c) {
		if(r>=0 && c>=0 && r<H && c<W) return true;
		return false;
	}
}
