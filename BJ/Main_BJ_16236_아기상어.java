package study0713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_BJ_16236_아기상어 {
	public static class Shark{
		int x;
		int y;
		int size;
		int eat;
		int count;

		public Shark(int x, int y, int size, int eat, int count) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.eat = eat;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Shark [x=" + x + ", y=" + y + ", size=" + size + ", eat=" + eat + ", count=" + count + "]";
		}
	}
	
	//마지막에 물고기 도착했을때 우선순위큐에 넣음
	public static class Fish implements Comparable<Fish> {
		int x;
		int y;
		int distance;
		
		public Fish(int x, int y, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public int compareTo(Fish o) {
			return this.distance==o.distance? (this.x==o.x? (this.y-o.y):this.x-o.x):this.distance-o.distance;
		}
	}
	
	//bfs 큐
	public static class Point {
		int x;
		int y;
		int distance;
		
		public Point(int x, int y, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		
		Shark shark = null;
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			for(int k=0; k<N; k++) {
				map[i][k] = Integer.parseInt(str[k]);
				if(map[i][k]==9) shark = new Shark(i, k, 2, 0, 0);
			}
		}
		
		int count = 0;
		Shark cur = shark;
		while(true) {
			cur = findFish(N, map, cur);
			
			if(cur==null) break;
			else {
				count = cur.count;
			}
		}
		
		System.out.println(count);
	}

	
	public static Shark findFish(int N, int[][] map, Shark shark){
		PriorityQueue<Fish> fish = new PriorityQueue<>();
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		queue.offer(new Point(shark.x, shark.y, 0));
		visited[shark.x][shark.y] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(map[cur.x][cur.y]!=0 && (cur.x!=shark.x || cur.y!=shark.y) && map[cur.x][cur.y]<shark.size) {
				fish.offer(new Fish(cur.x, cur.y, cur.distance));	
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int x = cur.x + X[i];
				int y = cur.y + Y[i];
				
				if(isValid(N, x, y) && !visited[x][y] && map[x][y]<=shark.size) {
					queue.offer(new Point(x, y, cur.distance+1));
					visited[x][y] = true;
				}
			}
		}
		
		if(fish.size()==0) {
			return null;
		}else {
			map[shark.x][shark.y]=0;
	
			Fish result = fish.poll();
			int x = result.x;
			int y = result.y;
			int size = shark.size;
			int eat = shark.eat;
			int count = shark.count + result.distance;
			
			if(eat+1 == size) {
				eat = 0;
				size +=1;
			}else {
				eat++;
			}
			
			return new Shark(x,y,size,eat,count);
		}
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static boolean isValid(int N, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<N) return true;
		return false;
	}
}
