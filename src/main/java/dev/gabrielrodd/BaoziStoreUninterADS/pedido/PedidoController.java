package dev.gabrielrodd.BaoziStoreUninterADS.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    //Injetando Service
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService  = pedidoService;
    }

    @GetMapping()
    public ResponseEntity<List<PedidoModel>> mostrar() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoService.mostrar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> mostrarPorId(@PathVariable Long id) {
        PedidoModel pedidoMostrar = pedidoService.mostrarPorId(id);
        if (pedidoMostrar != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(pedidoMostrar);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pedido de id " + id + " nao existe");
        }
    }

    @PostMapping()
    public ResponseEntity<PedidoModel> criar(@RequestBody PedidoModel novoPedido) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.criar(novoPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        Boolean deletarEncontrado = pedidoService.deletar(id);
        if (deletarEncontrado == true) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Pedido de ID " + id + " deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pedido de ID " + id + " nao existe");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable Long id, @RequestBody PedidoModel pedidoEditado) {
        PedidoModel pedidoEditar = pedidoService.editar(id, pedidoEditado);
        if (pedidoEditar != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Pedido de ID " + id + " editado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pedido de ID " + id + " nao encontrado!");
        }
    }
}
