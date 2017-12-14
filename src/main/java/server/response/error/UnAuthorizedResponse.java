package server.response.error;

import constant.ResponseConstant.ResponseCode;
import server.response.StringResponse;

public class UnAuthorizedResponse extends StringResponse {
	public UnAuthorizedResponse() {
		super(ResponseCode.UNAUTHORIZED_ERROR, ResponseCode.UNAUTHORIZED_ERROR_DETAIL);
	}
}
