package server.response.error;

import constant.ResponseConstant.ResponseCode;
import server.response.StringResponse;

public class UnknownErrorResponse extends StringResponse {
	public UnknownErrorResponse() {
		super(ResponseCode.UNKNOWN_ERROR, ResponseCode.UNKNOWN_ERROR_DETAIL);
	}
}
