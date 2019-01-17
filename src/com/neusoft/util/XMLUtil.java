package com.neusoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 处理XML的工具类
 * 
 * @author chenzhenhua
 *
 */
public class XMLUtil {

	/**
	 * 根据文件路径得到Document对象
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static Document getDocument(String filePath) {
		FileInputStream in = null;
		Document document = null;
		try {
			in = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Reader read = new InputStreamReader(in);
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(read);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * 获取文档的根节点
	 * 
	 * @param document
	 * @return
	 */
	public static Element getRootElement(Document document) {
		Element element = null;
		if (document != null) {
			element = document.getRootElement();
		}
		return element;
	}

	/**
	 * 取得根节点的单个子节点
	 * 
	 * @param document
	 * @param nodeName
	 * @return
	 */
	public static Element getNode(Document document, String nodeName) {
		Element element = null;
		if (document != null) {
			element = getRootElement(document).element(nodeName);
		}
		return element;
	}

	/**
	 * 取得某节点的单个子节点
	 * 
	 * @param elment
	 * @param nodeName
	 * @return
	 */
	public static Element getNode(Element elment, String nodeName) {
		Element elem = null;
		if (elment != null) {
			elem = elment.element(nodeName);
		}
		return elem;
	}

	/**
	 * 取得某节点的多个子节点
	 * 
	 * @param elment
	 * @param nodeName
	 * @return
	 */
	public static List<Element> getNodes(Element elment, String nodeName) {
		List<Element> elements = null;
		if (elment != null) {
			elements = elment.elements(nodeName);
		}
		return elements;
	}

	/**
	 * 获取档案门类的名称（如：文书档案，声像档案）
	 * 
	 * @param document
	 * @return
	 */
	public static String getArchiveCategoryName(Document document) {
		String ArchiveCategoryName = null;
		if (document != null) {
			// 获取节点名叫attribute的节点集合
			List<Element> attributeNodes = getNodes(
					getNode(document, "CObjectBean"), "attribute");
			if (attributeNodes != null && attributeNodes.size() > 0) {
				Element attributeNode = null;
				for (int i = 0; i < attributeNodes.size(); i++) {
					// 获取每个attribute节点的fieldname属性的值
					attributeNode = attributeNodes.get(i);
					String fieldnameValue = attributeNode.attribute("fieldname")
							.getValue();
					if ("sname".equalsIgnoreCase(fieldnameValue)) {
						ArchiveCategoryName = attributeNode.attribute("value")
								.getValue();
					}
				}
			}
		}
		return ArchiveCategoryName;
	}

	/**
	 * 获取档案有几级（比如：案卷、文件、电子文件，或者是文件、电子文件）
	 * 
	 * @param document
	 * @return
	 */
	public static List<String> getLevelsName(Document document) {
		List<String> levelsNameList = null;
		if (document != null) {
			// String ArchiveCategoryName = null;
			// 获取节点名叫objecttype的节点集合
			List<Element> objecttypeNodes = getObjecttypeNodes(document);
			if (objecttypeNodes != null) {
				levelsNameList = new ArrayList<String>();
				for (int i = 0; i < objecttypeNodes.size(); i++) {
					// 获取每个objecttype节点的name属性的值
					String nameValue = objecttypeNodes.get(i).attribute("name")
							.getValue();
					levelsNameList.add(nameValue);
				}
			}
		}
		return levelsNameList;
	}

	/**
	 * 获取节点名叫objecttype的节点集合
	 * 
	 * @param document
	 * @return
	 */
	public static List<Element> getObjecttypeNodes(Document document) {
		List<Element> objecttypeNodes = null;
		if (document != null) {
			objecttypeNodes = getNodes(
					getNode(document, "CObjectBean"), "objecttype");
		}
		
		return objecttypeNodes;
	}

	/**
	 * 获取案卷，或者文件或者电子文件节点下的所有字段信息（ 字段信息包括name属性的值，即字段名称， datatype属性的值，即字段类型，
	 * size属性的值 即 字段长度， default属性的值 即字段的默认值， bnull属性的值，即字段是否可以为空）
	 * 
	 * @param document
	 */
	public static Map<String, String> getFieldsInfo(Document document,
			String level) {
		Map<String, String> fieldsInfo = null;
		// 获取节点名叫objecttype的节点集合
		List<String> levelsNameList = getLevelsName(document);
		if (levelsNameList != null) {
			if (levelsNameList.contains(level)) {
				List<Element> objecttypeNodes = getObjecttypeNodes(document);
				Element objecttypeNode = null;
				for (int i = 0; i < objecttypeNodes.size(); i++) {
					objecttypeNode = objecttypeNodes.get(i);
					// 获取每个objecttype节点的name属性的值
					String nameValue = objecttypeNode.attribute("name")
							.getValue();
					if (level.equals(nameValue)) {
						Element fieldNode = null;
						List<Element> fieldNodes = getNodes(objecttypeNode,
								"field");
						if (fieldNodes != null) {
							fieldsInfo = new HashMap<String, String>();
							for (int j = 0; j < fieldNodes.size(); j++) {
								fieldNode = fieldNodes.get(j);
								if ("false".equalsIgnoreCase(fieldNode
										.attribute("isinternal").getValue())) {
									// 字段名字
									String fieldName = fieldNode.attribute(
											"name").getValue();
									// 数据类型
									String fieldDataType = fieldNode.attribute(
											"datatype").getValue();
									fieldDataType = number2ChineseDataType(fieldDataType);
									// 字段长度
									String fieldSize = fieldNode.attribute(
											"size").getValue();
									// 是否可以为空
									String isNull = fieldNode
											.attribute("bnull").getValue();
									isNull = Boolean.parseBoolean(isNull) ? "可以"
											: "不可以";
									// 字段默认值
									if (fieldNode.attribute("default") == null) {
										fieldsInfo.put(fieldName, fieldDataType
												+ "," + fieldSize + ","
												+ isNull);
									} else {
										String fieldDefaultValue = fieldNode
												.attribute("default")
												.getValue();
										fieldsInfo.put(fieldName, fieldDataType
												+ "," + fieldSize + ","
												+ isNull + ","
												+ fieldDefaultValue);
									}
								}
							}
						}
					}
				}
			}
		}

		return fieldsInfo;

	}

	/**
	 * 将数字转换成以中文显示的数据类型
	 * 
	 * @param number
	 * @return
	 */
	public static String number2ChineseDataType(String number) {
		String fieldDataType = "字符";
		int dataType = Integer.parseInt(number);
		switch (dataType) {
		case 1:
			fieldDataType = "字符";
			break;
		case 2:
			fieldDataType = "日期";
			break;
		case 3:
			fieldDataType = "整数";
			break;
		case 4:
			fieldDataType = "实数";
			break;
		case 5:
			fieldDataType = "货币";
			break;
		case 6:
			fieldDataType = "流水号";
			break;
		case 7:
			fieldDataType = "(媒体)大字段";
			break;
		}
		return fieldDataType;
	}

	public static void main(String[] args) {
		// Document document =
		// getDocument("C:/Users/neusoft/Desktop/22/export062980/format.seas");
//		Document document = getDocument("C:/Users/neusoft/Desktop/test2.seas");
//		getObjecttypeNodes(document);

		// Element element = getRootElement(document);
		// System.out.println(element.getName());

		// System.out.println(getNode(getNode(document, "CObjectBean"),
		// "attribute").attribute("value").getValue());
		// List<Element> list = getNodes(getNode(document, "CObjectBean"),
		// "attribute");
		// for (int i = 0; i < list.size(); i++) {
		// String string = list.get(i).attribute("fieldname").getValue();
		// if ("sname".equalsIgnoreCase(string)) {
		// String dangan = list.get(i).attribute("value").getValue();
		// System.out.println(dangan);
		// }
		//
		//
		// }
		// System.out.println(getArchiveCategoryName(document));
		// List<String> list = getLevelsName(document);
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i));
		// }
		// Map<String, String> FieldsInfo = getFieldsInfo(document, "案卷");
		// int count = 0;
		// for (String key : FieldsInfo.keySet()) {
		// System.out.println("key= " + key + " value= " + FieldsInfo.get(key));
		// count++;
		// }
		// System.out.println(count);

	}

}
