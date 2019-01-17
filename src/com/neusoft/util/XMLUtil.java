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
 * ����XML�Ĺ�����
 * 
 * @author chenzhenhua
 *
 */
public class XMLUtil {

	/**
	 * �����ļ�·���õ�Document����
	 * 
	 * @param filePath
	 *            �ļ�·��
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
	 * ��ȡ�ĵ��ĸ��ڵ�
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
	 * ȡ�ø��ڵ�ĵ����ӽڵ�
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
	 * ȡ��ĳ�ڵ�ĵ����ӽڵ�
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
	 * ȡ��ĳ�ڵ�Ķ���ӽڵ�
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
	 * ��ȡ������������ƣ��磺���鵵�������񵵰���
	 * 
	 * @param document
	 * @return
	 */
	public static String getArchiveCategoryName(Document document) {
		String ArchiveCategoryName = null;
		if (document != null) {
			// ��ȡ�ڵ�����attribute�Ľڵ㼯��
			List<Element> attributeNodes = getNodes(
					getNode(document, "CObjectBean"), "attribute");
			if (attributeNodes != null && attributeNodes.size() > 0) {
				Element attributeNode = null;
				for (int i = 0; i < attributeNodes.size(); i++) {
					// ��ȡÿ��attribute�ڵ��fieldname���Ե�ֵ
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
	 * ��ȡ�����м��������磺�����ļ��������ļ����������ļ��������ļ���
	 * 
	 * @param document
	 * @return
	 */
	public static List<String> getLevelsName(Document document) {
		List<String> levelsNameList = null;
		if (document != null) {
			// String ArchiveCategoryName = null;
			// ��ȡ�ڵ�����objecttype�Ľڵ㼯��
			List<Element> objecttypeNodes = getObjecttypeNodes(document);
			if (objecttypeNodes != null) {
				levelsNameList = new ArrayList<String>();
				for (int i = 0; i < objecttypeNodes.size(); i++) {
					// ��ȡÿ��objecttype�ڵ��name���Ե�ֵ
					String nameValue = objecttypeNodes.get(i).attribute("name")
							.getValue();
					levelsNameList.add(nameValue);
				}
			}
		}
		return levelsNameList;
	}

	/**
	 * ��ȡ�ڵ�����objecttype�Ľڵ㼯��
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
	 * ��ȡ���������ļ����ߵ����ļ��ڵ��µ������ֶ���Ϣ�� �ֶ���Ϣ����name���Ե�ֵ�����ֶ����ƣ� datatype���Ե�ֵ�����ֶ����ͣ�
	 * size���Ե�ֵ �� �ֶγ��ȣ� default���Ե�ֵ ���ֶε�Ĭ��ֵ�� bnull���Ե�ֵ�����ֶ��Ƿ����Ϊ�գ�
	 * 
	 * @param document
	 */
	public static Map<String, String> getFieldsInfo(Document document,
			String level) {
		Map<String, String> fieldsInfo = null;
		// ��ȡ�ڵ�����objecttype�Ľڵ㼯��
		List<String> levelsNameList = getLevelsName(document);
		if (levelsNameList != null) {
			if (levelsNameList.contains(level)) {
				List<Element> objecttypeNodes = getObjecttypeNodes(document);
				Element objecttypeNode = null;
				for (int i = 0; i < objecttypeNodes.size(); i++) {
					objecttypeNode = objecttypeNodes.get(i);
					// ��ȡÿ��objecttype�ڵ��name���Ե�ֵ
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
									// �ֶ�����
									String fieldName = fieldNode.attribute(
											"name").getValue();
									// ��������
									String fieldDataType = fieldNode.attribute(
											"datatype").getValue();
									fieldDataType = number2ChineseDataType(fieldDataType);
									// �ֶγ���
									String fieldSize = fieldNode.attribute(
											"size").getValue();
									// �Ƿ����Ϊ��
									String isNull = fieldNode
											.attribute("bnull").getValue();
									isNull = Boolean.parseBoolean(isNull) ? "����"
											: "������";
									// �ֶ�Ĭ��ֵ
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
	 * ������ת������������ʾ����������
	 * 
	 * @param number
	 * @return
	 */
	public static String number2ChineseDataType(String number) {
		String fieldDataType = "�ַ�";
		int dataType = Integer.parseInt(number);
		switch (dataType) {
		case 1:
			fieldDataType = "�ַ�";
			break;
		case 2:
			fieldDataType = "����";
			break;
		case 3:
			fieldDataType = "����";
			break;
		case 4:
			fieldDataType = "ʵ��";
			break;
		case 5:
			fieldDataType = "����";
			break;
		case 6:
			fieldDataType = "��ˮ��";
			break;
		case 7:
			fieldDataType = "(ý��)���ֶ�";
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
		// Map<String, String> FieldsInfo = getFieldsInfo(document, "����");
		// int count = 0;
		// for (String key : FieldsInfo.keySet()) {
		// System.out.println("key= " + key + " value= " + FieldsInfo.get(key));
		// count++;
		// }
		// System.out.println(count);

	}

}
