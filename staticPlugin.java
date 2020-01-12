package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h3>Static Plugin</h3>
 * Plugin for static file handling, for example stylesheets, scripts, etc.
 */
public class staticPlugin implements Plugin {

    /**
     * @param staticDir    private String of the directory where static files can be found
     */
    private static String staticDir;

    public staticPlugin(){
        //"D:\\#FH_Technikum\\Semester_3\\SWE\\MyWebServer\\
        staticDir = "/deploy/staticFiles/";
    }

    /**
     * Getter-function for static directory
     * @return the current static directory
     */
    public String getStaticDir() {
        return staticDir;
    }

    /**
     * Setter function for static directory
     * @param s     the path on which staticDir will be set
     */
    public void setStaticDir(String s) {
        staticDir = s;
    }

    /**
     * Method to determine if a request is suitable to be handled by the static plugin
     * Checks on the current static directory, if the request URL is home path or if it contains a directory indicator
     * @param req   the client request
     * @return a float between 0 and 1 depending on the handling capability of the request
     */
    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getRawUrl().contains(staticDir)) {
                return (float) 0.9;
            }
            else if(req.getUrl().getPath().equals("/")) {
                return (float) 0.8;
            }
            else if(req.getUrl().getPath().contains("/")) {
                return (float) 0.1;
            }
        }
        return 0;
    }

    /**
     * Method to handle requests that got flagged for static plugin handling
     * If the URL request equals the home page, the standard home webpage is created and send as response
     * Next URL check id for Material Design Bootstrap - the layout of the page
     * Alternatively if the requested URL is within the static directory the file search will also be started
     * The requested file type gets determined. If the file was found, the correct content type is set and a response is created.
     * @param req   the client request
     * @exception IOException on image to byte array conversion errors
     * @return  a correct server response or a 404 response if the file could not be found
     */
    @Override
    public Response handle(Request req) {
        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();

        if(req.getUrl().getPath().equals("/")) {
            // needed for plugin reload
            myPluginManager pmg = new myPluginManager();
            pmg.loadPlugins();

            String htmlString = html.getHome();
            res.setStatusCode(200);
            res.addHeader("Content-Type", "text/html");
            res.addHeader("Content-length", String.valueOf(htmlString.length()));
            res.addHeader("connection", "keep-alive");
            res.setContentType("text/html");
            res.setContent(htmlString);
            return res;
        }
        else if(req.getUrl().getPath().contains("/deploy/MDB") || req.getUrl().getPath().contains(staticDir)) {
            //System.out.println("Working Directory = " + System.getProperty("user.dir"));
            String path = req.getUrl().getPath().replace("/","\\");
            char abs = path.charAt(1);
            if(abs != ':') {
                if(path.split("\\\\")[1].equals("deploy") /*&& !path.split("\\\\")[2].equals("MDB")*/) {
                    path = String.join("",System.getProperty("user.dir"),path/*.substring(7)*/);
                } else {
                    path = String.join("",System.getProperty("user.dir"),path);
                }
            }
            //System.out.println("Checking for: " + path);
            boolean fileExists = new File (path).isFile();
            System.out.println(path + " - File found: " + fileExists);

            if(fileExists) {
                String contentType = "", folder = "";
                String nameSplit = req.getUrl().getPath().replace("/","\\").split("\\\\")[3];    //this only works if the static directory has the expected layout - watch out when changing

                switch (nameSplit.toLowerCase()) {
                    case "js":
                        contentType = "text/javascript";
                        break;
                    case "css":
                        contentType = "text/css";
                        break;
                    case "font":
                        contentType = "font/woff2";
                        break;
                    case "img": // needs an own response because no text
                        // we need to determine which type of image
                        folder = req.getUrl().getPath().split("/")[4];
                        if(!folder.contains(".")){  // without a "." in the name it's a directory
                            switch (folder.toLowerCase()) { // lower case so jpeg is always jpeg etc.
                                case "bmp":
                                    contentType = "image/bmp";
                                    break;
                                case "gif":
                                    contentType = "image/gif";
                                    break;
                                case "ico":
                                    contentType = "image/vnd.microsoft.icon";
                                    break;
                                case "jpeg":
                                case "jpg":
                                    contentType = "image/jpeg";
                                    break;
                                case "png":
                                    contentType = "image/png";
                                    break;
                                case "svg":
                                    contentType = "image/svg";
                                    break;
                                case "tif":
                                case "tiff":
                                    contentType = "image/tiff";
                                    break;
                                case "webp":
                                    contentType = "image/webp";
                                    break;
                            }
                        }
                        break;
                }
                try{
                    if(contentType.contains("image")) {
                        byte[] content = new byte[0];
                        Path imgPath = Paths.get(path);
                        content = Files.readAllBytes(imgPath);

                        res.setStatusCode(200);
                        res.addHeader("connection", "keep-alive");
                        res.addHeader("Content-Type", contentType);
                        res.setContentType(contentType);
                        res.setContent(content);
                    }
                    else {

                        String data = "";// = Files.readString(Paths.get(Diff));
                        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
                        String line;

                        while((line = input.readLine()) != null) {
                            data = String.join("\r\n", data, line);
                        }
                        data = data.substring(2);
                        input.close();

                        res.setStatusCode(200);
                        float test = data.length();
                        //System.out.println(test + " Content Length (String)");
                        res.addHeader("Content-length", String.valueOf(data.length()));
                        res.addHeader("connection", "keep-alive");
                        res.addHeader("Content-Type", contentType);
                        res.setContentType(contentType);
                        res.setContent(data);
                    }
                    return res;
                }
                catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            else{
                res.setStatusCode(404);
                res.addHeader("connection", "close");
                return res;
            }
        }
        res.setStatusCode(404);
        res.addHeader("connection", "close");
        return res;

        // Obsolete - staticFile handling was included in the MDB check above
        /*else if(req.getUrl().getPath().contains("/deploy/MDB")) {
            String path = req.getUrl().getRawUrl().replace("/","\\");
            path = String.join("",System.getProperty("user.dir"),path);
            System.out.println("Checking for: " + path);
            boolean fileExists = new File (path).isFile();
            System.out.println("File found: " + fileExists);

            if(fileExists) {    // einfach Inhalt anzeigen lassen - MIME Type richtig setzen
                String htmlString = html.getStatic();

                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);
            }
            else {
                res.setStatusCode(404);
                res.addHeader("connection", "close");
            }
        }*/
    }
}
