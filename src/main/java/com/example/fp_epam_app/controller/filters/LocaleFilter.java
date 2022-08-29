/**
 * @author Vitaly Balushchak
 * @version 1.0
 */
package com.example.fp_epam_app.controller.filters;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebFilter(filterName = "LocaleFilter", urlPatterns = "/*")
public class LocaleFilter implements Filter {

    private static final Logger log = Logger.getLogger(LocaleFilter.class);
    @Override
    public void init(FilterConfig config) {
    }
    /**
     * @param servletRequest;
     * @param servletResponse;
     * @param filterChain;
     * @throws ServletException;
     * @throws IOException;
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("Locale filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String locale= request.getParameter("locale");
        String defaultLocale = "en";

        if(locale != null){
            session.setAttribute("lang", locale);
        }
        else if (session.getAttribute("lang")==null){
            session.setAttribute("lang",defaultLocale);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
