package de.uulm.mi.web.http;

import de.uulm.mi.web.Http;

/**
 * An enum of available HTTP versions.
 * 
 * @see also http://tools.ietf.org/html/rfc2616.html#section-3.1
 * 
 * @author Benjamin Erb
 * 
 */
public enum HttpVersion
{
	/**
	 * HTTP/1.0
	 */
	VERSION_1_0(1, 0),

	/**
	 * HTTP/1.1
	 */
	VERSION_1_1(1, 1);

	private final int major;
	private final int minor;

	private HttpVersion(int major, int minor)
	{
		this.major = major;
		this.minor = minor;
	}

	@Override
	public String toString()
	{
		return Http.HTTP + Http.PROTOCOL_DELIMITER + major + "." + minor;
	}

	/**
	 * Extracts the HTTP version from the request line.
	 * 
	 * @param requestLine HTTP request line
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static HttpVersion extractVersion(String requestLine) throws IllegalArgumentException
	{
		//TODO: Extract HTTP Version from request line (see http://tools.ietf.org/html/rfc2616.html#section-5.1).
		return null;
	}
}
