package controller.user;

import model.Basket;
import model.ConfirmationCode;
import model.User;
import service.mail.MailService;
import service.mail.MailServiceImpl;
import utils.ConfirmCodeGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user/sendCode")
public class SendCodeToEmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        Basket basketFromSession = (Basket) req.getSession().getAttribute("basket");
        Long id = userFromSession.getId();
        String userEmail = userFromSession.getEmail();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String address = req.getParameter("address");
        req.setAttribute("userID", id);
        req.setAttribute("userEmail", userEmail);
        req.setAttribute("name", name);
        req.setAttribute("surname", surname);
        req.setAttribute("address", address);
        req.setAttribute("totalPrice", basketFromSession.getTotalPrice());
        if (basketFromSession.getConfirmationCode() != null) {
            req.setAttribute("error",
                    "Confirmation code has already sent to your email. Check it please!");
            req.getRequestDispatcher("/add_order.jsp").forward(req, resp);
            resp.sendRedirect("/user/order");
        }

        ConfirmationCode confirmationCode = new ConfirmationCode(userEmail);
        String code = ConfirmCodeGenerator.generateCode();
        confirmationCode.setCode(code);
        MailService mailService = new MailServiceImpl();
        mailService.sendConfirmCode(confirmationCode);
        basketFromSession.setConfirmationCode(confirmationCode);

        req.getRequestDispatcher("/add_order.jsp").include(req, resp);
        resp.sendRedirect("/user/order");
    }
}
