package sample.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SampleAutowiredAmqRoute extends RouteBuilder {

	//String msg="Hello from ofss";
	 String msg="{\"id\":\"4\",\"item\":\"GOT\",\"description\":\"hello\"}";
	
    @Override
    public void configure() throws Exception {
	    //this is used to recieve data from activemq
        from("activemq:sample.queue")
            .to("bean:databaseService?method=printjson(${body})");

	    /* This is used to send data to activemq*/
        from("timer://foo?repeatCount=1")     // use from("timer://foo?repeatCount=1")  to send only once the route starts and use  from("timer:bar") to send every second
        .setBody(constant(msg))
        .to("activemq:sample.queue");     //sample.queue is the name of queue
    }
    
   

}
