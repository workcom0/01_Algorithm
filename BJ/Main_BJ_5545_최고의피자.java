package study0707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_BJ_5545_최고의피자 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String[] str = br.readLine().split(" ");
		int A = Integer.parseInt(str[0]); //도우의 가격
		int B = Integer.parseInt(str[1]); //토핑의 가격
		int C = Integer.parseInt(br.readLine()); //도우의 열량
		List<Integer> D = new ArrayList<Integer>();
		
		for(int i=0; i<N; i++) {
			D.add(Integer.parseInt(br.readLine()));
		}
		Collections.reverse(D);
		
		float cal = C;
		float price = A;
		float result = cal/price;
		
		for(int i=0; i<N; i++) {
			float tmpCal = cal + D.get(i);
			float tmpPrice = price + B;
			
			if(result < tmpCal/tmpPrice) {
				cal += D.get(i);
				price += B;
				result = cal/price;
			}
		}
		
		System.out.println((int)result);
	}
}
