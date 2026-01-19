package com.spacecowboy89.dc.newsmanagement.exception;

import java.time.LocalDateTime;

public class PersistenceDataException extends GeneralException{
        public PersistenceDataException(String message, LocalDateTime dateTime){
            super(message,dateTime);
        }
}
