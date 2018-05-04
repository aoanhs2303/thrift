/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrift;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author cpu11251-local
 */
public class JavaThrift {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
               
            ServletContextHandler context3 = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context3.setContextPath("/user");
            context3.addServlet(new ServletHolder(new UserServlet()),"");
            
            ServletContextHandler context4 = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context4.setContextPath("/user_result");
            context4.addServlet(new ServletHolder(new UserResultServlet()),"");
            
            WebAppContext webapp = new WebAppContext();
            webapp.setContextPath("/hello");
            webapp.setResourceBase("src/thrift");
            
            ContextHandlerCollection contexts = new ContextHandlerCollection();
            contexts.setHandlers(new Handler[] { context3, context4, webapp });
            
            server.setHandler(contexts);
            
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(JavaThrift.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
