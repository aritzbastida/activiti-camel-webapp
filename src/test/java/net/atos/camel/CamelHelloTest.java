
package net.atos.camel;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;



public class CamelHelloTest extends CamelTestSupport {

    @EndpointInject(uri = "vm:start")
    private ProducerTemplate exchange;


    @Test
    public void testExecute() throws Exception {
        context.setTracing(true);

        String res = (String) exchange.sendBody("vm:start", ExchangePattern.InOut, "Hello camel!");
        
        assertEquals("Result should be 'Camel'", res, "Camel");   
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
    	return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
        		from("vm:start")
      		  		.process(new Processor() {
						
						@Override
						public void process(Exchange exchange) throws Exception {
							System.out.println(exchange.getIn().getBody());							
						}
					})      		 
      		  		.setBody(constant("Camel"))
      		  		.to("mock:result");	
            }
        };
    }
}