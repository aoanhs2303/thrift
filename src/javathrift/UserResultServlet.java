/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrift;

import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.thrift.TException;
import client.v2.Client2;
import client.v1.ClientStudent;
import thrift.Student;

/**
 *
 * @author cpu11251-local
 */
public class UserResultServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("userid");
//        System.out.println(id);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("userid"));
        String time = "";
        //Call client in thrift
        ClientStudent client = new ClientStudent();
//        Client2 client2 = new Client2();
        
        String[] user = null;
        String[] user2 = null;
        Student student = new Student();
        try {
            user = client.perform(id);
//            user2 = client2.perform2(id, time);
//            student = client.show(id);
            user2 = client.perform2(id, time);
            client.close();
//            client2.close();
        } catch (TException ex) {
            Logger.getLogger(UserResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //TemplateLoader load resource
            TemplateLoader templateLoader = TemplateResourceLoader.create("html/");
            //Template load file
            Template template = templateLoader.getTemplate("user_result.xtm");
            //Use TemplateDictionary to put to xtm
            TemplateDictionary templeDictionary = new TemplateDictionary();
            TemplateDataDictionary temp1 = templeDictionary.addSection("user1");
            temp1.setVariable("name", user[0]);
            temp1.setVariable("age", user[1]);
            temp1.setVariable("email", user[2]);
            temp1.setVariable("time", user[3]);
            
            TemplateDataDictionary temp2 = templeDictionary.addSection("user2");
            temp2.setVariable("name", user2[0]);
            temp2.setVariable("age", user2[1]);
            temp2.setVariable("email", user2[2]);  
            temp2.setVariable("time", user2[3]);
              
            TemplateDataDictionary temp3 = templeDictionary.addSection("user3");
            temp3.setVariable("name", student.getName());
            temp3.setVariable("age", ""+student.getAge());
            temp3.setVariable("email", student.getEmail());

              
            String data = template.renderToString(templeDictionary);
            
            
            resp.getWriter().write(data);
        } catch (TemplateException ex) {
            Logger.getLogger(UserResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
