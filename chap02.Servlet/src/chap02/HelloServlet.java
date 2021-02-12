package chap02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{
	//处理get请求
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException,IOException{
		//相应html请求
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head><title>servlet</title></head>");
		pw.println("<body><h1>第7-8周 5.2servlet应用</h1></body>");
		pw.println("</html>");
		pw.close();
	}
}
