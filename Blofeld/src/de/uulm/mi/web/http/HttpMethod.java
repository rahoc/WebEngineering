package de.uulm.mi.web.http;

/**
 * An enum of available HTTP methods.
 * 
 * @see also http://tools.ietf.org/html/rfc2616.html#section-5.1
 * 
 * @author Benjamin Erb
 * 
 */
public enum HttpMethod
{
	HEAD,
	GET,
	PUT,
	POST;
	
	//Complete other methods (see http://tools.ietf.org/html/rfc2616.html#section-9)

	@Override
	public String toString()
	{
		return this.name();
	}

	/**
	 * Extracts the HTTP method from the request line.
	 * 
	 * @param headerLine HTTP request line
	 * @return the HTTP method 
	 * @throws IllegalArgumentException
	 */
	public static HttpMethod extractMethod(String requestLine) throws IllegalArgumentException
	{
		//Extract HTTP Method from request line (see http://tools.ietf.org/html/rfc2616.html#section-5.1).
		String[] request = requestLine.split(" ");
		if (request[0] == "HEAD") {
			return HttpMethod.HEAD;
		}
		else if (request[0] == "GET") {
			return HttpMethod.GET;
		}
		else if (request[0] == "PUT") {
			return HttpMethod.PUT;
		}
		else if (request[0] == "POST") {
			return HttpMethod.POST;
		}
		else {
			throw new IllegalArgumentException("HTTP Method not known");
		
		}
	}
}
