package ge.umas.models.naprXML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.hadoop.fs.FSDataOutputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NaprReader {

	public static void Etl(InputStream is, FSDataOutputStream os)
			throws TransformerException, ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		factory.setValidating(false);
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(is);
		if (document.getChildNodes() != null)
			naprXml(document.getChildNodes());
		extractInHdfs(document, os);

	}

	static void naprXml(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {
			if (nodeList.item(count) != null) {
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
				int innerLength = tempNode.getChildNodes().getLength();
				if (innerLength > 1)
					innerNodes(tempNode);
				else if (innerLength == 1) {
					System.out.println(tempNode.getNodeName());
					String fieldAfterEtl = NaprEtl.fieldsEtl(tempNode);
					tempNode.getFirstChild().setTextContent(fieldAfterEtl);
					System.out.println(tempNode.getTextContent());
				}

			}
		}
	}

	static void extractAfterClean(Document doc, String fileName) throws IOException, TransformerException {
		DOMSource source = new DOMSource(doc);
		FileWriter writer = new FileWriter(new File("\\UMAS\\test\\Beka_Name_Dictionary\\NaprData\\" + fileName));
		StreamResult result = new StreamResult(writer);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(source, result);
	}

	static void extractInHdfs(Document doc, FSDataOutputStream outputStream) throws IOException, TransformerException {
		Charset UTF8 = Charset.forName("utf-8");
		DOMSource source = new DOMSource(doc);
		OutputStreamWriter oWr = new OutputStreamWriter(outputStream, UTF8);
		StreamResult result = new StreamResult(oWr);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(source, result);
	}

}
