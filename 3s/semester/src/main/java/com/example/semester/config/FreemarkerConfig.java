package com.example.semester.config;

import freemarker.template.Configuration;

import javax.servlet.ServletContext;

public class FreemarkerConfig {

    private static ServletContext sc;
    private static Configuration cfg;
    public static void setServletContext(ServletContext context) {
        sc = context;
    }

    public static Configuration getConfig() {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setServletContextForTemplateLoading(sc, "/WEB-INF/templates");
        }
        return cfg;
    }

}
