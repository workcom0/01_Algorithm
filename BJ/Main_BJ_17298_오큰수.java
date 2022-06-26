package study0626;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main_BJ_17298_오큰수 {
	public static class A{
		int data;
		int order;
		
		public A(int data, int order) {
			super();
			this.data = data;
			this.order = order;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Stack<A> stack = new Stack<>();
		int[] ans = new int[N];
		
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(str[i]);
			
			if(stack.size()==0) stack.push(new A(num, i));
			else {
				if(stack.peek().data >= num) stack.push(new A(num, i));
				else {
					while(true) {
						if(stack.size()==0) break;
						
						if(stack.peek().data >= num) break;
						
						A cur = stack.pop();
						ans[cur.order] = num;
					}
					stack.push(new A(num, i));
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			if(ans[i]==0) bw.write("-1 ");
			else bw.write(ans[i] + " ");
		}
		bw.close();
	}
}
