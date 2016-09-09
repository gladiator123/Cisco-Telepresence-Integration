package metier;

import javax.ejb.Remote;

@Remote
public interface ICameraManager {
	public String SetPositionLocal(String IP,int CamID,String Pan,int PanSpeed,String Tilt,int TiltSpeed,String Zoom,int ZoomSpeed,String Focus);
	public String MoveCam(String IP,int CamID,int MoveType,int Speed,String Dir);
	//StopCam
	public String MoveDistant(String IP,int callID,String MoveType);
	public String StopDistant(String IP,int callID);
}
