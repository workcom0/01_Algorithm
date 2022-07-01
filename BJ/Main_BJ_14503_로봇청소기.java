package study0701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_14503_로봇청소기 {
	public static class Clean{
		int x;
		int y;
		int d;
		
		public Clean(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}	
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		str = br.readLine().split(" ");
		Clean Start = new Clean(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int k=0; k<M; k++) {
				map[i][k] = Integer.parseInt(str[k]);
			}
		}
		
		System.out.println(solve(N, M, Start, map));
		
	}
	
	public static int[] X = {-1, 0, 1, 0};
	public static int[] Y = {0, 1, 0, -1};
	public static int solve(int N, int M, Clean Start, int[][] map) {
		boolean[][] visit = new boolean[N][M];
		int count = 0;
		
		Clean cur = Start;
		while(true) {
			
			if(!visit[cur.x][cur.y]) {
				count++;
				visit[cur.x][cur.y] = true;
			}
			
			int cnt = 0;
			int direction = cur.d;
			for(cnt=0; cnt<4; cnt++) {
				direction = direction-1;
				
				if(direction==-1) direction = 3;
				
				int x = cur.x+X[direction];
				int y = cur.y+Y[direction];
				if(isValid(N, M, x, y) && map[x][y]==0 && !visit[x][y]) {
					cur = new Clean(x, y, direction);
					break;
				}
			}
			
			if(cnt==4) {
				if(cur.d==0) {
					if(isValid(N, M, cur.x+X[2], cur.y+Y[2]) && map[cur.x+X[2]][cur.y+Y[2]]==0) {
						cur = new Clean(cur.x+X[2], cur.y+Y[2], cur.d);
					}else {
						break;
					}
				}else if(cur.d==1) {
					if(isValid(N, M, cur.x+X[3], cur.y+Y[3]) && map[cur.x+X[3]][cur.y+Y[3]]==0) {
						cur = new Clean(cur.x+X[3], cur.y+Y[3], cur.d);
					}else {
						break;
					}
				}else if(cur.d==2) {
					if(isValid(N, M, cur.x+X[0], cur.y+Y[0]) && map[cur.x+X[0]][cur.y+Y[0]]==0) {
						cur = new Clean(cur.x+X[0], cur.y+Y[0], cur.d);
					}else {
						break;
					}
				}else if(cur.d==3) {
					if(isValid(N, M, cur.x+X[1], cur.y+Y[1]) && map[cur.x+X[1]][cur.y+Y[1]]==0) {
						cur = new Clean(cur.x+X[1], cur.y+Y[1], cur.d);
					}else {
						break;
					}
				}
			}
		}
		
		return count;
	}
	
	public static boolean isValid(int N, int M, int r, int c) {
		if(r>=0 && c>=0 && r<N && c<M) return true;
		return false;
	}
}
