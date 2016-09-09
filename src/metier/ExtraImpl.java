package metier;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ExtraImpl implements IExtra{

	@EJB
	IConnectTP conn;
	@Override
	public String IncreaseVolume(String IP, int Steps) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Audio> <Volume> <Increase command='True'><Steps>"+Steps+"</Steps></Increase></Volume> </Audio></Command> ";
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
	public String DecreaseVolume(String IP, int Steps) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Audio> <Volume> <Decrease command='True'><Steps>"+Steps+"</Steps></Decrease></Volume> </Audio></Command> ";
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
	public String Mute(String IP) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Audio> <Volume> <Mute command='True'></Mute></Volume> </Audio></Command> ";
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
	public String Unmute(String IP) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   <Audio> <Volume> <UnMute command='True'></UnMute>  </Volume> </Audio></Command> ";
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
	public String SelectPreset(String IP) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String ImageLocalDisplay(String IP,boolean mode) {
		String address="http://"+IP+"/putxml";
		String data="";
		if(mode) data="<Command>   <Video> <Selfview> <Set command='True'> <Mode>On</Mode> </Set></Selfview> </Video></Command> ";
		else data="<Command>   <Video> <Selfview> <Set command='True'> <Mode>Off</Mode> </Set></Selfview> </Video></Command> ";
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
