package com.retoback.infrastructure.input.rest;

import com.retoback.application.dto.UsuarioAppRequestDto;
import com.retoback.application.handler.IUsuarioAppHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarioApp")
@RequiredArgsConstructor
public class UsuarioAppRestController {

private final IUsuarioAppHandler usuarioAppHandler;

@PostMapping("/save")
    public ResponseEntity<Void> saveUsuarioInUsuarioApp(UsuarioAppRequestDto usuarioAppRequestDto){
        usuarioAppHandler.saveUsuarioInUsuarioApp(usuarioAppRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
