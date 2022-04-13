package ws0413;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_9205_맥주마시면서걸어가기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			String[] str = br.readLine().split(" ");
			Point home = new Point(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			
			Point[] GS = new Point[N];
			for(int i=0; i<N; i++) {
				str = br.readLine().split(" ");
				GS[i] = new Point(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			}
			
			str = br.readLine().split(" ");
			Point rock = new Point(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			
			if(solve(N, home, rock, GS)) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
		}
	}
	
	public static boolean solve(int N, Point home, Point rock, Point[] GS) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[] check = new boolean[N];
		
		queue.offer(home);
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(Math.abs(cur.x-rock.x) + Math.abs(cur.y-rock.y) <= 1000) {
				return true;
			}
			
			for(int i=0; i<N; i++) {
				if(Math.abs(cur.x-GS[i].x) + Math.abs(cur.y-GS[i].y) <= 1000 && !check[i]) {
					queue.offer(GS[i]);
					check[i] = true;
				}
			}
		}
		
		return false;
	}
}
