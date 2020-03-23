package it.iftsrizzoli.miglioli.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String userID = "admin";
	private static final String password = "password";
	private static Logger log = LoggerFactory.getLogger(LoginServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		log.debug("Tentativo di login da {} {} ", user, pwd);
		if (userID.equals(user) && password.equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(30 * 60);
			log.debug("Utente {} loggato con successo.", user);

			response.sendRedirect("LoginSuccess.jsp");
		} else {
			// RequestDispatcher
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			// PrintWriter
			PrintWriter out = response.getWriter();

			log.debug("l'utente {} ha inserito credenziali errate.", user);

			out.println("<font color=red>Username o password errati.</font>");
			// in questo caso il controllo torna alla pagina "login.html"
			rd.include(request, response);
		}
	}

	private void removeCookies(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Rimuovo tutti i cookies");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setValue("");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}
}
