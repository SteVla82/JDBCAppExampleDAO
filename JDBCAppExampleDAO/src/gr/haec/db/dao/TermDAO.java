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
	private static PreparedStatement addStatement;
	private static PreparedStatement updateStatement;
	private static PreparedStatement deleteStatement;

	public TermDAO(Connection conn) throws SQLException {
		super(conn);
		selectByIdStatement = dbConnection.prepareStatement("SELECT term_id, name, slug, is_deleted FROM wp_terms WHERE term_id = ?;");
		selectAllStatement = dbConnection.prepareStatement("SELECT term_id, name, slug, is_deleted FROM wp_terms;");
		countStatement = dbConnection.prepareStatement("SELECT count(*) FROM wp_terms;");
		addStatement = dbConnection.prepareStatement("INSERT INTO wp_terms (name, slug) VALUES ('Course', 'course');");
		updateStatement = dbConnection.prepareStatement("UPDATE wp_terms SET name = 'Course_name', slug = 'course_name' WHERE name = 'Course';");
		deleteStatement = dbConnection.prepareStatement("UPDATE wp_terms SET is_deleted = 1 WHERE name = 'Course_name';");
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
	
	public static Term add() {
    	Term term = new Term();
    	
    	try {
    		addStatement.executeQuery();
    		term.setTermName(term.getTermName());
    		term.setTermSlug(term.getTermSlug());
    		} catch (SQLException e) {
    			System.out.println("Caught SQLException while inserting data to table wp_terms");
    			e.printStackTrace();
    			return null;
    		}
    	return term;
	}
	
	public static Term update() {
		Term term = new Term();
				
		try {
			updateStatement.executeQuery();
			term.setTermName(term.getTermName());
			term.setTermSlug(term.getTermSlug());
		} catch (SQLException e) {
			System.out.println("Caught SQLException while updating data to table wp_terms");
			e.printStackTrace();
			return null;
	    }
		return term;
	}
	
	public static Term delete() {
		Term term = new Term();
				
		try {
			deleteStatement.executeQuery();
			term.setIsDeleted(term.getIsDeleted());
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
			TermDAO.deleteStatement.close();
		} catch (SQLException e) {
			System.out.println("Could not close the DAO statements");
			e.printStackTrace();
		}
	}
}