package study0602;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_BJ_1991_트리순회 {
	public static class Node{
		char node;
		char left;
		char right;
		
		public Node(char node, char left, char right) {
			this.node = node;
			this.left = left;
			this.right = right;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		
		Node[] list = new Node[26];
		
		for(int n=0; n<N; n++) {
			String[] str = br.readLine().split(" ");
			int node = str[0].charAt(0);
			char left = str[1].charAt(0);
			char right = str[2].charAt(0);
			
			list[node-65] = new Node((char)node, left, right);
		}
		
		solve1(N, list, 0, list[0]); System.out.println();
		solve2(N, list, 0, list[0]); System.out.println();
		solve3(N, list, 0, list[0]); System.out.println();
	}
	
	public static void solve1(int N, Node[] list, int cnt, Node node) {
		System.out.print(list[cnt].node);
		
		if(node.left!='.') {
			solve1(N, list, (int)(node.left)-65, list[(int)(node.left)-65]);
		}
		
		if(node.right!='.') {
			solve1(N, list, (int)(node.right)-65, list[(int)(node.right)-65]);
		}
		
		if(node.left=='.' || node.right=='.') {
			return;
		}
	}
	
	public static void solve2(int N, Node[] list, int cnt, Node node) {
		if(node.left!='.') {
			solve2(N, list, (int)(node.left)-65, list[(int)(node.left)-65]);
		}
		
		System.out.print(list[cnt].node);
		
		if(node.right!='.') {
			solve2(N, list, (int)(node.right)-65, list[(int)(node.right)-65]);
		}
		
		if(node.left=='.' || node.right=='.') {
			return;
		}
	}
	
	public static void solve3(int N, Node[] list, int cnt, Node node) {
		if(node.left!='.') {
			solve3(N, list, (int)(node.left)-65, list[(int)(node.left)-65]);
		}
		
		if(node.right!='.') {
			solve3(N, list, (int)(node.right)-65, list[(int)(node.right)-65]);
		}
		
		System.out.print(list[cnt].node);
		
		if(node.left=='.' || node.right=='.') {
			return;
		}
	}
}
