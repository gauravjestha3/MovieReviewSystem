package com.example.movie.errors;

public enum CustomExceptionCode {
	USER_NOT_FOUND("ERR1001","User Data missing", "UserDataNotFoundException"),
	USER_NAME_NOT_FOUND("ERR1002","User Name Data missing", "UserNameDataNotFoundException"),
	NO_DATA_FOUND("ERR1003","No Data Found", "NoDataFoundException"),
	ALREADY_REVIEWED("ERR1004","Already reviewed","AlreadyReviewedException"),
	MOVIE_NOT_RELEASED("ERR1005","Movie not released","MovieNotReleased");
	private final String errCode;
	private final String errMsg;
	private final String description;

	/***
	 * @param text
	 */
	private CustomExceptionCode(final String errCode, final String errMsg,final String description) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.description=description;
	}

	@Override
	public String toString() {
		return errCode + ": " + errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public String getDescription() {
		return description;
	}
	

}
