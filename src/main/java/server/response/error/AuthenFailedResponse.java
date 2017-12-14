package server.response.error;

import constant.ResponseConstant.ResponseCode;
import server.response.StringResponse;

public class AuthenFailedResponse extends StringResponse {
	public AuthenFailedResponse() {
		super(ResponseCode.AUTHEN_ERROR, ResponseCode.AUTHEN_ERROR_DETAIL);
	}
}
