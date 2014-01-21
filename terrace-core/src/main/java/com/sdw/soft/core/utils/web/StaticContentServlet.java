/**
 * @Date 2014年1月21日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.core.utils.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 显示静态内容Servlet, 演示高效读取静态内容, 控制客户端缓存, 压缩传输, 弹出下载对话框
 * 演示文件高效读取,客户端缓存控制及Gzip压缩传输
 * @author syd
 */
public class StaticContentServlet extends HttpServlet {

	private static final long serialVersionUID = 5848706046403620100L;
	/*需要被Gzip压缩的Mime类型*/
	private static final String[] GZIP_MIME_TYPES = {
		"text/html", "application/xhtml+xml", "text/plain", "text/css","text/javascript", "application/x-javascript", "application/json"
	};
	/*需要被Gzip压缩的最小文件大小.*/
	private static final int GZIP_MINI_LENGTH = 512;
	
	private MimetypesFileTypeMap mimetypesFileTypeMap;
	
	private ApplicationContext applicationContext;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String contentPath = req.getParameter("contentPath");
		if(StringUtils.isBlank(contentPath)){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"contentPath parameter is required.");
			return ;
		}
		//获取请求内容的基本信息
		ContentInfo contentInfo = getContentInfo(contentPath);
		//根据Etag或ModifiedSince Header判断客户端的缓存文件是否有效,如仍有效则设置返回码为304,直接返回
		if(!Servlets.checkIfModifiedSince(req, resp, contentInfo.lastModified) || !Servlets.checkIfNoneMatchEtag(req, resp, contentInfo.etag)){
			return ;
		}
		// 设置Etag/过期时间
		Servlets.setExpiresHeader(resp, Servlets.ONE_YEAR_SECONDS);
		Servlets.setLastModifiedHeader(resp, contentInfo.lastModified);
		Servlets.setEtag(resp, contentInfo.etag);

		// 设置MIME类型
		resp.setContentType(contentInfo.mimeType);
		// 设置弹出下载文件请求窗口的Header
		if (req.getParameter("download") != null) {
			Servlets.setFileDownloadHeader(resp, contentInfo.fileName);
		}
		// 构造OutputStream
				OutputStream output;
				if (checkAccetptGzip(req) && contentInfo.needGzip) {
					// 使用压缩传输的outputstream, 使用http1.1 trunked编码不设置content-length.
					output = buildGzipOutputStream(resp);
				} else {
					// 使用普通outputstream, 设置content-length.
					resp.setContentLength(contentInfo.length);
					output = resp.getOutputStream();
				}

				// 高效读取文件内容并输出,然后关闭input file
				FileUtils.copyFile(contentInfo.file, output);
				output.flush();
	}
	
	/**
	 * 检查浏览器客户端是否支持gzip编码.
	 */
	private static boolean checkAccetptGzip(HttpServletRequest request) {
		// Http1.1 header
		String acceptEncoding = request.getHeader("Accept-Encoding");

		return StringUtils.contains(acceptEncoding, "gzip");
	}

	/**
	 * 设置Gzip Header并返回GZIPOutputStream.
	 */
	private OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Vary", "Accept-Encoding");
		return new GZIPOutputStream(response.getOutputStream());
	}

	/**
	 * 初始化.
	 */
	@Override
	public void init() throws ServletException {
		// 保存applicationContext以备后用，纯演示.
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		// 初始化mimeTypes, 默认缺少css的定义,添加之.
		mimetypesFileTypeMap = new MimetypesFileTypeMap();
		mimetypesFileTypeMap.addMimeTypes("text/css css");
	}
	/**
	 * 创建content基本信息
	 * @param contentPath
	 * @return
	 */
	private ContentInfo getContentInfo(String contentPath){
		ContentInfo contentInfo = new ContentInfo();
		
		String realFilePath = getServletContext().getRealPath(contentPath);
		File file = new File(realFilePath);
		contentInfo.file = file;
		contentInfo.contentPath = contentPath;
		contentInfo.fileName = file.getName();
		contentInfo.length = (int) file.length();
		
		contentInfo.lastModified = file.lastModified();
		contentInfo.etag = "W/\"" + contentInfo.lastModified + "\"";
		contentInfo.mimeType = mimetypesFileTypeMap.getContentType(contentInfo.fileName);
		if((contentInfo.length >= GZIP_MINI_LENGTH) && ArrayUtils.contains(GZIP_MIME_TYPES, contentInfo.mimeType)){
			contentInfo.needGzip = true;
		}else{
			contentInfo.needGzip = false;
		}
		return contentInfo;
	}
	/**
	 * 定义Content的基本信息.
	 *
	 */
	static class ContentInfo{
		protected String contentPath;
		protected File file;
		protected String fileName;
		protected int length;
		protected String mimeType;
		protected long lastModified;
		protected String etag;
		protected boolean needGzip;
	}
}


