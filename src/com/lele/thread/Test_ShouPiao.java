package com.lele.thread;

public class Test_ShouPiao {
	public static void main(String[] args) {
		Ticket ti = new Ticket();
		Thread t1 = new Thread(ti, "窗口1");
		Thread t2 = new Thread(ti, "窗口2");
		Thread t3 = new Thread(ti, "窗口3");
		Thread t4 = new Thread(ti, "窗口4");
		Thread t5 = new Thread(ti, "窗口5");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

	}
}

class Ticket implements Runnable {
	private  int  tic = 100;
	Object obj = new Object();

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				if (tic > 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"余票:"+tic--);
				} else {
					break;
				}
			}
		}

	}
}
