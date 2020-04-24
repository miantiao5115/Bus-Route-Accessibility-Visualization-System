package online.miantiao.Entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * ͨ�õķ��ص���
 * 
 */
@Component
public class ResultMessage {
	
	//״̬��   100-�ɹ�    200-ʧ��
	private int code;
	//��ʾ��Ϣ
	private String msg;
	
	//�û�Ҫ���ظ������������
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public ResultMessage success(){
		ResultMessage result = new ResultMessage();
		result.setCode(100);
		result.setMsg("����ɹ���");
		return result;
	}
	
	public ResultMessage fail(){
		ResultMessage result = new ResultMessage();
		result.setCode(200);
		result.setMsg("����ʧ�ܣ�");
		return result;
	}
	
	public ResultMessage add(String key,Object value){
		this.getResultMap().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public String toString() {
		return "ResultMessage [code=" + code + ", msg=" + msg + ", resultMap=" + resultMap + "]";
	}
	
}
