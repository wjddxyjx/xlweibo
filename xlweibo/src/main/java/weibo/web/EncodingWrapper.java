package weibo.web;
 

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingWrapper extends HttpServletRequestWrapper {
	private String ENCODING;
	/*
	 * 调用父类构造器
	 */
	public EncodingWrapper(HttpServletRequest request,String ENCODING) {
		super(request);
		this.ENCODING = ENCODING;
	}
	public String getParameter(String name){
		String value = getRequest().getParameter(name);
		if(value!=null){
			byte[] b;
			try {
				b = value.getBytes("ISO-8859-1");
				value = new String(b,ENCODING);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return value;	
	}
	
	
}
