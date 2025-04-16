package backend;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Accounts class manages user sessions within the application, including login, logout,
 * user creation, and session status checks. It interacts with the DataProcessing class
 * to load user information from storage and update it as needed.
 */

public class Accounts {

	private static boolean status;
	private static User user;
	private static ArrayList<User> userArrayList;

	/**
	 * Constructor for the Accounts class. Initializes the user list from storage,
	 * sets the login status to false, and clears the current user.
	 */
	public Accounts() {
		Accounts.userArrayList = DataProcessing.loadUserInfo();
		Accounts.status = false;
		Accounts.user = null;
	}
	/**
	 * Returns the currently logged-in user.
	 * @return The logged-in user, or null if no user is logged in.
	 */
	public static User getUser() {
		return user;
	}

    public static void setUser(User user) {
        Accounts.user = user;
    }
	/**
	 * Checks if a user is currently logged in.
	 * @return true if a user is logged in, false otherwise.
	 */
	public static boolean getStatus() {
		return status;
	}
    public static void setStatus(boolean status) {
        Accounts.status = status;
    }

    public static void setUserArrayList(ArrayList<User> userArrayList) {
        Accounts.userArrayList = userArrayList;
    }
    public static ArrayList<User>  getUserArrayList(ArrayList<User> userArrayList) {
        return Accounts.userArrayList;
    }
	/**
	 * Attempts to log in a user by their name.
	 * @param name The name of the user attempting to log in.
	 * @return true if login is successful, false if the user doesn't exist or someone is already logged in.
	 */
	public static boolean logIn(String name) {
		if (status == false && exist(name) == true) {
			status = true;
			user = find(name);
			return true;
		}
		else return false;
	}
	/**
	 * Logs out the currently logged-in user, if any.
	 * @return true if logout was successful, false if no user was logged in to begin with.
	 */
    public static boolean logOut() {
    	if (status == false) return false;
    	else {
    		save();
    		status = false;
    		user = null;
    		return true;
    	}
    }

    public static void save() {
    	DataProcessing.updateUsers(userArrayList);
    	return;
    }
	/**
	 * Creates a new user and adds them to the list of users if they don't already exist.
	 * @param person The User object to add.
	 * @return true if the user was successfully created, false if a user with the same email already exists.
	 */
    public static boolean create(User person) {
    	if (exist(person.getEmail())) {
    		return false;
    	}
    	else {
    		userArrayList.add(person);
    		return true;
    	}
    }
	/**
	 * Checks if a user exists based on their name.
	 * @param name The name of the user to check for.
	 * @return true if the user exists, false otherwise.
	 */
	public static boolean exist(String name) {
		Iterator<User> it = userArrayList.iterator();
		while (it.hasNext()) {
			if (name.equals(it.next().getEmail())) return true;
		}
		return false;
	}
	/**
	 * Finds a user by their name.
	 * @param name The name of the user to find.
	 * @return The User object if found, null otherwise.
	 */
	public static User find(String name) {
		User person;
		Iterator<User> it = userArrayList.iterator();
		while (it.hasNext()) {
			person = it.next();
			if (name.equals(person.getEmail())) return person;
		}
		return null;
	}


}
