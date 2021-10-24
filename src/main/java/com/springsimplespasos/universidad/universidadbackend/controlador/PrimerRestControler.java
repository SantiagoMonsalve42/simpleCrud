package com.springsimplespasos.universidad.universidadbackend.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//@RestController
//@RequestMapping("/restapi")
public class PrimerRestControler {
    Logger logger = LoggerFactory.getLogger(PrimerRestControler.class);
    @GetMapping("/hola-mundo")
    public ResponseEntity<String> holaMundo(){
        Map<String,Object> mng=new HashMap<>();
        mng.put("saludo","holaaa");
        logger.trace("trace log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warning info");
        logger.error("errorlog");
        return new ResponseEntity<>("Hola mundo", HttpStatus.ACCEPTED);
    }

}
