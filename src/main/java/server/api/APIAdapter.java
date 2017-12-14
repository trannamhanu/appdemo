package server.api;

import server.request.Request;
import server.response.Response;

public interface APIAdapter {
	public Response process(Request request);
}
