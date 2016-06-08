package com.dl.service.let;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import com.dl.service.common.JsonUtil;
import com.dl.service.db.DBRegister;
import com.dl.service.model.BaseModel;
import com.dl.service.model.UserInfoData;

/**
 * Servlet implementation class UserIconChange
 */
@WebServlet(description = "用户图标进行替换，上传服务器图片。", urlPatterns = { "/UserIconChange" })
public class UserIconChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String TAG = getClass().getSimpleName();
	private int i = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserIconChange() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		feedback(request, response);
	}

	/**
	 * 下午2:24:55
	 * 
	 * @author zhangyh2 TODO
	 */
	private void feedback(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		/**
		 * 加了synchronized后，并发访问i时就不存在线程安全问题了， 为什么加了synchronized后就没有线程安全问题了呢？
		 * 假如现在有一个线程访问Servlet对象，那么它就先拿到了Servlet对象的那把锁
		 * 等到它执行完之后才会把锁还给Servlet对象，由于是它先拿到了Servlet对象的那把锁，
		 * 所以当有别的线程来访问这个Servlet对象时，由于锁已经被之前的线程拿走了，后面的线程只能排队等候了
		 */
		synchronized (this) {// 在java中，每一个对象都有一把锁，这里的this指的就是Servlet对象
			i++;

			request.setCharacterEncoding("utf-8");
			// 获得磁盘文件条目工厂。
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 获取文件上传需要保存的路径，upload文件夹需存在。
			String pathcacth = request.getSession().getServletContext()
					.getRealPath("");
			String path = pathcacth.split("DLService")[0]+"\\ImageIcon";
			// 设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。
			factory.setRepository(new File(path));
			// 设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。
			factory.setSizeThreshold(1024 * 1024);
			// 上传处理工具类（高水平API上传处理？）
			ServletFileUpload upload = new ServletFileUpload(factory);
			String json = null;
			try {
				// 调用 parseRequest（request）方法 获得上传文件 FileItem 的集合list 可实现多文件上传。
				List<FileItem> list = upload
						.parseRequest(request);
				for (FileItem item : list) {
					// 获取表单属性名字。
					String name = item.getFieldName();
					// 如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
					if (item.isFormField()) {
						// 获取用户具体输入的字符串，
						String value = item.getString();
						request.setAttribute(name, value);
					}
					// 如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
					else {
						// 获取路径名
						String value = item.getName();
						// 取到最后一个反斜杠。
						int start = value.lastIndexOf("\\");
						// 截取上传文件的 字符串名字。+1是去掉反斜杠。
						String token = value.substring(0, start);
						String filename = Base64.encodeBase64(value.getBytes())
								.toString() + value.substring(start + 1);
						request.setAttribute(name, filename);

						/*
						 * 第三方提供的方法直接写到文件中。 item.write(new File(path,filename));
						 */
						// 收到写到接收的文件中。
						OutputStream out = new FileOutputStream(new File(path,
								filename));
						InputStream in = item.getInputStream();

						int length = 0;
						byte[] buf = new byte[1024];
						System.out.println("获取文件总量的容量:" + item.getSize());
						while ((length = in.read(buf)) != -1) {
							out.write(buf, 0, length);
						}
						in.close();
						out.close();
						String icon = request.getScheme() + "://"
								+ request.getServerName() + ":"
								+ request.getServerPort()
								+ "/ImageIcon/" + filename;
						UserInfoData data = DBRegister.FindInfoByToken(token);
						data.setIcon(icon);

						DBRegister.update(data);
					}
				}
				BaseModel<Object> mo = new BaseModel<Object>(200, "文件上传成功",
						null);
				json = JsonUtil.pojo2Json(mo);
			} catch (Exception e) {
				e.printStackTrace();
				BaseModel<Object> mo = new BaseModel<Object>(201, "文件保存失败",
						null);
				json = JsonUtil.pojo2Json(mo);
			}
			response.getWriter().write(json);
			System.out.println(TAG + "图片上传接口调用" + i + "次");

		}
	}
}
