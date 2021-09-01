package ru.job4j.controller;


import ru.job4j.model.Advt;
import ru.job4j.store.HbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeletePhotoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageName = req.getParameter("image");
        String nameAuthor = req.getParameter("name");
        String idAdvt = req.getParameter("idAdvt");
        String path = "C:\\images\\" + nameAuthor + "\\";
        for (File file : new File(path).listFiles()) {
            if (imageName.equals(file.getName().substring(0, file.getName().lastIndexOf(".")))) {
                file.delete();
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "updateAdvt.jsp?idAdvt="+ idAdvt);
        Advt advt = HbStore.instOf().getRowById(Advt.class, Integer.parseInt(idAdvt));
        advt.getCar().setImage(null);
        HbStore.instOf().updateAdvt(advt);
        resp.getWriter().write("updateAdvt.jsp?idAdvt="+ advt.getId());
    }
}
