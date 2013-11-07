package de.uulm.mi.web.http;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * An enum of available HTTP status codes.
 * 
 * @see also http://tools.ietf.org/html/rfc2616.html#section-6.1.1
 * 
 * @author Benjamin Erb
 * 
 */
public enum HttpStatusCode
{
	CONTINUE(100, "Continue"),
	OK(200, "OK"),
	BAD_REQUEST(400, "Bad Request"),
    SWITCH(101, "Switching Protocols"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    MOVEDPERM(301, "Moved Permanently"),
    UNAUTHORIZED(401, "Unauthorized"),
    PAYMENT(402, "Payment Required"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    GONE(410, "Gone"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway");
    
	//Complete status code list (see http://tools.ietf.org/html/rfc2616.html#section-6.1.1)

	private final int code;
	private final String reasonPhrase;

	private static final Map<Integer, HttpStatusCode> codeLookupTable = new HashMap<Integer, HttpStatusCode>();

	static
	{
		for (HttpStatusCode s : EnumSet.allOf(HttpStatusCode.class))
		{
			codeLookupTable.put(s.getCode(), s);
		}
	}

	private HttpStatusCode(int code, String reasonPhrase)
	{
		this.code = code;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Returns the numerical code.
	 * 
	 * @return Code
	 */
	public int getCode()
	{
		return code;
	}

	/**
	 * Returns the verbatim code.
	 * 
	 * @return reasons phrase
	 */
	public String getReasonPhrase()
	{
		return reasonPhrase;
	}

	/**
	 * Gets the {@link HttpStatusCode} type by the code number.
	 * 
	 * @param code
	 *            numerical code representation (i.e. 200)
	 * @return assosciated status code
	 */
	public static HttpStatusCode getStatusCode(int code)
	{
		return codeLookupTable.get(code);
	}
}
