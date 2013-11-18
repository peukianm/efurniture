package com.furniture.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.hosp.scheduling.ScheduleLocator;
import com.furniture.util.StringToolbox;
import com.furniture.util.SystemParameters;


public class BootstrapServlet extends HttpServlet 
{
    //private static final String CONTENT_TYPE = "text/html; charset=UTF-8"; 
    private static String SYSTEM_PROPERTIES_FILE = new String() ; 
    //private static String MESSAGES_FILE = new String() ;     
    
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config) ;
                                                  
        System.out.println("BOOTSTRAP="+this.getServletContext().getRealPath("") + "/config/system.properties" );
        SYSTEM_PROPERTIES_FILE = StringToolbox.replaceAll(this.getServletContext().getRealPath(""), "\\", "/") + "/config/system.properties" ;                                
        
        //Initiliazing Propertyfile, logging
        SystemParameters.getInstance(SYSTEM_PROPERTIES_FILE, this.getServletContext().getRealPath(""), true) ;
        
         
        //Initializing Scheduler
        //ScheduleLocator.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

}
