package level2;

import java.util.*;

class Solution_42746_가장큰수  {
    public static class Number implements Comparable<Number>{
        int x;
        String str;
        
        public Number(int x){
            this.x = x;
            this.str = String.valueOf(x);
        }
        
        @Override
        public int compareTo(Number o){
            String str1 = this.str + o.str;
            String str2 = o.str + this.str;
            return -str1.compareTo(str2);
        }
    }
    public String solution(int[] numbers) {
        PriorityQueue<Number> queue = new PriorityQueue<>();
        for(int i=0; i<numbers.length; i++){
            queue.offer(new Number(numbers[i]));
        }
        
        String answer = "";
        while(!queue.isEmpty()){
            answer += queue.poll().str;
        }
        
        if(answer.length()>1 && answer.charAt(0)=='0')
            return "0";
        return answer;
    }
}