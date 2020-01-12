package WebServer;

import BIF.SWE1.interfaces.Request;

import java.io.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

//static import for header value for content length parse to int - recommended by intellij
import static java.lang.Integer.parseInt;

/**
 * <h3>Request Class</h3>
 * Class to create and handle request objects
 */
public class myRequest implements Request {

    private InputStream is;
    private String method;
    private boolean isValid = false;
    private myUrl url = new myUrl();
    private Map<String, String> headers = new LinkedHashMap<>();
    private int contentLength;
    private String contentType;
    private InputStream contentStream;
    private String contentString;
    private byte[] contentBytes;

    /**
     * Set the request input stream
     * @param inputStream   the input stream for the request
     */
    public void setRequest(InputStream inputStream) {
        this.is = inputStream;
    }

    /**
     * get the request input stream
     * @return the input stream of the request object
     */
    public InputStream getInputStream() {
        return this.is;
    }

    /**
     * Method to validate if the request is correct.
     * Check for request method, if valid start reading input stream and create header hash map.
     * If header map contains content-type/-length set accordingly.
     * Finally read content and set content string
     * @exception IOException on stream error
     * @return true if everything can be handled correctly or false if something failed
     */
    @Override
    public boolean isValid() {
        if(this.method == null || this.method.isEmpty()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(this.is));
                String method = reader.readLine();

                if(method == null) {    //After testing the html Bootstrap Version, some requests seemed to be completely empty
                    this.isValid = false;
                    return false;
                }
                //System.out.println(method);
                String[] first_line = method.split(" ");
                if(first_line.length == 3){
                    this.method = first_line[0].toUpperCase();
                    this.url.setUrl(first_line[1]);

                    if(!this.method.equals("GET") && !this.method.equals("POST")) {
                        this.isValid = false;
                        return false;
                    }
                    else {
                        this.isValid = true;
                        boolean post = false;
                        if(this.method.equals("POST")) {
                            post = true;
                        }
                        String line;
                        while((line = reader.readLine()) != null && !line.equals("")) {     // set Header-Value Map
                            String[] keyValue = line.split(":",2);
                            String key = keyValue[0].toLowerCase();
                            String value = keyValue.length > 1 ? keyValue[1]: "";
                            //System.out.println(key + " : " + value);    //testing
                            this.headers.put(key,value);

                            if(key.equals("content-length")) {
                                this.contentLength = parseInt(value.trim());    // set contentLength
                            }

                            if(key.equals("content-type")) {
                                this.contentType = value.trim();    // set contentType
                            }
                        }
                        // after header is finished, we can read the content (we could also set content-length here manually)
                        if(reader.ready()){    // if no content will be following we want to skip the next part

                            // POST requests don't work with readline
                            if(post) {
                                if (headers.containsKey("content-length")) {
                                    int length = Integer.parseInt(headers.get("content-length").trim());
                                    char[] buffer = new char[length * 16];
                                    reader.read(buffer, 0, length);
                                    this.contentString = new String(buffer);
                                    this.contentString = this.contentString.replace("%0D%0A", "<br>"); //replace CRLF with br for html view
                                    this.contentString = URLDecoder.decode(this.contentString, StandardCharsets.UTF_8);    //decode special characters
                                }
                            }
                            else {
                                if((line = reader.readLine()) != null && !line.equals("")) {
                                    this.contentStream = new ByteArrayInputStream(line.getBytes());

                                    ByteArrayOutputStream result = new ByteArrayOutputStream();
                                    byte[] buffer = new byte[1024];
                                    int length;

                                    while ((length = this.contentStream.read(buffer)) != -1) {
                                        result.write(buffer, 0, length);
                                    }

                                    this.contentString =  result.toString("UTF-8");
                                    this.contentBytes = this.contentString.getBytes();
                                }
                            }
                         }
                        //System.out.println();
                        return true;
                    }
                }
                else {
                    this.isValid = false;
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return this.isValid;
    }

    /**
     * Get the request method (only GET/POST supported at the moment)
     * @return the request method
     */
    @Override
    public String getMethod() {
        return this.method;
    }

    /**
     * Get the URL object of the request
     * @return URL object
     */
    @Override
    public myUrl getUrl() {
        return this.url;
    }

    /**
     * Get the hash map that contains the request headers
     * @return the request header hash map
     */
    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    /**
     * get the amount of headers included in the request
     * @return the header amount as integer
     */
    @Override
    public int getHeaderCount() {
        return this.headers.size();
    }

    /**
     * Get the user agent header if it exists
     * @return user agent value if its exists as key in the hash map
     */
    @Override
    public String getUserAgent() {
        if(this.headers.size() == 0) { // otherwise the unit test wont initialize anything
            isValid();
        }
        if(this.headers.containsKey("user-agent")) {
            return this.headers.get("user-agent");
        }
        return "";
    }

    /**
     * Get the total content length
     * @return content length as integer
     */
    @Override
    public int getContentLength() {
        return this.contentLength;
    }

    /**
     * Get the MIME type of the content
     * @return the MIME type as string
     */
    @Override
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Get the content as input stream
     * @return the content as input stream
     */
    @Override
    public InputStream getContentStream() {
        if(this.contentStream != null) {
            return this.contentStream;
        }
        return null;
    }

    /**
     * get the content as string
     * @return the content as string
     */
    @Override
    public String getContentString() {
       return this.contentString;
    }

    /**
     * Get the content as byte array
     * @return the content as byte array
     */
    @Override
    public byte[] getContentBytes() {
        return this.contentBytes;

    }
}
