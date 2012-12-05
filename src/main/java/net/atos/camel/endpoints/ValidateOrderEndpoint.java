package net.atos.camel.endpoints;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.atos.camel.entities.Order;


@WebService
public interface ValidateOrderEndpoint {

	@WebMethod
    String validateOrder(@WebParam(name = "order") Order order);
	
}
