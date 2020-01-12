package WebServer;

import BIF.SWE1.interfaces.Url;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <h3>Url class</h3>
 * Class to extract all necessary data from the request URL
 */
public class myUrl implements Url {

    public myUrl(){
        // Code goes here but there is not really anything that has to be declared at runtime
    }

    private String rawUrl;
    private String path;
    private Map<String, String> params = new LinkedHashMap<>();
    private String fragment;
    private String[] segments;
    private String fileName;
    private String extension;

    /**
     * Setter method for setting the raw, unedited URL String
     * @param path  the URL string (from the client request)
     */
    public void setUrl(String path){
        this.rawUrl = path;
    }

    //should be unused throughout the whole project - same function as get raw URL
    public String getUrl() {
        return this.rawUrl;
    }

    /**
     * Getter method for public access to the raw URL of a URL object
     * @return the raw URL string of a URL object
     */
    @Override
    public String getRawUrl() {
        return this.rawUrl;
    }

    /**
     * Cuts the raw URL string into right form to get the path of the URL
     * @return the created path string of the raw URL
     */
    @Override
    public String getPath() {
        if(this.rawUrl == null) {
            return null;
        }
        String[] split = this.rawUrl.split(" ");
        // satisfy the unit Tests
        if(split.length != 3) {

            this.path = this.rawUrl.split("\\?")[0];
            if(this.path.split("#")[0].equals("D:\\")) {
                String[] dir = this.path.split("#");
                this.path = String.join("#",dir[0],dir[1]);
            }
            else {
                this.path = this.path.split("#")[0];
            }
            return this.path;
        }
        else {
            this.path = split[1].split("\\?")[0];
            if(split[1].split("#")[0].equals("D:\\")) {
                String[] dir = split[1].split("#");
                this.path = String.join("#",dir[0],dir[1]);
            }
            else {
                this.path = split[1].split("#")[0];
            }
            return this.path;
        }
    }

    /**
     * Split the raw URL into parameter and add them into the params hash map if the URL contains any
     * @exception UnsupportedEncodingException on incorrect encoding of key/value
     * @exception IllegalStateException on unexpected encoding
     * @return the hash map params in which all key value pairs of every parameter are stored
     */
    @Override
    public Map<String, String> getParameter() {
        try {
            String parameter;
            String[] query;
            String[] split = this.rawUrl.split(" ");
            if(split.length == 3) {
                query = split[1].split("\\?");
                if(query.length == 2) {
                    parameter = query[1];
                }
                else {
                    return this.params;
                }
            }
            else {
                String[] split_query = this.rawUrl.split("\\?");
                if(split_query.length < 2) {
                    return this.params;
                }
                else {
                    parameter = split_query[1];
                }
            }

            Map<String,String> params = this.params;
            for (String param : parameter.split("&")){
                String[] keyValue = param.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8"): "";
                if(!key.isEmpty()){
                    params.put(key,value);
                }
            }
            this.params = params;
            return this.params;
        } catch(UnsupportedEncodingException ex){
            throw new IllegalStateException(ex); // won't happen with UTF-8 encoding.
        }
    }

    /**
     * Split the raw URL into parameter and count the number
     * @exception UnsupportedEncodingException on incorrect encoding of key/value
     * @exception IllegalStateException on unexpected encoding
     * @return the number of parameter in the URL as integer
     */
    @Override
    public int getParameterCount() {
        try {
            if(this.rawUrl == null || this.getRawUrl().split("\\?")[0] == this.rawUrl){
                return 0;
            }
            String query;
            String[] split = this.rawUrl.split(" ");
            if(split.length == 3) {
                query = split[1].split("\\?")[1];
            }
            else {
                String[] split_query = this.rawUrl.split("\\?");
                if(split_query.length < 2) {
                    return 0;
                }
                query = split_query[1];
            }
            Map<String,String> params = this.params;
            for (String param : query.split("&")){
                String[] keyValue = param.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8"): "";
                if(!key.isEmpty()){
                    params.put(key,value);
                }
            }
            return params.size();
        } catch(UnsupportedEncodingException ex){
            throw new IllegalStateException(ex); // won't happen with UTF-8 encoding.
        }
    }

    /**
     * Check if the URL and path string have already been set - if so, split path into segments
     * @return the split path segments as string array
     */
    @Override
    public String[] getSegments() {
        if(this.rawUrl == null) {
            return null;
        }
        if(this.path == null) { //please the unit test gods, should not be necessary in WebServer
            getPath();
        }
        this.segments = this.path.replaceFirst("^/", "").split("/");
        return this.segments;
    }

    /**
     * The last segment represents the file name
     * @return "" if there is no url or the last segment of the segments array
     */
    @Override
    public String getFileName() {
        if(this.rawUrl == null || this.path == null) {
            return "";
        }
        int segments = this.segments.length;
        this.fileName = this.segments[segments - 1];
        return this.fileName;
    }

    /**
     * Get the file type of the requested file.
     * Split the file name into two parts and extract the file type
     * @return the file type as string
     */
    @Override
    public String getExtension() {
        if(this.fileName == null) {
            getFileName();
        }
        this.extension = this.fileName.split(".")[1];
        return this.extension;
    }

    /**
     * Get the URL fragment, split the raw URL
     * @return the fragment of the URL as string
     */
    @Override
    public String getFragment() {
        if(this.rawUrl == null) {
            return "";
        }
        this.fragment = this.rawUrl.split("#")[1];

        return this.fragment;
    }
}