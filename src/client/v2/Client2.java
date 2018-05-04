/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.v2;

import client.v1.ClientStudent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author cpu11251-local
 */
public class Client2 {
    
    private UserService.Client client;
    private TProtocol protocol;
    private TTransport transport;
    
    public Client2(){
        try {
            transport = new TSocket("localhost", 9090);
            transport.open();
            
            protocol = new TBinaryProtocol(transport);
            client = new UserService.Client(protocol);
        } catch (TTransportException ex) {
            Logger.getLogger(ClientStudent.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public String[] perform(int id, String time) throws TException{
        String[] split = null;
        String student = client.getUserById(id, time);
        split = student.split("_");
        return split;
    }
    
    public String[] perform2(int id, String time) throws TException{
        String[] split = null;
        String student = client.getUser2(id, time);
        split = student.split("_");
        return split;
    }
    
    public Student show(int id) throws TException {
        Student student = client.getFullInfo(id);
        System.out.println(student.name);
        return student;
    }
    
    public void close() {
        transport.close();
    }
    
    public static void main(String[] args) throws TException {
        Client2 cli = new Client2();
        String time = "from_client_2";
        String[] perform = cli.perform(1, time);
        if(perform == null)
            System.out.println("Return empty");
        else
            System.out.println(perform[0]);
    }
    
}
