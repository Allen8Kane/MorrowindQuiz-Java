package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DbConnect {
    private Connection _connection;
    String exitWord = ".exit";
    boolean open(String dbName){
        try {
            Class.forName("org.sqlite.JDBC");
            _connection = DriverManager.getConnection("jdbc:sqlite:"+dbName+"");
            System.out.println("Connected");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    boolean open(){
        try {
            Class.forName("org.sqlite.JDBC");
            _connection = DriverManager.getConnection("jdbc:sqlite:/home/allen8kane/Документы/Programming/java/MorrowindQuiz-Java/questions.db");
            System.out.println("Connected");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    ArrayList<Question> selectAllQuestions(){
        if(open()) {
            try {
                Statement statement = _connection.createStatement();
                String query = "SELECT * FROM Questions ORDER BY id";
                ResultSet resultSet = statement.executeQuery(query);
                ArrayList<Question> questions = new ArrayList<Question>();
                for (int i = 0; resultSet.next(); i++) {
                    //int id = resultSet.getInt("Id");
                    String question = resultSet.getString("Question");
                    String combat = resultSet.getString("Combat");
                    String magic = resultSet.getString("Magic");
                    String stealth = resultSet.getString("Stealth");
                    questions.add(new Question(question,combat,magic,stealth));
                }
                resultSet.close();
                statement.close();
                return questions;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ArrayList<Question> questions = new ArrayList<Question>();
                return questions;
            }
        }else {
            ArrayList<Question> questions = new ArrayList<Question>();
            return questions;
        }
    }
    ArrayList<PlayerClass> selectAllClasses(){
        if(open()) {
            try {
                Statement statement = _connection.createStatement();
                String query = "SELECT * FROM Classes ORDER BY id";
                ResultSet resultSet = statement.executeQuery(query);
                ArrayList<PlayerClass> playerClasses = new ArrayList<PlayerClass>();
                for (int i = 0; resultSet.next(); i++) {
                    //int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Classes");
                    String combat = resultSet.getString("Combat");
                    String magic = resultSet.getString("Magic");
                    String stealth = resultSet.getString("Stealth");
                    playerClasses.add(new PlayerClass(name, combat, magic, stealth));
                }
                resultSet.close();
                statement.close();
                return playerClasses;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ArrayList<PlayerClass> playerClasses = new ArrayList<PlayerClass>();
                return playerClasses;
            }
        }else {
            ArrayList<PlayerClass> playerClasses = new ArrayList<PlayerClass>();
            return playerClasses;
        }
    }
    void close(){
        try {
            _connection.close();
            System.out.println("Closed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
