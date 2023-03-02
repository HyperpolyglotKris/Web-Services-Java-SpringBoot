// Kristiyan Stoilov 

package com.a2.ws.soap.a2ws;

import java.util.ArrayList;
import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FileWs {
    void upload(@WebParam(name = "file") DataHandler attachment);

    DataHandler download();

    ArrayList<Integer> fibonacci();
}
