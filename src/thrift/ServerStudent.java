/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thrift;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import thrift.UserService.Processor;

/**
 *
 * @author cpu11251-local
 */
public class ServerStudent {
    public static UserServiceHandler handler;
    public static UserService.Processor processor;
    public static void main(String[] args) {
        handler = new UserServiceHandler();
        processor = new UserService.Processor(handler);
        
        Runnable simple;
        simple = new Runnable() {
            @Override
            public void run() {
                try {
                    simple(processor);
                } catch (TTransportException ex) {
                    Logger.getLogger(ServerStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            private void simple(Processor processor) throws TTransportException {
                TServerTransport serverTransport = new TServerSocket(9090);
                TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
                System.out.println("Starting the simple server...");
                server.serve();
            }
        };
        new Thread(simple).start();
    }
    
}
