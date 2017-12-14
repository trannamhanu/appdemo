package server.response.error;

import constant.ResponseConstant.ResponseCode;
import server.response.StringResponse;

public class BadRequestResponse extends StringResponse {

	public BadRequestResponse() {
		super(ResponseCode.BAD_REQUEST_ERROR, ResponseCode.BAD_REQUEST_ERROR_DETAIL);
	}
	
}
