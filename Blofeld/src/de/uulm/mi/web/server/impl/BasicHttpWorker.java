package de.uulm.mi.web.server.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.uulm.mi.web.http.HttpRequest;
import de.uulm.mi.web.http.HttpResponse;
import de.uulm.mi.web.http.HttpStatusCode;
import de.uulm.mi.web.http.impl.BasicHttpRequest;
import de.uulm.mi.web.http.impl.BasicHttpResponse;
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
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		HttpRequest req = new BasicHttpRequest( sb.toString());
		return req;
	}

	@Override
	protected HttpResponse handleRequest(HttpRequest request)
	{
		BasicHttpResponse response = new BasicHttpResponse(request.getHttpVersion(),null, null, null);
		Path file = Paths.get("C:\\myweb\\" + request.getRequestUri());
		byte[] fileArray;
		try {
			fileArray = Files.readAllBytes(file);
			response.setHttpStatusCode(HttpStatusCode.OK);
			response.setEntity(fileArray);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Host", request.getHeaders().get("Host"));
			response.setHeader(headers);
		} catch (IOException e) {
			response.setHttpStatusCode(HttpStatusCode.BAD_REQUEST);
		}
		return response;
	}

	@Override
	protected void sendResponse(HttpResponse response, OutputStream outputStream) throws IOException
	{
		//Build response string
		StringBuilder sb = new StringBuilder();
		sb.append(response.getHttpVersion().toString());
		sb.append(" ");
		sb.append(response.getStatusCode().getCode());
		sb.append(" ");
		sb.append(response.getStatusCode().getReasonPhrase());
		sb.append("\r\n");
		for (String singleKey : response.getHeaders().keySet()) {
		  sb.append(singleKey + ": " + response.getHeaders().get(singleKey));
		  sb.append("\r\n");
		}
		sb.append("\r\n");
		sb.append(response.getEntity());
		
		// Write it
		BufferedWriter bw = null;
		bw = new BufferedWriter(new OutputStreamWriter(outputStream));
		bw.write(sb.toString());
		
	}

	@Override
	protected boolean keepAlive(HttpRequest request, HttpResponse response)
	{
		// TODO Auto-generated method stub
		return false;
	}

	
}
