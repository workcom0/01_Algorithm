package com.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main_BJ_1620_나는야포켓몬마스터이다솜 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		HashMap<String, Integer> no = new HashMap<>();
		HashMap<Integer, String> name = new HashMap<>();
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();			
			no.put(tmp, i+1);
			name.put(i+1, tmp);
		}
		
		for(int i=0; i<M; i++) {
			String tmp = br.readLine();
			
			if(tmp.charAt(0)>=65) {
				sb.append(no.get(tmp)).append("\n");
			}else {
				int num = Integer.parseInt(tmp);
				sb.append(name.get(num)).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
