package gr.haec.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gr.haec.db.BaseDAO;
import gr.haec.model.Category;

public class CategoryDAO extends BaseDAO<Category> implements Dao<Category> {

	private PreparedStatement selectByIdStatement;
	private PreparedStatement selectAllStatement;
	private PreparedStatement countStatement;

	public CategoryDAO(Connection conn) throws SQLException {
		super(conn);
		selectByIdStatement = dbConnection.prepareStatement("SELECT wp_terms.term_id, wp_terms.name, wp_term_taxonomy.taxonomy, wp_term_taxonomy.description FROM wp_terms, wp_term_taxonomy WHERE wp_terms.term_id = ? ;");
		selectAllStatement = dbConnection.prepareStatement("SELECT wp_terms.term_id, wp_terms.name, wp_term_taxonomy.taxonomy, wp_term_taxonomy.description FROM wp_terms, wp_term_taxonomy WHERE taxonomy = 'category' AND wp_terms.term_id = wp_term_taxonomy.term_id;");
		countStatement = dbConnection.prepareStatement("SELECT count(*) FROM wp_term_taxonomy WHERE taxonomy = 'category';");
	}

	@Override
	public Category get(int term_id) {
		Category category = new Category();

		try {
			selectByIdStatement.setInt(1, term_id);
			selectByIdStatement.execute();
			ResultSet resultSet = selectByIdStatement.getResultSet();
			if (resultSet.first()) {
				category.setId(resultSet.getInt("term_id"));
				category.setName(resultSet.getString("name"));
				category.setTaxonomy(resultSet.getString("taxonomy"));
				category.setDescription(resultSet.getString("description"));
			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("Caught SQLException while executing get by id: " + term_id);
			e.printStackTrace();
			return null;
		}

		return category;
	}

	@Override
	public List<Category> getAll() {
		ResultSet resultSet;
		List<Category> objectList = new ArrayList<>();

		try {
			resultSet = selectAllStatement.executeQuery();

			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("term_id"));
				category.setName(resultSet.getString("name"));
				category.setTaxonomy(resultSet.getString("taxonomy"));
				category.setDescription(resultSet.getString("description"));
				objectList.add(category);
			}

			resultSet.close();
		} catch (SQLException e) {
			System.out.println("Caught SQLException while trying to retrieve all WP term_taxonomy");
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
			System.out.println("Caught SQLException while counting WP term_taxonomy");
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