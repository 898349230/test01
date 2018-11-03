package com.ab.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.ab.test.ForeachMap;

public class Dom4j {
	public static void main(String[] args) {
		
		createXMLFile("C:/Users/Administrator/Desktop/abc.xml");
		//readXML("C:/Users/Administrator/Desktop/test01.xml");
		//readXMLZW("C:/Users/Administrator/Desktop/xml示例报文/3.1响应报文.txt");
//		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><UserResponse>"
//						+ "<Status>00</Status><OperSeqList>"
//						+"<OperSeq>0001611162416684001</OperSeq>"
//						+"<OperSeq>0001689878734567787</OperSeq>"
//						+ "</OperSeqList><ErrDesc></ErrDesc></UserResponse>";
//		readXMLString(xmlStr);
	}
	
	/**
	 * 
	 * <?xml version="1.0" encoding="UTF-8"?>
     
		<element1>
		  <!--element1 的 comment-->
		  <element11 key="value">
		    <title>titleText</title>
		  </element11>
		  <element2>
		    <element21 class="com.ab.test01">
		      <!--element21的注释--><![CDATA[element21的 CDATA]]>
		      <text22>测试text</text22>
		    </element21>
		  </element2>
		</element1>
	 * 
	 * @param fileName
	 * @return
	 */
	private static int createXMLFile(String fileName){
		
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("utf-8");
		
		Element element1 = document.addElement("element1");
		element1.addComment("element1 的 comment");
		Element element11 = element1.addElement("element11");
		element11.addAttribute("key", "value");
		
		Element title = element11.addElement("title");
		title.addText("titleText");
		
		Element element2 = element1.addElement("element2");
		Element element21 = element2.addElement("element21");
		element21.addAttribute("class", "com.ab.test01");
		element21.addComment("element21的注释");
		element21.addCDATA("element21的 CDATA");
		Element text = element21.addElement("text22");
		text.addText("测试text");
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(new File(fileName)),format) ;
			writer.write(document);
			writer.close();
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private static void readXMLString(String xmlStr){
		try {
			Document document = DocumentHelper.parseText(xmlStr);
			
			Element rootElement = document.getRootElement();
			Iterator<Element> operSeqIter = rootElement.element("OperSeqList").elementIterator();
			while(operSeqIter.hasNext()){ //迭代OperSeqList的所有子元素，及所有OperSeq标签
				Element e = operSeqIter.next();
				String oper = e.getText();
				System.out.println(oper);//0001611162416684001   0001689878734567787
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	private static void readXMLZW(String fileName){
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new File(fileName));
			Element rootElement = document.getRootElement();
			System.out.println("rootElement name: "+rootElement.getName());
			List elements = rootElement.elements();
			Element svcCont = rootElement.element("SvcCont");
			/**
			 * <SvcCont name='Jhon' job = 'JAVA'><![CDATA[
					<?xml version="1.0" encoding="UTF-8"?>
						<UserResponse>
							<Status>00</Status>
							<OperSeqList>
								<OperSeq>0001611162416684001</OperSeq>
							</OperSeqList>
								<ErrDesc></ErrDesc>
						</UserResponse>
					]]>
				</SvcCont>
			 */
			System.out.println("svcCont : "+svcCont.getText());
			int attributeCount = svcCont.attributeCount();
			System.out.println("attributeCount : "+attributeCount);// attributeCount : 2
			Attribute name = svcCont.attribute("name");
			String nameText = name.getText();
			System.out.println("nameText :"+nameText); //  nameText :Jhon
			for (Object o : elements) {
				if(o instanceof Element ){
					System.out.println( ""+ ((Element) o).getName());
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void readXML(String fileName){
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new FileReader(new File(fileName)));
			Element rootElement = document.getRootElement();
			Iterator<Element> iterator = rootElement.elementIterator();
			System.out.println("rootName ： "+rootElement.getName());
			System.out.println();
			int e =1;
			while(iterator.hasNext()){
				Element element = (Element) iterator.next();
				System.out.println( " 第  "+e+" 个element的Name : "+ element.getName());
				System.out.println( " 第  "+e+" 个element的text : "+element.getText());
				for(int i=0,size = element.nodeCount();i<size;i++){
					System.out.println("第"+e+"个element的 node的 第"+(i+1)+" 个nodeName : "+element.node(i).getName());
				}
				e++;
			}
			System.out.println("=======================================================");
			treeWalk(document.getRootElement());
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}
	private static void treeWalk(Element element){
		System.out.println("ElementName = " +element.getName() +", elementNodeCount  : "+ element.nodeCount());
		for(int i = 0,size = element.nodeCount();i<size;i++){
			Node node = element.node(i); 
			if(node instanceof Element){
				System.out.println("if里面   nodeName：  "+((Element) node).getName());
				treeWalk((Element) node);
			}else{
				System.out.println("nodeName : " +node.getName());
				System.out.println( "nodeTypeName：" + node.getNodeTypeName() +
						"\rnodeText ：  " + node.getText());
			}
		}
	}
}
