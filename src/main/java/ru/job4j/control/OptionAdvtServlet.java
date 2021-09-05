package ru.job4j.control;

import org.json.JSONArray;
import ru.job4j.store.HbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class OptionAdvtServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("multipart/form-data");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        String filter = req.getParameter("filter");
        JSONArray array;
        switch (filter) {
            case "all" -> array = new JSONArray(HbStore.instOf().getAllAdvts());
            case "today" -> {
                Date today = new Date();
                array = new JSONArray(HbStore.instOf().getAdvtForDay(today));
            }
            case "withPhoto" -> array = new JSONArray(HbStore.instOf().getAdvtWithImage());
            default -> {
                array = new JSONArray(HbStore.instOf().getAdvtCurrentMark(filter));
            }
        }
        writer.println(array);
        writer.flush();
    }
}