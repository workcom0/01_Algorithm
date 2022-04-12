package bj1152;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BJ_21610_마법사상어와비바라기 {
	public static int N, M;
	public static int[][] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		A = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			str = br.readLine().split(" ");
			for(int k=1; k<=N; k++) {
				A[i][k] = Integer.parseInt(str[k-1]);
			}
		}
		
		List<Point> curCloud = new ArrayList<Point>();
		curCloud.add(new Point(N,1));
		curCloud.add(new Point(N,2));
		curCloud.add(new Point(N-1,1));
		curCloud.add(new Point(N-1,2));
		
		boolean[][] isCloud = new boolean[N+1][N+1];
		for(int m=0; m<M; m++) {
			str = br.readLine().split(" ");
			int d = Integer.parseInt(str[0]);
			int s = Integer.parseInt(str[1]);
			isCloud = new boolean[N+1][N+1];
			
			
			for(int i=curCloud.size()-1; i>=0; i--) {
				Point tmp = curCloud.get(i);
				curCloud.remove(i);
				
				Point newPoint = movePoint(tmp.x + X[d]*s, tmp.y + Y[d]*s);
				A[newPoint.x][newPoint.y] +=1;
				isCloud[newPoint.x][newPoint.y] = true;
			}
			
			int[][] add = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				for(int k=1; k<=N; k++) {
					if(isCloud[i][k]) {
						add[i][k] = crossWater(new Point(i,k));
					}
				}
			}
			
			for(int i=1; i<=N; i++) {
				for(int k=1; k<=N; k++) {
					if(isCloud[i][k]) {
						A[i][k] += add[i][k];
					}
				}
			}
			
			for(int i=1; i<=N; i++) {
				for(int k=1; k<=N; k++) {
					if(!isCloud[i][k] && A[i][k]>=2) {
						curCloud.add(new Point(i,k));
						A[i][k] -= 2;
					}
				}
			}
		}
		
		int sum = 0;
		for(int i=1; i<=N; i++) {
			for(int k=1; k<=N; k++) {
				sum += A[i][k];
			}
		}
		System.out.println(sum);
	}
	
	public static int[] X = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	public static int[] Y = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	public static Point movePoint(int x, int y) {
		Point tmp = new Point(x,y);
		
		if(x>=1 && x<=N) tmp.x = x; 
		else if(x<1) {
			while(x<1) {
				x+=N;
			}
			tmp.x = x;
		}
		else if(x>N) {
			while(x>N) {
				x-=N;
			}
			tmp.x = x;
		}
		
		if(y>=1 && y<=N) tmp.y = y;
		else if(y<1) {
			while(y<1) {
				y+=N;
			}
			tmp.y = y;
		}
		else if(y>N) {
			while(y>N) {
				y-=N;
			}
			tmp.y = y;
		}
		
		return tmp;
	}
	
	public static int[] R = {-1, -1, 1, 1};
	public static int[] C = {-1, 1, -1, 1};
	public static int crossWater(Point tmp) {
		int cnt = 0;
		
		for(int i=0; i<4; i++) {
			int x = tmp.x + R[i];
			int y = tmp.y + C[i];
			
			if(x>=1 && x<=N && y>=1 && y<=N && A[x][y]!=0) {
				cnt++;
			}
		}
		
		return cnt;
	}
}
