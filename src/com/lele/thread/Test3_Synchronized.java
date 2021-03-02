package com.lele.thread;

public class Test3_Synchronized {
	public static void main(String[] args) {
		TicketThread ts = new TicketThread();
		for (int i = 0; i < 3; i++) {
			new Thread(ts).start();
		}
	}

	static class TicketThread extends Thread {
		private int ticket = 1000;

		@Override
		public void run() {
			while (true) {

				synchronized (this) {

					if (ticket <= 0) {
						break;
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("窗口:" + Thread.currentThread().getName() + ",剩余票数为：" + ticket--);

				}

			}

		}

	}

}
