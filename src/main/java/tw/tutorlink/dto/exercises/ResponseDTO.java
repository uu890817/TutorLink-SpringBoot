package tw.tutorlink.dto.exercises;

import java.util.List;

public class ResponseDTO {
	
	private List<Object> dataList;
	private Object data;
	private Integer errorCode;
	private String errorMsg;
	
	public ResponseDTO() {
	}
	
	public ResponseDTO(List<Object> dataList, Integer errorCode, String errorMsg) {
		this.dataList = dataList;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public ResponseDTO(Object data, Integer errorCode, String errorMsg) {
		this.data = data;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	
	
	
	
}
