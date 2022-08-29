/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.tags;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class Tag extends SimpleTagSupport {
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<p class=\"a\">conferences<br><span style=\"color: aliceblue\">«EPAM System»</span></p>");
    }
}
