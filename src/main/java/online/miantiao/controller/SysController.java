package online.miantiao.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import online.miantiao.Entity.ResultMessage;
import online.miantiao.Entity.TAZAccessibilityInfo;
import online.miantiao.service.CalculateServiceImpl;

@Controller
public class SysController {
	
	private static final int SUCCESSCODE=200;
	
	private static final int FAILCODE=400; 
	
	@Autowired
	private CalculateServiceImpl service;
	
	/**
	  *  ���ϴ����ļ�ת��Ϊjson
	 * @param tazAccessibilityFile ��ͨ������ �ɴ���excel�ļ�
	 * @return
	 */
	@RequestMapping(value="/parseExcel")
	@ResponseBody
	public ResultMessage calculate(@RequestParam(value = "tazAccessibilityFile") MultipartFile tazAccessibilityFile) {
		
		ResultMessage resultMessage = new ResultMessage();
		
		if (tazAccessibilityFile == null) {
			System.out.println("��ͨ������ ���ɴ��������ļ��ϴ�ʧ��");
			resultMessage.setCode(FAILCODE);
			resultMessage.setMsg("��ͨ������ ���ɴ��������ļ��ϴ�ʧ��");
			return resultMessage;
		}
		
		// ��ͨ������ �ɴ��������ļ���
		String tazAccessibilityFileName = tazAccessibilityFile.getOriginalFilename();
		
		// ��ͨ������ �ɴ��������ļ���С
		long tazAccessibilityFileSize=tazAccessibilityFile.getSize();
		
		//��һ���ж��ļ��Ƿ�Ϊ�գ����ж����С�Ƿ�Ϊ0���������Ƿ�Ϊnull����֤�ļ����Ƿ�ϸ�
		if(tazAccessibilityFileName==null || ("").equals(tazAccessibilityFileName) ||!service.isExcelFile(tazAccessibilityFileName)){
            System.out.println("��ͨ�������ɴ��������ļ�Ϊ�ջ��ļ���ʽ����ȷ����ʹ��������.xls��.xlsx��׺�ĵ���");
            resultMessage.setCode(FAILCODE);
			resultMessage.setMsg("��ͨ�������ɴ��������ļ�Ϊ�ջ��ļ���ʽ����ȷ����ʹ��������.xls��.xlsx��׺�ĵ���");
			return resultMessage;
        }
		
        //�����ɴ���excel����ȡ��ͨ������ �ɴ�����Ϣ���ϡ�
        List<TAZAccessibilityInfo> tazAccessibilityInfoList = service.readTAZAccessibilityExcelFile(tazAccessibilityFile);
        if(tazAccessibilityInfoList == null ) {
        	System.out.println("��ͨ�������ɴ��������ļ�����Ϊ��");
        	resultMessage.setCode(FAILCODE);
    		resultMessage.setMsg("��ͨ�������ɴ��������ļ�����Ϊ��");
    		return resultMessage;
        }
        System.out.println("��ͨ����������:"+tazAccessibilityInfoList.size());
        
		resultMessage.setCode(SUCCESSCODE);
		resultMessage.setMsg("�����ɹ�");
		return resultMessage.add("tazAccessibilityInfoList", tazAccessibilityInfoList);
	}
}
