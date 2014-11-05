ShackServerTest With JAVA
===============

Here are TLeaf's Custom Header Names

```java

	// Unique TLeaf Header names for Authorization
	private static final String USERID_HEADER_NAME = "x-tleaf-user-id";
	private static final String APPID_HEADER_NAME = "x-tleaf-application-id"; // Same as other company's API Key
	private static final String ACCESSKEY_HEADER_NAME = "x-tleaf-access-token";

```


And Here is an example Access Token


```java

	// Example Access Token Information for Test
	private static final String USERID = "344bc889c8bb44dd6e4bb845d40007b9";
	private static final String APPID = "6b22f647ef8f2f3278a1322d8b000f81"; // Same as other company's API Key
	private static final String ACCESSKEY= "6b22f647ef8f2f3278a1322d8b000210";

```


Now Let's Look at how we can Use TLeaf's API


```java

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

```


TLeaf REST Server consumes JSON data, and produces JSON data
You can Change HttpResponse into InputStream and to String. Finally a Java Object by using JsonMappers.

To execute a POST request, Following steps should be added

```java

    	// mapping bodyObject into JSON String
    	Gson gson = new Gson();
    	String jsonObject = gson.toJson(bodyObject);
    		
    	// Puts it into the Request Body
	httpPost.setEntity( new StringEntity( jsonObject ) );

```

You can use any JSON mapping library, it's fine. Just remember to exclude null datas.
