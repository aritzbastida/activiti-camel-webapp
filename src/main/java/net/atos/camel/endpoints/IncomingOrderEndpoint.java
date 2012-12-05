package net.atos.camel.endpoints;


import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.atos.camel.entities.Order;


@WebService
public interface IncomingOrderEndpoint {
	
	@WebMethod
    String createOrder(@WebParam(name = "order") Collection<Order> orders);
	
}
