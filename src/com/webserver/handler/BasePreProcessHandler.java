package com.webserver.handler;

import com.webserver.HTTPRequest;
import com.webserver.HTTPResponse;
import com.webserver.route.HandlerOrder;

public abstract class BasePreProcessHandler {

    public abstract void doFilter(HTTPRequest request, HTTPResponse response, HandlerOrder order);
}
