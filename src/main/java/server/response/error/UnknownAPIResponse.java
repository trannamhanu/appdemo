package server.response.error;

import constant.ResponseConstant.ResponseCode;
import server.response.StringResponse;

public class UnknownAPIResponse extends StringResponse {
	public UnknownAPIResponse() {
		super(ResponseCode.UNKNOWN_API, ResponseCode.UNKNOWN_API_DETAIL);
	}
}
