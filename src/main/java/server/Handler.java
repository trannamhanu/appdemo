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

import constant.RequestConstant;
import constant.ResponseConstant;
import server.api.APIAdapter;
import server.api.APIManager;
import server.response.ErrorResponse;
import server.response.Response;

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
			System.out.println("Error parsing json");
			response = new ErrorResponse(ResponseConstant.ResponseCode.BAD_REQUEST_ERROR,
					ResponseConstant.ResponseCode.BAD_REQUEST_ERROR_DETAIL);
			writeResponse(response, servletResponse);
			return;
		}

		String api = (String) inputObject.get(RequestConstant.API_KEY);
		if (api == null) {
			response = new ErrorResponse(ResponseConstant.ResponseCode.BAD_REQUEST_ERROR,
					ResponseConstant.ResponseCode.BAD_REQUEST_ERROR_DETAIL);
		} else {
			APIAdapter adapter = APIManager.getAPI(api);
			if (adapter == null) {
				response = new ErrorResponse(ResponseConstant.ResponseCode.UNKNOWN_API,
						ResponseConstant.ResponseCode.UNKNOWN_API_DETAIL);
			} else {
				response = adapter.process(inputObject);
			}
		}

		writeResponse(response, servletResponse);

	}
	
	public void writeResponse(Response response, HttpServletResponse servletResponse) throws IOException {
		OutputStream out = servletResponse.getOutputStream();
		out.write(response.toString().getBytes());
		out.close();
		out.flush();
	}

}
