package ws0408;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_14502_연구소 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < M; k++) {
				map[i][k] = Integer.parseInt(str[k]);
			}
		}

		wall(N, M, map, 0);
		System.out.println(max);

	}

	public static void wall(int N, int M, int[][] tmpMap, int cnt) {
		if (cnt == 3) {
			virus(N, M, tmpMap);
			return;
		}

		for(int i=0; i<N; i++) {
			for(int k=0; k<M; k++) {
				if(tmpMap[i][k]==1 || tmpMap[i][k]==2) continue;
				
				// map[r][c]가 0이라면
				// map복사해서 tmpMap만들기
				int[][] Map = copy(N, M, tmpMap);

				// tmpMap[r][c]에 1 넣고 wall() 재귀 호출
				Map[i][k] = 1;
				wall(N, M, Map, cnt + 1);
				Map[i][k] = 0;
			}
		}
		

	}

	public static int[][] copy(int N, int M, int[][] map) {
		int[][] tmpMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				tmpMap[i][k] = map[i][k];
			}
		}

		return tmpMap;
	}

	public static int[] X = { -1, 0, 1, 0 };
	public static int[] Y = { 0, 1, 0, -1 };
	public static int max = 0;

	public static void virus(int N, int M, int[][] map) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] check = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (map[i][k] == 2) {
					queue.offer(new Point(i, k));
					check[i][k] = true;
				}
			}
		}

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int x = cur.x + X[i];
				int y = cur.y + Y[i];

				if (isValid(N, M, x, y) && map[x][y] == 0 && !check[x][y]) {
					queue.offer(new Point(x, y));
					check[x][y] = true;
					map[x][y] = 2;
				}
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (map[i][k] == 0) {
					count++;
				}
			}
		}

		max = Math.max(max, count);
	}

	public static boolean isValid(int N, int M, int r, int c) {
		if (r >= 0 && c >= 0 && r < N && c < M)
			return true;
		return false;
	}
}
