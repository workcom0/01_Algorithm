package study0629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_5014_스타트링크 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		int F = Integer.parseInt(str[0]);
		int S = Integer.parseInt(str[1]);
		int G = Integer.parseInt(str[2]);
		int U = Integer.parseInt(str[3]);
		int D = Integer.parseInt(str[4]);
		int[] map = new int[F+1];
		
		if(solve(map, F, S, G, U, D)) {
			System.out.println(map[G]-1);
		}else {
			System.out.println("use the stairs");
		}
	}
	
	public static boolean solve(int[] map, int F, int S, int G, int U, int D) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(S);
		map[S] = 1;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(cur==G) {
				return true;
			}
			
			if(cur+U<=F && map[cur+U]==0) {
				queue.offer(cur+U);
				map[cur+U] = map[cur]+1;
			}
			
			if(cur-D>=1 && map[cur-D]==0) {
				queue.offer(cur-D);
				map[cur-D] = map[cur]+1;
			}
		}
		
		return false;
	}
}
