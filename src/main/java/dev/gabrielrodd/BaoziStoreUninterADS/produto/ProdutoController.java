package dev.gabrielrodd.BaoziStoreUninterADS.produto;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    //Injetando dependencia service
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    //GET
    @GetMapping()
    public ResponseEntity<List<ProdutoModel>> mostrar() {
        List<ProdutoModel> listaProdutos = produtoService.mostrar();
        return ResponseEntity.status(HttpStatus.OK)
                .body(listaProdutos);
    }

    //GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> mostrarPorId(@PathVariable Long id) {
        ProdutoModel produto = produtoService.mostrarPorId(id);
        if (produto != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(produto);
        }else{
            return ResponseEntity.status((HttpStatus.NOT_FOUND))
                    .body("Produto de id " + id + " nao existe");
        }
    }

    //POST
    @PostMapping()
    public ResponseEntity<String> criar(@RequestBody ProdutoModel novoProduto) {
        produtoService.criar(novoProduto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Produto " + novoProduto.getNome() + " criado com sucesso");
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        ProdutoModel produtoDeletar = produtoService.deletar(id);
        if (produtoDeletar != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Produto de id " + id + " excluido com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto de id " + id + " nao existe" );
        }
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable Long id, @RequestBody ProdutoModel novoProduto) {
        ProdutoModel produto = produtoService.editar(id, novoProduto);
        if (produto != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Produto " + produto.getNome() + " alterado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item de di " + id + " nao existe");
        }

    }

}
