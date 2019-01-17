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
	 * 将模板导出Excel
	 * 
	 * @param excelName
	 *            生成的excel的名字
	 * @param exportPath
	 *            导出到的路径
	 * @param columnTitle
	 *            列头
	 * @param volume
	 *            案卷表格
	 * @param file
	 *            案卷表格
	 * @param electronicalFile
	 *            电子文件表格
	 * @return
	 */
	public static boolean exportExcel(String excelName, String exportPath,
			Object[] columnTitle, JTable volume, JTable file,
			JTable electronicalFile) {
		boolean flag = true;
		OutputStream out = null;
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			out = new FileOutputStream(exportPath + File.separator + excelName
					+ ".xls");
			if (volume != null) {
				HSSFSheet volumeSheet = workbook.createSheet("案卷");
				volumeSheet.setDefaultColumnWidth(15);
				// 产生表格标题行
				HSSFRow row = createRow(volumeSheet, columnTitle);
				TableModel tableModel = volume.getModel();
				setExcelRowData(volumeSheet, tableModel);
			}
			if (file != null) {
				HSSFSheet fileSheet = workbook.createSheet("文件");
				fileSheet.setDefaultColumnWidth(15);
				// 产生表格标题行
				HSSFRow row = createRow(fileSheet, columnTitle);
				TableModel tableModel = file.getModel();
				setExcelRowData(fileSheet, tableModel);
			}
			if (electronicalFile != null) {
				HSSFSheet electronicalFileSheet = workbook.createSheet("电子文件");
				electronicalFileSheet.setDefaultColumnWidth(15);
				// 产生表格标题行
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
	 * 产生表格标题行
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
	 * 写入Excel中的行数据
	 * 
	 * @param volumeSheet
	 * @param tableModel
	 */
	public static void setExcelRowData(HSSFSheet volumeSheet,
			TableModel tableModel) {
		int rowCount = tableModel.getRowCount(); // 表格中数据的行数
		int column = FieldSettingFrame.columnTitle.length; // 表列数
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
	 * 根据Excel文件的路径获取到该Excel文件中的所有sheet的名称
	 * @param excelPath
	 * @return
	 */
	public static List<String> getSheetNamesByExcel(String excelPath) {
		List<String> sheetNames = new ArrayList<String>();
			 //得到Excel工作簿对象   
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
	 * 根据Excel文件的路径，获取HSSFWorkbook对象
	 * @param excelPath
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbookByExcelPath(String excelPath) {
		HSSFWorkbook wb = null;
		try{
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelPath));
			 //得到Excel工作簿对象   
			wb = new HSSFWorkbook(fs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return wb;
	}
	
	/**
	 * 根据Excel文件的路径，获取HSSFSheet对象集合
	 * @param excelPath
	 * @return
	 */
	public static List<HSSFSheet> getHSSFSheetsByExcelPath(String excelPath) {
		List<HSSFSheet> hSSFSheets = new ArrayList<HSSFSheet>();
		 //得到Excel工作簿对象   
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
	 * 根据数据Excel路径获取数据Excel中的字段信息(即获取所有的列名)
	 * @param excelPath
	 * @param levelsName
	 * @return
	 */
	public static List<String> getFieldsByDataExcelPath(HSSFSheet hSSFSheet) {
		List<String> fields = new ArrayList<String>();
//		System.out.println("LastRowNum = " + hSSFSheet.getLastRowNum());
//		System.out.println("PhysicalNumberOfRows = " + hSSFSheet.getPhysicalNumberOfRows());
		HSSFRow row = hSSFSheet.getRow(0);//获取指定行，索引从0开始
		int columnCount = row.getPhysicalNumberOfCells();//获取总列数
		for (int i = 0; i < columnCount; i++) {
			String CellValue = row.getCell(i).getStringCellValue();
			fields.add(CellValue);
		}
		return fields;
	}

	/**
	 * 获取数据Excel中的总行数
	 * @param excelPath
	 * @param levelsName
	 * @return
	 */
	public static int getDataExcelCount(HSSFSheet hSSFSheet){
		int rows = hSSFSheet.getPhysicalNumberOfRows();
		return rows;
	}
	
	/**
	 * 根据数据Excel路径获取HSSFSheet对象
	 * @param excelPath
	 * @param levelsName
	 * @return
	 */
	public static HSSFSheet getHSSFSheet(HSSFWorkbook hssFWorkbook ,String levelsName){
		HSSFSheet hSSFSheet = hssFWorkbook.getSheet(levelsName);//读取名称为levelsName的sheet
		return hSSFSheet;
	}
	
	/**
	 * 根据数据Excel路径添加一行数据，该行加到最后一行
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
	 * 根据Excel文件的路径获，获取HSSFSheet中的字段信息
	 * @param excelPath
	 * @return
	 */
	public static Map<String,String> getFieldsByExcelPath(String excelPath,String levelsName) {
		Map<String,String> fields = new HashMap<String,String>();
		 //得到Excel工作簿对象   
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
//			System.out.println("行" + rowNum + "列" + columnNum);
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
	 * 根据模板Excel路径，生成一个数据Excel文件
	 * @param pathString
	 * @return
	 */
	public static boolean buildDataExcel(String pathString,String excelName) {
		boolean flag = true;
		OutputStream out = null;
		List<String> sheetNames = ExcelUtil.getSheetNamesByExcel(pathString);
		int sheetNameSize = 0;
		if (sheetNames !=null && sheetNames.size() > 0) {
			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
				try {
					out = new FileOutputStream(SystemUtil.getProjectRootPath() + File.separator + excelName
							+ "数据.xls");
					sheetNameSize = sheetNames.size();
					for (int i = 0; i < sheetNameSize; i++) {
						String sheetName = sheetNames.get(i);
						if ("案卷".equals(sheetName)) {
							HSSFSheet volumeSheet = workbook.createSheet("案卷");
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
						}else if ("文件".equals(sheetName)) {
							HSSFSheet volumeSheet = workbook.createSheet("文件");
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
						}else if ("电子文件".equals(sheetName)) {
							HSSFSheet volumeSheet = workbook.createSheet("电子文件");
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
		String pathString = SystemUtil.getProjectRootPath() + File.separator + FileUtil.findExcelFile(SystemUtil.getProjectRootPath(),"数据").get(0).getName();
//		System.out.println(getSheetNamesByExcel(pathString));
//		Map<String,String> fields = getFieldsByExcelPath(pathString,"案卷");
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
