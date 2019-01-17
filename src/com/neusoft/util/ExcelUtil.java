package com.neusoft.util;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.neusoft.view.FieldSettingFrame;

public class ExcelUtil {

	/**
	 * ��ģ�嵼��Excel
	 * 
	 * @param excelName
	 *            ���ɵ�excel������
	 * @param exportPath
	 *            ��������·��
	 * @param columnTitle
	 *            ��ͷ
	 * @param volume
	 *            ������
	 * @param file
	 *            ������
	 * @param electronicalFile
	 *            �����ļ����
	 * @return
	 */
	public static boolean exportExcel(String excelName, String exportPath,
			Object[] columnTitle, JTable volume, JTable file,
			JTable electronicalFile) {
		boolean flag = true;
		OutputStream out = null;
		// ����һ��������
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			out = new FileOutputStream(exportPath + File.separator + excelName
					+ ".xls");
			if (volume != null) {
				HSSFSheet volumeSheet = workbook.createSheet("����");
				volumeSheet.setDefaultColumnWidth(15);
				// ������������
				HSSFRow row = createRow(volumeSheet, columnTitle);
				TableModel tableModel = volume.getModel();
				setExcelRowData(volumeSheet, tableModel);
			}
			if (file != null) {
				HSSFSheet fileSheet = workbook.createSheet("�ļ�");
				fileSheet.setDefaultColumnWidth(15);
				// ������������
				HSSFRow row = createRow(fileSheet, columnTitle);
				TableModel tableModel = file.getModel();
				setExcelRowData(fileSheet, tableModel);
			}
			if (electronicalFile != null) {
				HSSFSheet electronicalFileSheet = workbook.createSheet("�����ļ�");
				electronicalFileSheet.setDefaultColumnWidth(15);
				// ������������
				HSSFRow row = createRow(electronicalFileSheet, columnTitle);
				TableModel tableModel = electronicalFile.getModel();
				setExcelRowData(electronicalFileSheet, tableModel);
			}
			workbook.write(out);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					flag = false;
					e.printStackTrace();
				}
			}
		}
		return flag;

	}

	/**
	 * ������������
	 * 
	 * @param sheet
	 * @param columnTitle
	 * @return
	 */
	public static HSSFRow createRow(HSSFSheet sheet, Object[] columnTitle) {
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < columnTitle.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(
					(String) columnTitle[i]);
			cell.setCellValue(text);
		}
		return row;
	}

	/**
	 * д��Excel�е�������
	 * 
	 * @param volumeSheet
	 * @param tableModel
	 */
	public static void setExcelRowData(HSSFSheet volumeSheet,
			TableModel tableModel) {
		int rowCount = tableModel.getRowCount(); // ��������ݵ�����
		int column = FieldSettingFrame.columnTitle.length; // ������
		int index = 0;
		for (int i = 0; i < rowCount; i++) {
			index++;
			HSSFRow row = volumeSheet.createRow(index);
			for (int j = 0; j < column; j++) {
				String value = (String) tableModel.getValueAt(i, j);
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(value);
			}
		}
	}

	/**
	 * ����Excel�ļ���·����ȡ����Excel�ļ��е�����sheet������
	 * @param excelPath
	 * @return
	 */
	public static List<String> getSheetNamesByExcel(String excelPath) {
		List<String> sheetNames = new ArrayList<String>();
			 //�õ�Excel����������   
			HSSFWorkbook wb = getHSSFWorkbookByExcelPath(excelPath);
			int sheets = wb.getNumberOfSheets();
			for (int i = 0; i < sheets; i++) {
				HSSFSheet sheet = wb.getSheetAt(i);
				String sheetName = sheet.getSheetName();
				sheetNames.add(sheetName);
			}
		return sheetNames;
	}
	
	
	/**
	 * ����Excel�ļ���·������ȡHSSFWorkbook����
	 * @param excelPath
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbookByExcelPath(String excelPath) {
		HSSFWorkbook wb = null;
		try{
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelPath));
			 //�õ�Excel����������   
			wb = new HSSFWorkbook(fs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return wb;
	}
	
	/**
	 * ����Excel�ļ���·������ȡHSSFSheet���󼯺�
	 * @param excelPath
	 * @return
	 */
	public static List<HSSFSheet> getHSSFSheetsByExcelPath(String excelPath) {
		List<HSSFSheet> hSSFSheets = new ArrayList<HSSFSheet>();
		 //�õ�Excel����������   
		HSSFWorkbook wb = getHSSFWorkbookByExcelPath(excelPath);
		int sheets = wb.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
//			String sheetName = sheet.getSheetName();
			hSSFSheets.add(sheet);
		}
	return hSSFSheets;
	}
	
	/**
	 * ��������Excel·����ȡ����Excel�е��ֶ���Ϣ(����ȡ���е�����)
	 * @param excelPath
	 * @param levelsName
	 * @return
	 */
	public static List<String> getFieldsByDataExcelPath(HSSFSheet hSSFSheet) {
		List<String> fields = new ArrayList<String>();
//		System.out.println("LastRowNum = " + hSSFSheet.getLastRowNum());
//		System.out.println("PhysicalNumberOfRows = " + hSSFSheet.getPhysicalNumberOfRows());
		HSSFRow row = hSSFSheet.getRow(0);//��ȡָ���У�������0��ʼ
		int columnCount = row.getPhysicalNumberOfCells();//��ȡ������
		for (int i = 0; i < columnCount; i++) {
			String CellValue = row.getCell(i).getStringCellValue();
			fields.add(CellValue);
		}
		return fields;
	}

	/**
	 * ��ȡ����Excel�е�������
	 * @param excelPath
	 * @param levelsName
	 * @return
	 */
	public static int getDataExcelCount(HSSFSheet hSSFSheet){
		int rows = hSSFSheet.getPhysicalNumberOfRows();
		return rows;
	}
	
	/**
	 * ��������Excel·����ȡHSSFSheet����
	 * @param excelPath
	 * @param levelsName
	 * @return
	 */
	public static HSSFSheet getHSSFSheet(HSSFWorkbook hssFWorkbook ,String levelsName){
		HSSFSheet hSSFSheet = hssFWorkbook.getSheet(levelsName);//��ȡ����ΪlevelsName��sheet
		return hSSFSheet;
	}
	
	/**
	 * ��������Excel·�����һ�����ݣ����мӵ����һ��
	 * @param excelPath
	 * @param levelsName
	 * @return
	 */
	public static HSSFRow createRow(HSSFSheet hSSFSheet){
		int rows = getDataExcelCount(hSSFSheet);
		HSSFRow hssFRow = hSSFSheet.createRow(rows);
		return hssFRow;
	}
	
	/**
	 * ����Excel�ļ���·���񣬻�ȡHSSFSheet�е��ֶ���Ϣ
	 * @param excelPath
	 * @return
	 */
	public static Map<String,String> getFieldsByExcelPath(String excelPath,String levelsName) {
		Map<String,String> fields = new HashMap<String,String>();
		 //�õ�Excel����������   
		HSSFWorkbook wb = getHSSFWorkbookByExcelPath(excelPath);
		int sheets = wb.getNumberOfSheets();
		HSSFSheet hSSFSheet = null;
		for (int i = 0; i < sheets; i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			String sheetName = sheet.getSheetName();
			if (levelsName.equals(sheetName)) {
				hSSFSheet = sheet;
			}
		}
		if (hSSFSheet != null) {
			int rowNum = hSSFSheet.getPhysicalNumberOfRows();
			int columnNum = hSSFSheet.getRow(0).getPhysicalNumberOfCells();
//			System.out.println("��" + rowNum + "��" + columnNum);
			String fieldNameColumn = "";
			for (int row = 1; row < rowNum; row++) {
				String otherColumn = "";
				for (int column = 0; column < columnNum; column++) {
					if (column == 0) {
						fieldNameColumn = hSSFSheet.getRow(row).getCell(column).getStringCellValue();
					}else {
						if (column == 4) {
							if (!"".equals(hSSFSheet.getRow(row).getCell(column).getStringCellValue())) {
								otherColumn += hSSFSheet.getRow(row).getCell(column).getStringCellValue() + ",";
							}
						}else {
							otherColumn += hSSFSheet.getRow(row).getCell(column).getStringCellValue() + ",";
						}
					}
				}
				fields.put(fieldNameColumn, StringUtil.getNewStr(otherColumn));
			}
			
			
		}
	return fields;
	}
	
	
	/**
	 * ����ģ��Excel·��������һ������Excel�ļ�
	 * @param pathString
	 * @return
	 */
	public static boolean buildDataExcel(String pathString,String excelName) {
		boolean flag = true;
		OutputStream out = null;
		List<String> sheetNames = ExcelUtil.getSheetNamesByExcel(pathString);
		int sheetNameSize = 0;
		if (sheetNames !=null && sheetNames.size() > 0) {
			// ����һ��������
			HSSFWorkbook workbook = new HSSFWorkbook();
				try {
					out = new FileOutputStream(SystemUtil.getProjectRootPath() + File.separator + excelName
							+ "����.xls");
					sheetNameSize = sheetNames.size();
					for (int i = 0; i < sheetNameSize; i++) {
						String sheetName = sheetNames.get(i);
						if ("����".equals(sheetName)) {
							HSSFSheet volumeSheet = workbook.createSheet("����");
							volumeSheet.setDefaultColumnWidth(15);
							Map<String, String> fields = getFieldsByExcelPath(pathString, sheetName);
							HSSFRow row = volumeSheet.createRow(0);
							int index = 0;
							HSSFCell cell = row.createCell(index);
							cell.setCellValue("volumeId");
							for (Entry<String, String> entry: fields.entrySet()) {
								index++;
							    String key = entry.getKey();
							    HSSFCell cell2 = row.createCell(index);
								cell2.setCellValue(key);
							}
						}else if ("�ļ�".equals(sheetName)) {
							HSSFSheet volumeSheet = workbook.createSheet("�ļ�");
							volumeSheet.setDefaultColumnWidth(15);
							Map<String, String> fields = getFieldsByExcelPath(pathString, sheetName);
							HSSFRow row = volumeSheet.createRow(0);
							int index = 0;
							if (sheetNameSize == 3) {
								HSSFCell cell = row.createCell(index);
								cell.setCellValue("volumeId");
								index++;
								HSSFCell cell2 = row.createCell(index);
								cell2.setCellValue("fileId");
							}else if (sheetNameSize == 2) {
								HSSFCell cell = row.createCell(index);
								cell.setCellValue("fileId");
							}
							for (Entry<String, String> entry: fields.entrySet()) {
								index++;
							    String key = entry.getKey();
							    HSSFCell cell3 = row.createCell(index);
								cell3.setCellValue(key);
							}
						}else if ("�����ļ�".equals(sheetName)) {
							HSSFSheet volumeSheet = workbook.createSheet("�����ļ�");
							volumeSheet.setDefaultColumnWidth(15);
							Map<String, String> fields = getFieldsByExcelPath(pathString, sheetName);
							HSSFRow row = volumeSheet.createRow(0);
							int index = 0;
							HSSFCell cell = row.createCell(index);
							cell.setCellValue("fileId");
							index++;
							HSSFCell cell2 = row.createCell(index);
							cell2.setCellValue("electronicalFileId");
							for (Entry<String, String> entry: fields.entrySet()) {
								index++;
							    String key = entry.getKey();
							    HSSFCell cell3 = row.createCell(index);
								cell3.setCellValue(key);
							}
						}
						
					}
					workbook.write(out);
				} catch (Exception e) {
					flag = false;
					e.printStackTrace();
				}
				finally {
					if (out != null) {
						try {
							out.close();
						} catch (IOException e) {
							flag = false;
							e.printStackTrace();
						}
					}
				}
		}
		return flag;
	}
	
	public static void main(String[] args) {
		String pathString = SystemUtil.getProjectRootPath() + File.separator + FileUtil.findExcelFile(SystemUtil.getProjectRootPath(),"����").get(0).getName();
//		System.out.println(getSheetNamesByExcel(pathString));
//		Map<String,String> fields = getFieldsByExcelPath(pathString,"����");
//		System.out.println("**************************************************");
//		for (Entry<String, String> entry: fields.entrySet()) {
//
//		    String key = entry.getKey();
//
//		    String value = entry.getValue();
//		    System.out.println(key + "-" + value);
//
//		}
		System.out.println("pathString = " + pathString);
		

	}
}
