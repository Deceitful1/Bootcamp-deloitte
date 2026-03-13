package com.gablins.services;

import com.gablins.DTOs.ClienteVO;
import com.gablins.DTOs.mapper.ClientVOToEntityMapper;
import com.gablins.entities.ClientVOMapper;
import com.gablins.entities.Cliente;
import com.gablins.exceptions.ClienteNotFoundException;
import com.gablins.repositories.ClienteRepository;
import com.gablins.services.validator.ClienteServiceValidator;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gablins.services.hateoas.Hateoas.addHateoasLinks;
import static com.gablins.services.hateoas.Hateoas.addHateoasLinksToList;

@Service
public class ClienteService
{

    private final ClienteRepository clienteRepository;
    private final ClienteServiceValidator clienteServiceValidator;

    public ClienteService(ClienteRepository clienteRepository, ClienteServiceValidator clienteServiceValidator)
    {
        this.clienteRepository = clienteRepository;
        this.clienteServiceValidator = clienteServiceValidator;
    }

    public List<ClienteVO> findAll()
    {

        var resultList = ClientVOMapper.toVOList(clienteRepository.findAll());
        addHateoasLinksToList(resultList);

        return resultList;
    }

    public ClienteVO create(ClienteVO cliente)
    {

        var objeto = ClientVOToEntityMapper.VOToObject(cliente);

        //valida criação do objeto
        clienteServiceValidator.validateCreate(objeto);

        //salva o cliente no banco de dados
        Cliente client = clienteRepository.save(objeto);

        var result = ClientVOMapper.toVO(client);

        //adiciona links hateoas no objeto
        addHateoasLinks(result.getId(), result.getCpf(), result.getEmail(), result);
        return result;
    }

    public ClienteVO findById(Long id)
    {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente com id: " + id + " não encontrado")
        );
        var result = ClientVOMapper.toVO(cliente);


        addHateoasLinks(id, result.getCpf(), result.getEmail(), result);
        return result;
    }

    public ClienteVO update(ClienteVO clienteAtualizado)
    {


        Cliente entity = clienteRepository.findById(clienteAtualizado.getId()).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado."));
        Update.updateClient(entity, clienteAtualizado);

        var result = clienteRepository.save(entity);
        var result2 = ClientVOMapper.toVO(result);

        addHateoasLinks(result2.getId(), result2.getCpf(), result2.getEmail(), result2);
        return result2;
    }

    public void delete(Long id)
    {
        if (!clienteRepository.existsById(id))
        {
            throw new ClienteNotFoundException("Cliente não encontrado");
        }
            clienteRepository.deleteById(id);

    }

    public ClienteVO findByEmail(String email)
    {
        ClienteServiceValidator.existsByValidator.validateExistsByEmail(clienteRepository, email);

        Cliente cliente = clienteRepository.findByEmail(email);

        var result = ClientVOMapper.toVO(cliente);
        addHateoasLinks(result.getId(), result.getCpf(), result.getEmail(), result);
        return result;
    }

    public ClienteVO findByCpf(String cpf)
    {
        ClienteServiceValidator.existsByValidator.validateExistsByCpf(clienteRepository, cpf);
        Cliente cliente = clienteRepository.findByCpf(cpf);

        return ClientVOMapper.toVO(cliente);
    }


}
