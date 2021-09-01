package ru.job4j.controller;

import ru.job4j.store.HbStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAdvtServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("multipart/form-data");
        resp.setCharacterEncoding("UTF-8");
        String id = req.getParameter("idAdvt");
        HbStore.instOf().deleteAdvt(Integer.parseInt(id));
    }
}
