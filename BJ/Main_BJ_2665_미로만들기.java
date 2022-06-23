package study0623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 1. 검은방에 간 경우
 *    ㄴ (1) (x,y)에 방문한 적이 없으면 그 위치에 wall+1
 *    ㄴ (2) (x,y)에 방문한 적이 있긴한데 검은방을 흰방으로 바꾼 개수가 더 적다면 그 위치에 wall+1
 *    
 * 2. 흰방에 간 경우
 *    ㄴ (1) (x,y)에 방문한 적이 없으면 그 위치에 wall
 *    ㄴ (2) (x,y)에 방문한 적이 있긴한데 검은방을 흰방으로 바꾼 개수가 더 적다면 그 위치에 wall
*/
public class Main_BJ_2665_미로만들기 {
	public static class Point{
		int x;
		int y;
		int wall;
		
		public Point(int x, int y, int wall) {
			super();
			this.x = x;
			this.y = y;
			this.wall = wall; //검은방을 흰방으로 바꾼 개수
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split("");
			for(int k=0; k<N; k++) {
				map[i][k] = Integer.parseInt(str[k]);
			}
		}
		
		solve(N, map);
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static void solve(int N, int[][] map) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] visited = new boolean[N][N];
		int[][] count = new int[N][N];
		
		queue.offer(new Point(0, 0, 0));
		visited[0][0] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
		
			for(int i=0; i<4; i++) {
				int x = cur.x + X[i];
				int y = cur.y + Y[i];
				
				if(isValid(N, x, y)) {
					//1. 0 검은방인 경우
					if(map[x][y]==0 && !visited[x][y]) {
						queue.offer(new Point(x, y, cur.wall+1));
						visited[x][y] = true;
						count[x][y] = cur.wall+1;
					}else if(map[x][y]==0 && visited[x][y] && count[x][y]>cur.wall+1) {
						queue.offer(new Point(x, y, cur.wall+1));
						count[x][y] = cur.wall+1;
					}
					
					
					//2. 1 흰방인경우
					if(map[x][y]==1 && !visited[x][y]) {
						queue.offer(new Point(x, y, cur.wall));
						visited[x][y] = true;
						count[x][y] = cur.wall;
					}else if(map[x][y]==1 && visited[x][y] && count[x][y]>cur.wall) {
						queue.offer(new Point(x, y, cur.wall));
						count[x][y] = cur.wall;
					}
				}
			}
		}
		
		System.out.println(count[N-1][N-1]);
	}
	
	public static boolean isValid(int N, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<N) return true;
		return false;
	}
}
