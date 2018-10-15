import java.sql.PreparedStatement;

/**
 * @author Experis Academy
 * 
 *         / /** create
 */
public class create {

    /**
     * 
     * @param address_line_1 dette er en adressen
     * @param postal_code
     * @param city
     * @param country
     */
    public void insertAddress(String address_line_1, String postal_code, String city, String country, String status) {
        String sql = "INSERT INTO ADDRESS(address_line_1, postal_code, city, country) VALUES(?,?,?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, address_line_1);
            pstmt.setString(2, postal_code);
            pstmt.setString(3, city);
            pstmt.setString(4, country);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param location_id
     * @param address_id
     * @param name
     * @param description
     */
    public void insertLocation(int location_id, int address_id, String name, String description, String status) {
        String sql = "INSERT INTO LOCATION(location_id, address_id, name, description) VALUES(?,?,?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, location_id);
            pstmt.setInt(2, address_id);
            pstmt.setString(3, name);
            pstmt.setString(4, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param person_id
     * @param address_id
     * @param firstname
     * @param lastname
     * @param date_of_birth
     */
    public void insertPerson(int person_id, int address_id, String firstname, String lastname, String date_of_birth) {
        String sql = "INSERT INTO PERSON(person_id, address_id, firstname, lastname, date_of_birth, status) VALUES(?,?,?,?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, person_id);
            pstmt.setInt(2, address_id);
            pstmt.setString(3, firstname);
            pstmt.setString(4, lastname);
            pstmt.setString(5, date_of_birth);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param contact_id
     * @param person_id
     * @param contact_type
     * @param contact_detail
     */
    public void insertContact(int contact_id, int person_id, String contact_type, String contact_detail) {
        String sql = "INSERT INTO CONTACT(contact_id, person_id, contact_type, contact_detail) VALUES(?,?,?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contact_id);
            pstmt.setInt(2, person_id);
            pstmt.setString(3, contact_type);
            pstmt.setString(4, contact_detail);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param association_id
     * @param name
     * @param description
     */
    public void insertAssociation(int association_id, String name, String description) {
        String sql = "INSERT INTO ASSOCIATION(association_id, name, description) VALUES(?,?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, association_id);
            pstmt.setString(2, name);
            pstmt.setString(3, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param team_id
     * @param association_id
     * @param coach_id
     * @param owner_id
     * @param location_id
     */
    public void insertTeam(int team_id, int association_id, int coach_id, int owner_id, int location_id) {
        String sql = "INSERT INTO TEAM(team_id, association_id, coach_id, owner_id, location_id) VALUES(?,?,?,?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, team_id);
            pstmt.setInt(2, association_id);
            pstmt.setInt(3, coach_id);
            pstmt.setInt(4, owner_id);
            pstmt.setInt(5, location_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param season_id
     * @param start_date
     * @param end_date
     * @param name
     * @param description
     */
    public void insertSeason(int season_id, String start_date, String end_date, String name, String description) {
        String sql = "INSERT INTO SEASON(season_id, start_date, end_Date, name, description) VALUES(?,?,?,?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, season_id);
            pstmt.setString(2, start_date);
            pstmt.setString(3, end_Date);
            pstmt.setString(4, name);
            pstmt.setString(5, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param match_id
     * @param match_date
     * @param home_team_id
     * @param away_team_id
     * @param owner_id
     * @param location_id
     */
    public void insertMatch(int match_id, String match_date, int home_team_id, int away_team_id, int owner_id,
            int location_id) {
        String sql = "INSERT INTO MATCH(match_id, match_date, home_team_id, away_team_id, owner_id, location_id) VALUES(?,?,?,?,?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, match_id);
            pstmt.setString(2, match_name);
            pstmt.setInt(3, home_team_id);
            pstmt.setInt(4, away_team_id);
            pstmt.setInt(5, owner_id);
            pstmt.setInt(6, location_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param goal_type_id
     * @param type
     */
    public void insertGoalType(int goal_type_id, String type) {
        String sql = "INSERT INTO GOAL_TYPE(goal_type_id, type) VALUES(?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, goal_type_id);
            pstmt.setString(2, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param goal_id
     * @param player_id
     * @param goal_type_id
     * @param match_id
     * @param description
     */
    public void insertMatchGoal(int goal_id, int player_id, int goal_type_id, int match_id, String description) {
        String sql = "INSERT INTO MATCH_GOAL(goal_id, player_id, goal_type_id, match_id, description) VALUES(?,?,?,?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, goal_id);
            pstmt.setInt(2, player_id);
            pstmt.setInt(3, goal_type_id);
            pstmt.setInt(4, match_id);
            pstmt.setString(5, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param coach_id
     * @param person_id
     */
    public void insertCoach(int coach_id, int person_id) {
        String sql = "INSERT INTO COACH(coach_id, person_id) VALUES(?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, coach_id);
            pstmt.setInt(2, person_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param owner_id
     * @param person_id
     */
    public void insertOwner(int owner_id, int person_id) {
        String sql = "INSERT INTO OWNER(owner_id, person_id) VALUES(?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, owner_id);
            pstmt.setInt(2, person_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param player_id
     * @param person_id
     * @param team_id
     * @param normal_position
     * @param number
     */
    public void insertPlayer(int player_id, int person_id, int team_id, String normal_position, String number) {
        String sql = "INSERT INTO PLAYER(player_id, person_id, team_id, normal_position, number) VALUES(?,?,?,?,?,\"active\")";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, person_id);
            pstmt.setInt(3, team_id);
            pstmt.setString(4, normal_position);
            pstmt.setString(5, number);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param player_id
     * @param match_id
     * @param position
     */
    public void insertMatchPosition(int player_id, int match_id, String position) {
        String sql = "INSERT INTO MATCH_POSITION(player_id, match_id, position) VALUES(?,?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, player_id);
            pstmt.setInt(2, match_id);
            pstmt.setString(3, position);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param match_id
     * @param team_id
     * @param score
     * @param result
     */
    public void insertResult(int match_id, int team_id, String score, String result) {
        String sql = "INSERT INTO RESULT(match_id, team_id, score, result) VALUES(?,?,?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, match_id);
            pstmt.setInt(2, team_id);
            pstmt.setString(3, score);
            pstmt.setString(4, result);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
