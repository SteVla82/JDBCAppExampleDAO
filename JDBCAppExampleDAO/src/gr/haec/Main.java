package gr.haec;

import java.sql.SQLException;
import java.util.List;

import gr.haec.db.DAOFactory;
import gr.haec.db.dao.Dao;
import gr.haec.db.dao.TermDAO;
import gr.haec.library.Library;
import gr.haec.model.Category;
import gr.haec.model.Post;
import gr.haec.model.Tag;
import gr.haec.model.Term;
import gr.haec.model.User;

public class Main {

	public static void printPosts() {
		Dao<Post> postDao = null;

		try {
			postDao = DAOFactory.getInstance().getPostDao();
		} catch (SQLException e) {
			System.out.println("Could not connect to the database");
			e.printStackTrace();
			return;
		}

		int postsCount = postDao.countAll();
		System.out.println("WP Database contains: " + postsCount + " posts");
		System.out.println("");

		System.out.println("Acquiring all wordpress posts");
		System.out.println("-----------------------------");
		List<Post> allPosts = postDao.getAll();

		if (allPosts != null) {
			for (int i = 0; i < allPosts.size(); i++) {
				Post currentPost = allPosts.get(i);
				System.out.println(currentPost);
				System.out.println("");
			}
		} else {
			System.out.println("Could not retrieve wp posts");
		}

		System.out.println("Retrieving post by id");
		System.out.println("---------------------");
		Post postById = postDao.get(38);

		if (postById != null) {
			System.out.println(postById);
			System.out.println("");
		}

		// User chooses id
		System.out.println("Retrieving post by id. Choose an id : ");
		System.out.println("--------------------------------------");
		int postid = Library.readInt();
		Post postByIdpost = postDao.get(postid);

		if (postByIdpost != null) {
			System.out.println(postByIdpost);
			System.out.println("");
		}
	}

	public static void printUsers() {
		Dao<User> userDao = null;
		try {
			userDao = DAOFactory.getInstance().getUserDao();
		} catch (SQLException e) {
			System.out.println("Could not connect to the database");
			e.printStackTrace();
			return;
		}

		int usersCount = userDao.countAll();
		System.out.println("WP Database contains: " + usersCount + " users");
		System.out.println("");

		System.out.println("Acquiring all wordpress users");
		System.out.println("-----------------------------");
		List<User> allUsers = userDao.getAll();

		if (allUsers != null) {
			for (int i = 0; i < allUsers.size(); i++) {
				User currentUser = allUsers.get(i);
				System.out.println(currentUser);
				System.out.println("");
			}
		} else {
			System.out.println("Could not retrieve wp users");
		}

		System.out.println("Retrieving user by id");
		System.out.println("---------------------");
		User userById = userDao.get(1);

		if (userById != null) {
			System.out.println(userById);
			System.out.println("");

		}
		// User chooses id
		System.out.println("Retrieving user by id. Choose an id : ");
		System.out.println("--------------------------------------");
		int userid = Library.readInt();
		User userByIduser = userDao.get(userid);

		if (userByIduser != null) {
			System.out.println(userByIduser);
			System.out.println("");
		}
	}

	public static void printTerms() {
		Dao<Term> termDao = null;

		try {
			termDao = DAOFactory.getInstance().getTermDao();
		} catch (SQLException e) {
			System.out.println("Could not connect to the database");
			e.printStackTrace();
			return;
		}

		int termsCount = termDao.countAll();
		System.out.println("WP Database contains: " + termsCount + " terms");
		System.out.println("");

		System.out.println("Acquiring all wordpress terms");
		System.out.println("-----------------------------");
		List<Term> allTerms = termDao.getAll();

		if (allTerms != null) {
			for (int i = 0; i < allTerms.size(); i++) {
				Term currentTerm = allTerms.get(i);
				System.out.println(currentTerm);
				System.out.println("");
			}
		} else {
			System.out.println("Could not retrieve wp terms");
		}

		System.out.println("Retrieving term by id");
		System.out.println("---------------------");
		Term termById = termDao.get(6);

		if (termById != null) {
			System.out.println(termById);
			System.out.println("");

		}
		// User chooses id
		System.out.println("Retrieving term by id. Choose an id : ");
		System.out.println("--------------------------------------");
		int termid = Library.readInt();
		Term termByIduser = termDao.get(termid);

		if (termByIduser != null) {
			System.out.println(termByIduser);
			System.out.println("");
		}

		// CRUD Test
		Term term = new Term();
		System.out.println("Inserting data to wp_terms");
		System.out.println("");
		TermDAO.add(term);
		System.out.println("Updating data from wp_terms");
		System.out.println("");
		TermDAO.update(term);
		System.out.println("Deleting data from wp_terms");
		System.out.println("");
		TermDAO.delete(term);
	}

	public static void printCategories() {

		Dao<Category> categoryDao = null;

		try {
			categoryDao = DAOFactory.getInstance().getCategoryDao();
		} catch (SQLException e) {
			System.out.println("Could not connect to the database");
			e.printStackTrace();
			return;
		}

		int categoryCount = categoryDao.countAll();
		System.out.println("WP Database contains: " + categoryCount + " categories");
		System.out.println("");

		System.out.println("Acquiring all wordpress categories");
		System.out.println("----------------------------------");
		List<Category> allCategories = categoryDao.getAll();

		if (allCategories != null) {
			for (int i = 0; i < allCategories.size(); i++) {
				Category currentCategory = allCategories.get(i);
				System.out.println(currentCategory);
				System.out.println("");
			}
		} else {
			System.out.println("Could not retrieve wp categories");
		}

		System.out.println("Retrieving category by id");
		System.out.println("-------------------------");
		Category categoryById = categoryDao.get(4);

		if (categoryById != null) {
			System.out.println(categoryById);
			System.out.println("");

		}
		// User chooses id
		System.out.println("Retrieving category by id. Choose an id : ");
		System.out.println("------------------------------------------");
		int categoryId = Library.readInt();
		Category categoryByIduser = categoryDao.get(categoryId);

		if (categoryByIduser != null) {
			System.out.println(categoryByIduser);
			System.out.println("");
		}
	}

	public static void printTags() {
		Dao<Tag> tagDao = null;

		try {
			tagDao = DAOFactory.getInstance().getTagDao();
		} catch (SQLException e) {
			System.out.println("Could not connect to the database");
			e.printStackTrace();
			return;
		}

		int tagCount = tagDao.countAll();
		System.out.println("WP Database contains: " + tagCount + " tags");
		System.out.println("");

		System.out.println("Acquiring all wordpress tags");
		System.out.println("----------------------------");
		List<Tag> allTags = tagDao.getAll();

		if (allTags != null) {
			for (int i = 0; i < allTags.size(); i++) {
				Tag currentTags = allTags.get(i);
				System.out.println(currentTags);
				System.out.println("");
			}
		} else {
			System.out.println("Could not retrieve wp Tags");
		}

		System.out.println("Retrieving tag by id");
		System.out.println("--------------------");
		Tag tagById = tagDao.get(8);

		if (tagById != null) {
			System.out.println(tagById);
			System.out.println("");

		}
		// User chooses id
		System.out.println("Retrieving tag by id. Choose an id : ");
		System.out.println("-------------------------------------");
		int tagid = Library.readInt();
		Tag tagByIduser = tagDao.get(tagid);

		if (tagByIduser != null) {
			System.out.println(tagByIduser);
			System.out.println("");
		}
	}

	public static void main(String[] args) {

		printPosts();
		printUsers();
		printTerms();
		printCategories();
		printTags();

		try {
			DAOFactory.getInstance().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not close the DAO Factory instance");
			e.printStackTrace();
		}

	}
}
