package WebServer;

import java.sql.Timestamp;

/**
 * <h3>HTML Constructor Class</h3>
 * Class to create html pages with the correct content
 */
public class htmlConstructor {

    public static String username = "";
    public static String lvaID ="";
    public static String lvaBez = "";

    private static String begin = String.join(" ","",
            "<!doctype html>",
            "<html lang=\"en\">",
                "<head>",
                    "<meta charset=\"utf-8\">",
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">",
                    "<meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">",
                    "<link rel=\"icon\" href=\"deploy/MDB/img/mdb-favicon.ico\" type=\"image/x-icon\">",

                    "<title>SWE1</title>",
                    "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">",
                    //Font Awesome -->
                    "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.11.2/css/all.css\">",
                    //Bootstrap core CSS -->
                    "<link rel=\"stylesheet\" href=\"deploy/MDB/css/bootstrap.min.css\">",
                    //Material Design Bootstrap -->
                    "<link rel=\"stylesheet\" href=\"deploy/MDB/css/mdb.min.css\">",
                    //Your custom styles (optional) -->
                    //"<link rel=\"stylesheet\" href=\"deploy/MDB/css/style.css\">",
                    //<!-- MDBootstrap Datatables  -->
                    "<link href=\"deploy/MDB/css/addons/datatables.min.css\" rel=\"stylesheet\">",
                "</head>",
                "<body>",
                    "<nav class=\"navbar navbar-dark bg-dark\">",
                        "<div class=\"container d-flex flex-column flex-md-row justify-content-between\">",
                            "<a class=\"nav-item nav-link\" href=\"/\">Home</a>",
                            "<a class=\"nav-item nav-link\" href=\"/temp\">Temperature</a>",
//                            "<a class=\"nav-item nav-link\" href=\"/static\">Static</a>",
                            "<a class=\"nav-item nav-link\" href=\"/toLower\">ToLower</a>",
                            "<a class=\"nav-item nav-link\" href=\"/navi\">Navigation</a>",
                            "<a class=\"nav-item nav-link\" href=\"/login\">Login</a>",
            "</div>",
                    "</nav>"
            );

    private static String beginLog = String.join(" ","",
            "<!doctype html>",
            "<html lang=\"en\">",
            "<head>",
            "<meta charset=\"utf-8\">",
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">",
            "<meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">",
            "<link rel=\"icon\" href=\"deploy/MDB/img/mdb-favicon.ico\" type=\"image/x-icon\">",

            "<title>SWE1</title>",
            "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">",
            //Font Awesome -->
            "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.11.2/css/all.css\">",
            //Bootstrap core CSS -->
            "<link rel=\"stylesheet\" href=\"deploy/MDB/css/bootstrap.min.css\">",
            //Material Design Bootstrap -->
            "<link rel=\"stylesheet\" href=\"deploy/MDB/css/mdb.min.css\">",
            //Your custom styles (optional) -->
            //"<link rel=\"stylesheet\" href=\"deploy/MDB/css/style.css\">",
            //<!-- MDBootstrap Datatables  -->
            "<link href=\"deploy/MDB/css/addons/datatables.min.css\" rel=\"stylesheet\">",
            "</head>",
            "<body>",
            "<nav class=\"navbar navbar-dark bg-dark\">",
            "<div class=\"container d-flex flex-column flex-md-row justify-content-between\">",
            "<a class=\"nav-item nav-link\" href=\"/\">Home</a>",
            "<a class=\"nav-item nav-link\" href=\"/temp\">Temperature</a>",
//                            "<a class=\"nav-item nav-link\" href=\"/static\">Static</a>",
            "<a class=\"nav-item nav-link\" href=\"/toLower\">ToLower</a>",
            "<a class=\"nav-item nav-link\" href=\"/navi\">Navigation</a>",
            "<a class=\"nav-item nav-link\" href=\"/login\">Logout</a>",
            "</div>",
            "</nav>"
    );

//    private static String navBegin = String.join("", "",
//            "<body>",
//                "<nav class=\"navbar navbar-dark bg-dark\">",
//                    "<div class=\"container d-flex flex-column flex-md-row justify-content-between\">",
//                        "<a class=\"nav-item nav-link\" href=\"/\">Home</a>",
//                        "<a class=\"nav-item nav-link\" href=\"/temp\">Temperature</a>",
//                        "<a class=\"nav-item nav-link\" href=\"/static\">Static</a>",
//                        "<a class=\"nav-item nav-link\" href=\"toLower\">ToLower</a>",
//                        "<a class=\"nav-item nav-link\" href=\"/navi\">Navigation</a>"
//            );
//
//    private static String navAdd = "";
//
//    private static String navEnd = String.join("", "",
//                    "</div>",
//                "</nav>"
//            );

    private static String end = String.join(" ","",
                //"<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>",
                //    "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>",
                //    "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>",
                //<!-- jQuery -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/jquery.min.js\"></script>",
                //<!-- Bootstrap tooltips -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/popper.min.js\"></script>",
                //<!-- Bootstrap core JavaScript -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/bootstrap.min.js\"></script>",
                //<!-- MDB core JavaScript -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/mdb.min.js\"></script>",
                //<!-- MDBootstrap Datatables  -->
                "<script type=\"text/javascript\" src=\"deploy/MDB/js/addons/datatables.min.js\"></script>",
                "<script type=\"text/javascript\">",
                    "$(document).ready(function () {",
                        "$('#TEMPERATURE').DataTable();",
                        "$('.dataTables_length').addClass('bs-select');",
                    "});",
                "</script>",
            "</body>",
            "</html>"
            );

    private static String home = String.join(" ","",
                "<div class=\"container\">",
                    "<h1>Softwareprojekt SWE1 Startseite</h1>",
                "</div>"
            );

    private static String tempBegin = String.join("","",
                "<div class=\"container\">",
                    "<h1>Temperature Plugin</h1>",
                "</div>",
                "<div class=\"container\">",
                    "<form action=\"/GetTemperature\" method=\"post\" enctype=\"plain/text\">",
                        "Choose a Date:<br>",
                        "<input type=\"date\" name=\"date\"><br>",
                        "<input type=\"submit\"><br>",
                    "</form>",
                "</div>",
             "<div class=\"container\">",
                    "<table id=\"TEMPERATURE\" class=\"table table-striped table-bordered table-sm\" cellspacing=\"0\" width=\"100%\">",
                        "<thead>",
                            "<tr>",
                                "<th class=\"th-sm\">ID</th>",
                                "<th class=\"th-sm\">Temperature</th>",
                                "<th class=\"th-sm\">Timestamp</th>",
                             "</tr>",
                        "</thead>",
                        "<tbody>"
            );

    private static String tempContent = "";
    private static String tempXML = "";

    private static String XMLDeclaration = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><statistics>";
    private static String XMLEnd = "</statistics>";

    private static String tempEnd = String.join("","",
                        "</tbody>",
                        "<tfoot>",
                            "<tr>",
                                "<th>ID</th>",
                                "<th>Temperature</th>",
                                "<th>Timestamp</th>",
                            "</tr>",
                        "</tfoot>",
                    "</table>",
                "</div>"
            );

    private static String stat = String.join("","",
                "<div class=\"container\">",
                    "<h1>Static Plugin</h1>",
                "</div>"
             );

    private static String toLower = String.join("","",
                "<div class=\"container\">",
                    "<h1>toLower Plugin</h1>",
                    "<form class=\"text-center\" style=\"color: #757575;\" action=\"/toLower\" method=\"post\" enctype=\"plain/text\">",
                        "<div class=\"md-form amber-textarea active-amber-textarea\">",
                            "<textarea id=\"form19\" class=\"md-textarea form-control\" name=\"toLowerText\" rows=\"3\"></textarea>",
                            "<label for=\"form19\">Write your toLower Text Here</label>",
                        "</div>",
                    "<button class=\"btn btn-outline-info btn-rounded btn-block z-depth-0 my-4 waves-effect\" type=\"submit\">Send</button>",
                    "</form>"
            );

    private static String lowerText = "";

//    public void addNav(String nav) {
//        navAdd = String.join("", navAdd,
//                    "<a class=\"nav-item nav-link\" href=\"/",nav,"\">",nav,"</a>"
//                );
//    }

    private static String navi = String.join("","",
            "<div class=\"container\">",
                "<form action=\"/navi\" method=\"post\">",
                    "<button class=\"btn btn-outline-info btn-rounded btn-block z-depth-0 my-2 waves-effect\" type=\"submit\">Load Data</button>",
                "</form>",
                "<form class=\"text-center\" style=\"color: #757575;\" action=\"/navi\" method=\"post\" accept-charset=\"UTF-8\">",
                    "<div class=\"md-form amber-textarea active-amber-textarea\">",
                    "<textarea id=\"form19\" class=\"md-textarea form-control\" name=\"address\" rows=\"3\"></textarea>",
                    "<label for=\"form19\">Check out if a certain Address is in the street map...</label>",
                    "</div>",
                    "<button class=\"btn btn-outline-info btn-rounded btn-block z-depth-0 my-4 waves-effect\" type=\"submit\">Send</button>",
                "</form>",
            "</div>",
            "<div class=\"container\">"
            );

    private static String naviText = "";

    private static String naviEnd = String.join("","",
            "</div>"
            );

    private static String login = String.join("", "",
            "<div class=\"flex-center flex-column\">",
                "<form action=\"/login\" method=\"post\">",
                    "<h3>Login as: </h3>",
                    "<input type=\"submit\" name=\"user\" value=\"sysadmin\"/>",
                    "<input type=\"submit\" name=\"user\" value=\"lva\" />",
                    "<input type=\"submit\" name=\"user\" value=\"lektor\" />",
                    "<input type=\"submit\" name=\"user\" value=\"student\" />",
                "</form>",
            "</div>"
            );


    private static String loginForm = String.join("","",
            "<div class=\"flex-center flex-column\">",
                "<form action=\"/login\" method=\"post\">",
                    "Username: <input type=\"text\" name=\"un\"><br>\n",
                    "Password: <input type=\"password\" name=\"password\"><br>\n",
                    "<div>\n" +
                        "<input type=\"radio\" id=\"sysadmin\" name=\"user\" value=\"sysadmin!\" checked> <label for=\"sysadmin\">Sysadmin</label>\n" +
                        "<input type=\"radio\" id=\"sgo\" name=\"user\" value=\"sgo!\"> <label for=\"sgo\">Studiengangsorg</label>\n" +
                        "<input type=\"radio\" id=\"lektor\" name=\"user\" value=\"lektor!\"> <label for=\"lektor\">Lektor</label>\n" +
                        "<input type=\"radio\" id=\"student\" name=\"user\" value=\"student!\"> <label for=\"student\">Student</label>\n" +
                    "</div>",
                    "<input type=\"submit\" value=\"Login\">",
                "</form>",
            "</div>"
            );

    private static String lvaList = String.join("", "",
            "<div class=\"container\">",
                "<h1>Welcome", htmlConstructor.username, "</h1>",
            "</div>"
    );

    private static String lvaTableBegin =String.join("", "",
            "<div class=\"container\">",
                "<table id=\"LVAs\" class=\"table table-striped table-bordered table-sm\" cellspacing=\"0\" width=\"100%\">",
                    "<thead>",
                        "<tr>",
                            "<th class=\"th-sm\">LVA</th>",
                            "<th class=\"th-sm\">Lektor Name</th>",
                            "<th class=\"th-sm\">Lektor Vorname</th>",
                        "</tr>",
                    "</thead>",
                    "<tbody>"
    );
    private static String lvaTable ="";
    private static String lvaTableEnd = String.join("","",
                    "</tbody>",
                    "<tfoot>",
                        "<tr>",
                            "<th>LVA</th>",
                            "<th>Lektor Name</th>",
                            "<th>Lektor Vorname</th>",
                        "</tr>",
                    "</tfoot>",
                "</table>",
            "</div>"
            );

    private static String resLvaTableBegin =String.join("", "",
            "<div class=\"container\">",
            "<table id=\"LVAs\" class=\"table table-striped table-bordered table-sm\" cellspacing=\"0\" width=\"100%\">",
            "<thead>",
            "<tr>",
            "<th class=\"th-sm\">LVA</th>",
            "<th class=\"th-sm\">Bezeichnung</th>",
            "</tr>",
            "</thead>",
            "<tbody>"
    );
    private static String resLvaTable ="";
    private static String resLvaTableEnd = String.join("","",
            "</tbody>",
            "<tfoot>",
            "<tr>",
            "<th>LVA</th>",
            "<th>Bezeichnung</th>",
            "</tr>",
            "</tfoot>",
            "</table>",
            "</div>"
    );

    private static String sgTableBegin =String.join("", "",
            "<div class=\"container\">",
            "<table id=\"Studiengaenge\" class=\"table table-striped table-bordered table-sm\" cellspacing=\"0\" width=\"100%\">",
                "<thead>",
                    "<tr>",
                        "<th class=\"th-sm\">ID</th>",
                        "<th class=\"th-sm\">Studiengang</th>",
                        "<th class=\"th-sm\">Jahrgang</th>",
                        "<th class=\"th-sm\">Studiengangsleiter</th>",
                    "</tr>",
                "</thead>",
                "<tbody>"
    );
    private static String sgTable ="";
    private static String sgTableEnd = String.join("","",
                "</tbody>",
                    "<tfoot>",
                        "<tr>",
                            "<th>ID</th>",
                            "<th>Studiengang</th>",
                            "<th>Jahrgang</th>",
                            "<th>Studiengangsleiter</th>",
                        "</tr>",
                    "</tfoot>",
                "</table>",
            "</div>"
    );

    private static String addSgForm = String.join("", "",
            "<div class=\"flex-center flex-column\">",
                "<form action=\"/login?addSG\" method=\"post\">",
                    "<input type=\"text\" name=\"sg\" placeholder=\"Studiengang\">\n",
                    "<input type=\"text\" name=\"jg\"placeholder=\"Jahrgang\">\n",
                    "<input type=\"text\" name=\"uid\"placeholder=\"SG-Leiter\">\n",
                    "<input type=\"submit\" value=\"add Sg\">",
                "</form>",
            "</div>",
            "<div class=\"flex-center flex-column\">",
                "<form action=\"/login?delSG\" method=\"post\">",
                    "<input type=\"text\" name=\"id\" placeholder=\"Studiengangs-ID\">\n",
                    "<input type=\"submit\" value=\"delete Sg\">",
                "</form>",
            "</div>"
            );


    private static String sgLvaTableBegin =String.join("", "",
            "<div class=\"container\">",
                "<table id=\"LVAs\" class=\"table table-striped table-bordered table-sm\" cellspacing=\"0\" width=\"100%\">",
                    "<thead>",
                        "<tr>",
                            "<th class=\"th-sm\">ID</th>",
                            "<th class=\"th-sm\">LVA</th>",
                            "<th class=\"th-sm\">Lektor Name</th>",
                            "<th class=\"th-sm\">Lektor Vorname</th>",
                        "</tr>",
                    "</thead>",
                "<tbody>"
    );
    private static String sgLvaTable ="";
    private static String sgLvaTableEnd = String.join("","",
                "</tbody>",
                "<tfoot>",
                    "<tr>",
                        "<th class=\"th-sm\">ID</th>",
                        "<th class=\"th-sm\">LVA</th>",
                        "<th class=\"th-sm\">Lektor Name</th>",
                        "<th class=\"th-sm\">Lektor Vorname</th>",
                    "</tr>",
                "</tfoot>",
            "</table>",
            "</div>"
    );

    private static String dmSgLva = String.join("", "",
            "<div class=\"flex-center flex-column\">",
                "<form action=\"/sg/",htmlConstructor.lvaID,"/",htmlConstructor.lvaBez,"?addLVA\" method=\"post\">",
                    "<input type=\"text\" name=\"lva\" placeholder=\"Lehrveranstaltung\">\n",
                    "<input type=\"text\" name=\"name\"placeholder=\"Lektor-UID\">\n",
                    "<input type=\"text\" name=\"uid\"placeholder=\"Studiengangs-ID\">\n",
                    "<input type=\"submit\" value=\"add LVA\">",
                "</form>",
            "</div>",
            "<div class=\"flex-center flex-column\">",
                "<form action=\"/sg/",htmlConstructor.lvaID,"/",htmlConstructor.lvaBez,"?addLVA\" method=\"post\">",
                    "<input type=\"text\" name=\"id\" placeholder=\"LVA-ID\">\n",
                    "<input type=\"submit\" value=\"delete LVA\">",
                "</form>",
            "</div>"
    );

    private static String lvaUnitTableBegin =String.join("", "",
            "<div class=\"container\">",
            "<table id=\"LVAs\" class=\"table table-striped table-bordered table-sm\" cellspacing=\"0\" width=\"100%\">",
            "<thead>",
            "<tr>",
            "<th class=\"th-sm\">Bezeichnung</th>",
            "<th class=\"th-sm\">Beginn</th>",
            "<th class=\"th-sm\">Ende Name</th>",
            "<th class=\"th-sm\">Lektor Nachname</th>",
            "<th class=\"th-sm\">Lektor Vormame</th>",
            "</tr>",
            "</thead>",
            "<tbody>"
    );
    private static String lvaUnitTable ="";
    private static String lvaUnitTableEnd = String.join("","",
            "</tbody>",
            "<tfoot>",
            "<tr>",
            "<th class=\"th-sm\">Bezeichnung</th>",
            "<th class=\"th-sm\">Beginn</th>",
            "<th class=\"th-sm\">Ende Name</th>",
            "<th class=\"th-sm\">Lektor Nachname</th>",
            "<th class=\"th-sm\">Lektor Vormame</th>",
            "</tr>",
            "</tfoot>",
            "</table>",
            "</div>"
    );

    /**
     * Construct the standard home page
     * @return a joined string that represents the homepage
     */
    public String getHome() {
        return String.join("", begin,home,end);
    }

    /**
     * Expand the temperature table that will be displayed for the temperature plugin html page
     * @param id the primary key of the db entry (not needed)
     * @param temp the temperature
     * @param ts the timestamp
     */
    public void appendTable(int id, double temp, Timestamp ts) {
        String idString = String.valueOf(id);
        String tempString = String.valueOf(temp);
        String tsString = String.valueOf(ts);
        tempContent = String.join("",tempContent,
                "<tr>",
                "<td>",idString,"</td>",
                "<td>",tempString,"</td>",
                "<td>",tsString,"</td>",
                "</tr>"
        );
    }

    /**
     * Append db entries to the xml string that will be shown when a certain day is requested
     * @param id the primary key of the db entry (not needed)
     * @param temp the temperature
     * @param ts the timestamp
     */
    public void appendXML(int id, double temp, Timestamp ts) {
        String idString = String.valueOf(id);
        String tempString = String.valueOf(temp);
        String tsString = String.valueOf(ts);
        String date = tsString.split(" ")[0];
        tempXML = String.join("", tempXML,
                "<date>", date,
                "   <timestamp>", tsString,
                "       <id>", idString, "</id>",
                "       <temperature>", tempString, "</temperature>",
                "   </timestamp>",
                "</date>"
                );
    }

    public void appendLVA(String lva, String name, String vorname) {
        lvaTable = String.join("", lvaTable,
                "<tr>",
                    "<td>",lva,"</td>",
                    "<td>",name,"</td>",
                    "<td>",vorname,"</td>",
                "</tr>"
        );
    }

    public void appendResLVA(String lva, String bezeichnung) {
        resLvaTable = String.join("", resLvaTable,
                "<tr>",
                "<td>",lva,"</td>",
                "<td>",bezeichnung,"</td>",
                "</tr>"
        );
    }

    public void appendSg(String id, String sg, String jg, String sgl) {
        sgTable = String.join("", sgTable,
                "<tr>",
                    "<td><a href=\"/sg/",id,"/",sg,"\">",id,"</a></td>",
                    "<td><a href=\"/sg/",id,"/",sg,"\">",sg,"</a></td>",
                    "<td>",jg,"</td>",
                    "<td>",sgl,"</td>",
                "</tr>"
        );
    }

    public void appendSgLvA(String id,String bez,String nachname,String vorname){
        sgLvaTable = String.join("", sgLvaTable,
                "<tr>",
                "<td><a href=\"/sgo/lva/",bez,"/",id,"/",nachname,"\">",id,"</a></td>",
                "<td><a href=\"/sgo/lva/",bez,"/",id,"/",nachname,"\">",bez,"</a></td>",
                "<td>",nachname,"</td>",
                "<td>",vorname,"</td>",
                "</tr>"
        );
    }

    public void appendLvaUnit(String bez,String begin,String ende, String nachname,String vorname){
        lvaUnitTable = String.join("", lvaUnitTable,
                "<tr>",
                "<td>",bez,"</td>",
                "<td>",begin,"</td>",
                "<td>",ende,"</td>",
                "<td>",nachname,"</td>",
                "<td>",vorname,"</td>",
                "</tr>"
        );
    }

    /**
     * Set the text that will be displayed in the toLower plugin under the text input field
     * @param s the lowercase text
     */
    public void setLowerText(String s) {
        lowerText = String.join("", "<div class=\"container\">",s, "</div>" );
    }

    public void addMap(String address) {
        naviText = String.join("", naviText, address, "<br>");
    }

    /**
     * Create the html page for the temperature plugin
     * @return the html page as string
     */
    public String getTemp() {
        return String.join("",begin,tempBegin,tempContent,tempEnd,end);
    }

    /**
     * Create the xml file as a string - used for getting all timestamps and data of a certain date in the temperature plugin
     * @return the XML for a certain day as a string
     */
    public String getXML() { return String.join("",XMLDeclaration,tempXML,XMLEnd); }

    /**
     * Return the html page for the static file plugin
     * @return the html page for the static file plugin
     */
    public String getStatic() {
        return String.join("",begin,stat,end);
    }

    /**
     * Create the html page for the to Lower plugin
     * reset the input in order to wipe the content if site gets reloaded
     * @return the html page for the to lower plugin as a string
     */
    public String getToLower() {
        String toLowerString = String.join("",begin,toLower,lowerText,end);
        lowerText = "";
        return  toLowerString;
    }

    public String getNavi() {
        String naviString = String.join("",begin,navi,naviText,naviEnd,end);
        naviText = "";
        return  naviString;
    }

    public String getLogin() {
        return String.join("",begin,login,end);
    }

    public String getLoginForm() {
        return String.join("",begin,loginForm,end);
    }

    public String getLvaList() {
        String lvaString = String.join("",beginLog,lvaList,lvaTableBegin,lvaTable,lvaTableEnd,end);
        lvaTable = "";
        return lvaString;
    }

    public String getResLvaList() {
        String lvaString = String.join("",beginLog,lvaList,resLvaTableBegin,resLvaTable,resLvaTableEnd,end);
        resLvaTable = "";
        return lvaString;
    }

    public String getSgList() {
        String lvaString = String.join("",beginLog,lvaList,sgTableBegin,sgTable,sgTableEnd,addSgForm,end);
        sgTable = "";
        return lvaString;
    }

    public String getSgLvaList() {
        System.out.println(htmlConstructor.lvaBez + "  " + htmlConstructor.lvaID);
        dmSgLva = String.join("", "",
                "<div class=\"flex-center flex-column\">",
                "<form action=\"/sg/",htmlConstructor.lvaID,"/",htmlConstructor.lvaBez,"?addLVA\" method=\"post\">",
                "<input type=\"text\" name=\"lva\" placeholder=\"Lehrveranstaltung\">\n",
                "<input type=\"text\" name=\"name\"placeholder=\"Lektor-ID\">\n",
                "<input type=\"submit\" value=\"add LVA\">",
                "</form>",
                "</div>",
                "<div class=\"flex-center flex-column\">",
                "<form action=\"/sg/",htmlConstructor.lvaID,"/",htmlConstructor.lvaBez,"?addLVA\" method=\"post\">",
                "<input type=\"text\" name=\"id\" placeholder=\"LVA-ID\">\n",
                "<input type=\"submit\" value=\"delete LVA\">",
                "</form>",
                "</div>"
        );
        String lvaString = String.join("",beginLog,lvaList,sgLvaTableBegin,sgLvaTable,sgLvaTableEnd,dmSgLva,end);
        sgLvaTable = "";
        return lvaString;
    }

    public String getLvaUnitList() {
        String lvaString = String.join("",beginLog,lvaList,lvaUnitTableBegin,lvaUnitTable,lvaUnitTableEnd,end);
        lvaUnitTable = "";
        return lvaString;
    }
}
