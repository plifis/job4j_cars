package ru.job4j.control;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.model.Advt;
import ru.job4j.model.Author;
import ru.job4j.model.Car;
import ru.job4j.model.User;
import ru.job4j.store.HbStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateAdvtServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        int idAuthor = Integer.parseInt(req.getParameter("idAuthor"));
        int idCar = Integer.parseInt(req.getParameter("idCar"));
        Author author = HbStore.instOf().getAuthorById(idAuthor);
        HttpSession sc = req.getSession();
        User currentUser = (User) sc.getAttribute("user");
        if ((currentUser == null) || (!author.getUser().getName().equals(currentUser.getName()))) {
            resp.getWriter().write("Объявление может редактировать, только автор.");
        } else {
        for (Advt advt : author.getList()) {
            if (advt.getCar().getId() == idCar) {
                resp.getWriter().write("updateAdvt.jsp?idAdvt="+ advt.getId());
            }
        }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        User user = (User) req.getSession().getAttribute("user");
        String nameImage = System.currentTimeMillis() + user.getName();
        factory.setRepository(repository);
        Map<String, String> map = new HashMap<>();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("c:\\images\\" + user.getName() + "\\");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String ext = item.getName().substring(item.getName().lastIndexOf("."));
                    File file = new File(folder + File.separator + nameImage + ext);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                } else {
                    map.put(item.getFieldName(), item.getString("UTF-8"));
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        int idAdvt = Integer.parseInt(map.get("idAdvt"));
        Advt oldAdvt = HbStore.instOf().getRowById(Advt.class, idAdvt);
        Car car = oldAdvt.getCar();
        car.setBody(map.get("body"));
        car.setDescription(map.get("description"));
        car.setMark(map.get("mark"));
        oldAdvt.setCar(car);
        oldAdvt.setSale(map.get("issale").equals("on"));
        oldAdvt.setPrice(Float.parseFloat(map.get("price")));
        if (HbStore.instOf().updateAdvt(oldAdvt)) {
            resp.getWriter().write("Объявление обновлено");
        } else {
            resp.getWriter().write("Не удалось обновить объявление. Просьба обратиться к администратору.");
        }
    }


}
