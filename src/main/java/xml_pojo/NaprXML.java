package xml_pojo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NaprXML {
	
	final String folderNameRead = "\\UMAS\\Production\\Source\\MIA\\BorderCrossOld\\";
	final String folderNameWrite = "\\UMAS\\Production\\Source\\MIA\\BorderCross\\";

	public static void main(String[] args) throws IOException {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(false);
			factory.setValidating(false);
			DocumentBuilder builder = factory.newDocumentBuilder();

			File file = new File("C:\\Users\\b.saldadze\\Desktop\\NNN\\2017-09-15");
			Document document = builder.parse(file);
			if (document.getChildNodes() != null)
				naprXml(document.getChildNodes());
			extractAfterClean(document);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	static void naprXml(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {
			if (nodeList.item(count) != null ) {
				Node tempNode = nodeList.item(count);
				if (tempNode.hasChildNodes()) {
					if (tempNode.getNodeName().contains("subject")) {
						innerNodes(tempNode);
					}
					
					naprXml(tempNode.getChildNodes());

				}
			}
		}
	}

	static void innerNodes(Node node) {
		Node tempNode = null;
		if (node.hasChildNodes() && node.getChildNodes() != null) {
			NodeList nodeList = node.getChildNodes();
			int length = nodeList.getLength();
			for (int count = 0; count < length; count++) {
				tempNode = nodeList.item(count);
				if (tempNode != null && tempNode.getNodeName().contains("ns4:")) {
					int innerLength = tempNode.getChildNodes().getLength();
					if (innerLength > 1)
						innerNodes(tempNode);
					else if (innerLength == 1) {
						System.out.println(tempNode.getNodeName());
						String fieldAfterEtl = fieldsEtl(tempNode);
						tempNode.getFirstChild().setTextContent(fieldAfterEtl);
						System.out.println(tempNode.getTextContent());
					}
				}

			}
		}
	}

	static void extractAfterClean(Document doc) throws IOException, TransformerException {
		DOMSource source = new DOMSource(doc);
		FileWriter writer = new FileWriter(new File("C:\\Users\\b.saldadze\\Desktop\\NNN\\Result.xml"));
		StreamResult result = new StreamResult(writer);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(source, result);
	}

	private static String fieldsEtl(Node node) {
		String nodeValue = node.getFirstChild().getTextContent();
		if (node.getNodeName().equals("PersonalNumber")) {
			return nodeValue.replaceAll("[^0-9]", "");
		}
		
		
		return "Test";
	}

}
