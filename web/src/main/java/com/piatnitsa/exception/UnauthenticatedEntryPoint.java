package com.piatnitsa.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UnauthenticatedEntryPoint implements AuthenticationEntryPoint {
    private static final String UTF_8 = "UTF-8";
    private static final String UNAUTHORIZED_MESSAGE = "exception.unauthorized";
    private final MessageSource messageSource;

    @Autowired
    public UnauthenticatedEntryPoint(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding(UTF_8);
        String details = messageSource.getMessage(UNAUTHORIZED_MESSAGE, null, request.getLocale());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter()
                .write(objectMapper.writeValueAsString(new ErrorResponse(HttpStatus.UNAUTHORIZED.toString(), details)));
    }
}
