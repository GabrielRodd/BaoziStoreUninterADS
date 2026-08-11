package dev.gabrielrodd.BaoziStoreUninterADS.cliente;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    //Injetando repository
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> mostrar() {
        return clienteRepository.findAll();
    }

    public ClienteModel mostrarPorId(Long id) {
        Optional<ClienteModel> clienteBuscado = clienteRepository.findById(id);
        return clienteBuscado.orElse(null);
    }

    public ClienteModel criar(ClienteModel novoCliente) {
        return clienteRepository.save(novoCliente);
    }

    public ClienteModel deletar(Long id) {
        ClienteModel clienteDeletar = mostrarPorId(id);
        if (clienteDeletar != null) {
            clienteRepository.deleteById(id);
            return clienteDeletar;
        } else {
            return null;
        }
    }

    public ClienteModel editar(Long id, ClienteModel clienteEditado) {
        if (clienteRepository.existsById(id)) {
            clienteEditado.setId(id);
            return clienteRepository.save(clienteEditado);
        }else{
            return null;
        }
    }

}
