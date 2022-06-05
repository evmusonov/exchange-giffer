package exchange.controller;

import exchange.dto.response.HttpErrorDTO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SimpleErrorController implements ErrorController {
    @RequestMapping(
            path = "/error",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public HttpErrorDTO handleError(HttpServletRequest request) {
        return new HttpErrorDTO(
                request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString(),
                Integer.parseInt(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString())
        );
    }
}
