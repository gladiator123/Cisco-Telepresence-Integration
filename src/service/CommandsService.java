package service;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import metier.*;

@Path("/PIRL")
public class CommandsService {
	@EJB 
	private ICallManager icall;
	@EJB 
	private ICameraManager icam;
	@EJB 
	private IExtra iext;
	@EJB 
	private ISave isave;
	//________________________ Call Commands
	@GET
	@Path("Call/NewCall/{IP}")
	@Produces("Application/xml")
	public Response OpenCall(@PathParam(value="IP")String IP,@QueryParam(value="dest")String dest){
		String ret=icall.OpenCall(IP,dest);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	@GET
	@Path("Call/EndCall/{IP}")
	@Produces("Application/xml")
	public Response CloseCall(@PathParam(value="IP")String IP,@QueryParam(value="ID")int ID){
		String ret=icall.CloseCall(IP,ID);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	@GET
	@Path("Call/AcceptCall/{IP}")
	@Produces("Application/xml")
	public Response AcceptCall(@PathParam(value="IP")String IP,@QueryParam(value="ID") int ID, @QueryParam(value="Type")String Type){
		String ret=icall.AcceptCall(IP,ID,Type);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	@GET
	@Path("Call/RejectCall/{IP}")
	@Produces("Application/xml")
	public Response RejectCall(@PathParam(value="IP")String IP,@QueryParam(value="ID") int ID){
		String ret=icall.RejectCall(IP,ID);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}	
	
	@GET
	@Path("Call/Notify/{IP}")
	@Produces("Application/xml")
	public Response Notify(@PathParam(value="IP")String IP,@QueryParam(value="Status") String status) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException{
		String ret=icall.Callinfo(IP,status);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	//________________________ Camera Commands	
	@GET
	@Path("Camera/Local/PositionSet/{IP}")
	@Produces("Application/xml")
	public Response SetPositionLocal(@PathParam(value="IP")String IP, @QueryParam(value="CamID")int CamID,@QueryParam(value="Pan") String Pan,@QueryParam(value="Tilt") String Tilt,
			@QueryParam(value="Zoom")String Zoom,@QueryParam(value="Focus") String Focus){
		String ret=icam.SetPositionLocal( IP, CamID,Pan,0,Tilt,0,Zoom,0, Focus);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	@GET
	@Path("Camera/Local/Move/{IP}")
	@Produces("Application/xml")
	public Response MoveCam(@PathParam(value="IP")String IP,@QueryParam(value="CamID") int CamId,@QueryParam(value="MoveType") int MoveType,@QueryParam(value="Dir") String Dir,@QueryParam(value="Speed") int Speed){
		
		String ret="";
		ret+=icam.MoveCam(IP,CamId,MoveType,Speed,Dir);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	
	@GET
	@Path("Camera/Distant/Move/{IP}")
	@Produces("Application/xml")
	public Response MoveDistant(@PathParam(value="IP")String IP,@QueryParam(value="CallID") int callID,@QueryParam(value="MoveType") String MoveType){
		
		String ret="";
		ret+=icam.MoveDistant(IP,callID,MoveType);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	@GET
	@Path("Camera/Distant/Stop/{IP}")
	@Produces("Application/xml")
	public Response StopDistant(@PathParam(value="IP")String IP,@QueryParam(value="CallID") int callID){
		
		String ret="";
		ret+=icam.StopDistant(IP,callID);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	//________________________ History Commands	
	
	@GET
	@Path("History/Call/Logs/{IP}")
	@Produces("Application/xml")
	public Response CallLogs(@PathParam(value="IP")String IP){
		
		String ret="";
		ret+=isave.CallLogs(IP);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	@GET      
	@Path("History/Call/OpenedCall/{IP}")
	@Produces("Application/xml")
	public Response OpenedCall(@PathParam(value="IP")String IP){
		
		String ret="";
		ret+=isave.OpenedCall(IP);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	@GET
	@Path("History/Preset/List/{IP}")
	@Produces("Application/xml")
	public Response PresetList(@PathParam(value="IP")String IP,@QueryParam(value="ID") int ID){
		
		String ret="";
		ret+=isave.PresetList(IP,ID);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	@GET
	@Path("History/Preset/Edit/{IP}")
	@Produces("Application/xml")
	public Response PresetEdit(@PathParam(value="IP")String IP ,@QueryParam(value="PresetId")int PresetId, @QueryParam(value="ListPosition")int ListId,@QueryParam(value="Name") String Name){
		
		String ret="";
		ret+=isave.PresetEdit(IP,PresetId,ListId,Name);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	@GET
	@Path("History/Preset/Store/{IP}")
	@Produces("Application/xml")
	public Response PresetStore(@PathParam(value="IP")String IP ,@QueryParam(value="PId")int PresetId, @QueryParam(value="LId")int ListId,@QueryParam(value="Name") String Name){
		
		String ret="";
		ret+=isave.PresetStore(IP,PresetId,ListId,Name);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	
	
	@GET
	@Path("History/Preset/Activate/{IP}")
	@Produces("Application/xml")
	public Response PresetActivate(@PathParam(value="IP")String IP,@QueryParam(value="PresetId")int Id){
		
		String ret="";
		ret+=isave.PresetActivate(IP,Id);
		return Response.ok().entity(ret)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	
	//________________________ Audio Commands	
	
		@GET
		@Path("Audio/IncreaseVolume/{IP}")
		@Produces("Application/xml")
		public Response IncreaseVolume(@PathParam(value="IP")String IP,@QueryParam(value="Steps")int Steps){
			
			String ret="";
			ret+=iext.IncreaseVolume(IP,Steps);
			return Response.ok().entity(ret)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		}
		
		@GET
		@Path("Audio/DecreaseVolume/{IP}")
		@Produces("Application/xml")
		public Response DecreaseVolume(@PathParam(value="IP")String IP,@QueryParam(value="Steps")int Steps){
			
			String ret="";
			ret+=iext.DecreaseVolume(IP,Steps);
			return Response.ok().entity(ret)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		}
		@GET
		@Path("Audio/Mute/{IP}")
		@Produces("Application/xml")
		public Response Mute(@PathParam(value="IP")String IP){
			
			String ret="";
			ret+=iext.Mute(IP);
			return Response.ok().entity(ret)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		}
		
		@GET
		@Path("Audio/Unmute/{IP}")
		@Produces("Application/xml")
		public Response Unmute(@PathParam(value="IP")String IP){
			
			String ret="";
			ret+=iext.Unmute(IP);
			return Response.ok().entity(ret)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		}
		
		//________________________ Video Commands	
		@GET
		@Path("Video/LocalDisplay/{IP}")
		@Produces("Application/xml")
		public Response ImageLocalDisplay(@PathParam(value="IP")String IP,@QueryParam(value="Mode")boolean mode){
			
			String ret="";
			ret+=iext.ImageLocalDisplay(IP,mode);
			return Response.ok().entity(ret)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS").build();
		}
	
}
