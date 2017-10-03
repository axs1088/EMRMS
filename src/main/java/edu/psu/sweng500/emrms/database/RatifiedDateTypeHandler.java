package edu.psu.sweng500.emrms.database;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;

import edu.psu.sweng500.emrms.util.RatifiedDate;


/** Handle RatifiedDates. */
public class RatifiedDateTypeHandler extends BaseTypeHandler<RatifiedDate> {

	@Override
	public void setParameter(PreparedStatement ps, int i, RatifiedDate parameter, JdbcType jdbcType) throws SQLException {
		if(parameter != null && !parameter.isRatified()) {
			if(jdbcType == null) {
				throw new TypeException(
						"JDBC requires that the JdbcType must be specified for all nullable parameters.");
			}
			try {
				ps.setNull(i, jdbcType.TYPE_CODE);
			} catch(SQLException e) {
				throw new TypeException(
						"Error setting null for parameter #"
								+ i
								+ " with JdbcType "
								+ jdbcType
								+ " . "
								+ "Try setting a different JdbcType for this parameter or a different jdbcTypeForNull configuration property. "
								+ "Cause: " + e, e);
			}
		} else {
			super.setParameter(ps, i, parameter, jdbcType);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, RatifiedDate parameter, JdbcType jdbcType) throws SQLException {
		ps.setDate(i, new Date(parameter.getTime()));
	}

	@Override
	public RatifiedDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
		RatifiedDate result = null;
		Date date = rs.getDate(columnName);
		if(date != null) {
			result = new RatifiedDate(date.getTime());
		}
		return result;
	}

	@Override
	public RatifiedDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		RatifiedDate result = null;
		Date date = rs.getDate(columnIndex);
		if(date != null) {
			result = new RatifiedDate(date.getTime());
		}
		return result;
	}

	@Override
	public RatifiedDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		RatifiedDate result = null;
		Date date = cs.getDate(columnIndex);
		if(date != null) {
			result = new RatifiedDate(date.getTime());
		}
		return result;
	}
}
