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

import server.api.APIAdapter;
import server.api.APIManager;
import util.Constant;

public class Handler extends AbstractHandler {

	public void handle(String target, Request baseRequest, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws IOException, ServletException {

		baseRequest.setHandled(true);
		servletResponse.setContentType("application/json; charset=utf-8");
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		Respond respond = null;

		InputStreamReader isr = new InputStreamReader(baseRequest.getInputStream());
		BufferedReader reader = new BufferedReader(isr);
		String inputBody = reader.readLine();

		JSONParser parser = new JSONParser();
		JSONObject inputObject = null;

		try {
			inputObject = (JSONObject) parser.parse(inputBody);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String api = (String) inputObject.get(Constant.REQUEST.API_KEY);
		if (api == null) {
			respond = new Respond(Constant.RESPONSE.CODE_ERROR, null);
		} else {
			APIAdapter adapter = APIManager.getAPI(api);
			respond = adapter.process(inputObject);
		}

		OutputStream out = servletResponse.getOutputStream();
		out.write(respond.toJsonObject().toJSONString().getBytes());
		out.close();
		out.flush();

	}

}
