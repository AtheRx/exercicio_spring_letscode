package br.com.bb.letscode.exercicio1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.bb.letscode.exercicio1.model.Cliente;
import br.com.bb.letscode.exercicio1.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public HttpStatus clienteCreate(Cliente cliente) {
        if (cliente == null) {
            return HttpStatus.NO_CONTENT;
        }

        this.clienteRepository.save(cliente);
        return HttpStatus.CREATED;
    }

    public Iterable<Cliente> getClientes() {
        return this.clienteRepository.findAll();
    }

    public HttpStatus updateCliente(long id, Cliente clienteUpdate) {
        boolean clienteNaoCadastrado = clienteRepository.findById(id).isEmpty();
        if (clienteNaoCadastrado) {
            return HttpStatus.BAD_REQUEST;
        }

        Cliente clienteFromDataBase = this.clienteRepository.findById(id).get();
        clienteFromDataBase.setName(clienteUpdate.getName());
        clienteFromDataBase.setAge(clienteUpdate.getAge());
        clienteFromDataBase.setVatNumber(clienteUpdate.getVatNumber());
        clienteFromDataBase.setEmail(clienteUpdate.getEmail());
        clienteRepository.save(clienteFromDataBase);

        return HttpStatus.OK;
    }

    public HttpStatus deleteCliente(long id) {
        boolean clienteNaoCadastrado = clienteRepository.findById(id).isEmpty();
        if (clienteNaoCadastrado) {
            return HttpStatus.BAD_REQUEST;
        }

        this.clienteRepository.deleteById(id);        
        return HttpStatus.OK;
    }
}
