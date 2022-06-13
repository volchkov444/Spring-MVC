package ru.volchkov.dao;

import org.springframework.stereotype.Component;
import ru.volchkov.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT;
    private static final String URL = "jdbc:postgresql://localhost:5432/People";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();

        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM Person";
        ResultSet resultSet = statement.executeQuery(SQL);
        while (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
            people.add(person);
        }
        return people;
    }

   /* public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }*/

    public void save(Person person) throws SQLException {
        /*person.setId(++PEOPLE_COUNT);
        people.add(person);*/
        Statement statement= connection.createStatement();
        String SQL="INSERT INTO Person VALUES("+1+",'"+person.getName()+"',"+person.getAge()+",'"+person.getEmail()+"')";
        statement.executeUpdate(SQL);
    }

        /*Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }*/
}
