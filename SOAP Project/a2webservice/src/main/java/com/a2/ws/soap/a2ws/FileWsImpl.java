// Kristiyan Stoilov 

package com.a2.ws.soap.a2ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class FileWsImpl implements FileWs {
    InputStream inputStream = null;
    OutputStream outputStream = null;
    String path = "C://Users//Kristian//Pictures//test.png";

    @WebMethod
    public void upload(DataHandler attachment) {
        try {
            inputStream = attachment.getInputStream();
            outputStream = new FileOutputStream(new File(path));
            byte[] b = new byte[100000];
            int byteRead = 0;
            while ((byteRead = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, byteRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @WebMethod
    public DataHandler download() {
        return new DataHandler(new FileDataSource(new File("C://Users//Kristian//Pictures//test.png")));
    }

    @WebMethod
    public ArrayList<Integer> fibonacci() {
        int first = 0, second = 1, amount = 10, result;
        ArrayList<Integer> fibonacciNumbers = new ArrayList<>();
        fibonacciNumbers.add(0);
        fibonacciNumbers.add(1);
        for (int i = 0; i < amount - 2; i++) {
            result = first + second;
            first = second;
            second = result;
            fibonacciNumbers.add(result);
        }
        return fibonacciNumbers;
    }
}