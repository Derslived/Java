/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Derslived
 */
public class JDBCTemplate<T> {

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localHost/kaampay";
        String userName = "root";
        String password = "";
        return DriverManager.getConnection(url, userName, password);

    }

    private void addParameters(PreparedStatement stmt, Object[] params) throws SQLException {
        int counter = 1;
        for (Object param : params) {
            stmt.setObject(counter, param);
            counter++;
        }
    }

    public int update(String sql, Object[] params) throws Exception {

        PreparedStatement stmt = getConnection().prepareStatement(sql);
        addParameters(stmt, params);

        int result = stmt.executeUpdate();
        return result;
    }

    public List<T> query(String sql, RowMapper<T> mapper) throws Exception {

        PreparedStatement stmt = getConnection().prepareStatement(sql);

        List<T> rows = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            rows.add(mapper.mapRow(rs));
        }
        return rows;

    }

    public T queryForObject(String sql, Object[] params, RowMapper<T> mapper) throws Exception {

        T row = null;

        PreparedStatement stmt = getConnection().prepareStatement(sql);
        addParameters(stmt, params);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            row = mapper.mapRow(rs);
        }
        return row;

    }

}
