package com.niuxin.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import Decoder.BASE64Decoder;


public class ReceivePictureAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;

	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	public void upload() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String photo = request.getParameter("photo");
		String name = request.getParameter("name");
		try {
			// 对base64数据进行解码 生成 字节数组，不能直接用Base64.decode（）；进行解密
			byte[] photoimg = new BASE64Decoder().decodeBuffer(photo);
			for (int i = 0; i < photoimg.length; ++i) {
				if (photoimg[i] < 0) {
					// 调整异常数据
					photoimg[i] += 256;
				}
			}
			// byte[] photoimg =
			// Base64.decode(photo);//此处不能用Base64.decode（）方法解密，我调试时用此方法每次解密出的数据都比原数据大
			// 所以用上面的函数进行解密，在网上直接拷贝的，花了好几个小时才找到这个错误（菜鸟不容易啊）
			System.out.println("图片的大小：" + photoimg.length);
			File file = new File("e:", "decode.png");
			File filename = new File("e:\\name.txt");
			if (!filename.exists()) {
				file.createNewFile();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			FileOutputStream out1 = new FileOutputStream(filename);
			out1.write(name.getBytes());
			out.write(photoimg);
			out.flush();
			out.close();
			out1.flush();
			out1.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	

	//这是服务端的代码：
	public void download() {
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
			try {
				Date date = new Date();
				String id = request.getParameter("id");
				response.setContentType("text/plain");
				response.setHeader("Content-Disposition", "attachment; filename="+id);
				InputStream inputStream = new FileInputStream("E:\\decode.png");//此时读取的是资源的绝对路径
				OutputStream outputStream = response.getOutputStream();
			//	InputStream inputStream = new ByteArrayInputStream(in);
				byte[] buffer = new byte[1024];
				int i = -1;
				while ((i = inputStream.read(buffer)) != -1) {
				 outputStream.write(buffer, 0, i);
				}
				outputStream.flush();
				outputStream.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	//客户端代码：使用了android_async_http

//	 HttpClient.client.get(url, new BinaryHttpResponseHandler() {
//			             @Override
//			             public void onSuccess(byte[] arg0) {
//			                 super.onSuccess(arg0);
//			                 System.out.println(arg0.length);
//			                 imageView.setImageBitmap(BitmapFactory.decodeByteArray(arg0, 0, arg0.length));
//			             }


}
