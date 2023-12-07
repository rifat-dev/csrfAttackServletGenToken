package com.rifat.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/accounts")
public class UserPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/");
            return;
        }

        String csrfToken = CsrfTokenGenerator.generateCsrfToken();
        session.setAttribute("csrfToken", csrfToken);

        request.getRequestDispatcher("/userPage.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String expectedCsrfToken = (String) session.getAttribute("csrfToken");
        String actualCsrfToken = request.getParameter("csrfToken");

        if (user != null && expectedCsrfToken != null && actualCsrfToken != null && expectedCsrfToken.equals(actualCsrfToken)) {

            double amount = Double.parseDouble(request.getParameter("amount"));
            int recipientId = Integer.parseInt(request.getParameter("account"));

            User recipient = UserRepository.getUserById(recipientId);

            if (recipient != null && amount > 0 && user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
            }

            String newCsrfToken = CsrfTokenGenerator.generateCsrfToken();
            session.setAttribute("csrfToken", newCsrfToken);

            response.sendRedirect("/accounts");
        } else {
            response.sendRedirect("/");
        }
    }
}
