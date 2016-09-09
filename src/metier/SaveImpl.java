package metier;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SaveImpl implements ISave{

	@EJB
	IConnectTP conn;
	
	@Override
	public String CallLogs(String IP) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <CallHistory><Get command='True'> </Get></CallHistory></Command> ";
		String ret="no data";
		try {
			ret=conn.Post("admin","C1sc0123",address ,data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;

	}

	@Override
	public String OpenedCall(String IP) {
		String address="http://"+IP+"/getxml?location=/Status/Call";
		
		String ret="no data";
		try {
			ret=conn.Get("admin","C1sc0123",address );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	
	public String PresetList(String IP,int Id) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Camera><Preset><List command='True'></List></Preset></Camera> </Command>";
		String ret="no data";
		try {
			ret=conn.Post("admin","C1sc0123",address ,data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
		
	}

	@Override
	public String PresetEdit(String IP, int PresetId, int ListId, String Name) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Camera><Preset><Edit command='True'> <PresetId>"+PresetId+"</PresetId><ListPosition>"+ListId+"</ListPosition><Name>"+Name+"</Name></Edit></Preset></Camera> </Command>";
		String ret="no data";
		try {
			ret=conn.Post("admin","C1sc0123",address ,data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String PresetStore(String IP, int PresetId, int ListId, String Name) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Camera><Preset><Store command='True'> <PresetId>"+PresetId+"</PresetId><CameraId>1</CameraId><ListPosition>"+ListId+"</ListPosition><Name>"+Name+"</Name></Store></Preset></Camera> </Command>";
		String ret="no data";
		try {
			ret=conn.Post("admin","C1sc0123",address ,data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String PresetActivate(String IP, int PresetId) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Camera><Preset><Activate command='True'> <PresetId>"+PresetId+"</PresetId></Activate></Preset></Camera> </Command>";
		String ret="no data";
		try {
			ret=conn.Post("admin","C1sc0123",address ,data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String PresetRemove(String IP, int PresetId) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Camera><Preset><Remove command='True'> <PresetId>"+PresetId+"</PresetId></Remove></Preset></Camera> </Command>";
		String ret="no data";
		try {
			ret=conn.Post("admin","C1sc0123",address ,data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
