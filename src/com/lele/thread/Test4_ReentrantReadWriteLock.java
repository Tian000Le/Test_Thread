package com.lele.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test4_ReentrantReadWriteLock {
	public static void main(String[] args) {
		My target = new My();
		Thread t = new Thread(target, "1号窗口：");
		Thread t2 = new Thread(target, "2号窗口：");
		Thread t3 = new Thread(target, "3号窗口：");
		Thread t4 = new Thread(target, "4号窗口：");
		t.start();
		t2.start();
		t3.start();
		t4.start();
	}

	static class My implements Runnable {
		int sum = 100;
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

		@Override
		public void run() {
			while (true) {
				// synchronized (this) {
				lock.writeLock().lock(); //写锁
				//lock.readLock().lock();//读锁
				try {
					if (sum >= 0) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + sum--);
					}else {
						break;
						}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.writeLock().unlock();// 防止死锁，会自动释放，不释放就独占报错了
				}
			}
		}
	}
}
