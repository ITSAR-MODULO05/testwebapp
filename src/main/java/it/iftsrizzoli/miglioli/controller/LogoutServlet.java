package it.iftsrizzoli.miglioli.controller;

import java.io.IOException;

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
 * Servlet implementation class LogoutServlet
 */
@WebServlet(urlPatterns = "/logout", name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(LogoutServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		removeCookies(request, response);
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		log.debug("L'utente {} esce dal sito", user);
		// invalida la sessione se esiste
		session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("user");
			session.getMaxInactiveInterval();
			session.invalidate();
		}
		removeCookies(request, response);
		response.sendRedirect("login.html");
	}
	
	private void removeCookies(HttpServletRequest request,HttpServletResponse response) {
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
