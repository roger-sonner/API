package br.com.primeira.primeiraapi.controller;

import br.com.primeira.primeiraapi.controller.form.ClienteForm;
import br.com.primeira.primeiraapi.controller.form.putClienteForm;
import br.com.primeira.primeiraapi.controller.form.putProdutoForm;
import br.com.primeira.primeiraapi.dto.ClienteDto;
import br.com.primeira.primeiraapi.model.Cliente;
import br.com.primeira.primeiraapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController  // Assume que todo método é um ResponseBody
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping()
    //@ResponseBody
    public List<Cliente> getClientes(String nomeCliente) {  // recebe o parametro (nomeCliente) da url
        List<Cliente> clientes;
        if(nomeCliente == null) {
            clientes = clienteRepository.findAll();
        } else{
            clientes = clienteRepository.findByNome(nomeCliente);
        }
        return clientes;
    }
    /*
    @GetMapping("/{id}")  // @PathVariable indica que o parametro vem na url
    public ClienteDto getCliente(@PathVariable("id") Long codigo){  // o parametro no GetMapping deve ter o mesmo do método (ex: sgetCliente(Long id))
     */

    @GetMapping("/{id}")  // @PathVariable indica que o parametro vem na url
    public ResponseEntity<ClienteDto> getCliente(@PathVariable Long id){  // o parametro no GetMapping deve ter o mesmo do método (ex: sgetCliente(Long id))
        Optional<Cliente> cliente = clienteRepository.findById(id); //  .getReferenceById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(new ClienteDto(cliente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping                          // o @RequestBody indica que os parametros serão enviados no corpo da requisição
    @Transactional
    public ResponseEntity<ClienteDto> postCliente(@RequestBody ClienteForm form, UriComponentsBuilder uriBuilder){  // form é um DTO,
        Cliente cliente = form.converterCliente();  // converte o objeto tipo ClienteForm em um objeto tipo Cliente apenas com o que vai ser gravado no banco
        clienteRepository.save(cliente);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> putCliente(@PathVariable Long id, @RequestBody @Valid putClienteForm form){
        Optional<Cliente> optional = clienteRepository.findById(id);
        if(optional.isPresent()){
            Cliente cliente = form.atualizar(id, clienteRepository);
            return ResponseEntity.ok(new ClienteDto(cliente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteCliente(@PathVariable Long id){
        Optional<Cliente> optional = clienteRepository.findById(id);
        if(optional.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

