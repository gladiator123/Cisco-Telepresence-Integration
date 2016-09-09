package metier;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CameraManagerImpl implements ICameraManager{

	@EJB
	IConnectTP conn;

	public String SetPositionLocal(String IP, int CamID, String Pan, int PanSpeed, String Tilt, int TiltSpeed,
			String Zoom, int ZoomSpeed, String Focus) {

		String address="http://"+IP+"/putxml";
		String data="<Command>   "
				+ "<Camera> <PositionSet command='True'> "
				+ "<CameraId>"+CamID+"</CameraId><Pan>"+Pan+"</Pan>"
						+ "<Tilt>"+Tilt+"</Tilt><Zoom>"+Zoom+"</Zoom><Focus>"+Focus+"</Focus>"
				+ "</PositionSet>   "
				+ "</Camera></Command> ";
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
	public String MoveDistant(String IP, int callID, String MoveType) {

		String address="http://"+IP+"/putxml";
		String data="<Command>   "
				+ "<FarEndControl>"
				+ "<Camera> <Move command='True'> "
					+ "<CallId>"+callID+"</CallId> "
					+ "<Value>"+MoveType+"</Value> "
				+ "</Move></Camera>"
				+ "</FarEndControl>"
				+ "</Command> ";
		String ret="no data";
		System.out.println(data);
		try {
			ret=conn.Post("admin","C1sc0123",address ,data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;


	}

	@Override
	public String StopDistant(String IP, int callID) {
		String address="http://"+IP+"/putxml";
		String data="<Command>   "
				+ "<FarEndControl>"
				+ "<Camera> <Stop command='True'> "
					+ "<CallId>"+callID+"</CallId> "
				+ "</Stop></Camera>"
				+ "</FarEndControl>"
				+ "</Command> ";
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
	public String MoveCam(String IP,int CamID,int MoveType,int Speed,String Dir) {
		String address="http://"+IP+"/putxml";
		String data="";
		if (MoveType==1) 		data="<Command>   <Camera> <Ramp command='True'> <CameraId>"+CamID+"</CameraId><Pan>"+Dir+"</Pan></Ramp><PanSpeed>"+Speed+" </PanSpeed>   </Camera></Command> ";
		else if (MoveType==2) data="<Command>   <Camera> <Ramp command='True'> <CameraId>"+CamID+"</CameraId><Tilt>"+Dir+"</Tilt></Ramp> <TiltSpeed>"+Speed+" </TiltSpeed>  </Camera></Command> ";
		else if (MoveType==3) data="<Command>   <Camera> <Ramp command='True'> <CameraId>"+CamID+"</CameraId><Zoom>"+Dir+"</Zoom></Ramp>  <ZoomSpeed>"+Speed+" </ZoomSpeed> </Camera></Command> ";
		else if (MoveType==4) data="<Command>   <Camera> <Ramp command='True'> <CameraId>"+CamID+"</CameraId><Focus>"+Dir+"</Focus></Ramp> <FocusSpeed>"+Speed+" </FocusSpeed>  </Camera></Command> ";
		
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
