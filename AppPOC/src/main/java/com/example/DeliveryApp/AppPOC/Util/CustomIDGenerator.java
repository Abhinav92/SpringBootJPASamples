package com.example.DeliveryApp.AppPOC.Util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

public class CustomIDGenerator {
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		String prefix = "REP";
		Connection connection = session.connection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select count(ID) as Id from RELEASE_PARAMETERS");
			if (rs.next()) {
				int id = rs.getInt(1) + 101;
				String generatedId = prefix + new Integer(id).toString();
				return generatedId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
