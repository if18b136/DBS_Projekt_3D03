package WebServer;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>SWE Semesterproject MyWebserver</h1>
 *
 * @author  Maximilian Rotter, if18b136
 * @version 0.8.3
 * @since   16.09.2019
 *
 */
public class Startup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listen();
    }

    private static void listen(){
        /**
         * This method represents the Server Startup Protocol.
         * In it Threading and Socket Connection accepting takes place.
         * @param listener      the main socket listener with port number input
         * @param threads       a linked List containing all open Threads
         * @param thread_num    the current number of a thread, gets incremented with every use
         * @param temp          the separate temperature db thread
         * @param s             Socket connection to accept client requests
         * @param is            the input stream for the socket connection
         * @param os            the output stream for the socket connection
         * @param t             new thread for every socket connection
         * @exception IOException On input/output stream error
         * @see IOException
         */
        try {
            java.net.ServerSocket listener = new java.net.ServerSocket(8081);
            List<Thread> threads = new LinkedList<Thread>();
            int thread_num = 1;

            /* auskommentiert fuer datenbankenprojekt


            tempThread temp = new tempThread("Thread " + thread_num);
            temp.start();
            threads.add(temp);
            thread_num++;
            */

            while(true){
                Socket s = listener.accept();
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();

                MyThread t = new MyThread("Thread " + thread_num, is, os);
                t.start();
                threads.add(t);
                thread_num++;
            }
            //for (MyThread t : threads) {
            //    t.stopMyThread();
            //    t.join();
            //}
            //listener.close();
        } catch (IOException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
