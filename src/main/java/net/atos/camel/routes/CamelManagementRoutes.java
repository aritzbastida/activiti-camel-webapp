package net.atos.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.BreakpointSupport;
import org.apache.camel.impl.DefaultDebugger;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.Processor;


public class CamelManagementRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		//setCamelDebugger();
		setCamelTracer();

        
        from("jetty:http://0.0.0.0:80/ping")
        	.transform(constant("PONG\n"))
        	.process( new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					exchange.getOut().setBody("HOLA MUNDO!");
					exchange.getOut().setHeader("to", "aritz.bastida@atos.net");
					exchange.getOut().setHeader("subject", "no lo leas...");
				}
        		
        	})
        	.to("smtps://smtp.gmail.com:465?username={{email.username}}&password={{email.password}}&sslContextParameters=#sslContextParameters");
        
		System.out.println("Configuring management routes...");
	}
	

	@SuppressWarnings("unused")
	private void setCamelDebugger() {
        BreakpointSupport breakpoint = new BreakpointSupport() {
            // this method is invoked before we are about to enter the given processor
            // from your Java editor you can just add a breakpoint in the code line below
            public void beforeProcess(Exchange exchange, Processor processor, ProcessorDefinition definition) {
                String body = exchange.getIn().getBody(String.class);              
                log.info("Before " + definition + " with body " + body);
            }
        };
        
		// use debugger
        getContext().setDebugger(new DefaultDebugger());
        getContext().getDebugger().addBreakpoint(breakpoint);		
	}

	@SuppressWarnings("unused")
	private void setCamelTracer() {
        getContext().setTracing(true);
	}
	
}
