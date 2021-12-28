//Database Systems (Module IDS) 

import java.sql.*;


// The DatabaseHelper class encapsulates the communication with our database
class DatabaseHelper {
    // Database connection info
    private static final String DB_CONNECTION_URL = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
    private static final String USER = "a11832991"; //TODO: use a + matriculation number
    private static final String PASS = "dbs21"; //TODO: use your password (default: dbs19)

    // The name of the class loaded from the ojdbc14.jar driver file
    //private static final String CLASSNAME = "oracle.jdbc.driver.OracleDriver";

    // We need only one Connection and one Statement during the execution => class variable
    private static Statement stmt;
    private static Connection con;

    
    //CREATE CONNECTION
    DatabaseHelper() {
        try {
            //Loads the class into the memory
            //Class.forName(CLASSNAME);

            // establish connection to database
            con = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASS);
            stmt = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //////INSERT STATEMENTS//////


    //INSERT INTO Person
    void insertIntoPerson(int social_insurance_ID, String name, String date) {
        try {
            String sql = "INSERT INTO PERSON VALUES (" + social_insurance_ID + ", '" +
                    name +
                    "', " +
                    "to_date('" + date + "' ,'DD/MM/YYYY')" +
                    ")";
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Error at: insertIntoPerson\nmessage: " + e.getMessage());
        }
    }


    //Insert Into Team
    void insertIntoTeam(String name, int age_limit) {
        try {
            String sql = "INSERT INTO TEAM VALUES ( ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setNull(1, Types.NULL); //for the Trigger on Null
            pstmt.setString(2, name);
            pstmt.setInt(3, age_limit);
            pstmt.execute();
        } catch (Exception e) {
            System.err.println("Error at: insertIntoTeam\nmessage: " + e.getMessage());
        }
    }


    //INSERT INTO D_DATE
    void insertIntoDate(String date) {
        try {
            String sql = "INSERT INTO D_DATE VALUES (to_date('"
                    + date  +
                    "', 'DD/MM/YYYY'))";
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Error at: insertIntoDate\nmessage: " + e.getMessage());
        }
    }


    //INSERT INTO SPORT_FACILITY
    void insertIntoSportFacility(String name, String street, String city, int postal_code) {
        try {
            String sql = "INSERT INTO SPORT_FACILITY VALUES ('" + name + "', '" +
                    street +
                    "', '" +
                    city + "', " +
                    postal_code +
                    ")";
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Error at: insertIntoSportFacility\nmessage: " + e.getMessage());
        }
    }


    //INSERT INTO F_FIELD
    void insertIntoField(String sf_name, String sport_type, String ground) {
        try {
            String sql = "INSERT INTO F_FIELD (F_SF_NAME, SPORT_TYPE, GROUND) VALUES ('" +
                    sf_name +
                    "', '" +
                    sport_type + "', '" +
                    ground +
                    "')";
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Error at: insertIntoField\nmessage: " + e.getMessage());
        }
    }


    //INSERT INTO COACH
    void insertIntoCoach(int social_insurance_ID, int salary, int team_ID, String coachingstyle) {
        try {
            String sql;
            if(coachingstyle.isEmpty()) {
                sql = "INSERT INTO COACH (C_SOCIAL_INSURANCE_ID, SALARY, C_TEAM_ID) " +
                        "VALUES (" + social_insurance_ID + ", " +
                        salary +
                        ", " + team_ID + ")";
            }
            else{
                sql = "INSERT INTO COACH (C_SOCIAL_INSURANCE_ID, SALARY, C_TEAM_ID, COACHINGSTYLE) " +
                        "VALUES (" + social_insurance_ID + ", " +
                        salary  +
                        ", " + team_ID +
                        ", '" + coachingstyle + "')";
            }
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Error at: insertIntoCoach\nmessage: " + e.getMessage());
        }
    }


    //INSERT INTO PLAYER
    void insertIntoPlayer(int social_insurance_ID, String position, int salary, int team_ID) {
        try {
            String sql = "INSERT INTO PLAYER (P_SOCIAL_INSURANCE_ID, P_POSITION, SALARY, P_TEAM_ID) VALUES (" +
                    social_insurance_ID +
                    ", '" +
                    position + "', " +
                    salary + ", " +
                    team_ID + ")";
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Error at: insertIntoPlayer\nmessage: " + e.getMessage());
        }
    }


    //INSERT INTO MANAGER
    void insertIntoManager(int social_insurance_ID, int player_ID, int salary) {
        try {
            String sql = "INSERT INTO P_MANAGER (M_SOCIAL_INSURANCE_ID, M_PLAYER_ID, SALARY) VALUES (" +
                    social_insurance_ID + ", " +
                    player_ID + ", " +
                    salary  + ")";
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Error at: insertIntoManager\nmessage: " + e.getMessage());
        }
    }


    //INSERT INTO IS_FRIEND
    void insertIntoIsFriend(int player_ID1, int player_ID2) {
        try {
            String sql = "INSERT INTO IS_FRIEND VALUES (" +
                    player_ID1 + ", " +
                    player_ID2 + ")";
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Error at: insertIntoIsFriend\nmessage: " + e.getMessage());
        }
    }


    //INSERT INTO PLAYS_IN
    void insertIntoPlaysIn(int team_ID, String date, String sf_name) {
        try {
            String sql = "INSERT INTO PLAYS_IN VALUES (" +
                    team_ID + ", to_date('" +
                    date + "', 'DD/MM/YYYY'), '" +
                    sf_name + "')";
            stmt.execute(sql);
        } catch (Exception e) {
            System.err.println("Error at: insertIntoPlaysIn\nmessage: " + e.getMessage());
        }
    }

    //////SELECT STATEMENTS//////

    //SELECT Person
    int selectcountFromPerson() {
        int num_of_persons = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM PERSON");
            if(rs.next()){
                num_of_persons = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromPerson\n message: " + e.getMessage()).trim());
        }
        return num_of_persons;
    }


    //SELECT Team
    int selectcountFromTeam() {
        int num_of_team = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM TEAM");
            if(rs.next()){
                num_of_team = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromTeam\n message: " + e.getMessage()).trim());
        }
        return num_of_team;
    }


    //SELECT Date
    int selectcountFromDate() {
        int num_of_date = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM D_DATE");
            if(rs.next()){
                num_of_date = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromDate\n message: " + e.getMessage()).trim());
        }
        return num_of_date;
    }


    //SELECT Sport_facility
    int selectcountFromSportFacility() {
        int num_of_sf = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM SPORT_FACILITY");
            if(rs.next()){
                num_of_sf = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromDate\n message: " + e.getMessage()).trim());
        }
        return num_of_sf;
    }


    //SELECT FIELD
    int selectcountFromField() {
        int num_of_fields = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM F_FIELD");
            if(rs.next()){
                num_of_fields = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromField\n message: " + e.getMessage()).trim());
        }
        return num_of_fields;
    }


    //SELECT Coach
    int selectcountFromCoach() {
        int num_of_coaches = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM COACH");
            if(rs.next()){
                num_of_coaches = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromCoaches\n message: " + e.getMessage()).trim());
        }
        return num_of_coaches;
    }


    //SELECT PLAYER
    int selectcountFromPlayer() {
        int num_of_players = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM PLAYER");
            if(rs.next()){
                num_of_players = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromPlayer\n message: " + e.getMessage()).trim());
        }
        return num_of_players;
    }


    //SELECT MANAGER
    int selectcountFromManager() {
        int num_of_managers = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM P_MANAGER");
            if(rs.next()){
                num_of_managers = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromManager\n message: " + e.getMessage()).trim());
        }
        return num_of_managers;
    }


    //SELECT IS_FRIEND
    int selectcountFromIsFriend() {
        int num_of_frienships = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM IS_FRIEND");
            if(rs.next()){
                num_of_frienships = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromIsFriend\n message: " + e.getMessage()).trim());
        }
        return num_of_frienships;
    }


    //SELECT PLAYS_IN
    int selectcountFromPlaysIn() {
        int num_of_plays_in = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM PLAYS_IN");
            if(rs.next()){
                num_of_plays_in = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(("Error at: selectcountFromPlaysIn\n message: " + e.getMessage()).trim());
        }
        return num_of_plays_in;
    }


    public void close()  {
        try {
            stmt.close(); //clean up
            con.close();
        } catch (Exception ignored) {
        }
    }
}