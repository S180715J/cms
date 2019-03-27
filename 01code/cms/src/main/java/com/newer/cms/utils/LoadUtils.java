package com.newer.cms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传与下载的工具类
 * @author Administrator
 *
 */
public class LoadUtils {
	
	@Value("${path}")
	private  static String INITPATH="/cmshtml/access/";

	/**
	 * 文件下载的工具方法
	 * 
	 * @param filePath
	 *            具体文件所在的绝对路径及文件名 例如：C:\\Users\\Administrator\\Desktop\\演员的自我修养.txt
	 * @param fileName
	 *            下载时显示的文件的名字 例如：演员的自我修养.txt
	 * @param request
	 * @return 文件下载
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> download(String filePath, String fileName, HttpServletRequest request)
			throws IOException {

		File file = new File(filePath);
		InputStream in = new FileInputStream(file);

		// 将流文件写入到一个byte数组中
		byte[] body = new byte[in.available()];
		in.read(body);

		// 解决文件下载时的文件名乱码问题
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.contains("Firefox")) {
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}

		HttpHeaders headers = new HttpHeaders();
		// 设置下载的响应头信息
		headers.add("Content-Disposition", "attachment;filename=" + fileName);

		// 设置状态码
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, status);

		return responseEntity;
	}
	
	/**
	 * 创建文件夹
	 * 
	 * @param filePath
	 */
	public static void createDirectory(File file) {
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 获取随机32为的UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 文件上传
	 * @param pictur
	 * @param session
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String upload(MultipartFile fileRecord, HttpServletRequest request) throws IllegalStateException, IOException {

		// 保存文件
		// 1.给需要保存的文件给一个随机的文件名
		// ①需要知道保存的文件名的后缀名
		String fileSuffix = fileRecord.getOriginalFilename().substring(fileRecord.getOriginalFilename().lastIndexOf("."),
				fileRecord.getOriginalFilename().length());
		// ②保存的文件名
		String fileName = getUUID() + fileSuffix;
		// 2.具体文件保存的位置
		// .../static/images/uroewruowruowurow.jpg
		// ①获取保存的文件的文件夹的绝对路径
		Calendar date=Calendar.getInstance();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
		String name=format.format(date.getTime());
		String realPath = request.getServletContext().getRealPath(INITPATH+"/"+name);
		// ②创建该文件夹，若不存在则创
		File filepath = new File(realPath); 
		createDirectory(filepath); // 调用创建文件夹的方法
		File tempFile = new File(realPath + File.separator + fileName);
		// ③保存文件
		fileRecord.transferTo(tempFile);
		return tempFile.getPath();
	}
	
	/**
	 * 日期处理工具类
	 * @param date
	 * @return
	 */
	public static String formTime(Date date) {
		
		return "";
	}
}
