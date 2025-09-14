package com.spacecowboy89.dc.newsmanagement.dto;

import java.time.LocalDateTime;

public record ErrorResponse (int status, String message, LocalDateTime timestamp){}
