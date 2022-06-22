package study0622;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 현재 상자가 세워져있는경우, 세로로 놓아져있는경우, 가로로 놓아져있는경우 이렇게 3가지로 케이스를 나눠서 구함.
 * 세로로 놓아져있거나 가로로 놓아져있는 경우에는 가운데 좌표를 대표 값으로 함.
*/
public class Main_BJ_14947_상자배달 {
	public static class Point{
		int x;
		int y;
		int state; //세워져있음:1, 세로:2, 가로:3
		
		public Point(int x, int y, int state) {
			super();
			this.x = x;
			this.y = y;
			this.state = state;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] map = new int[N][M];
		
		Point start = null;
		Point end = null;
		for(int i=0; i<N; i++) {
			str = br.readLine().split("");
			for(int k=0; k<M; k++) {
				map[i][k] = Integer.parseInt(str[k]);
				
				if(map[i][k]==2) {
					start = new Point(i, k, 1);
				}else if(map[i][k]==3) {
					end = new Point(i, k, 0);
				}
			}
		}
		
		System.out.println(solve(N, M, map, start, end));
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};	
	public static int solve(int N, int M, int[][] map, Point start, Point end) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][][] visited = new boolean[N][M][4];
		int[][][] count = new int[N][M][4];
		
		queue.offer(start);
		visited[start.x][start.y][start.state] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.state==1) {
				if(map[cur.x][cur.y]==3) {
					return count[cur.x][cur.y][cur.state];
				}
			}else if(cur.state==2) {
				if(map[cur.x][cur.y]==3 || map[cur.x-1][cur.y]==3 || map[cur.x+1][cur.y]==3) {
					return count[cur.x][cur.y][cur.state];
				}
			}else {
				if(map[cur.x][cur.y]==3 || map[cur.x][cur.y-1]==3 || map[cur.x][cur.y+1]==3) {
					return count[cur.x][cur.y][cur.state];
				}
			}
			
			if(cur.state==1) {
				//세워져 있는 경우
				for(int i=0; i<4; i++) {
					int x = cur.x + X[i]*2;
					int y = cur.y + Y[i]*2;
					
					if(isValid(N, M, x+X[i], y+Y[i])) {
						if(i%2==0 && !visited[x][y][2]) {
							if(map[x][y]!=0 || (map[x-1][y]!=0 && map[x+1][y]!=0)) {
								queue.offer(new Point(x, y, 2));
								visited[x][y][2] = true;
								count[x][y][2] = count[cur.x][cur.y][cur.state] + 1;
							}
						}else if(i%2==1 && !visited[x][y][3]){
							if(map[x][y]!=0 || (map[x][y-1]!=0 && map[x][y+1]!=0)) {
								queue.offer(new Point(x, y, 3));
								visited[x][y][3] = true;
								count[x][y][3] = count[cur.x][cur.y][cur.state] + 1;
							}
						}
					}			
				}
				
				
			}else if(cur.state==2) {
				//세로로 누워있음				
				if(isValid(N, M, cur.x-2, cur.y) && !visited[cur.x-2][cur.y][1] && map[cur.x-2][cur.y]!=0) {
					queue.offer(new Point(cur.x-2, cur.y, 1));
					visited[cur.x-2][cur.y][1] = true;
					count[cur.x-2][cur.y][1] = count[cur.x][cur.y][cur.state] + 1;
				}
				
				if(isValid(N, M, cur.x+2, cur.y) && !visited[cur.x+2][cur.y][1] && map[cur.x+2][cur.y]!=0) {
					queue.offer(new Point(cur.x+2, cur.y, 1));
					visited[cur.x+2][cur.y][1] = true;
					count[cur.x+2][cur.y][1] = count[cur.x][cur.y][cur.state] + 1;
				}
				
				if(isValid(N, M, cur.x, cur.y+1) && !visited[cur.x][cur.y+1][2]) {
					if(map[cur.x][cur.y+1]!=0 || (map[cur.x-1][cur.y+1]!=0&&map[cur.x+1][cur.y+1]!=0)) {
						queue.offer(new Point(cur.x, cur.y+1, 2));
						visited[cur.x][cur.y+1][2] = true;
						count[cur.x][cur.y+1][2] = count[cur.x][cur.y][cur.state] + 1;
					}					
				}
				
				if(isValid(N, M, cur.x, cur.y-1) && !visited[cur.x][cur.y-1][2]) {
					if(map[cur.x][cur.y-1]!=0 || (map[cur.x-1][cur.y-1]!=0&&map[cur.x+1][cur.y-1]!=0)) {
						queue.offer(new Point(cur.x, cur.y-1, 2));
						visited[cur.x][cur.y-1][2] = true;
						count[cur.x][cur.y-1][2] = count[cur.x][cur.y][cur.state] + 1;
					}					
				}
				
			}else if(cur.state==3) {
				//가로로 누워있음
				if(isValid(N, M, cur.x, cur.y-2) && !visited[cur.x][cur.y-2][1] && map[cur.x][cur.y-2]!=0) {
					queue.offer(new Point(cur.x, cur.y-2, 1));
					visited[cur.x][cur.y-2][1] = true;
					count[cur.x][cur.y-2][1] = count[cur.x][cur.y][cur.state] + 1;
				}
				
				if(isValid(N, M, cur.x, cur.y+2) && !visited[cur.x][cur.y+2][1] && map[cur.x][cur.y+2]!=0) {
					queue.offer(new Point(cur.x, cur.y+2, 1));
					visited[cur.x][cur.y+2][1] = true;
					count[cur.x][cur.y+2][1] = count[cur.x][cur.y][cur.state] + 1;
				}
				
				if(isValid(N, M, cur.x+1, cur.y) && !visited[cur.x+1][cur.y][3]) {
					if(map[cur.x+1][cur.y]!=0 || (map[cur.x+1][cur.y-1]!=0&&map[cur.x+1][cur.y+1]!=0)) {
						queue.offer(new Point(cur.x+1, cur.y, 3));
						visited[cur.x+1][cur.y][3] = true;
						count[cur.x+1][cur.y][3] = count[cur.x][cur.y][cur.state] + 1;
					}					
				}
				
				if(isValid(N, M, cur.x-1, cur.y) && !visited[cur.x-1][cur.y][3]) {
					if(map[cur.x-1][cur.y]!=0 || (map[cur.x-1][cur.y-1]!=0&&map[cur.x-1][cur.y+1]!=0)) {
						queue.offer(new Point(cur.x-1, cur.y, 3));
						visited[cur.x-1][cur.y][3] = true;
						count[cur.x-1][cur.y][3] = count[cur.x][cur.y][cur.state] + 1;
					}					
				}
			}
		}
		
		return -2;
	}

	
	public static boolean isValid(int N, int M, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<M) return true;
		return false;
	}
}
