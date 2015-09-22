package com.webapp.threadlocal;

class Result2 {
	int sum;
}


class Calculator2 {
	
	public Calculator2() {
		//GlobalVariable.sum = 0;
	}
	
	void summerize(int start, int end) {
		for (int i=start ; i<end ; i++) {
			GlobalVariable.result.get().sum += i;
			try {
				Thread.sleep((int)(Math.random()*100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void multiplay(int mul) {
		GlobalVariable.result.get().sum *= mul;
	}
	
}

class MyThread2 extends Thread {

	@Override
	public void run() {
		GlobalVariable.result.set(new Result2());
		
		Calculator2 c = new Calculator2();
		c.summerize(1, 11);
		
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.multiplay(10);
		System.out.println("sum = " + GlobalVariable.result.get().sum);
	}
	
}


public class GlobalTransferTest {
	
	public static void main(String[] args) {
		
		for (int i=0 ; i<10 ; i++) {
			new MyThread2().start();
		}
		
//		Result r2 = new Result();
//		Calculator c2 = new Calculator();
//		c2.summerize(r2, 1, 101);
//		c2.multiplay(r2, 10);
//		System.out.println("sum = " + GlobalVariable.sum);
	}

	
	
	
}
