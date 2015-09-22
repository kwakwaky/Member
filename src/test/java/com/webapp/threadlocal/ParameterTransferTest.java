package com.webapp.threadlocal;

class Result {
	int sum;
	
}


class Calculator {
	
	public Calculator() {
		//GlobalVariable.sum = 0;
	}
	
	void summerize(Result result, int start, int end) {
		for (int i=start ; i<end ; i++) {
			result.sum += i;
		}
	}
	
	void multiplay(Result result, int mul) {
		result.sum *= mul;
	}
	
}

class MyThread extends Thread {

	@Override
	public void run() {
		Result r = new Result();
		Calculator c = new Calculator();
		c.summerize(r, 1, 11);
		
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.multiplay(r, 10);
		System.out.println("sum = " + GlobalVariable.result.get().sum);
	}
	
}


public class ParameterTransferTest {
	
	public static void main(String[] args) {
		
		for (int i=0 ; i<5 ; i++) {
			new MyThread().start();
		}
		
		Result r2 = new Result();
		Calculator c2 = new Calculator();
		c2.summerize(r2, 1, 101);
		c2.multiplay(r2, 10);
		System.out.println("sum = " + GlobalVariable.result.get().sum);
	}

	
	
	
}
