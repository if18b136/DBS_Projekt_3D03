package WebServer;

import BIF.SWE1.interfaces.Plugin;
import java.io.*;
import java.sql.SQLException;
import java.util.InputMismatchException;

/**
 * <h3>Client Connection Threads</h3>
 * All client requests are threaded and then handled accordingly
 */
public class MyThread extends Thread {

    /**
     * @param threadName Private String of thread name
     * @param is                Private input stream for client request
     * @param os                Private output stream for server response
     */
    private String threadName;
    private InputStream is;
    private OutputStream os;

    /**
     * Constructor for class MyThread
     * @param name          The number of the thread
     * @param is            The input stream on which the client sends it's request
     * @param os            The output stream on which the server sends it's response
     */
    public MyThread(String name, InputStream is, OutputStream os) {
        this.threadName = name;
        this.is = is;
        this.os = os;
    }

    @Override
    public void run() {

        /**
         * the main method of the treaded Server connection
         * @param urlObj        The Url Object of the client request
         * @param reqObj        The client request object
         * @param resObj        The server response object for the client request
         * @param pmg           The plugin manager object for request handling
         * @param html          The html construction object for the correct webpage display
         * @param htmlString    String for the default webpage
         * @param plugin        select the correct plugin for request handling
         * @exception InputMismatchException On incorrect request from client
         * @see InputMismatchException
         */
        try {
            myUrl urlObj;
            myRequest reqObj = new myRequest();
            myResponse resObj = new myResponse();
            myPluginManager pmg = new myPluginManager();
            htmlConstructor html = new htmlConstructor();

            reqObj.setRequest(this.is);

            // check if request is valid
            if (reqObj.isValid()) {
                urlObj = reqObj.getUrl();

                // default webpage if path is empty
                if (urlObj.getPath().equals("") || urlObj.getPath().equals("/")) { // eventuell in PluginManager verschieben und generalisieren zsm mit anderen paths
                    System.out.println();
                    String htmlString = html.getHome();
                    resObj.setStatusCode(200);
                    resObj.addHeader("Content-Type", "text/html");
                    resObj.addHeader("Content-length", String.valueOf(htmlString.length()));
                    resObj.addHeader("connection", "keep-alive");
                    resObj.setContentType("text/html");
                    resObj.setContent(htmlString);

                    resObj.send(os);
                } else if (urlObj.getPath().equals("/favicon.ico")) {
                    // Ignore favicon
                    System.out.println("favicon");
                } else {
                    //System.out.println("Request Path: " + urlObj.getPath());
                    Plugin plugin = pmg.selectPlugin(reqObj);
                    resObj = (myResponse) plugin.handle(reqObj);
                    resObj.send(os);
                }
            } else {
                throw new InputMismatchException(this.threadName + ": The send request does not seem to be valid. Url: " + reqObj.getUrl().getRawUrl());
            }
        }catch(InputMismatchException | SQLException ime) {
            ime.printStackTrace();
            return;
        }
    }
}
