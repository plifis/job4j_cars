package ru.job4j.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class UploadServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageName = req.getParameter("image");
        String nameAuthor = req.getParameter("name");
        File downloadFile = null;
        String path = "C:\\images\\" + nameAuthor + "\\";
        for (File file : new File(path).listFiles()) {
            if (imageName.equals(file.getName().substring(0, file.getName().lastIndexOf(".")))) {
                downloadFile = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)) {
            resp.getOutputStream().write(stream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
