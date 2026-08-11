package dev.gabrielrodd.BaoziStoreUninterADS.produto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    //Injetando dependencia service
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/mostrar")
    public ResponseEntity<List<ProdutoModel>> mostrar() {
        List<ProdutoModel> listaProdutos = produtoService.mostrar();
        return ResponseEntity.status(HttpStatus.OK)
                .body(listaProdutos);
    }
}
