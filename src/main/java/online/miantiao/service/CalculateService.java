package online.miantiao.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import online.miantiao.Entity.TAZAccessibilityInfo;


public interface CalculateService {

	/**
	 * ����ļ��Ƿ���excel�ļ�
	 * @param fileName �ļ���
	 * @return
	 */
	public boolean isExcelFile(String fileName);
	
	/**
	  * ��ȡ��ͨ�������ɴ��Ե�excel�ļ�����
	 * @param Mfile ���ص��ļ�
	 * @return
	 */
	public List<TAZAccessibilityInfo> readTAZAccessibilityExcelFile(MultipartFile Mfile); 


}
