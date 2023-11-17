package com.example.semester.servlets.adsPageServlet;

import com.example.semester.config.FreemarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="")
public class AdsPageServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Template tmpl = FreemarkerConfig.getConfig().getTemplate("./ads-page/ads.ftl");
            tmpl.process(null, resp.getWriter());
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
