package net.atos.camel.routes;

import java.util.ArrayList;
import java.util.List;

import net.atos.camel.entities.Order;
import net.atos.camel.processors.ActivitiStarterProcessor;
import net.atos.camel.processors.IncomingOrdersProcessor;
import net.atos.camel.processors.ValidationProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.cxf.message.MessageContentsList;


public class BusinessRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {	
		
		from("cxf:bean:incomingOrderEndpoint")
			.to(ExchangePattern.InOnly, "vm:processIncomingOrders") // Investigar  
			.transform().constant("PROCESSING ORDERS...");
			
		from("vm:processIncomingOrders")
			.process(new IncomingOrdersProcessor())
			.split(body())	// iterate list of Orders
			.to("log:incomingOrder1?showExchangeId=true")
			.process(new ActivitiStarterProcessor())
			.to("log:incomingOrder2?showExchangeId=true")			
			.to("activiti:activiti-camel-example");
			
		
		from("activiti:activiti-camel-example:logServiceTask")
			.setBody().property("ORDER")
		//	.multicast()
		//	.to("direct:ordercsv", "direct:orderxml")
			.log("[[ORDER LOGGED]]");
		
		
		from("activiti:activiti-camel-example:validateServiceTask")
			.to("vm:asyncvalidation");
		
		from("vm:asyncvalidation")		
			.setBody().property("ORDER")		
			.setHeader(CxfConstants.OPERATION_NAME).constant("validateOrder")
			.to(ExchangePattern.InOut, "cxf:bean:validateOrderEndpoint")
			.process(new ValidationProcessor())	
			.delay(1000)
			.to("activiti:activiti-camel-example:receiveValidateServiceTask");
			
		
		from("direct:ordercsv")		// -------------------> CORREEEEEEEEEEEEEEEEEEGIR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			.marshal().bindy(BindyType.Csv, "net.atos.camel.entities")
			.to("file:d://cameldata/orders?fileName=orders-${date:now:yyyyMMdd-hhmmss}.csv");
		
		from("direct:orderxml")
			.marshal("jaxb")
			.to("file:d://cameldata/orders?fileName=orders-${date:now:yyyyMMdd-hhmmss}.xml");		
			
		from("vm:orderjms")
			.marshal("jaxb")
			.to("jms:queue:ProcessedOrdersInQUEUE?jmsMessageType=Text");						// JMS Queue		

		
		//from("jms:queue:ProcessedOrdersInQUEUE").to("jms:queue:ProcessedOrdersOutQUEUE");
		
		//from("jms:queue:ProcessedOrdersOutQUEUE").to("file:d://cameldata/orders?fileName=orders-${date:now:yyyyMMdd-hhmmss}.xml");
				

		
		
		/*
		from("activiti:helloCamelProcess:serviceTask1")
				.log("BODY: ${in.body}")
				.log(LoggingLevel.INFO,
						"Received message on service task ${property.var1}")
				.setProperty("var2").constant("world").setBody().properties();

		from("file:d://cameldata/start?delay=10000").log("Processing...").to(
				"direct:start");

		from("direct:start").log("Starting...").convertBodyTo(String.class)
				.setProperty("var1").constant("hello")
				.log("This is the message: ${in.body}")
				.log("This is the message: ${property.var1}")
				.to("activiti:helloCamelProcess");
		 */
		
		// route starts from the cxf webservice, see camel-cxf.xml for details
		// from("cxf:bean:orderEndpoint").to("seda:incomingOrders").transform().constant("OK");

		// test route
		// from("seda:incomingOrders").to("mock:end");

		System.out.println("Configuring business routes...");
	}

}
