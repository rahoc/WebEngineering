package de.uulm.mi.web.http.impl;

import java.util.HashMap;
import java.util.Map;

import de.uulm.mi.web.http.HttpMethod;
import de.uulm.mi.web.http.HttpRequest;
import de.uulm.mi.web.http.HttpVersion;

public class BasicHttpRequest implements HttpRequest
{
	//  add setters/appropriate constructors
	HttpVersion httpVersion;
	Map<String, String> headers;
	byte[] entity;
	HttpMethod httpMethod;
	String requestUri;
	
	public BasicHttpRequest(String requestLine) { 
		httpVersion = HttpVersion.extractVersion(requestLine);
		httpMethod = HttpMethod.extractMethod(requestLine);
		setHeaders(requestLine);
		setEntitiy(requestLine);
		setRequestUri(requestLine);
	}
	
	public void setHeaders(String requestLine) {
		Map<String, String> extractedHeaders = new HashMap<String, String>();
		String[] request = requestLine.split("\r\n");
		for (int i=1; i<request.length; i++) {
			if (request[i]=="") {
				break;
			}
			extractedHeaders.put(request[i].split(":")[0], request[i].split(":")[1]);
		}
		this.headers = extractedHeaders;
	}
	
	public void setEntitiy(String requestLine) {
		String[] request = requestLine.split("\r\n\r\n");
		this.entity = request[1].getBytes();
	}
	
	public void setRequestUri(String requestLine) {
		String[] request = requestLine.split(" ");
		this.requestUri = request[1];
	}
	
	@Override
	public HttpVersion getHttpVersion()
	{
		return httpVersion;
	}

	@Override
	public Map<String, String> getHeaders()
	{
		return headers;
	}

	@Override
	public byte[] getEntity()
	{
		return entity;
	}

	@Override
	public HttpMethod getHttpMethod()
	{
		return httpMethod;
	}

	@Override
	public String getRequestUri()
	{
		return requestUri;
	}

}
