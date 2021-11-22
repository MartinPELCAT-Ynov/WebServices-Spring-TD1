package com.ynov.j2eetdspring.services;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LoggerService {

    public void log(String message) {
        Logger logger = Logger.getLogger(LoggerService.class.getName());
        logger.info(message);
    }

}
