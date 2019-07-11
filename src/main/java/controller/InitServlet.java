package controller;

import controller.product.AllProductServlet;
import controller.user.AllUsersServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        AllUsersServlet allUsersServlet = new AllUsersServlet();
        allUsersServlet.initialUser();
        AllProductServlet allProductServlet = new AllProductServlet();
        allProductServlet.initialProduct();
    }
}
