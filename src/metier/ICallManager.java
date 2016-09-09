package metier;

import java.io.IOException;

import javax.ejb.Remote;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

@Remote
public interface ICallManager {

	public String OpenCall(String IP,String dest);
	public String CloseCall(String IP,int ID);
	public String AcceptCall(String IP,int ID,String Type);
	public String RejectCall(String IP,int ID);
	public String CloseAll(String IP);
	public String Callinfo(String IP,String status) throws SAXException, IOException, ParserConfigurationException, TransformerException;
	public String Callrequest(String ret,String status) throws ParserConfigurationException, SAXException, IOException, TransformerException ;	
}
