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
			// Read the Inputstream in a well formatted requestLine String
			br = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = br.readLine()) != null &&!line.isEmpty()) {
				sb.append(line+"\r\n");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
		}
		
		// Create BasicHttpRequest
		HttpRequest req = new BasicHttpRequest( sb.toString());
		return req;
	}

	@Override
	protected HttpResponse handleRequest(HttpRequest request)
	{
		// Create empty response
		BasicHttpResponse response = new BasicHttpResponse(request.getHttpVersion(),null, null, null);
		// read specific file
		// TODO: Change from testing with fixed file to the request URI (windows vs. unix formatting)
		Path file = Paths.get("C:\\myweb\\index.html"); // + request.getRequestUri());
		byte[] fileArray;
		try {
			// Read the file as bytes
			fileArray = Files.readAllBytes(file);
			// Status OK
			response.setHttpStatusCode(HttpStatusCode.OK);
			// Entity
			response.setEntity(fileArray);
			
		} catch (IOException e) {
			response.setHttpStatusCode(HttpStatusCode.BAD_REQUEST);
		}
		// Create Headers
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Host", request.getHeaders().get("Host"));
			response.setHeader(headers);
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
		// TODO Auto-generated method stub musst be implemented for testing set to true...
		return true;
	}

	
}
