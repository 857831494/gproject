package com.gproject.common.net.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gproject.common.dto.proto.TipDTO.GameTip;
import com.gproject.common.dto.proto.TipDTO.TipCode;

public class HttpUtil {
	
	/**
	 * 从网络获取json数据,(String byte[})
	 * 
	 * @param path
	 * @return
	 */
	public static String doGet(String url) throws Exception{
		URL curl = new URL(url.trim());
		// 打开连接
		HttpURLConnection urlConnection = (HttpURLConnection) curl.openConnection();

		if (200 == urlConnection.getResponseCode()) {
			// 得到输入流
			InputStream is = urlConnection.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while (-1 != (len = is.read(buffer))) {
				baos.write(buffer, 0, len);
				baos.flush();
			}
			return baos.toString("utf-8");
		}
		return null;
	}

	public static InputStream getInputStream(String path) throws Exception{
		URL url = new URL(path.trim());
		// 打开连接
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		if (200 == urlConnection.getResponseCode()) {
			return urlConnection.getInputStream();
		}
		return null;
	}
	
	public static void main(String[] arg) throws Exception {
		String string="http://127.0.0.1:9090/cai";
		GameTip.Builder dBuilder=GameTip.newBuilder();
		dBuilder.setCode(TipCode.PlayerInFighting);
		String ddString=doPost(string, new String(dBuilder.build().toByteArray()));
		System.out.println(ddString);
	}
	
	
	
	// 获取其他页面的数据
	/**
	 * POST请求获取数据
	 */
	public static String doPost(String path, String body)throws Exception {
		URL url = null;
		url = new URL(path);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("POST");// 提交模式
		// conn.setConnectTimeout(10000);//连接超时 单位毫秒
		// conn.setReadTimeout(2000);//读取超时 单位毫秒
		// 发送POST请求必须设置如下两行
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
		// 获取URLConnection对象对应的输出流
		PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
		// 发送请求参数
		printWriter.write(body);// post的参数 xx=xx&yy=yy
		// flush输出流的缓冲
		printWriter.flush();
		// 开始获取数据
		BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len;
		byte[] arr = new byte[1024];
		while ((len = bis.read(arr)) != -1) {
			bos.write(arr, 0, len);
			bos.flush();
		}
		bos.close();
		return bos.toString("utf-8");
		
	}
	
}
