package com.example.semester.DAO;

import com.example.semester.database.DB;
import com.example.semester.utils.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.List;
import java.util.Date;

abstract public class AbstractDAO<T> {
    protected DB database;
    protected Object transfer(ResultSet rs) {
        try {
            ResultSetMetaData rsmt = rs.getMetaData();
            System.out.println("Column name: " + rsmt.getTableName(1));
            String naym = rsmt.getTableName(1);
            String name = Service.getClassName(naym);
            System.out.println("ClassName: com.example.semester.models." + name);
            Class<?> cls = Class.forName("com.example.semester.models." + name);
            Object obj = cls.newInstance();

            for (int i = 0; i < rsmt.getColumnCount(); i++) {
                String columnName = rsmt.getColumnName(i + 1);
                Method m;
                if (columnName.indexOf('_') != -1) {
                    columnName = Service.getAttributeName(columnName);
                }
                System.out.println("Getting method: " + "set" + Service.capitalize(columnName));
                if (columnName.toLowerCase().contains("date")) {
                    System.out.println("In date branch");
                    m = cls.getDeclaredMethod("set" + Service.capitalize(columnName),
                            Date.class);
                }
                else {
                    m = cls.getDeclaredMethod("set" + Service.capitalize(columnName),
                                    Class.forName(rsmt.getColumnClassName(i + 1)));
                }
                m.invoke(obj, rs.getObject(rsmt.getColumnLabel(i + 1)));
            }
            return obj;
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    private String getUpdateQuery(String tableName) {
        try {
            String query = "update " + tableName + " set ";
            Class cls = Class.forName("com.example.semester.models." + Service.getClassName(tableName));
            for (Field field : cls.getDeclaredFields()) {
                System.out.println("Field: " + field.getName());
                if (!field.getName().equals("id")) {
                    query += Service.getColumnName(field.getName()) + " = ?, ";
                }
            }
            query = query.substring(0, query.length() - 2) + " where id = ?";
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(T element) {
        String query = getUpdateQuery(Service.getTableName(element.getClass().getName()));
        System.out.println("Update query: " + query);
        try (PreparedStatement s = this.database.getConnection().prepareStatement(query)) {
            Class cls = Class.forName(element.getClass().getName());
            insertValue(element, cls, query, false);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(T element) {
        String query = "insert into ";
        Class cls = element.getClass();
        try {
            Annotation a = cls.getAnnotations()[0];
            query += a.getClass().getDeclaredMethod("name").invoke(a, (Object[]) null) + " (";
            String valuesToAdd = "values (";
            for (Field field : cls.getDeclaredFields()) {
                if (!field.getName().equals("id")) {
                    query += Service.getColumnName(field.getName()) + ", ";
                    valuesToAdd += "?, ";
                }
            }
            query = query.substring(0, query.length() - 2) + ") " + valuesToAdd.substring(0, valuesToAdd.length() - 2) + ")";
            System.out.println("Query: " + query);
            insertValue(element, cls, query, true);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    private void insertValue(T element, Class<?> cls, String query, boolean ignoreId) {
        try {
            PreparedStatement ps = this.database.getConnection().prepareStatement(query);
            for (int i = 0; i < cls.getDeclaredFields().length; i++) {

                Field field = cls.getDeclaredFields()[i];
                String className = field.getType().getName();
                Method m = cls.getDeclaredMethod("get" + Service.capitalize(field.getName()));

                if (className.toLowerCase().contains("int")) {
                    if (!field.getName().equals("id")) {
                        ps.setInt(i, (Integer) m.invoke(element));
                    }
                    else if (!ignoreId) {
                        ps.setInt(cls.getDeclaredFields().length, (Integer) m.invoke(element));
                    }
                }
                else if (className.contains("String")) {
                    ps.setString(i, (String) m.invoke(element));
                }
                else {
                    ps.setDate(i, new java.sql.Date(((Date) m.invoke(element)).getTime()));
                }
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(T element) {
        String tableName = Service.getTableName(element.getClass().getName());
        String query = "delete from " + tableName + " where id = ?";
        try (PreparedStatement s = this.database.getConnection().prepareStatement(query)) {
            Class cls = Class.forName(element.getClass().getName());
            Method m = cls.getDeclaredMethod("getId");
            s.setInt(1, (int) m.invoke(element));
            s.executeUpdate();
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public T get(int id) {
        return null;
    }

    public abstract List<T> getAll();
}
