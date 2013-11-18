package com.furniture.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SystemParameters {
	
	/** The instance of the singleton */
    private static SystemParameters _instance = null ;
    
    /** The Logger instance of the class */
    private final static Logger logger = Logger.getLogger(SystemParameters.class) ;  
    
    private long startUpTimestamp = System.currentTimeMillis() ;
    
    /** Constant for enabling scheduler  */    
    public static final String SCHEDULER_ENABLED = "SCHEDULER_ENABLED" ; 
    
    /** Constant for scheduler delay */    
    public static final String SCHEDULER_DELAY = "SCHEDULER_DELAY" ;
    
    /** Indicator for the scheduled_Task resource rows policy */
    public static final String PURGE_SCHEDULED_TASK_RESOURCE = "PURGE_SCHEDULED_TASK_RESOURCE" ; 
    
    /** Constant for scheduler period */    
    public static final String SCHEDULER_PERIOD = "SCHEDULER_PERIOD" ; 
    
    /** Constant for the maximum number of scheduled task execution */    
    public static final String MAXIMUM_NUMBER_OF_SCHEDULED_TASK_EXECUTION = "MAXIMUM_NUMBER_OF_SCHEDULED_TASK_EXECUTION" ; 
    
    /** These variables reference the ids at the database */
    public static final String SCHEDULE_TASK_ID_DISK_CLEAN_UP = "SCEDULE_TASK_ID_DISK_CLEAN_UP";
    
    /** Max minutes that a task can be at the state is_idle == 1 */
    public static final String MAX_MINUTES = "MAX_MINUTES";
    
    /** Max DAYS that a task can be left on the system */    
    public static final String MAX_DAYS_FOR_TASK = "MAX_DAYS_FOR_TASK";

    
    /** Used for caching the system.properties file*/
    private Properties properties = new Properties() ; 
    
    public static final String MULTIPART_UPLOAD_DIRECTORY = "MULTIPART_UPLOAD_DIRECTORY" ; 
    
    public static String appRoot = "";
    
    //Testing purposes
//    private static int staticCounter = 0;
//    private int counter = 0;    
	 
	private SystemParameters(){          
        super() ;         
        logger.info("Initializing from empty constructor") ;             
    }
	
	 private SystemParameters(String parametersFile, String appRoot)
	    {                
	        try
	        {
	            this.appRoot = appRoot;
//	           staticCounter++;
//	            counter++;        
            
	            // read the parameters
	            FileInputStream in = new FileInputStream(parametersFile) ;
	            properties = new Properties() ; 
	            properties.put(this.MULTIPART_UPLOAD_DIRECTORY,StringToolbox.replaceAll(appRoot, "\\", "/") + "/tmp/");
	            properties.load(in);
	            in.close() ;    						                            
	   
	            String logFileFullPathName = StringToolbox.replaceAll(appRoot, "\\", "/") + "/logs/furniture.log" ; 
            	            
	            Properties logProperties = new Properties() ;         
	            
	            logProperties.put("log4j.rootLogger", properties.getProperty("log4j.rootLogger")) ;
	            logProperties.put("log4j.appender.FILEAPPENDER", properties.getProperty("log4j.appender.FILEAPPENDER")) ;
	            logProperties.put("log4j.appender.FILEAPPENDER.MaxFileSize", properties.getProperty("log4j.appender.FILEAPPENDER.MaxFileSize")) ;
	            logProperties.put("log4j.appender.FILEAPPENDER.File", logFileFullPathName) ; // The full path is known from the user.dir
	            logProperties.put("log4j.appender.FILEAPPENDER.layout", properties.getProperty("log4j.appender.FILEAPPENDER.layout")) ;
	            logProperties.put("log4j.appender.FILEAPPENDER.Append", properties.getProperty("log4j.appender.FILEAPPENDER.Append")) ;
	            
	            logProperties.put("log4j.appender.FILEAPPENDER.MaxBackupIndex", properties.getProperty("log4j.appender.FILEAPPENDER.MaxBackupIndex")) ;
	            
	            logProperties.put("log4j.appender.FILEAPPENDER.layout.ConversionPattern", properties.getProperty("log4j.appender.FILEAPPENDER.layout.ConversionPattern")) ;
	            logProperties.put("log4j.appender.FILEAPPENDER.Threshold", properties.getProperty("log4j.appender.FILEAPPENDER.Threshold")) ;            
	            
	            //SNMP logging properties
//	            logProperties.put("log4j.appender.TRAP_LOG", properties.getProperty("log4j.appender.TRAP_LOG"));
//	            logProperties.put("log4j.appender.TRAP_LOG.ImplementationClassName", properties.getProperty("log4j.appender.TRAP_LOG.ImplementationClassName"));
//	            logProperties.put("log4j.appender.TRAP_LOG.ManagementHost", properties.getProperty("log4j.appender.TRAP_LOG.ManagementHost"));
//	            logProperties.put("log4j.appender.TRAP_LOG.ManagementHostTrapListenPort", properties.getProperty("log4j.appender.TRAP_LOG.ManagementHostTrapListenPort"));
//	            logProperties.put("log4j.appender.TRAP_LOG.EnterpriseOID", properties.getProperty("log4j.appender.TRAP_LOG.EnterpriseOID"));
//	            logProperties.put("log4j.appender.TRAP_LOG.LocalIPAddress", properties.getProperty("log4j.appender.TRAP_LOG.LocalIPAddress"));
//	            logProperties.put("log4j.appender.TRAP_LOG.LocalTrapSendPort", properties.getProperty("log4j.appender.TRAP_LOG.LocalTrapSendPort"));
//	            logProperties.put("log4j.appender.TRAP_LOG.GenericTrapType", properties.getProperty("log4j.appender.TRAP_LOG.GenericTrapType"));
//	            logProperties.put("log4j.appender.TRAP_LOG.SpecificTrapType", properties.getProperty("log4j.appender.TRAP_LOG.SpecificTrapType"));
//	            logProperties.put("log4j.appender.TRAP_LOG.ApplicationTrapOID", properties.getProperty("log4j.appender.TRAP_LOG.ApplicationTrapOID"));
//	            logProperties.put("log4j.appender.TRAP_LOG.CommunityString", properties.getProperty("log4j.appender.TRAP_LOG.CommunityString"));
//	            logProperties.put("log4j.appender.TRAP_LOG.SysUpTime", properties.getProperty("log4j.appender.TRAP_LOG.SysUpTime"));
//	            logProperties.put("log4j.appender.TRAP_LOG.Threshold", properties.getProperty("log4j.appender.TRAP_LOG.Threshold"));
//	            logProperties.put("log4j.appender.TRAP_LOG.layout", properties.getProperty("log4j.appender.TRAP_LOG.layout"));    
//	            logProperties.put("log4j.appender.TRAP_LOG.layout.ConversionPattern", properties.getProperty("log4j.appender.TRAP_LOG.layout.ConversionPattern"));            
	            PropertyConfigurator.configure(logProperties) ; 
	            
	            logger.info("Initializing from : " + parametersFile + "\n log file is written to : " + logFileFullPathName) ;             
	                                                                                    	                                      	             
	        }
	        catch ( FileNotFoundException e ) {            
	            e.printStackTrace();
	        	logger.fatal("Cannot find system.properties file : " + parametersFile + " due to : " + e.toString()) ; 
	            logger.fatal(e, e) ; 
	        }
	        catch ( IOException e ) {
	        	e.printStackTrace();
	            logger.fatal("General IO error while loading system.properties file : " + parametersFile + " into Properties object, due to : " + e.toString()) ; 
	            logger.fatal(e, e) ; 
	        }        
	    }
	    
		/** Singleton static instance getter method. This methos is static synchronized in order to
	     * to achieve the single instatiaton of the _instance parameters. Even in a multithreaded
	     * environment (like JVM) this is guaranteed through the static synchronization. 
	     * 
	     * @return a SystemParameter instance
	     */
	     
		public static synchronized SystemParameters getInstance(){        
			if ( _instance == null ) {               
	            _instance = new SystemParameters() ; 
	        }                
			return _instance ; 
		}

		/** Singleton static instance getter method. This methos is static synchronized in order to
	     * to achieve the single instatiaton of the _instance parameters. Even in a multithreaded
	     * environment (like JVM) this is guaranteed through the static synchronization. This method 
	     * is just an overloaded version of the previous one. It is used from the Web tier for initialization
	     * purposes. 
	     * @param parametersFile is the full path name of the system.parameters file	     
	     * @param refresh if true then the cache is flushed and refreshed, used for refreshing operations
	     * should they are necessary of course. 
	     * @return a SystemParameter instance
	     */
		public static synchronized SystemParameters getInstance(String parametersFile, String appRoot, boolean refresh){        	        
			if ( ( _instance == null ) || refresh )   {
	            _instance = new SystemParameters(parametersFile, appRoot) ; 
	        }			
			return _instance ; 
		}


	    /**
	     * Reads a system parameter from the cache
	     * @param name is the name of the parameter
	     * @return the String value of the parameter. 
	     */
	    public String getProperty(String name)  {
	        return (String)properties.get(name) ; 
	    }
	    
	    /**
	     * Returns the startup timestamp 
	     * @return the start up timestamp (epoc number)
	     */
	    public long getStartupTimestamp()  {
	        return this.startUpTimestamp ; 
	    }

}
