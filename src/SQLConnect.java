import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 *
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 *
 * http://dev.mysql.com/downloads/connector/j/
 *
 * You will see the following exception if it's not in your class path:
 *
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 *
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 *
 * You will get the following exception if the credentials are wrong:
 *
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 *
 * You will instead get the following exception if MySQL isn't installed, isn't
 * running, or if your serverName or portNumber are wrong:
 *
 * java.net.ConnectException: Connection refused
 */
public class SQLConnect {

  private String charName;

  /**
   * The name of the MySQL account to use (or empty for anonymous)
   */
  private String userName;

  /**
   * The password for the MySQL account (or empty for anonymous)
   */
  private String password;

  /**
   * The name of the computer running MySQL
   */
  private final String serverName = "localhost";

  /**
   * The port of the MySQL server (default is 3306)
   */
  private final int portNumber = 3306;

  /**
   * The name of the database we are testing with (this default is installed with MySQL)
   */
  private final String dbName = "starwarsfinal";

  /**
   * Get a new database connection
   */
  public Connection getConnection() throws SQLException {
    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", this.userName);
    connectionProps.put("password", this.password);

    conn = DriverManager.getConnection("jdbc:mysql://"
                    + this.serverName + ":" + this.portNumber + "/" + this.dbName,
            connectionProps);

    return conn;
  }

  /**
   * Obtains username and password from console input
   */
  public void getCredentials() {
    Scanner console = new Scanner(System.in);
    System.out.println("Enter username: ");
    userName = console.nextLine();
    System.out.println("Enter password: ");
    password = console.nextLine();
  }

  /**
   * Stores console input into character name
   * @param connection  connection to the database
   * @return            String with stored console input
   */
  public String getCharacterName(Connection connection) {
    Scanner console = new Scanner(System.in);
    ArrayList<String> charNames = new ArrayList<>();
    charNames = printCharNames(connection);
    charName = console.nextLine();
    while (!isCharValid(charNames, charName)) {
      charNames = printCharNames(connection);
      charName = console.nextLine();
    }
    System.out.println(charName);
    return charName;
  }

  /**
   * Checks if input character is valid anme
   * @param charNames list of all names in the database
   * @param charName  name user entered
   * @return          true if character name valid false otherwise
   */
  public boolean isCharValid(ArrayList<String> charNames, String charName) {
    if (charNames.contains((String) charName)) {
      return true;
    } else {
      System.out.println("Wrong character name! Try again");
      return false;
    }
  }

  /**
   * Print available character names
   * @param connection connection to the database
   * @return           List of character names
   */
  public ArrayList<String> printCharNames(Connection connection) {
    ArrayList<String> charNames = new ArrayList<>();
    try {
      Statement stmt = connection.createStatement(
              ResultSet.TYPE_SCROLL_INSENSITIVE,
              ResultSet.CONCUR_READ_ONLY);
      String sql;
      sql = "SELECT character_name FROM characters";
      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("Choose among following character names: ");
      ResultSetMetaData rsmd = rs.getMetaData();
      rs.first();
      String names = rs.getString("character_name");
      int columnsNumber = rsmd.getColumnCount();
      while (rs.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
          if (i > 1) System.out.print(",  ");
          String columnValue = rs.getString(i);
          charNames.add(columnValue);
          System.out.print(columnValue);
        }
        System.out.println("");

      }
    } catch (SQLException e) {
      System.out.println("ERROR: Could not create the table");
      e.printStackTrace();
    }
    return charNames;

  }

  /**
   * Run a SQL command which does not return a recordset:
   * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
   *
   * @throws SQLException If something goes wrong
   */
  public boolean executeUpdate(Connection conn, String command) throws SQLException {
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
      stmt.executeUpdate(command); // This will throw a SQLException if it fails
      return true;
    } finally {

      // This will run whether we throw an exception or not
      if (stmt != null) { stmt.close(); }
    }
  }

  /**
   * Connect to MySQL and do some stuff.
   */
  public void run() {

    // Connect to MySQL
    Connection conn = null;
    try {
      conn = this.getConnection();
      System.out.println("Connected to database");


    } catch (SQLException e) {
      System.out.println("ERROR: Could not connect to the database");
      e.printStackTrace();
      return;
    }
    String charName = this.getCharacterName(conn);
    try {
      Statement stmt2 = conn.createStatement(
              ResultSet.TYPE_SCROLL_INSENSITIVE,
              ResultSet.CONCUR_READ_ONLY);
      Statement stmt = conn.createStatement(
              ResultSet.TYPE_SCROLL_INSENSITIVE,
              ResultSet.CONCUR_READ_ONLY);
      String update1 =
              ( "drop procedure if exists track_character;");
      String update2 =
              (
                      "CREATE PROCEDURE track_character (IN charactern VARCHAR(45))\n" +
                      "BEGIN\n" +
                      "SELECT c.character_name, t.planet_name, m.title, " +
                      "SUM(t.departure-t.arrival) AS sum_of_scenes\n" +
                      "FROM characters c \n" +
                      "\tJOIN timetable t\n" +
                      "\t\tON c.character_name = t.character_name\n" +
                      "\tJOIN movies m\n" +
                      "\t\tON t.movie_id = m.movie_id\n" +
                      "WHERE c.character_name = charactern\n" +
                      "GROUP BY planet_name, title;\n" +
                      "END;\n");
      String call1 =
              ( "set @character = '" + charName + "';");
      String call2 =
              ( "call track_character(@character);");
      this.executeUpdate(conn, update1);
      this.executeUpdate(conn, update2);
      stmt.executeQuery(call1);
      ResultSet rs2 = stmt2.executeQuery(call2);
      ResultSetMetaData rsmd2 = rs2.getMetaData();
      System.out.println("Called track_character");
      rs2.first();
      int columnsNumber = rsmd2.getColumnCount();
      while (rs2.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
          if (i > 1) System.out.print(",  ");
          String columnValue = rs2.getString(i);
          System.out.print(columnValue);
        }
        System.out.println("");
      }
    } catch (SQLException e) {
      System.out.println("ERROR: Wrong input");
      e.printStackTrace();
      return;
    } finally {
      System.out.println("Closing connection..");
      try { conn.close(); } catch (Exception e) {}
    }
  }

  /**
   * Connect to the DB and do some stuff
   */
  public static void main(String[] args) {
    SQLConnect app = new SQLConnect();
    app.getCredentials();
    app.run();
  }
}