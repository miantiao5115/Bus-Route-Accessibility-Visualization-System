package online.miantiao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import online.miantiao.Entity.TAZAccessibilityInfo;

public class ReadTAZAccessibilityExcel {

	// ������
	private int totalRows = 0;
	// ������
	private int totalCells = 0;
	// ������Ϣ������
	private String errorMsg;

	// ���췽��
	public ReadTAZAccessibilityExcel() {
	}

	// ��ȡ������
	public int getTotalRows() {
		return totalRows;
	}

	// ��ȡ������
	public int getTotalCells() {
		return totalCells;
	}

	// ��ȡ������Ϣ-��ʱδ�õ���ʱ����
	public String getErrorInfo() {
		return errorMsg;
	}

	/**
	 * ��EXCEL�ļ�
	 * 
	 * @param fielName
	 * @return
	 */
	public List<TAZAccessibilityInfo> getExcelInfo(MultipartFile Mfile) {

		// ��spring�ļ��ϴ���MultipartFileת����CommonsMultipartFile����
		CommonsMultipartFile cf = (CommonsMultipartFile) Mfile;
		// ��ȡ���ش洢·��
		File file = new File("D:\\fileupload");
		// ����һ��Ŀ¼ ������·�����ɵ�ǰ File ����ָ����������һ����ĸ�·������
		if (!file.exists())
			file.mkdirs();
		// �½�һ���ļ�
		File file1 = new File("D:\\fileupload\\" + new Date().getTime() + ".xls");
		// ���ϴ����ļ�д���½����ļ���
		try {
			cf.getFileItem().write(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ʼ���ɴ�����Ϣ�ļ���
		List<TAZAccessibilityInfo> tazAccessibilityInfoList = new ArrayList<TAZAccessibilityInfo>();
		// ��ʼ��������
		FileInputStream is = null;
		Workbook wb = null;
		try {
			// �����½����ļ�ʵ����������
			is = new FileInputStream(file1);
			// ����excel��������ݶ�ȡ�ͻ���Ϣ
			// ��excel��2003ʱ
			if (CheckFileType.isExcel2003(Mfile.getOriginalFilename()))
				wb = new HSSFWorkbook(is);
			// ��excel��2007ʱ
			if (CheckFileType.isExcel2007(Mfile.getOriginalFilename()))
				wb = new XSSFWorkbook(is);
			// ��ȡExcel�����Ϣ
			if (wb != null)
				tazAccessibilityInfoList = readExcelValue(wb);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return tazAccessibilityInfoList;
	}

	/**
	 * ��ȡExcel�������ϸ��Ϣ
	 * 
	 * @param wb
	 * @return
	 */
	private List<TAZAccessibilityInfo> readExcelValue(Workbook wb) {
		// �õ���һ��sheet
		Sheet sheet = wb.getSheetAt(0);

		// �õ�Excel������
		this.totalRows = sheet.getPhysicalNumberOfRows();

		// �õ�Excel������(ǰ����������)
		// �ж���������һ�����ҵ�һ�б����б��⣨������bug�����ļ���һ��û����ֵ�����ˣ�
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		} else {
			return null;
		}

		List<TAZAccessibilityInfo> tazAccessibilityInfoList = new ArrayList<TAZAccessibilityInfo>();
		TAZAccessibilityInfo tazAccessibilityData;
		// ����һ������
		// NumberFormat nf = NumberFormat.getInstance();
		// ���ַ��������Զ���".0"�����ֿ�ֱ�ӽ��
		// ������ǿ�ѧ�����������־�ת�����˴����ŵģ����磺12345678912345�Ŀ�ѧ��������1.23457E+13�����������ʽ����ͱ�����ַ�����12,345,678,912,345������Ҳ��������Ҫ�Ľ��������Ҫ������ȥ��
		// if (s.indexOf(",") >= 0) {
		// s = s.replace(",", "");
		// }
		// ѭ��Excel����,�ӵڶ��п�ʼ�����ⲻ���
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;
			tazAccessibilityData = new TAZAccessibilityInfo();
			// ѭ��Excel����
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				if (null != cell) {
					if (c == 0) {
						tazAccessibilityData.setFid((int) cell.getNumericCellValue());// �õ����е�һ��ֵ
					} else if (c == 1) {
						tazAccessibilityData.setAccessibility((int) cell.getNumericCellValue());// �õ����еڶ���ֵ
					}
				}
			}
			// ��Ӷ��󵽼�����
			tazAccessibilityInfoList.add(tazAccessibilityData);
		}
		return tazAccessibilityInfoList;
	}

}
