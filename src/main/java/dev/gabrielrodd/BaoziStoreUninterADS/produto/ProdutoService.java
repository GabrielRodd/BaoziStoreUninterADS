package dev.gabrielrodd.BaoziStoreUninterADS.produto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    //Injetando dependencia do repository
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoModel> mostrar() {
        return produtoRepository.findAll();
    }

    public ProdutoModel criar(ProdutoModel novoProduto) {
        return produtoRepository.save(novoProduto);
    }

    public ProdutoModel mostrarPorId(Long id) {
        Optional<ProdutoModel> produtoBuscado = produtoRepository.findById(id);
        return produtoBuscado.orElse(null);
    }

    public ProdutoModel deletar(Long id) {
        ProdutoModel produtoDeletar = mostrarPorId(id);
        if (produtoDeletar != null) {
            produtoRepository.delete(produtoDeletar);
            return produtoDeletar;
        } else {
            return null;
        }
    }

    public ProdutoModel editar(Long id, ProdutoModel produtoEditado) {
        if (produtoRepository.existsById(id)) {
            produtoEditado.setId(id);
            return produtoRepository.save(produtoEditado);
        }else{
            return null;
        }
    }
}

