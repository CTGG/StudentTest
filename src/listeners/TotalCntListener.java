package listeners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class totalCntListener
 *
 */
@WebListener
public class TotalCntListener implements ServletContextListener, ServletContextAttributeListener {
	int counter;
//	String counterFilePath= "C:\\Users\\st0001\\Desktop\\StudentTest\\WebContent\\totalCounter.txt";
	String counterFilePath = "/Users/G/Desktop/StudentTest/WebContent/totalCounter.txt";


	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
	
    public void contextInitialized(ServletContextEvent cse) {
    	try {
    		System.out.println("Reading Start"); 		
    		BufferedReader reader = new BufferedReader(new FileReader(counterFilePath));
    		counter = Integer.parseInt( reader.readLine() );
    		reader.close();
    		System.out.println("Reading " + counter);}
    	catch (Exception e) {
    		System.out.println(e.toString());
    	}
    	ServletContext servletContext= cse.getServletContext();
    	servletContext.setAttribute("totalCounter", counter);
    	System.out.println("Application initialized");
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0) {
    	System.out.println("ServletContextattribute added");
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scae) {
    	System.out.println("ServletContextattribute replaced");
    	writeCounter(scae);
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0) {
    	System.out.println("ServletContextattribute removed");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	System.out.println("Application shut down");
    }
	
    synchronized void writeCounter(ServletContextAttributeEvent scae) {
    	ServletContext servletContext= scae.getServletContext();
    	counter =  (int) servletContext.getAttribute("totalCounter");

    	try {
    		BufferedWriter writer = new BufferedWriter(new FileWriter(counterFilePath));
    		writer.write(Integer.toString(counter));
    		writer.close();
    		System.out.println("Writing");
    	}catch (Exception e) {
    		System.out.println(e.toString());
    	}
    }



	
}
