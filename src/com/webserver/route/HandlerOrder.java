package com.webserver.route;

import com.webserver.HTTPRequest;
import com.webserver.HTTPResponse;
import com.webserver.handler.BaseHTTPHandler;
import com.webserver.handler.BasePreProcessHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HandlerOrder {
    private List<BasePreProcessHandler> preProcessHandlers;
    private BaseHTTPHandler mainHandler;
    private int curr = 0;

    public HandlerOrder() {
        preProcessHandlers = new ArrayList<>();
    }

    public void addPreProcessorHandlers(BasePreProcessHandler preProcessHandler) {
        preProcessHandlers.add(preProcessHandler);
    }

    public HandlerOrder addHandler(BaseHTTPHandler handler) {
        mainHandler = handler;
        return this;
    }

    public void startNextHandler(HTTPRequest request, HTTPResponse response) throws IOException {
        if (curr == -1) {
            return;
        }
        if (curr == preProcessHandlers.size()) {
            if (mainHandler != null) {
                mainHandler.service(request, response);
            }
        } else {
            preProcessHandlers.get(curr++).doFilter(request, response, this);
        }
    }
}
