package de.uulm.mi.web.http.impl;

import java.util.Map;

import de.uulm.mi.web.http.HttpResponse;
import de.uulm.mi.web.http.HttpStatusCode;
import de.uulm.mi.web.http.HttpVersion;

public class BasicHttpResponse implements HttpResponse
{
	// add setters/appropriate constructors
	HttpVersion httpVersion;
	Map<String, String> headers;
	byte[] entity;
	HttpStatusCode httpStatusCode;
	
	public BasicHttpResponse(HttpVersion version, HttpStatusCode statusCode, Map<String, String> headers, byte[] entity) {
		this.httpVersion = version;
		this.httpStatusCode = statusCode;
		this.entity = entity;
		this.headers = headers;
	}
	
	public void setHttpStatusCode(HttpStatusCode status) {
		this.httpStatusCode = status;
	}
	public void setEntity(byte[] entity) {
		this.entity = entity;
	}
	public void setHeader(Map<String, String> headers) {
		this.headers = headers;
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
	public HttpStatusCode getStatusCode()
	{
		return httpStatusCode;
	}

}
