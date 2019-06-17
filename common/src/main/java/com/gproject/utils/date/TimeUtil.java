package com.gproject.utils.date;

public class TimeUtil {
	private static volatile long now = System.currentTimeMillis();
	
	
	static{
		Thread timeThread = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					now = System.currentTimeMillis();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		});
		timeThread.setDaemon(true);
		timeThread.start();
	}
	
	/**
	 * 获取时间戳
	 * @return
	 */
	public static int getTimestamp(){
		return (int) (now / 1000);
	}
	
	/**
	 * 获取上次更新的毫秒数，每30毫秒更新一次
	 * @return
	 */
	public static long getTimeMills(){
		return now;
	} 
	
	/**
	 * 获取当前时间的毫秒数
	 * @return
	 */
	public static long getInstantMills(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取当前时间的毫秒数
	 * @return
	 */
	public static long getInstantNano(){
		return System.nanoTime();
	}
	
	/**
	 * 是否超时,存在一个30ms内的误差
	 * @param startTime 开始时间
	 * @param timeOutPeriod	超时周期
	 * @return
	 */
	public static boolean isTimeOut(long startTime ,long timeOutPeriod){
		return getTimeMills() - startTime > timeOutPeriod;
	}
	
	/**
	 * 是否超时，存在30ms内的误差
	 * @param expirationTime 过期时间
	 * @return
	 */
	public static boolean isTimeOut(long expirationTime){
		return getTimeMills() > expirationTime;
	}
	
}
