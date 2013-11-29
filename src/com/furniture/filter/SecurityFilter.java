package com.furniture.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.furniture.bean.SessionBean;

/**
 *
 * @author Jonathan Buckland
 */
public class SecurityFilter implements Filter {

    private final static String FILTER_APPLIED = "_security_filter_applied";

    public SecurityFilter() { //called once. no method arguments allowed here!
    }

    public void init(FilterConfig conf) throws ServletException {
    }

    public void destroy() {
    }

    /**
     * Creates a new instance of SecurityCheckFilter
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest hreq = (HttpServletRequest) request;
        HttpServletResponse hres = (HttpServletResponse) response;
        HttpSession session = hreq.getSession();
//        Enumeration<String> en = session.getAttributeNames();


        String checkforloginpage = hreq.getServletPath();

//        try {
////            System.out.println("+++++++++++++++++++++");
//            //System.out.println("CHECKING FOR PAGE=" + checkforloginpage);
////            System.out.println("SESSION=" + session);
////            System.out.println("SESSIONBEAN=" + session.getAttribute("sessionBean"));
////            System.out.println("USER=" + ((SessionBean) session.getAttribute("sessionBean")).getUsers());
////            System.out.println("++++++++++++++++");
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
        //String checkforloginpage = hreq.getPathTranslated();
        if (checkforloginpage == null) {
            checkforloginpage = "";
        }


        //System.out.println("Checking for="+checkforloginpage);
        
         
        
        
//        if ((request.getAttribute(FILTER_APPLIED) == null)
//                && (!checkforloginpage.endsWith("index.jsp"))                
//                && (!checkforloginpage.endsWith("loginPage.jsf"))
//                && (!checkforloginpage.endsWith("error.jsf"))
//                && (checkforloginpage.contains("backend") || checkforloginpage.contains("common") || checkforloginpage.contains("templates"))) {
//
//            request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
//            String user = null;            
//            if (session != null) {
//                if (session.getAttribute("sessionBean") != null && ((SessionBean) session.getAttribute("sessionBean")).getUsers() != null) {
//                    user = "OK";
//                }
//            } 
//
//            if (user == null) {
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!REDIRECTING fROM SECURITY FILTER " + checkforloginpage);
//
//                if ("partial/ajax".equals(hreq.getHeader("Faces-Request"))) {
//                    // It's a JSF ajax request.            	           	
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!AJAX CALL--->REDIRECTING fROM SECURITY FILTER " + checkforloginpage);
//                    hres.setContentType("text/xml");
//                    hres.getWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").printf("<partial-response><redirect url=\"%s\"></redirect></partial-response>", "/efurniture/index.jsp?faces-redirect=true");
//                    //hres.sendRedirect("herp/index.jsp?faces-redirect=true");            	            	
//                } else {
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!NORMAL CALL--->REDIRECTING fROM SECURITY FILTER " + checkforloginpage);
//                    hres.sendRedirect("/efurniture/index.jsp?faces-redirect=true");
//                }
//                return;
//            }
//        }
        
        
        
        
        
        
        

//        if (!hreq.getRequestURI().startsWith(hreq.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF resources (CSS/JS/Images/etc)
//            hres.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//            hres.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//            hres.setDateHeader("Expires", 0); // Proxies.
//        }
        chain.doFilter(request, response);
    }
}