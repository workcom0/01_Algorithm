
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algo1_구미_05반_변수경 {
	
	//Number클래스
	//num을 생성자로 하는 클래스
	//num에 맞게 영어로 읽을 때의 문자열도 저장
	public static class Number implements Comparable<Number>{
		int num;
		String eng;
		
		public Number(int num) {
			super();
			this.num = num;
			transEng(num);
		}

		public void transEng(int num) {
			String str1 = "";
			String str2 = "";
			
			str2 = tmp(num);
			
			num = num/10;
			
			//만약 두자리 수라면
			if(num!=0) {
				str1 = tmp(num);
				str1 += " ";
			}
			
			this.eng = str1+str2;
		}
		
		//num에 따라서 맞는 영어 문자열을 리턴
		public String tmp(int num) {
			String tmp = "";
			
			if(num%10==0) {
				tmp = "zero";
			}else if(num%10==1) {
				tmp = "one";	
			}else if(num%10==2) {
				tmp = "two";	
			}else if(num%10==3) {
				tmp = "three";	
			}else if(num%10==4) {
				tmp = "four";	
			}else if(num%10==5) {
				tmp = "five";	
			}else if(num%10==6) {
				tmp = "six";	
			}else if(num%10==7) {
				tmp = "seven";	
			}else if(num%10==8) {
				tmp = "eight";	
			}else if(num%10==9) {
				tmp = "nine";	
			}
			
			return tmp;
		}

		//마지막에 사전순으로 정렬을 위한 메서드
		@Override
		public int compareTo(Number o) {
			return this.eng.compareTo(o.eng);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");		
		int M = Integer.parseInt(str[0]);
		int N = Integer.parseInt(str[1]);
		
		//리스트에 Number형을 저장
		List<Number> list = new ArrayList<>();
		for(int i=M; i<=N; i++) {
			list.add(new Number(i));
		}
		
		//사전순으로 정렬
		Collections.sort(list);		
		
		int index=0; //한 줄에 10개씩 출력을 하기 위한 인덱스 
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i).num + " ");
			index++;
			
			//만약 한 줄에 10개를 출력했으면 줄바꿈하기
			if(index==10) {
				System.out.println();
				index = 0;
			}
		}
	}
}
