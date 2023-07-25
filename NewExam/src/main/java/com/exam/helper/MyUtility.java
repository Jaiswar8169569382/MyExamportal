package com.exam.helper;

import javax.servlet.http.HttpServletRequest;

public class MyUtility {

	public static String getUrl(HttpServletRequest request)
	{
String requestUrl=request.getRequestURL().toString();
		
		return requestUrl.replace(request.getServletPath(), "");
	}
}
