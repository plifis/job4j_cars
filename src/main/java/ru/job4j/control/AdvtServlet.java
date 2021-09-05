package ru.job4j.control;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.*;
import ru.job4j.model.Advt;
import ru.job4j.model.Author;
import ru.job4j.model.Car;
import ru.job4j.model.User;
import ru.job4j.store.HbStore;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvtServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        JSONArray array = new JSONArray(HbStore.instOf().getAllAdvts());
        writer.println(array);
        writer.flush();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

        User user = (User) req.getSession().getAttribute("user");// не удалять

        String nameImage = null;
        factory.setRepository(repository);
        Map<String, String> map = new HashMap<>();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("c:\\images\\" + user.getName());
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String ext = item.getName().substring(item.getName().lastIndexOf("."));
                    nameImage = System.currentTimeMillis() + user.getName();
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
        if (HbStore.instOf().findUserByName(user.getName()) != null) {
            Car car = new Car(map.get("description"),
                    map.get("mark"),
                    map.get("body"),
                    nameImage);
            Advt advt = new Advt(car,
                    Float.parseFloat(map.get("price")));
            Author author = HbStore.instOf().findAuthorByUser(user);
            if (author == null) {
                author = new Author(user);
            }
            author.addAdvt(advt);
            HbStore.instOf().save(author);
        }
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
