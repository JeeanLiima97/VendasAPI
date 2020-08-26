package br.com.jean.rest.controller;

import br.com.jean.exception.PedidoNaoEncontradoException;
import br.com.jean.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegrasNegocioException(RegraNegocioException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
public ApiErrors handlePedidoNaoEncontradoException(PedidoNaoEncontradoException exception){
        return new ApiErrors(exception.getMessage());
}

@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException exception){
    List<String> errors = exception.getBindingResult().getAllErrors().stream().map(erro -> erro.getDefaultMessage())
            .collect(Collectors.toList());
    return new ApiErrors(errors);

}
}
