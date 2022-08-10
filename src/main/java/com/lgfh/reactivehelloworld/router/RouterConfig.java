package com.lgfh.reactivehelloworld.router;

import com.lgfh.reactivehelloworld.handler.CustomerHandler;
import com.lgfh.reactivehelloworld.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    private final CustomerHandler handler;

    private final CustomerStreamHandler streamHandler;

    public RouterConfig(CustomerHandler handler, CustomerStreamHandler streamHandler) {
        this.handler = handler;
        this.streamHandler = streamHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers)
                .GET("/router/customers/stream", streamHandler::getCustomers)
                .GET("/router/customer/{input}", handler::findCustomer)
                .POST("/router/customer/save", handler::saveCustomer)
                .build();
    }
}
