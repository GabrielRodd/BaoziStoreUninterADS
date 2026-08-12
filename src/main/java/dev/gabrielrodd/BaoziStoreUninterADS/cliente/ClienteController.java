package dev.gabrielrodd.BaoziStoreUninterADS.cliente;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                    .body("Cliente de id " + id +  " nao encontrado");
        }
    }

    //POST
    @PostMapping()
    public ResponseEntity<String> criar(@RequestBody ClienteModel novoCliente) {
        clienteService.criar(novoCliente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente " + novoCliente.getNome() + " criado com sucesso");
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable Long id, @RequestBody ClienteModel entradaClienteEditado) {
        ClienteModel clienteEditado = clienteService.editar(id, entradaClienteEditado);
        if (clienteEditado != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Cliente ID " + clienteEditado.getId() + " editado com sucesso !");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de id " + id +  " nao encontrado !");
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        ClienteModel clienteDeletar = clienteService.deletar(id);
        if (clienteDeletar != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Cliente de id " + id + " deletado com sucesso !");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de id " + id + " nao existe no banco de dados!");
        }
    }

}
