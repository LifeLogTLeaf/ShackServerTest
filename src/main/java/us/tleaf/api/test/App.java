package us.tleaf.api.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

/**
 * Tests TLeaf's REST Server
 * @author susu
 * Date : Nov 5, 2014 4:00:03 PM
 */
public class App {
	
	// Unique TLeaf Header names for Authorization
	private static final String USERID_HEADER_NAME = "x-tleaf-user-id";
	private static final String APPID_HEADER_NAME = "x-tleaf-application-id"; // Same as other company's API Key
	private static final String ACCESSKEY_HEADER_NAME = "x-tleaf-access-token";
	
	// Example Access Token Information for Test
	private static final String USERID = "344bc889c8bb44dd6e4bb845d40007b9";
	private static final String APPID = "6b22f647ef8f2f3278a1322d8b000f81"; // Same as other company's API Key
	private static final String ACCESSKEY= "6b22f647ef8f2f3278a1322d8b000210";
	
	private static final String baseUrl = "http://14.63.171.66:8081/tleafstructure/";
	
    public static void main( String[] args ) {
    	
    	System.out.println( "Starting REST Api Request...\n" );

    	// Create a HTTP GET Request
    	HttpGet httpGetRequest = new HttpGet( "http://14.63.171.66:8081/tleafstructure/" + "api/user" );
    	
    	// Set custom Headers ( for Authorization ) and Content-Type
    	httpGetRequest.addHeader( USERID_HEADER_NAME, USERID );
    	httpGetRequest.addHeader( APPID_HEADER_NAME, APPID );
    	httpGetRequest.addHeader( ACCESSKEY_HEADER_NAME, ACCESSKEY );
    	httpGetRequest.setHeader( "content-type","application/json" );
    	
    	// Create a Default HTTP Client
    	HttpClient client = new DefaultHttpClient();
    	
    	try {
			HttpResponse response = client.execute( httpGetRequest );
		} catch ( Exception e ) {}
    	
    	// Starting GET request on 'rest.tleaf.us/tleafstructure/api/user'
    	executeRequest( baseUrl + "api/user" , "GET" , null );
    	
    	// making dummy Data for POST request
    	RawData rawData = new RawData();
    	
    	HashMap<String,Object> hashMap = new HashMap<String,Object>();
    	hashMap.put( "content", "I used Shack today and it was Awesome" );
    	
    	rawData.setData( hashMap );
    	
    	// Starting GET request on 'rest.tleaf.us/tleafstructure/api/user/app/log'
    	executeRequest( baseUrl + "api/user/app/log" , "POST" , rawData );

    }
    
    private static void executeRequest ( String url , String method , Object bodyObject ) {
    	
    	// make a Default Apache Http Client
    	HttpClient client = new DefaultHttpClient();
    	
    	HttpUriRequest httpUriRequest;
    	
    	System.out.println( url + "  " + method );
    	
    	// Makes UriRequest as the Method Specified
    	switch( method ) {
    	
    		case "GET" :
    			httpUriRequest = makeGetRequest( url ); break;
    		
    		case "POST" : 
    			httpUriRequest = makePostRequest( url , bodyObject ); break;
    		
    		case "DELETE" : 
    			httpUriRequest = makeDeleteRequest( url ); break;
    		
    		default : httpUriRequest = makeGetRequest( url ); break;
    	
    	}
    	
    	// Sets Http Header for Authorization ( Only for Requests under /api/* )
    	setHeader( httpUriRequest );
    	
		try {
			
	    	// Executes Request	
			HttpResponse response = client.execute( httpUriRequest );
			
			String responseString = responseToString ( response ) ;
			
	    	// Prints the Response
	    	System.out.println( responseString + "\n" );
	    	
//			Gson gson = new Gson();
//			UserInfo userInfo = new UserInfo();
//			gson.fromJson(responseString, UserInfo.class);
	    	
		} catch ( Exception e ) { e.printStackTrace(); }
		
    }
    
    private static String responseToString ( HttpResponse response ) throws IOException {
    	
    	BufferedReader br;
		StringBuilder sb = new StringBuilder();
		String line;
		
		br = new BufferedReader( new InputStreamReader( response.getEntity().getContent() ) );
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		
		return sb.toString();
    	
    }
    
    private static HttpUriRequest makeGetRequest ( String url ) {
    	return new HttpGet( url );
    }
    
    private static HttpUriRequest makePostRequest ( String url , Object bodyObject ) {
    	
    	HttpPost httpPost = new HttpPost( url );
    	
    	try {
    		
    		// mapping bodyObject into JSON String
    		Gson gson = new Gson();
    		String jsonObject = gson.toJson(bodyObject);
    		
    		System.out.println(jsonObject);
    		
    		// Puts it into the Request Body
			httpPost.setEntity( new StringEntity( jsonObject ) );
			
		} catch ( Exception e ) {
			System.out.println( "Failed to Send Body data into POST request" );
			e.printStackTrace();
		}
    	
    	return httpPost;
    }
    
    private static HttpUriRequest makeDeleteRequest ( String url ) {
    	return new HttpDelete( url );
    }
    
    private static void setHeader ( HttpUriRequest request ) {
    	
    	request.addHeader( USERID_HEADER_NAME, USERID );
    	request.addHeader( APPID_HEADER_NAME, APPID );
    	request.addHeader( ACCESSKEY_HEADER_NAME, ACCESSKEY );
    	request.setHeader( "content-type","application/json" );
    	
    }
}
