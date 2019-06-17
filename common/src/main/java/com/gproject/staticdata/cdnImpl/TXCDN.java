//package com.gproject.staticdata.cdnImpl;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.stream.Collectors;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//
//import com.gproject.common.IDef.InitParame;
//import com.gproject.staticdata.StaticDataDef;
//import com.gproject.staticdata.StaticDataDef.ICDN;
//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.ClientConfig;
//import com.qcloud.cos.auth.BasicCOSCredentials;
//import com.qcloud.cos.auth.COSCredentials;
//import com.qcloud.cos.model.GetObjectRequest;
//import com.qcloud.cos.region.Region;
//
//@Component(StaticDataDef.TX_CDN)
//@Lazy(true)
//public class TXCDN implements ICDN {
//
//	static String SID = "AKIDBB3hDWoR3QXVEhvte9ONBk8G9qIUnPDr";
//	static String SKEY = "YryqSovmOtCj3B3lgNo0A5mDPyhGl22n";
//	static String bucketName = "gametest-1258636600";
//	COSClient cosClient = null;
//
//	static Logger logger=LoggerFactory.getLogger(TXCDN.class);
//	@Override
//	public String getJson(String fileName) {
//		// TODO Auto-generated method stub
//		try {
//			// TODO Auto-generated method stub
//			// File file=new File(ExcelObj.getExcelPath()+fileName);
//			GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, fileName);
//
//			// cosClient.getObject(getObjectRequest, file);
//			InputStream inputStream=cosClient.getObject(getObjectRequest).getObjectContent();
//			String result = new BufferedReader(new InputStreamReader(inputStream))
//			        .lines().collect(Collectors.joining(System.lineSeparator()));
//			return result;
//		} catch (Exception e) {
//			// TODO: handle exception
//			logger.error("出现了错误，获取json========");
//			logger.error(ExceptionUtils.getStackTrace(e));
//		}
//		return null;
//	}
//
//	@Override
//	public boolean delJson(String fileName) {
//		// TODO Auto-generated method stub
//		cosClient.deleteObject(bucketName, fileName);
//		return false;
//	}
//
//	
//	@Override
//	public boolean updateJson(String key, String json) {
//		// TODO Auto-generated method stub
//		cosClient.putObject(bucketName, key, json);
//		return true;
//		
//	}
//
//	@Override
//	public void init(InitParame initParame) {
//		// TODO Auto-generated method stub
//		COSCredentials cred = new BasicCOSCredentials(SID, SKEY);
//		// 2 设置bucket的区域, COS地域的简称请参照
//		// https://cloud.tencent.com/document/product/436/6224
//		// clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法,
//		// 使用可参见源码或者接口文档 FAQ 中说明。
//		ClientConfig clientConfig = new ClientConfig(new Region("ap-guangzhou"));
//		// 3 生成 cos 客户端。
//		cosClient = new COSClient(cred, clientConfig);
//	}
//
//}
