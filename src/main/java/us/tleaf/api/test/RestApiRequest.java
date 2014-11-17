package us.tleaf.api.test;

import java.util.Map;

public interface RestApiRequest {
	
	public abstract void setHeader ( String accessKey, String userId, String appId );
	public abstract void setRequest ( String requestMethod, Map<String,Object> params );
	public abstract void executeRequest ();

}
