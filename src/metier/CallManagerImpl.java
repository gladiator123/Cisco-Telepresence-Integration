package metier;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Stateless
public class CallManagerImpl implements ICallManager{

	@EJB
	IConnectTP conn;

	public String OpenCall(String IP,String dest) {

		String address="http://"+IP+"/putxml";
		String data="<Command>    <Dial command='True'>    <Number>"+dest+"</Number>    <Protocol>Sip</Protocol>  </Dial> </Command> ";

		String ret="no data available";
		try{
			ret=conn.Post("admin","C1sc0123",address,data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}

	public String CloseAll(String IP) {

		//ConnectTP conn=new ConnectTP();
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Call> <DisconnectAll command='True'>  </DisconnectAll> </Call> </Command> ";
		String ret="no data available";
		try{
			ret=conn.Post("admin","C1sc0123",address,data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}



	@Override
	public String CloseCall(String IP, int ID) {
		//ConnectTP conn=new ConnectTP();
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Call> <Disconnect command='True'> <CallId>"+ID+"</CallId> </Disconnect> </Call> </Command> ";
		String ret="no data available";
		try{
			ret=conn.Post("admin","C1sc0123",address,data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;

	}

	@Override
	public String AcceptCall(String IP, int ID, String Type) {
		//ConnectTP conn=new ConnectTP();
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Call> <Accept command='True'> <CallId>"+ID+"</CallId> </Accept> </Call> </Command> ";
		String ret="no data available";
		try{
			ret=conn.Post("admin","C1sc0123",address,data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;

	}

	@Override
	public String RejectCall(String IP, int ID) {
		//ConnectTP conn=new ConnectTP();
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Call> <Reject command='True'> <CallId>"+ID+"</CallId> </Reject> </Call> </Command> ";
		String ret="no data available";
		try{
			ret=conn.Post("admin","C1sc0123",address,data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;

	}
	public String Callinfo(String IP,String status) throws SAXException, IOException, ParserConfigurationException, TransformerException{
		String address="http://"+IP+"/getxml?location=/Status/Call";

		String ret="no data";
		try {
			ret=conn.Get("admin","C1sc0123",address );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Callrequest(ret,status);
	}



	public String Callrequest(String ret,String status) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(ret));
		Document doc = builder.parse(is);
		NodeList nList = doc.getElementsByTagName("Call");
		Document newDoc=builder.newDocument();
		Element rootElement = newDoc.createElement(status+"Call");
		newDoc.appendChild(rootElement);

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode =  nList.item(temp);


			

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				String answer= eElement.getElementsByTagName("Status").item(0).getFirstChild().getTextContent();
				
				if(answer.equals(status))
				{
					Node newNode = newDoc.importNode(nNode, true);
					rootElement.appendChild(newNode);
				}
			}
		}
		StringWriter sw = new StringWriter();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		transformer.transform(new DOMSource(newDoc), new StreamResult(sw));
		
		return( sw.toString());
		
	}
}
