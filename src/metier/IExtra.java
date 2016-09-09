package metier;

import javax.ejb.Remote;

@Remote
public interface IExtra {
	public String IncreaseVolume (String IP,int Steps);
	public String DecreaseVolume (String IP,int Steps);
	public String Mute (String IP);
	public String Unmute (String IP);
	public String SelectPreset (String IP);
	public String ImageLocalDisplay(String IP,boolean mode);
}
