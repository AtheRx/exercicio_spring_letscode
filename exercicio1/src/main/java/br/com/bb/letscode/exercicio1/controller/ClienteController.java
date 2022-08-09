package br.com.bb.letscode.exercicio1.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bb.letscode.exercicio1.model.Cliente;
import br.com.bb.letscode.exercicio1.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Void> clienteCreate(@Valid @RequestBody Cliente cliente){
        return new ResponseEntity<Void>(this.clienteService.clienteCreate(cliente));
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Cliente>> getClientes(){
        return ResponseEntity.ok(this.clienteService.getClientes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable(value = "id")long id, @RequestBody Cliente cliente){
        return new ResponseEntity<Void>(this.clienteService.updateCliente(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable(value = "id")long id){
        return new ResponseEntity<Void>(this.clienteService.deleteCliente(id));
    }
    

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(e -> {
                    String fieldName = ((FieldError) e).getField();
                    String errorMessage = e.getDefaultMessage();

                    errors.put(fieldName, errorMessage);
                });
        return errors;
    }
}
