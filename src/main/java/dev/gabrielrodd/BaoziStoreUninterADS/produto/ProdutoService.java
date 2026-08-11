package dev.gabrielrodd.BaoziStoreUninterADS.produto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    //Injetando dependencia do repository
    private ProdutoRepository produtoRepository;

    public ProdutoService (ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoModel> mostrar() {
        return produtoRepository.findAll();
    }


}
