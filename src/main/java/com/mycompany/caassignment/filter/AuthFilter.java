/*
 * Copyright (C) 2016 Dietmar Steiner (open.source@d-steiner.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.mycompany.caassignment.filter;

/**
 * This filter class contains all functionality to redirect all requests except
 * to the login page to the login page.
 *
 * @author Dietmar
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Dietmar
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    /**
     * This filter class contains all functionality to redirect all requests
     * except to the login page to the login page.
     *
     *
     */
    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    /**
     * redirects all requests to login.xhtml exept for:
     *      /login.xhtml
     *      /register.xhtml
     *      *.js.xhtml
     *      when session exists and has a user attribute
     * 
     * @param request the request object
     * @param response the response object
     * @param chain the filter chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
    */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException{
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession session = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if (reqURI.contains("/login.xhtml")
            		|| reqURI.contains("/register.xhtml")
                    || reqURI.contains(".js.xhtml")
                    || (session != null && session.getAttribute("user") != null)) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
            }
    }

    @Override
    public void destroy() {

    }
}
