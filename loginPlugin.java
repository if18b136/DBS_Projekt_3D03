package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import Database.database;
import oracle.jdbc.OracleTypes;

import java.sql.*;

public class loginPlugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getRawUrl().contains("deploy")) {
                return 0;
            }
            if(req.getUrl().getPath().contains("login")) {
                return (float) 0.9;
            }
            else if(req.getUrl().getPath().contains("sg")) {
                return (float) 0.9;
            }
            else if(req.getUrl().getPath().contains("lva")) {
                return (float) 0.9;
            }
        }
        return 0;
    }

    @Override
    public Response handle(Request req) throws SQLException {
        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();
        if(req.getMethod().equals("POST")) {
            if (req.getUrl().getRawUrl().contains("addSG")) {
                String[] data = req.getContentString().split("&");
                String sg = data[0].split("=")[1];
                String jg = data[1].split("=")[1];
                String uid = data[2].split("=")[1].substring(0, 8);

                System.out.println(htmlConstructor.username);

                addSg(sg, jg, uid);
                getstgCursor();
                String htmlString = html.getSgList();
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);

            }
            else if(req.getUrl().getRawUrl().contains("delSG")) {
                System.out.println(req.getContentString());
                System.out.println(req.getContentLength());
                String id = req.getContentString().substring(3, req.getContentLength());

                delSg(id);
                getstgCursor();

                String htmlString = html.getSgList();
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);
            }
            else if(req.getUrl().getRawUrl().contains("addLVA")) {
                int cL = req.getContentLength();
                System.out.println(cL);
                String[] data = req.getContentString().split("&");
                System.out.println(req.getContentString());
                String lvaName = data[0].split("=")[1];
                //String sgID = data[1].split("=")[1]; nixht mehr gebraucht - lesen wir aus der url aus
                String lektorUID = data[1].split("=")[1].substring(0,1);
                System.out.println(lektorUID + ">");
                // function call to add lva

                String[] dataForm = req.getUrl().getPath().split("/");
                String sg_t = dataForm[1];
                String id = dataForm[2];
                String name = dataForm[3];
                htmlConstructor.lvaID = id;
                htmlConstructor.lvaBez = name;

                int sgId = Integer.parseInt(id);
                int lektorID = Integer.parseInt(lektorUID);
                addLVA(sgId, lvaName, lektorID);

                sgLvaCursor(id);
                String htmlString = html.getSgLvaList();
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);

            }
            else {
                String[] creds = req.getContentString().split("&");
                String username = creds[0].split("=")[1];
                String password = creds[1].split("=")[1];
                String type = creds[2].split("=")[1].split("!")[0];
                int login = 0;
                switch (type.toLowerCase()) {
                    case "sysadmin":
                        login = sysadminLogin(username, password);
                        break;
                    case "sgo":
                        login = sgoLogin(username, password);
                        break;
                    case "lektor":
                        login = lektorLogin(username, password);
                        break;
                    case "student":
                        login = studentLogin(username, password);
                        break;
                    // default case fehlt!
                }
                System.out.println(login);
                if (login == 1) {
                    htmlConstructor.username = username;
                    String htmlString = "";
                    switch (type.toLowerCase()) {
                        case "sysadmin":

                            break;
                        case "sgo":
                            getstgCursor();
                            htmlString = html.getSgList();
                            break;
                        case "lektor":
                            getResLvaCursor(username);
                            htmlString = html.getResLvaList();
                            break;
                        case "student":
                            getLvaCursor(username);
                            htmlString = html.getLvaList();
                            break;

                        // default case fehlt!
                    }
                    res.setStatusCode(200);
                    res.addHeader("Content-Type", "text/html");
                    res.addHeader("Content-length", String.valueOf(htmlString.length()));
                    res.addHeader("connection", "keep-alive");
                    res.setContentType("text/html");
                    res.setContent(htmlString);
                } else {
                    System.out.println("Login unsuccessfull.");
                    String htmlString = html.getLoginForm();
                    res.setStatusCode(200);
                    res.addHeader("Content-Type", "text/html");
                    res.addHeader("Content-length", String.valueOf(htmlString.length()));
                    res.addHeader("connection", "keep-alive");
                    res.setContentType("text/html");
                    res.setContent(htmlString);
                }
            }
        }
        else if(req.getMethod().equals("GET") && !req.getUrl().getRawUrl().contains("deploy")){ // bootstrap upsi
            /*if(req.getUrl().getPath().contains("login")) {
                htmlConstructor.username = "";
                String htmlString = html.getLoginForm();
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);
            }
            else */
            if(req.getUrl().getPath().contains("sg") && !htmlConstructor.username.equals("")) {
                System.out.println(req.getUrl().getPath());
                if(req.getUrl().getRawUrl().contains("addLVA")) {
                    String[] data = req.getContentString().split("&");
                    System.out.println(req.getContentString());
                    String sg = data[0].split("=")[1];
                    String jg = data[1].split("=")[1];
                    String uid = data[2].split("=")[1].substring(0, 8);
                    // function call to add lva
                    int sgID = 1;
                    //addLVA(sg, title, uidLektor);
                }
                // even though we added some new lva we want to look at the lvas now
                String[] data = req.getUrl().getPath().split("/");
                String sg = data[1];
                String id = data[2];
                String name = data[3];
                htmlConstructor.lvaID = id;
                htmlConstructor.lvaBez = name;

                sgLvaCursor(id);
                String htmlString = html.getSgLvaList();
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);

            }
            else if(req.getUrl().getPath().contains("sgo/lva") && !htmlConstructor.username.equals("")) {
                String[] data = req.getUrl().getPath().split("/");
                String lva = data[1];
                String bez = data[2];
                String id = data[3];
                String lektor = data[4];

                lvaUnitsCursor(id);
                String htmlString = html.getLvaUnitList();
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);
            }
            else {
                htmlConstructor.username = "";
                String htmlString = html.getLoginForm();
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString);
            }
        }
        else {
            htmlConstructor.username = "";
            String htmlString = html.getLoginForm();
            res.setStatusCode(200);
            res.addHeader("Content-Type", "text/html");
            res.addHeader("Content-length", String.valueOf(htmlString.length()));
            res.addHeader("connection", "keep-alive");
            res.setContentType("text/html");
            res.setContent(htmlString);
        }

        return res;
    }

    public int sysadminLogin(String username, String password) throws SQLException {
        CallableStatement stmt=database.getInstance().connect().prepareCall("{?= call sp_login_sysadmin(?,?)}");
        stmt.setString(2,username);
        stmt.setString(3,password);
        stmt.registerOutParameter(1,Types.INTEGER);
        stmt.execute();
        return stmt.getInt(1);
    }

    public int sgoLogin(String username, String password) throws SQLException {
        CallableStatement stmt=database.getInstance().connect().prepareCall("{?= call sp_login_sgo(?,?)}");
        stmt.setString(2,username);
        stmt.setString(3,password);
        stmt.registerOutParameter(1,Types.INTEGER);
        stmt.execute();
        return stmt.getInt(1);
    }

    public int lektorLogin(String username, String password) throws SQLException {
        CallableStatement stmt=database.getInstance().connect().prepareCall("{?= call sp_login_lektor(?,?)}");
        stmt.setString(2,username);
        stmt.setString(3,password);
        stmt.registerOutParameter(1,Types.INTEGER);
        stmt.execute();
        return stmt.getInt(1);
    }

    public int studentLogin(String username, String password) throws SQLException {
        CallableStatement stmt=database.getInstance().connect().prepareCall("{?= call sp_login_student(?,?)}");
        stmt.setString(2,username);
        stmt.setString(3,password);
        stmt.registerOutParameter(1,Types.INTEGER);
        stmt.execute();
        return stmt.getInt(1);
    }

    public void getLvaCursor(String username) throws SQLException {
        htmlConstructor html = new htmlConstructor();
        Connection connect = database.getInstance().connect();
        CallableStatement cstmt = connect.prepareCall("{call sp_lva_info(?,?)}");
        cstmt.setString(1, username);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rd = (ResultSet) cstmt.getObject(2);
        while (rd.next()) {
            String lva = rd.getString("titel");
            String bezeichnung = rd.getString("nachname");
            String lektor = rd.getString("vorname");
            html.appendLVA(lva,bezeichnung,lektor);
        }
    }

    public void getResLvaCursor(String username) throws SQLException {
        htmlConstructor html = new htmlConstructor();
        Connection connect = database.getInstance().connect();
        CallableStatement cstmt = connect.prepareCall("{call sp_res_lva(?,?)}");
        cstmt.setString(1, username);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rd = (ResultSet) cstmt.getObject(2);
        while (rd.next()) {
            String lva = rd.getString("titel");
            String bezeichnung = rd.getString("bezeichnung");
            html.appendResLVA(lva,bezeichnung);
        }
    }

    public void getstgCursor() throws SQLException {
        htmlConstructor html = new htmlConstructor();
        Connection connect = database.getInstance().connect();
        CallableStatement cstmt = connect.prepareCall("{call sp_all_stg(?)}");
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rd = (ResultSet) cstmt.getObject(1);
        while (rd.next()) {
            String id = rd.getString("studiengang_id");
            String sg = rd.getString("bezeichnung");
            String jg = rd.getString("jahrgang");
            String sgl = rd.getString("nachname");
            html.appendSg(id,sg,jg,sgl);
        }
    }

    public void addSg(String sg, String jg, String uid) throws SQLException {
        Connection connect = database.getInstance().connect();
        CallableStatement cstmt = connect.prepareCall("{call sp_add_major(?,?,?)}");
        cstmt.setString(1,uid);
        cstmt.setString(2,sg);
        cstmt.setString(3,jg);
        cstmt.execute();
    }

    public void delSg(String id) throws SQLException {
        Connection connect = database.getInstance().connect();
        CallableStatement cstmt = connect.prepareCall("{call sp_delete_major(?)}");
        cstmt.setString(1,id);
        cstmt.execute();
    }

    public void sgLvaCursor(String id) throws SQLException {
        htmlConstructor html = new htmlConstructor();
        Connection connect = database.getInstance().connect();
        CallableStatement cstmt = connect.prepareCall("{call SP_lvas_of_stg(?,?)}");
        cstmt.setString(1,id);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rd = (ResultSet) cstmt.getObject(2);
        while (rd.next()) {
            String lva_id = rd.getString("lva_id");
            String bez = rd.getString("titel");
            String nachname = rd.getString("nachname");
            String vorname = rd.getString("vorname");

            html.appendSgLvA(lva_id,bez,nachname,vorname);
        }
    }
    public void lvaUnitsCursor(String id) throws SQLException {
        htmlConstructor html = new htmlConstructor();
        Connection connect = database.getInstance().connect();
        CallableStatement cstmt = connect.prepareCall("{call sp_units_of_lva(?,?)}");
        cstmt.setString(1,id);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rd = (ResultSet) cstmt.getObject(2);
        while (rd.next()) {
            String bez = rd.getString("bezeichnung");
            String begin = rd.getString("anfang");
            String ende = rd.getString("ende");
            String nachname = rd.getString("nachname");
            String vorname = rd.getString("vorname");

            html.appendLvaUnit(bez,begin,ende,nachname,vorname);
        }
    }

    public void addLVA(int sgID, String title, int lektorID) throws SQLException {
        Connection connect = database.getInstance().connect();
        CallableStatement cstmt = connect.prepareCall("{call sp_add_lecture(?,?,?)}");
        cstmt.setInt(1,sgID);
        cstmt.setString(2,title);
        cstmt.setInt(3,lektorID);
        cstmt.execute();
    }
}
