package de.uulm.mi.web.server.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import de.uulm.mi.web.http.HttpRequest;
import de.uulm.mi.web.http.HttpResponse;
import de.uulm.mi.web.server.HttpWorker;

public class BasicHttpWorker extends HttpWorker
{
	public BasicHttpWorker(Socket socket, BasicHttpServer server)
	{
		super(socket, server);
	}

	@Override
	protected HttpRequest parseRequest(InputStream inputStream) throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected HttpResponse handleRequest(HttpRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void sendResponse(HttpResponse response, OutputStream outputStream) throws IOException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean keepAlive(HttpRequest request, HttpResponse response)
	{
		// TODO Auto-generated method stub
		return false;
	}

	
}
