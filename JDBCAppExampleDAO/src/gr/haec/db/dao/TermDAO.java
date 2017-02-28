package gr.haec.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gr.haec.db.BaseDAO;
import gr.haec.model.Term;

public class TermDAO extends BaseDAO<Term> {

	private PreparedStatement selectByIdStatement;
	private PreparedStatement selectAllStatement;
	private PreparedStatement countStatement;

	public TermDAO(Connection conn) throws SQLException {
		super(conn);
		selectByIdStatement = dbConnection.prepareStatement("SELECT term_id, name FROM wp_terms WHERE term_id = ?;");
		selectAllStatement = dbConnection.prepareStatement("SELECT term_id, name FROM wp_terms;");
		countStatement = dbConnection.prepareStatement("SELECT count(*) FROM wp_terms;");
	}

	@Override
	public Term get(int term_id) {
		Term term = new Term();

		try {
			selectByIdStatement.setInt(1, term_id);
			selectByIdStatement.execute();
			ResultSet resultSet = selectByIdStatement.getResultSet();
			if (resultSet.first()) {
				term.setTermId(resultSet.getInt("term_id"));
				term.settermName(resultSet.getString("name"));
			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("Caught SQLException while executing get by id: " + term_id);
			e.printStackTrace();
			return null;
		}

		return term;
	}

	@Override
	public List<Term> getAll() {
		ResultSet resultSet;
		List<Term> objectList = new ArrayList<>();

		try {
			resultSet = selectAllStatement.executeQuery();

			while (resultSet.next()) {
				Term term = new Term();
				term.setTermId(resultSet.getInt("term_id"));
				term.settermName(resultSet.getString("name"));
				objectList.add(term);
			}

			resultSet.close();
		} catch (SQLException e) {
			System.out.println("Caught SQLException while trying to retrieve all WP terms");
			e.printStackTrace();
			return null;
		}

		return objectList;
	}

	@Override
	public int countAll() {
		int count = 0;
		try {
			ResultSet resultSet = countStatement.executeQuery();
			if (resultSet.first()) {
				count = resultSet.getInt(1);
			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("Caught SQLException while counting WP terms");
			e.printStackTrace();
			return -1;
		}
		return count;
	}

	@Override
	public void close() {
		try {
			this.selectAllStatement.close();
			this.selectByIdStatement.close();
			this.countStatement.close();
		} catch (SQLException e) {
			System.out.println("Could not close the DAO statements");
			e.printStackTrace();
		}
	}
}