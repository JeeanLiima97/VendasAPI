package br.com.jean.rest.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCliente){
        return String.format("Ola %s", nomeCliente);
    }

}
