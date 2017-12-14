package server.response.error;

import constant.ResponseConstant.ResponseCode;
import server.response.StringResponse;

public class SuccessResponse extends StringResponse {
	public SuccessResponse() {
		super(ResponseCode.SUCCESS, ResponseCode.SUCCESS_DETAIL);
	}
}
