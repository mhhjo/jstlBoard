package utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

/** 
 * Utility 기능을 수행하는 클래스
 * 
 * @author 홍길동
 * @version 1.0
 */
public class JSFunction {
	
	/** 
	 * 메시지를 알림창에 띄우고, 지정한 url로 이동. jsp페이지에서 사용
	 * 
	 * @param msg	알림창에 띄울 메시지
	 * @param url	이동할 URL주소
	 * @param out	출력을 위한 jsp 내장객체
	 */
	public static void alertLocation(String msg,String url, JspWriter out) {
		try {
			String script=""
					+ "<script>"
					+ "	alert('"+msg+"');"
					+ "	location.href='"+url+"';"
					+ "</script>";
			out.println(script);
			
		}catch(Exception e) {e.printStackTrace();}
	}
	
	/** 
	 * 메시지를 띄우고 이전 페이지로 이동하는 메서드. jsp페이지에서 사용
	 * 
	 * @param msg 알림창에 띄울 메시지 
	 * @param out 출력을 위한 jsp 내장객체
	 */
	public static void alertBack(String msg,JspWriter out) {
		try {
			String script=""
					+ "<script>"
					+ "	alert('"+msg+"');"
					+ " history.back();"
					+ "</script>";
			out.println(script);
		}catch(Exception e) {e.printStackTrace();}
	}
	
	//메서드오버로딩. 서블릿에서 사용
	/** 
	 * 메시지를 띄우고,지정한 url로 이동. 서블릿 컨트롤러에서 사용
	 * 
	 * @param response response객체
	 * @param msg 알림창에 띄울 메시지 
	 * @param url 이동할 url주소
	 */
	public static void alertLocation(HttpServletResponse response,String msg,String url) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer=response.getWriter();//서블릿에서 출력하기 위한 객체생성
			String script=""
					+ "<script>"
					+ "	alert('"+msg+"');"
					+ "	location.href='"+url+"';"
					+ "</script>";
			writer.println(script);
			
		}catch(Exception e) {e.printStackTrace();}
	}
	//메서드오버로딩. 서블릿에서 사용
	/** 
	 * 메시지를 띄우고 이전 페이지로 이동하는 메서드. 서블릿 컨트롤러에서 사용
	 * 
	 * @param response response객체
	 * @param msg 알림창에 띄울 메시지 
	 * 
	 */
	public static void alertBack(HttpServletResponse response,String msg) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer=response.getWriter();//서블릿에서 출력하기 위한 객체생성
			String script=""
					+ "<script>"
					+ "	alert('"+msg+"');"
					+ "	history.back();"
					+ "</script>";
			writer.println(script);
			
		}catch(Exception e) {e.printStackTrace();}
	}	
}
