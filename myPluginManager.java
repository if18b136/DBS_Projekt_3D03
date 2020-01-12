package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * <h3>Plugin Manager Class</h3>
 * Class to load plugins, add new ones and select which plugin is needed for a request
 */
public class myPluginManager implements PluginManager {

    private List pluginList = new ArrayList<Plugin>();

    /**
     * The iterator for the plugin list
     * @return the iterable list of plugins
     */
    public Iterator<Plugin> iterator() {
        return this.pluginList.iterator();
    }

    private Iterable<Plugin> plugins = pluginList;

    /**
     * Constructor - load plugins at runtime
     */
    public myPluginManager(){
        loadPlugins(); // outsourced into a method so that plugins can also be reloaded at any given time
    }

    /**
     * check the text file with the list of plugins that can be loaded and add them
     * @exception IOException on scanner/file error
     * @exception IllegalAccessException on insufficient access rights
     * @exception InstantiationException on error at class instancing
     * @exception ClassNotFoundException on missing plugin class
     */
    public void loadPlugins(){
        try{
            File pluginFile = new File("D:\\#FH_Technikum\\Semester_3\\SWE\\MyWebServer\\src\\WebServer\\plugins.txt"); // change to dynamic path
            Scanner sc = new Scanner(pluginFile);
            String pluginName;
            while (sc.hasNextLine()) {
                pluginName = sc.nextLine();
                add(pluginName);
                // every additional plugin needs to be added to the nav bar
                //if (!pluginName.equals("tempPlugin") && !pluginName.equals("staticPlugin") && !pluginName.equals("naviPlugin") && !pluginName.equals("toLowerPlugin")) {
                //    htmlConstructor html = new htmlConstructor();
                //    html.addNav(pluginName);
                //}
            }
        } catch(IOException | IllegalAccessException | InstantiationException | ClassNotFoundException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * Second part of the iterator
     * @return iterator
     */
    @Override
    public Iterable<Plugin> getPlugins() {
        return this::iterator;
    }

    /**
     * Check if a certain plugin exists then adds it to the plugin list if it is not already in the list
     * @param plugin the plugin that will be added
     */
    @Override
    public void add(Plugin plugin) {
        boolean exists = false;
        for(Object pluginTemp : pluginList){
            if(plugin == pluginTemp){
                exists = true;
            }
        }
        if(!exists && plugin != null){
            this.pluginList.add(plugin);
        }
    }

    /**
     * Check for correct name form first
     * Create a new instance of the name and check if a class with the same name exists
     * If the class exists, add a instance of the plugin to the plugin list
     * (Throw errors for various exceptions)
     * @param plugin the plugin name
     * @throws InstantiationException on error at class instancing
     * @throws IllegalAccessException on insufficient access rights
     * @throws ClassNotFoundException on missing plugin class
     */
    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String pluginString;

        if(!plugin.contains(".")) {
            pluginString = "WebServer." + plugin;
        }
        else {
            pluginString = plugin;
        }

        if(Class.forName(pluginString).newInstance() instanceof Plugin ) {
            Plugin newPlugin = (Plugin) Class.forName(pluginString).newInstance();
            this.add(newPlugin);
        }
        else {
            throw new ClassNotFoundException("No class with name " + pluginString + " found.");
        }
    }

    /**
     * clear plugin list for reloading purposes
     */
    @Override
    public void clear() {
        this.pluginList = new ArrayList<Plugin>(); // alternative list.Clear(), garbage collector sollte hier jedoch gut greifen
    }

    /**
     * Get all canHandle float nums from the plugins in order to decide which plugin will handle the request
     * @param req the client request
     * @return the plugin that will handle the client request
     */
    public Plugin selectPlugin(Request req) {
        Plugin plugin = null;
        float max = 0;
        for (Plugin p : this.plugins) {
            float canHandle = p.canHandle(req);
            if (canHandle > max) {
                max = canHandle;
                plugin = p;
            }
        }
        return plugin;
    }
}
