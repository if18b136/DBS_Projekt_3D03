package WebServer;

import BIF.SWE1.interfaces.Response;

import java.io.*;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * <h3>Response Class</h3>
 * Class to create and send response objects
 */
public class myResponse implements Response {

    private Map<String, String> responseMap = new LinkedHashMap<>();
    private Integer statusCode; //integer because int is set to 0 standard
    private String status;
    private String contentType;
    private String serverHeader = "BIF-SWE1-Server";
    private String content;
    private byte[] byteContent = null;
    private OutputStream network;

    /**
     * Get the hash map with the response key value pairs
     * @return the response headers as hash map
     */
    @Override
    public Map<String, String> getHeaders() {
        return this.responseMap;
    }

    /**
     * Get the length of the content
     * @exception UnsupportedEncodingException on error with getBytes encoding
     * @return content length as integer
     */
    @Override
    public int getContentLength() {
        try {
            return this.content.getBytes("UTF-8").length;
        } catch(UnsupportedEncodingException uex) {
            uex.printStackTrace();
            return 0;
        }
    }

    /**
     * Get the written content of a response - implemented for unit testing of plugin handling
     * @return the content that will be sent as response to the client
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Get the MIME type of the response content
     * @return the content type as string
     */
    @Override
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Sets the content type of the response.
     * @param contentType  the MIME type of the content
     */
    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Get the current set status code
     * @return the previously set status Code
     */
    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * Set the status code to a given integer
     * @param status the status code that will be set for the response
     */
    @Override
    public void setStatusCode(int status) {
        this.statusCode = status;
    }

    /**
     * Get the correct status for the response HTTP headers first line
     * @return the correct string for the set status code
     */
    @Override
    public String getStatus() {
        switch(this.statusCode) {
            case 200:
                this.status = "200 OK";
                break;
            case 404:
                this.status = "404 Not Found";
                break;
            case 500:
                this.status = "500 Internal Server Error";
                break;
            default:
                break;
        }
        return this.status;
    }

    /**
     * Write a new header value pair into the HTTP header hash map
     * @param header the header key
     * @param value the header value
     */
    @Override
    public void addHeader(String header, String value) {
        this.responseMap.put(header,value);
    }

    /**
     * Get the currently set server header
     * @return the currently set server header
     */
    @Override
    public String getServerHeader() {
        return this.serverHeader;
    }

    /**
     * Set the current server header and add it to the HTTP response header hash map
     * @param server server header as a string value
     */
    @Override
    public void setServerHeader(String server) {
        this.serverHeader = server;
        this.responseMap.put("Server",this.serverHeader);
    }

    /**
     * set the content that will be sent with the response as string
     * @param content the to-be-sent content as string
     */
    @Override
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * set the content that will be sent with the response as byte array (for images)
     * @param content the to-be-sent content as byte array
     */
    @Override
    public void setContent(byte[] content) {
        this.content = new String(content);
        this.byteContent = content;
    }

    /**
     * Set the content that will be sent with the response as input stream
     * @param stream the to-be-sent content as input stream
     */
    @Override
    public void setContent(InputStream stream) {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            this.content = result.toString("UTF-8");
        } catch(IOException use) {
            use.printStackTrace();
        }
    }

    /**
     * Construct the outgoing server response.
     * Check if status code is set, if yes write it into the output stream
     * Write all header information into the output stream
     * Add the content lastly and flush/close the output stream
     *
     * INFO: every string that gets written into the network needs a linefeed! -> Add "\r\n" to every string
     * @param network the output stream that will be send to the client as response
     * @exception IllegalStateException on set content but not set content type
     * @exception IOException on output stream error
     */
    @Override
    public void send(OutputStream network) {
        try {
            if(this.statusCode != null) {
                getStatus();
                String line = "HTTP/1.1 " + this.status + "\r\n";
                network.write(line.getBytes());
            }
            for(Map.Entry elem : this.responseMap.entrySet()) {
                String line = elem.getKey() + ": " + elem.getValue() + "\r\n";
                network.write(line.getBytes());
            }
            String lineEnd = "\r\n";
            network.write(lineEnd.getBytes());
            if(this.content != null) {
                if(this.byteContent != null) {
                    network.write(this.byteContent);
                } else {
                    network.write(this.content.getBytes());
                }
            }
            else {
                if(this.contentType != null) {
                    throw new IllegalStateException("Setting a content type but no content is not allowed");
                    //throw new java.lang.Error("Setting a content type but no content is not allowed");
                }
            }
            network.flush();    // added while server configuration
            network.close();
        } catch (IllegalStateException ex) {
            //ex.printStackTrace();
            //Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("Setting a content type but no content is not allowed");
            // why do you have to throw the exception here again?
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
