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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        Long id = userFromSession.getId();
        String userEmail = userFromSession.getEmail();
        Basket basketFromSession = (Basket) req.getSession().getAttribute("basket");
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

        req.setAttribute("userID", id);
        req.setAttribute("userEmail", userEmail);
        req.setAttribute("totalPrice", basketFromSession.getTotalPrice());
        req.getRequestDispatcher("/add_order.jsp").forward(req, resp);
        resp.sendRedirect("/user/order");
    }
}
