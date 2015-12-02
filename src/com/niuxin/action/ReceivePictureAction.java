package com.niuxin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.tomcat.util.bcel.classfile.Constant;

import com.niuxin.bean.Form;
import com.niuxin.service.IFormService;
import com.niuxin.util.Constants;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;
import Decoder.BASE64Decoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReceivePictureAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;

	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	@Resource
	private IFormService formService;

	public void upload() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String photo = request.getParameter("photo");
		String filename = request.getParameter("filename");
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
			String os = System.getProperty ("os.name");
			String path ="";
			if(os.contains("Windows")){
				path = Constants.WINDOWS_UPLOAD_PICTUREDIR; 
			}else{
				path = Constants.LINUX_UPLOAD_PICTUREDIR; 	
			}
			File file = new File(path, filename+".png");

			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			out.write(photoimg);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 这是服务端的代码：
	public void download() {

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		try {
			String str = new GetJsonString().getJsonString(request);
			// 用json进行解析
			JSONArray jsar = JSONArray.fromObject(str);
			// 用json进行解析
			JSONObject json_data = jsar.getJSONObject(0);
			Integer id = json_data.getInt("formid");			
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=" + id);
			Form form = formService.selectById(id);
			String path = form.getPictureurl();
			String os = System.getProperty ("os.name");
				if(os.contains("Windows")){
					path = Constants.WINDOWS_UPLOAD_PICTUREDIR +path; 
				}else{
					path = Constants.LINUX_UPLOAD_PICTUREDIR +path; 	
				}
				System.out.println(path);
			InputStream inputStream = new FileInputStream(path);// 此时读取的是资源的绝对路径
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
			outputStream.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
