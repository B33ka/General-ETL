package ge.umas.rs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlMapper {

	public void ReadRS(String methodName)
			throws TransformerException, ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		factory.setValidating(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		File file = new File("C:\\Users\\b.saldadze\\Desktop\\RS\\complaints.xml");
		Document document = builder.parse(file);

		NodeList nodeList = document.getElementsByTagName("RsData");

		rsXml(nodeList);
		extractAfterClean(document, "complaintsETL.xml");
		System.out.println("");

	}

	static void extractAfterClean(Document doc, String fileName) throws IOException, TransformerException {
		DOMSource source = new DOMSource(doc);
		FileWriter writer = new FileWriter(new File("C:\\Users\\b.saldadze\\Desktop\\RS\\" + fileName));
		StreamResult result = new StreamResult(writer);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(source, result);
	}

	static void rsXml(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {
			if (nodeList.item(count) != null) {
				Node tempNode = nodeList.item(count);
				if (tempNode.hasChildNodes()) {
					if (tempNode.getNodeName().contains("RsData")) {
						innerNodes(tempNode);
					}
					rsXml(tempNode.getChildNodes());

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
				String methodName = tempNode.getNodeName();
				switch (methodName) {
				case "ComplaintsData":
					//ComplaintsData.mapMethod(node);
					break;
				case "ExciseDeclarationData":
					System.out.println("Well done");
					break;
				}

			}
		}
	}

}
