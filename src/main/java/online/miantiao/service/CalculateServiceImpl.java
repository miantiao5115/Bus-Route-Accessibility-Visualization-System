package online.miantiao.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import online.miantiao.Entity.TAZAccessibilityInfo;
import online.miantiao.utils.CheckFileType;
import online.miantiao.utils.ReadTAZAccessibilityExcel;

@Service
public class CalculateServiceImpl implements CalculateService {
	
	/**
	  * ʵ���ж��ļ��Ƿ���excel�ļ�
	 */
	public boolean isExcelFile(String fileName) {
		// TODO Auto-generated method stub
		return CheckFileType.validateExcel(fileName);
	}
	
	/**
	 * ����Excel�ļ�
	 */
	public List<TAZAccessibilityInfo> readTAZAccessibilityExcelFile(MultipartFile Mfile) {
		// TODO Auto-generated method stub
		return new ReadTAZAccessibilityExcel().getExcelInfo(Mfile);
	}
	

	

}
