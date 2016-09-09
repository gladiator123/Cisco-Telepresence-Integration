package metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ejb.Stateless;
import org.apache.http.HttpResponse;
import org.apache.http.auth.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;

@Stateless (name="connect")
public class ConnectTP implements IConnectTP{ //Connect to Tele presence API with GET or SET

	public String Get(String user,String password,String URL){

		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, password);
		provider.setCredentials(AuthScope.ANY, credentials);
		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
	   
		HttpResponse response;
		HttpGet req=new HttpGet(URL);
		req.setHeader("Accept", "application/xml");
		String ret="";
		try {
			response = client.execute(req);
			//int statusCode = response.getStatusLine().getStatusCode();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String inputLine;


			while ((inputLine = reader.readLine()) != null) {
				ret+=inputLine+"\n";
				//System.out.println(inputLine);
			}
			reader.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
		

	}

	public String Post(String user,String password,String URL,String Data) throws IOException{


		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, password);
		provider.setCredentials(AuthScope.ANY, credentials);
		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();

		HttpPost httpPost = new HttpPost(URL);

		String xml = Data;
		StringEntity entity;
		String ret="";
		try {
			entity = new StringEntity(xml);

			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/xml");
			httpPost.setHeader("Content-type", "application/xml");

			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpPost);


			//int statusCode = response.getStatusLine().getStatusCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String inputLine;


			while ((inputLine = reader.readLine()) != null) {
				ret+=inputLine+"\n";
				//System.out.println(inputLine);
			}
			reader.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	

}
