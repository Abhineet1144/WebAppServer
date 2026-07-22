package com.test;

import com.webserver.handler.BaseHTTPHandler;
import com.webserver.route.HandlerOrder;
import com.webserver.route.RequestRouter;

public class TestMapper extends RequestRouter {
    @Override
    public HandlerOrder getRouteOrder(String endpoint) {
        if (matches("/hi", endpoint)) {
            return new HandlerOrder().addHandler(new HelloServlet());
        } else {
            return new HandlerOrder().addHandler(new BaseHTTPHandler());
        }
    }
}
