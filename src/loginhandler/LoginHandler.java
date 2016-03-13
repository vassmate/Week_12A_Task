package loginhandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description = "Handling user logins", urlPatterns = {"/LoginHandler"})

public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// doGet method for making response to user input.
	// It redirects the user to a page depending on searchData method's result.
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String indexPage = new String("/LoginServlet/index.html");
		String profilePage = new String("/LoginServlet/profile.html");
			
		if(searchData(request) == false){
			writer.println(
					"<html>"
					+ "<head>"
					+ "<title>Error</title>"
					+ "</head>"
					+ "<body>"
					+ "<h2 align='center'>The username or password is wrong!</h2>"
					+ "<h3 align='center'>Redirecting to login page...</h3>"
					+ "</body>"
					+ "</html>");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Refresh", "5; URL=" + indexPage);
		}
		else{
			writer.println(
					"<html>"
					+ "<head>"
					+ "<title>Welcome</title>"
					+ "</head>"
					+ "<body>"
					+ "<h2 align='center'>Welcome " + request.getParameter("uname") + " !</h2>"
					+ "<h3 align='center'>"
					+ "<br>Have a nice day!"
					+ "<br>Redirecting to profile page...</h3>"
					+ "</body>"
					+ "</html>");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Refresh", "5; URL=" + profilePage);
		}
		System.out.println("User joined from: " + request.getRemoteAddr());
	}
	
	// doPost method calls doGet method.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// getUserData makes an HttpSession and puts the Attribute names to a HashMap and returns it.
	// In the HashMap the usernames get stored as keys with their corresponding password as values.
	private HashMap<String, String> getUserData(HttpServletRequest request){
		HttpSession loginSession = request.getSession();
		loginSession.setAttribute("user01", "Mate");
		loginSession.setAttribute("pass01", "mate");
		loginSession.setAttribute("user02", "Kyle");
		loginSession.setAttribute("pass02", "Kyle01");
		loginSession.setAttribute("user03", "admin");
		loginSession.setAttribute("pass03", "pass123");
		
		HashMap<String, String> userData = new HashMap<>();
		userData.put("user01", "pass01");
		userData.put("user02", "pass02");
		userData.put("user03", "pass03");
		
		return userData;
	}
	
	// searchData takes the HashMap from getUserData and iterates over it.
	// If it founds a matching Key/Value pair it returns with true.
	private boolean searchData(HttpServletRequest request){
		HttpSession loginSession = request.getSession();
		HashMap<String, String> userData = getUserData(request);
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		boolean foundData = false;
		
		for (Entry<String, String> data: userData.entrySet()) {
			String currentUser = (String) loginSession.getAttribute(data.getKey());
			String currentPass = (String) loginSession.getAttribute(data.getValue());
			
			System.out.println("User: " + currentUser + " Pass: " + currentPass);
			
			if (username.equals(currentUser) && password.equals(currentPass)) {
				foundData = true;
				return foundData;
			}
		}
		return foundData;
	}
}
