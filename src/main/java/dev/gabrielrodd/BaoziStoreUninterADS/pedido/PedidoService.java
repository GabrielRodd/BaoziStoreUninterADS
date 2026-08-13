package dev.gabrielrodd.BaoziStoreUninterADS.pedido;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    //Injetando repository
    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoModel> mostrar() {
        return pedidoRepository.findAll();
    }

    public PedidoModel mostrarPorId(Long id) {
        Optional<PedidoModel> pedidoMostrar = pedidoRepository.findById(id);
        return pedidoMostrar.orElse(null);
    }

    public PedidoModel criar(PedidoModel novoPedido) {
        return pedidoRepository.save(novoPedido);
    }

    public Boolean deletar(Long id) {
        Boolean pedidoDeletar = pedidoRepository.existsById(id);
        if (pedidoDeletar != false) {
            pedidoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public PedidoModel editar(Long id, PedidoModel pedidoEditado) {
        if (pedidoRepository.existsById(id)) {
            pedidoEditado.setId(id);
            return pedidoRepository.save(pedidoEditado);
        }else{
            return null;
        }
    }
}
