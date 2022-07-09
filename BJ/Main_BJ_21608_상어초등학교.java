package study0707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_BJ_21608_상어초등학교 {
	public static class Point implements Comparable<Point>{
		int x;
		int y;
		int favorite;
		int empty;
		
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int favorite, int empty) {
			super();
			this.x = x;
			this.y = y;
			this.favorite = favorite;
			this.empty = empty;
		}

		@Override
		public int compareTo(Point o) {
			return o.favorite==this.favorite? (o.empty==this.empty ?(this.x==o.x ? this.y-o.y: this.x-o.x): o.empty-this.empty) : o.favorite-this.favorite;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1];
		List<Integer>[] favList = new List[N*N+1];
		for(int i=1; i<=N*N; i++) {
			favList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N*N; i++) {
			String[] str = br.readLine().split(" ");
			int order = Integer.parseInt(str[0]);
			for(int k=1; k<=4; k++) {
				favList[order].add(Integer.parseInt(str[k]));
			}
			
			solve(N, map, order, favList);
		}
		
		for(int i=1; i<=N; i++) {
			for(int k=1; k<=N; k++) {
				count(N, map, new Point(i, k), favList);
			}
		}
		
		System.out.println(result);
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static void solve(int N, int[][] map, int order, List<Integer>[] favList) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		
		for(int i=1; i<=N; i++) {
			for(int k=1; k<=N; k++) {
				if(map[i][k]==0) {
					int fav = 0;
					int empty = 0;
					
					for(int j=0; j<4; j++) {
						int deltax = i+X[j];
						int deltay = k+Y[j];
						
						if(isValid(N, deltax, deltay) && map[deltax][deltay]==0) {
							empty++;
						}else if(isValid(N, deltax, deltay) && favList[order].contains(map[deltax][deltay])) {
							fav++;
						}
					}
					queue.offer(new Point(i, k, fav, empty));					
				}
			}
		}
		
		Point cur = queue.poll();
		map[cur.x][cur.y] = order;
		return;
	}
	
	public static int result = 0;
	public static void count(int N, int[][] map, Point cur, List<Integer>[] favList) {
		int cnt = 0;
		
		for(int i=0; i<4; i++) {
			int deltaX = cur.x+X[i];
			int deltaY = cur.y+Y[i];
			
			if(isValid(N, deltaX, deltaY) && favList[map[cur.x][cur.y]].contains(map[deltaX][deltaY])) {
				cnt++;
			}
		}
		
		if(cnt==1) result += 1;
		else if(cnt==2) result += 10;
		else if(cnt==3) result += 100;
		else if(cnt==4) result += 1000;
	}
	
	public static boolean isValid(int N, int x, int y) {
		if(x>=1 && y>=1 && x<=N && y<=N) return true;
		return false;
	}
}
