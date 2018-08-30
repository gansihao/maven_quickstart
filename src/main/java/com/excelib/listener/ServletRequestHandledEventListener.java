package com.excelib.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
public class ServletRequestHandledEventListener implements ApplicationListener<ServletRequestHandledEvent> {
    final static Logger logger = LoggerFactory.getLogger(ServletRequestHandledEventListener.class);

    public void onApplicationEvent(ServletRequestHandledEvent event) {
        logger.info("[Listener]" + event.getDescription());
    }
}