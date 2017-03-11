package gr.haec.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gr.haec.db.BaseDAO;
import gr.haec.model.Term;
import gr.haec.library.Library;

public class TermDAO extends BaseDAO<Term> {

	private PreparedStatement selectByIdStatement;
	private PreparedStatement selectAllStatement;
	private PreparedStatement countStatement;
	private static PreparedStatement addStatement;
	private static PreparedStatement updateStatement;
	private static PreparedStatement deleteStatement;

	public TermDAO(Connection conn) throws SQLException {
		super(conn);
		selectByIdStatement = dbConnection
				.prepareStatement("SELECT term_id, name, slug, is_deleted FROM wp_terms WHERE term_id = ?;");
		selectAllStatement = dbConnection.prepareStatement("SELECT term_id, name, slug, is_deleted FROM wp_terms;");
		countStatement = dbConnection.prepareStatement("SELECT count(*) FROM wp_terms;");
		addStatement = dbConnection
				.prepareStatement("INSERT INTO wp_terms (term_id, name, slug, is_deleted) VALUES (null, ? , ?, ?);");
		updateStatement = dbConnection.prepareStatement(
				"UPDATE wp_terms SET name = ?, slug = ?, is_deleted = ? WHERE term_id = ? AND name = ?;");
		deleteStatement = dbConnection.prepareStatement("UPDATE wp_terms SET is_deleted = 1 WHERE name = ?;");
	}

	@Override
	public Term get(int term_id) {
		Term term = new Term();

		try {
			selectByIdStatement.setInt(1, term_id);
			selectByIdStatement.executeQuery();
			ResultSet resultSet = selectByIdStatement.getResultSet();
			if (resultSet.first()) {
				term.setTermId(resultSet.getInt("term_id"));
				term.setTermName(resultSet.getString("name"));
				term.setTermSlug(resultSet.getString("slug"));
				term.setIsDeleted(resultSet.getInt("is_deleted"));
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
				term.setTermName(resultSet.getString("name"));
				term.setTermSlug(resultSet.getString("slug"));
				term.setIsDeleted(resultSet.getInt("is_deleted"));
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

	public static Term add(Term term) {

		try {
			System.out.println("Do you want to add a new term? Type yes or no");
			if (Library.readString().equals("yes")) {
				System.out.println("Insert new Term name");
				addStatement.setString(1, Library.readString());
				System.out.println("Insert new Term slug");
				addStatement.setString(2, Library.readString());
				System.out.println("Do you want this term deleted? Type 0 for no or 1 for yes");
				addStatement.setInt(3, Library.readInt());
				addStatement.executeUpdate();
				addStatement.close();
			} else {
				System.out.println("You didn't insert new data");
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println("Caught SQLException while inserting data to table wp_terms");
			e.printStackTrace();
			return null;
		}
		return term;
	}

	public static Term update(Term term) {

		try {
			System.out.println("Do you want to update a term? Type yes or no");
			if (Library.readString().equals("yes")) {
				System.out.println("Update Term name");
				updateStatement.setString(1, Library.readString());
				System.out.println("Update Term slug");
				updateStatement.setString(2, Library.readString());
				System.out.println("Do you want this term deleted? Type 0 for no or 1 for yes");
				updateStatement.setInt(3, Library.readInt());
				System.out.println("Which id does this term have that you want to update?");
				updateStatement.setString(4, Library.readString());
				System.out.println("What name does this term have that you want to update?");
				updateStatement.setString(5, Library.readString());
				updateStatement.executeUpdate();
			} else {
				System.out.println("You didn't update any data");
				System.out.println("");
			}

		} catch (SQLException e) {
			System.out.println("Caught SQLException while updating data to table wp_terms");
			e.printStackTrace();
			return null;
		}
		return term;
	}

	public static Term delete(Term term) {

		try {
			System.out.println("Do you want to delete a term? Type yes or no");
			if (Library.readString().equals("yes")) {
				System.out.println("Which Term do you want to delete?");
				deleteStatement.setString(1, Library.readString());
				deleteStatement.executeUpdate();
			} else {
				System.out.println("You didn't delete any data");
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println("Caught SQLException while deleting data to table wp_terms");
			e.printStackTrace();
			return null;
		}
		return term;
	}

	@Override
	public void close() {
		try {
			this.selectAllStatement.close();
			this.selectByIdStatement.close();
			this.countStatement.close();
			TermDAO.addStatement.close();
			TermDAO.updateStatement.close();
			TermDAO.deleteStatement.close();
		} catch (SQLException e) {
			System.out.println("Could not close the DAO statements");
			e.printStackTrace();
		}
	}
}