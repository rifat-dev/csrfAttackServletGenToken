package com.rifat.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String csrfToken = (String) session.getAttribute("csrfToken");

        if (csrfToken == null) {
            csrfToken = CsrfTokenGenerator.generateCsrfToken();
            session.setAttribute("csrfToken", csrfToken);
            System.out.println("doGet / " + csrfToken);
        }

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String expectedCsrfToken = (String) request.getSession().getAttribute("csrfToken");
        String actualCsrfToken = request.getParameter("csrfToken");
        System.out.println("expectedCsrfToken = " + expectedCsrfToken + "; " + "actualCsrfToken = " + actualCsrfToken);
        
        if (expectedCsrfToken != null && actualCsrfToken != null && expectedCsrfToken.equals(actualCsrfToken)) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = UserRepository.getUserByUserNameAndPassword(username, password);

            String newCsrfToken = CsrfTokenGenerator.generateCsrfToken();
            request.getSession().setAttribute("csrfToken", newCsrfToken);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("/accounts");
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect("/");
        }
    }
}
