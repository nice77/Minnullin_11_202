package com.example.task.DAO;

import com.example.task.DB;
import com.example.task.models.Vote;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VoteDAO {
    public static List<Vote> getVotes() {
        DB database = DB.getInstance();
        try (Statement stmt = database.getConn().createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from votes");
            List<Vote> voteList = new ArrayList<>();
            while (rs.next()) {
                voteList.add(new Vote(rs.getString("login")));
            }
            return voteList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addVote(Vote vote) {
        DB database = DB.getInstance();
        try (PreparedStatement stmt = database.getConn().prepareStatement("insert into votes (login) values (?)")) {
            stmt.setString(1, vote.getLogin());
            System.out.println("Statement: " + stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
