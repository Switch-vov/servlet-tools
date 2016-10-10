package com.pc.download;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// 获取参数
		String fileName = request.getParameter("fileName");
		// 对文件名进行转码，从iso-8859-1->utf-8
		fileName = new String(fileName.getBytes("iso-8859-1"), "utf-8");

		// 获取文件类型
		String fileType = request.getServletContext().getMimeType(fileName);
		// 获取文件所在真实路径
		String realPath = request.getServletContext().getRealPath("download/" + fileName);
		
		// 解决下载页面中文问题
		String userAgent = request.getHeader("User-Agent");
		// 火狐浏览器采用Base64编码
		if(userAgent.contains("Firefox")) {
			fileName = base64EncodeFileName(fileName);
		} else {
			fileName = URLEncoder.encode(fileName, "utf-8");
		}
		
		// 设置下载所需响应头
		// 设置文件mine类型
		response.addHeader("Content-Type", fileType);
		// 设置文件下载
		response.addHeader("Content-Disposition", "attachment;filename=	" + fileName);
		
		// 创建文件字节流
		FileInputStream fis = new FileInputStream(realPath);
		OutputStream fos = response.getOutputStream();
		
		// 缓冲数组
		byte[] buf = new byte[4096];
		int len = 0;
		while((len = fis.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		// 关闭文件流(response的文件流由容器管理，不需要关闭)
		fis.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// 火狐浏览器编码问题
	public static String base64EncodeFileName(String fileName) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		try {
			return "=?UTF-8?B?" + new String(base64Encoder.encode(fileName.getBytes("UTF-8"))) + "?=";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}