package Repository;
import Exceptions.BusinessLogicException;
import Exceptions.DatabaseException;
import Exceptions.EntityNotFoundException;
import Model.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * A generic repository class for handling database operations for entities.
 * Provides methods to create, read, update, delete, and fetch all entities.
 *
 * @param <T> the type of entity this repository handles.
 */


public class DBRepository<T> implements IRepository<T> {

    private final Class<T> classType;
    private final List<String> fields;
    private String primaryKeyName;

    private String url = "jdbc:mysql://localhost:3306/CourtHouse";
    private String username = "root";
    private String password = "Project12!";

    /**
     * Constructs a new DBRepository for the specified class type.
     * Initializes the fields and primary key for the entity.
     *
     * @param classType the class type of the entity this repository manages.
     */

    public DBRepository(Class<T> classType) {
        this.classType = classType;
        List<String> list = new ArrayList<>();
        for (Method i : classType.getMethods()) {
            if (i.getName().startsWith("set")) {
                String name = i.getName();
                list.add(name.substring(3));
            }
        }
        fields = list;
        primaryKeyName = null;
        for (Method i : classType.getMethods()) {
            if (i.getName().endsWith("ID")) {
                String name = i.getName();
                primaryKeyName = name.substring(3);
            }
        }
    }

    /**
     * Inserts a new entity into the database.
     *
     * @param entity the entity to insert.
     * @throws DatabaseException if a database error occurs during the operation.
     */

    public void create(T entity) {
        try (Connection connection = getConnection()){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO ");
            if (getTableName().equals("Case")) {
                stringBuilder.append("`").append(getTableName()).append("`");
            } else {
                stringBuilder.append(getTableName());
            }
            stringBuilder.append("(");
            for (String columnName: fields) {
                stringBuilder.append(columnName);
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append(") VALUES (");
            for (int i=0; i< fields.size(); i++){
                stringBuilder.append("?,");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append(")");

            PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());

            populateStatement(statement, entity);
            statement.execute();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to create entity", e);
        }
    }

    /**
     * Reads an entity from the database by its ID.
     *
     * @param id the primary key of the entity to fetch.
     * @return the entity found in the database.
     * @throws DatabaseException if a database error occurs during the operation.
     * @throws EntityNotFoundException if no entity is found with the specified ID.
     */

    public T read(String id) {

        try (Connection connection = getConnection()) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT ");
            for (String columnName: fields) {
                stringBuilder.append(columnName);
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append(" FROM ");
            if (getTableName().equals("Case")) {
                stringBuilder.append("`").append(getTableName()).append("`");
            } else {
                stringBuilder.append(getTableName());
            }
            stringBuilder.append(" WHERE ");
            stringBuilder.append(primaryKeyName);
            stringBuilder.append(" = ?");

            PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T result = getNewClassInstance();
                for (String field : fields) {
                    Method m = classType.getMethod("set"+field, String.class);
                    m.invoke(result,resultSet.getString(field));
                }
                return result;
            }
            throw new EntityNotFoundException("Could not find entity with id " + id);

        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new DatabaseException("Could not read entity.",e);
        }
    }

    /**
     * Deletes an entity from the database by its ID.
     *
     * @param id the primary key of the entity to delete.
     * @throws DatabaseException if a database error occurs during the operation.
     */

    public void delete(String id) {
        try (Connection connection = getConnection()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DELETE FROM ");
            if (getTableName().equals("Case")) {
                stringBuilder.append("`").append(getTableName()).append("`");
            } else {
                stringBuilder.append(getTableName());
            }
            stringBuilder.append(" WHERE ");
            stringBuilder.append(primaryKeyName);
            stringBuilder.append(" = ?");

            PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
            statement.setString(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all entities of the specified type from the database.
     *
     * @return a list of all entities.
     * @throws DatabaseException if a database error occurs during the operation.
     */


    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        try (Connection connection = getConnection()) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT ");
            for (String columnName: fields) {
                stringBuilder.append(columnName);
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append(" FROM ");
            if (getTableName().equals("Case")) {
                stringBuilder.append("`").append(getTableName()).append("`");
            } else {
                stringBuilder.append(getTableName());
            }

            PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T mappedEntity = getNewClassInstance();
                for (String field : fields) {
                    Method m = classType.getMethod("set"+field, String.class);
                    m.invoke(mappedEntity, resultSet.getString(field));
                }
                result.add(mappedEntity);
            }
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new DatabaseException("Failed to fetch data.",e);
        }
        return result;
    };

    /**
     * Updates an existing entity in the database.
     *
     * @param entity the entity with updated information.
     * @throws DatabaseException if a database error occurs during the operation.
     */


    public void update(T entity) {
        try (Connection connection = getConnection()){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE  ");
            if (getTableName().equals("Case")) {
                stringBuilder.append("`").append(getTableName()).append("`");
            } else {
                stringBuilder.append(getTableName());
            }
            stringBuilder.append(" SET ");
            for (String columnName: fields) {
                stringBuilder.append(columnName);
                stringBuilder.append("=");
                Method getter = classType.getMethod("get"+columnName);
                stringBuilder.append("'")
                        .append(getter.invoke(entity))
                        .append("',");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append(" WHERE ");
            stringBuilder.append(primaryKeyName);
            stringBuilder.append(" = ?");

            PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
            Method getter = classType.getMethod("get"+primaryKeyName);
            statement.setString(1, (String) getter.invoke(entity));
            statement.execute();


        } catch (SQLException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new DatabaseException("Failed to update entity", e);
        }
    };

    /**
     * Determines the table name based on the class type of the entity.
     *
     * @return the table name as a string.
     * @throws RuntimeException if the class type is not supported.
     */

    private String getTableName() {
        if (classType.equals(Client.class)){
            return "Client";
        } else if (classType.equals(Judge.class)) {
            return "Judge";
        } else if (classType.equals(Lawyer.class)) {
            return "Lawyer";
        } else if (classType.equals(Case.class)) {
            return "Case";
        } else if (classType.equals(LawyerAssignment.class)) {
            return "LawyerAssignment";
        }
        throw new RuntimeException("Class " + classType + " is not supported.");
    }

    /**
     * Populates a prepared statement with values from the entity.
     *
     * @param statement the prepared statement to populate.
     * @param entity the entity to extract values from.
     */

    private void populateStatement(PreparedStatement statement, T entity) {
        for (int i = 0; i < fields.size(); i++) {

            try {
                Method getter = classType.getMethod("get"+fields.get(i));
                String value = (String) getter.invoke(entity);
                statement.setString(i + 1, value);
            } catch (Exception e){
                System.out.println("Failed to perpare statement.");
                e.printStackTrace();
            };
        }
    }

    /**
     * Creates a new instance of the entity's class type.
     *
     * @return a new instance of the entity.
     * @throws RuntimeException if an error occurs during instantiation.
     */

    private T getNewClassInstance() {
        try {
            return classType.getConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Establishes a connection to the database.
     *
     * @return a database connection.
     * @throws SQLException if an error occurs during connection.
     */

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
