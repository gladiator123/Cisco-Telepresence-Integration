package metier;


import java.io.IOException;

import javax.ejb.Local;

@Local
public interface IConnectTP {
	
	public String Get(String user,String password,String URL) ;
	public String Post(String user,String password,String URL,String Data) throws IOException ;
	
	
}
