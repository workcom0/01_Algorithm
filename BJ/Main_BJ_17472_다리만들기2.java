package aTest;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Main_BJ_17472_다리만들기2 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] map = new int[N][M];
		boolean[][] check = new boolean[N][M];
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int k=0; k<M; k++) {
				map[i][k] = Integer.parseInt(str[k]);
			}
		}
		//-----------입력 받기 끝
		
		
		int index = 1;
		for(int i=0; i<N; i++) {
			for(int k=0; k<M; k++) {
				if(map[i][k]==1 && !check[i][k]) {
					landNaming(N, M, map, check, i, k, index);
					index++;
				}
			}
		}
		index = index-1;
		//-----------섬마다 라벨링 끝
		
		int[][] matrix = new int[index+1][index+1];
		for(int i=1; i<=index; i++) {
			Arrays.fill(matrix[i], Integer.MAX_VALUE);
		}
		
		for(int i=0; i<N; i++) {
			int count = 0;
			Stack<Integer> stack = new Stack<>();
			for(int k=0; k<M; k++) {
				if(map[i][k]!=0 && stack.isEmpty()) {
					stack.add(map[i][k]);
				}else if(map[i][k]==0 && !stack.isEmpty()) {
					count++;
				}else if(map[i][k]!=0 && !stack.isEmpty() && stack.peek()!=map[i][k]) {
					int A = stack.pop();
					int B = map[i][k];
					
					if(count>=2) {
						matrix[A][B] = Math.min(matrix[A][B], count);
						matrix[B][A] = Math.min(matrix[A][B], count);
					}
					
					stack.add(B);
					count=0;
				}else if(map[i][k]!=0 && !stack.isEmpty() && stack.peek()==map[i][k]) {
					count=0;
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			int count = 0;
			Stack<Integer> stack = new Stack<>();
			for(int k=0; k<N; k++) {
				if(map[k][i]!=0 && stack.isEmpty()) {
					stack.add(map[k][i]);
				}else if(map[k][i]==0 && !stack.isEmpty()) {
					count++;
				}else if(map[k][i]!=0 && !stack.isEmpty() && stack.peek()!=map[k][i]) {
					int A = stack.pop();
					int B = map[k][i];
					
					if(count>=2) {
						matrix[A][B] = Math.min(matrix[A][B], count);
						matrix[B][A] = Math.min(matrix[A][B], count);
					}
					
					stack.add(B);
					count=0;
				}else if(map[k][i]!=0 && !stack.isEmpty() && stack.peek()==map[k][i]) {
					count=0;
				}
			}
		}
		
		
		int sum = 0;
		boolean[] isVisited = new boolean[index+1];
		PriorityQueue<Pos> queue = new PriorityQueue<>();
		queue.offer(new Pos(1,0));
		while(!queue.isEmpty()) {
			Pos cur = queue.poll();
			
			if(isVisited[cur.node]) continue;
			isVisited[cur.node] = true;
			sum += cur.w;
			
			for(int i=1; i<=index; i++) {
				if(matrix[cur.node][i]!=Integer.MAX_VALUE && !isVisited[i]) {
					queue.offer(new Pos(i, matrix[cur.node][i]));
				}
			}
		}
		
		for(int i=1; i<=index; i++) {
			if(!isVisited[i]) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		
		System.out.println(sum);
		
	}
	
	public static class Pos implements Comparable<Pos>{
		int node;
		int w;
		
		public Pos(int node, int w) {
			super();
			this.node = node;
			this.w = w;
		}

		@Override
		public int compareTo(Pos o) {
			return this.w-o.w;
		}
		
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};	
	public static void landNaming(int N, int M, int[][] map, boolean[][] check, int r, int c, int index) {
		Queue<Point> queue = new LinkedList<Point>();
		
		queue.offer(new Point(r,c));
		check[r][c] = true;
		map[r][c] = index;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int i=0; i<4; i++) {
				int x = cur.x + X[i];
				int y = cur.y + Y[i];
				
				if(isVaild(N, M, x, y) && !check[x][y] && map[x][y]==1) {
					queue.offer(new Point(x,y));
					check[x][y] = true;
					map[x][y] = index;
				}
			}
		}
	}
	
	public static boolean isVaild(int N, int M, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<M) return true;
		return false;
	}
}
