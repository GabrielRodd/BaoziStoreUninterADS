package dev.gabrielrodd.BaoziStoreUninterADS.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    //Injetando Service
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService  = pedidoService;
    }

    public ResponseEntity<List<PedidoModel>> mostrar() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoService.mostrar());
    }

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

    public ResponseEntity<PedidoModel> criar(@RequestBody PedidoModel novoPedido) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.criar(novoPedido));
    }

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
}
