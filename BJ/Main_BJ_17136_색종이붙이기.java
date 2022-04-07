package aTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BJ_17136_색종이붙이기 {
	public static int[][] map;
	public static boolean[][] check;
	public static int[] count;
	public static int totalcount=0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[10][10];
		check = new boolean[10][10];
		count = new int[6];
		Arrays.fill(count, 5);
		for(int i=0; i<10; i++) {
			String[] str = br.readLine().split(" ");
			for(int k=0; k<10; k++) {
				map[i][k] = Integer.parseInt(str[k]);
				if(map[i][k]==1) {
					totalcount++;
				}
			}
		}
		
		solve(0,0, map);
		
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}		
	}
	
	public static int[] X = {0, 1, 1};
	public static int[] Y = {1, 1, 0};
	public static int min = Integer.MAX_VALUE;
	public static void solve(int x, int y, int[][] curMap) {
		if(totalcount==0) {
			int sum = 0;
			for(int i=1; i<=5; i++) {
				sum+= count[i];
			}
			min = Math.min(min, 25-sum);
		}
		
		if(x==10) {
			return ;
		}
		
		if(curMap[x][y]==0) {
			solve(y==9?x+1:x,(y+1)%10, curMap);
		}else {
			for(int i=1; i<=5; i++) {
				if(isFill(x,y,i,curMap)) {
					int[][] tmp = copy(curMap);
					tmp = fillzero(tmp, x, y, i);
					count[i]--;
					totalcount=totalcount-i*i;
					solve(y==9?x+1:x,(y+1)%10, tmp);
					count[i]++;
					totalcount=totalcount+i*i;
				}
			}
		}

	}
	
	public static int[][] fillzero(int[][] tmp, int x, int y, int size){
		for(int i=x; i<x+size; i++) {
			for(int k=y; k<y+size; k++) {
				tmp[i][k] = 0;
			}
		}
		return tmp;
	}
	
	public static int[][] copy(int[][] arr){
		int[][] curMap = new int[10][10];
		
		for(int i=0; i<10; i++) {
			for(int k=0; k<10 ;k++) {
				curMap[i][k] = arr[i][k];
			}
		}
		return curMap;
	}
	
	public static boolean isFill(int x, int y, int size, int[][] curMap) {
		if(count[size]==0) {
			return false;
		}
		
		for(int i=x; i<x+size; i++) {
			for(int k=y; k<y+size; k++) {
				if(!isVaild(i, k)) return false;
				if(curMap[i][k]!=1) {
					return false;
				}
			}
		}
		
		
		return true;
	}
	
	public static boolean isVaild(int x, int y) {
		if(x>=0 && y>=0 && x<10 && y<10) return true;
		return false;
	}
	
	
}
