package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import constant.Api;
import constant.RequestConstant;
import server.api.APIAdapter;
import server.api.APIManager;
import server.response.Response;
import server.response.error.AuthenFailedResponse;
import server.response.error.BadRequestResponse;
import server.response.error.UnknownAPIResponse;
import server.session.SessionManager;

public class Handler extends AbstractHandler {

	public void handle(String target, Request baseRequest, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws IOException, ServletException {

		baseRequest.setHandled(true);
		servletResponse.setContentType("application/json; charset=utf-8");
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		Response response = null;

		InputStreamReader isr = new InputStreamReader(baseRequest.getInputStream());
		BufferedReader reader = new BufferedReader(isr);
		String inputBody = reader.readLine();

		JSONParser parser = new JSONParser();
		JSONObject inputObject = null;

		try {
			inputObject = (JSONObject) parser.parse(inputBody);
		} catch (ParseException e) {
			sendBadRequestResponse(servletResponse);
			return;
		}

		//check api
		String api = (String) inputObject.get(RequestConstant.API_KEY);
		if (api == null) {
			sendBadRequestResponse(servletResponse);
			return;
		}
		
		APIAdapter adapter = APIManager.getAPI(api);
		if (adapter == null) {
			sendUnknownAPIResponse(servletResponse);
			return;
		}
		
		
		//check token
		String token = (String) inputObject.get(RequestConstant.TOKEN_KEY);
		if (!api.equals(Api.AUTHEN_USER)) {
			if (token == null || !SessionManager.validateSession(token)) {
				sendAuthenFailedResponse(servletResponse);
				return;
			}
		}
		
		//check query
		JSONObject query = (JSONObject) inputObject.get(RequestConstant.QUERY_KEY);
		if (query == null) {
			sendBadRequestResponse(servletResponse);
			return;
		}
			
		server.request.Request request = new server.request.Request(api, token, query);
		response = adapter.process(request);
		writeResponse(response, servletResponse);

	}
	
	public void writeResponse(Response response, HttpServletResponse servletResponse) throws IOException {
		OutputStream out = servletResponse.getOutputStream();
		out.write(response.toString().getBytes());
		out.close();
		out.flush();
	}
	
	public void sendBadRequestResponse(HttpServletResponse servletResponse) {
		try {
			writeResponse(new BadRequestResponse(), servletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendUnknownAPIResponse(HttpServletResponse servletResponse) {
		try {
			writeResponse(new UnknownAPIResponse(), servletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendAuthenFailedResponse(HttpServletResponse servletResponse) {
		try {
			writeResponse(new AuthenFailedResponse(), servletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
