package metier;

import javax.ejb.Remote;

@Remote
public interface ISave {
	public String CallLogs (String IP);
	public String OpenedCall (String IP);
	public String PresetList (String IP,int Id);
	public String PresetEdit (String IP,int PresetId,int ListId,String Name);
	public String PresetStore (String IP,int PresetId,int ListId,String Name);
	public String PresetActivate (String IP,int PresetId);
	public String PresetRemove (String IP,int PresetId);
	
}
