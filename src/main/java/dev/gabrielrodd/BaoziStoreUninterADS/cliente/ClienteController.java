package dev.gabrielrodd.BaoziStoreUninterADS.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    //Injetando service
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //GET
    @GetMapping()
    public ResponseEntity<List<ClienteModel>> mostrar() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clienteService.mostrar());
    }

    //GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> mostrarPorId(@PathVariable Long id) {
        ClienteModel clienteMostrar = clienteService.mostrarPorId(id);
        if (clienteMostrar != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(clienteMostrar);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente nao existente");
        }
    }

    //POST
    @PostMapping()
    public ResponseEntity<String> criar(@RequestBody ClienteModel novoCliente) {
        clienteService.criar(novoCliente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente " + novoCliente.getNome() + " criado com sucesso");
    }

}
