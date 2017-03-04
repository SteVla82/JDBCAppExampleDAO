package gr.haec;

import java.sql.SQLException;
import java.util.List;

import gr.haec.db.DAOFactory;
import gr.haec.db.dao.Dao;
import gr.haec.library.Library;
import gr.haec.model.Category;
import gr.haec.model.Post;
import gr.haec.model.Tag;
import gr.haec.model.Term;
import gr.haec.model.User;
import gr.haec.db.dao.CategoryDAO;

public class Main {

	public static void main(String[] args) {
		Dao<Post> postDao = null;
		Dao<User> userDao = null;
		Dao<Term> termDao = null;
		Dao<Category> categoryDao = null;
		Dao<Tag> tagDao = null;

		try {
			postDao = DAOFactory.getInstance().getPostDao();
			userDao = DAOFactory.getInstance().getUserDao();
			termDao = DAOFactory.getInstance().getTermDao();
			categoryDao = DAOFactory.getInstance().getCategoryDao();
			tagDao = DAOFactory.getInstance().getTagDao();
		} 
		catch (SQLException e) {
			System.out.println("Could not connect to the database");
			e.printStackTrace();
			return;
		}
		
		System.out.println("Inserting data to wp_terms");
		System.out.println("");
		CategoryDAO.add();
		

		int postsCount = postDao.countAll();
		System.out.println("WP Database contains: " + postsCount + " posts");
		System.out.println("");
		int usersCount = userDao.countAll();
		System.out.println("WP Database contains: " + usersCount + " users");
		System.out.println("");
		int termsCount = termDao.countAll();
		System.out.println("WP Database contains: " + termsCount + " terms");
		System.out.println("");
		int categoriesCount = categoryDao.countAll();
		System.out.println("WP Database contains : " + categoriesCount + " categories");
		System.out.println("");
		int tagsCount = tagDao.countAll();
		System.out.println("WP Database contains: " + tagsCount + " tags");
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
		
		System.out.println("Acquiring all wordpress categories with description");
		System.out.println("---------------------------------------------------");
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
		
		System.out.println("Acquiring all wordpress tags with description");
		System.out.println("---------------------------------------------");
		List<Tag> allTags = tagDao.getAll();

		if (allTags != null) {
			for (int i = 0; i < allTags.size(); i++) {
				Tag currentTag = allTags.get(i);
				System.out.println(currentTag);
				System.out.println("");
			}
		} else {
			System.out.println("Could not retrieve wp tags");
		}

		System.out.println("Retrieving post by id");
		System.out.println("---------------------");
		Post postById = postDao.get(38);

		if (postById != null) {
			System.out.println(postById);
			System.out.println("");

			// User chooses id
			System.out.println("Retrieving post by id. Choose an id : ");
			System.out.println("--------------------------------------");
			int postid = Library.readInt();
			Post postByIdpost = postDao.get(postid);

				if (postByIdpost != null) {
					System.out.println(postByIdpost);
					System.out.println("");
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

			try {
				DAOFactory.getInstance().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not close the DAO Factory instance");
				e.printStackTrace();
			}
		}
	}
}