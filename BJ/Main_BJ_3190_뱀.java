package study220715;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main_BJ_3190_¹ì {
	public static class Direction{
		int m;
		char d;
		
		public Direction(int m, char d) {
			super();
			this.m = m;
			this.d = d;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		
		for(int i=0; i<K; i++) {
			String[] str = br.readLine().split(" ");
			int r = Integer.parseInt(str[0]);
			int c = Integer.parseInt(str[1]);
			
			map[r-1][c-1] = 1;
		}
		
		Queue<Direction> D = new LinkedList<>();
		int L = Integer.parseInt(br.readLine());
		for(int i=0; i<L; i++) {
			String[] str = br.readLine().split(" ");
			int m = Integer.parseInt(str[0]);
			char d = str[1].charAt(0);
			
			D.offer(new Direction(m, d));
		}
		
		
		Deque<Point> snake = new ArrayDeque<>();
		snake.offerFirst(new Point(0,0));
		map[0][0] = 2;
		
		while(true) {
			count++;
			if(solve(N, map, D, snake)) continue;
			else break;
		}
		
		System.out.println(count);
	}
	
	public static int curD = 1;
	public static int count = 0;
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static boolean solve(int N, int[][] map, Queue<Direction> D, Deque<Point> snake) {
		Point cur = snake.peekFirst();
		int x = cur.x + X[curD];
		int y = cur.y + Y[curD];
		
		if(isValid(N, map, x, y)) {
			if(map[x][y]==0) {
				snake.offerFirst(new Point(x,y));
				Point tail = snake.pollLast();
				map[tail.x][tail.y]=0;
				map[x][y]=2;
			}else {
				snake.offerFirst(new Point(x,y));
				map[x][y]=2;
			}
		}else {
			return false;
		}
		
		
		if(D.size()!=0 && count==D.peek().m) {
			Direction dir = D.poll();
			if(dir.d=='D') {
				curD +=1;
				if(curD==4) curD=0;
			}else if(dir.d=='L') {
				curD -=1;
				if(curD==-1) curD=3;
			}
		}
		
		return true;
	}
	
	public static boolean isValid(int N, int[][] map, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<N && map[r][c]!=2) return true;
		else return false;
	}
}
