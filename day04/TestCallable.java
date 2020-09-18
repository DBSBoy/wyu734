package day04;

import java.util.concurrent.Callable;

public class TestCallable implements Callable<Integer> {
	 public Integer call() throws Exception {
	        int sum = 0;
	 
	        for (int i = 0; i <= 100000; i++) {
	            sum += i;
	        }
	 
	        return sum;
	    }
}

