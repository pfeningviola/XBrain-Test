package com.xbrain.testproject.services;

import com.xbrain.testproject.models.entities.Client;
import com.xbrain.testproject.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean isClientRegistered(Long clientId){
        return clientRepository.existsById(clientId);
    }

    public Client findClientById(Long id){
        Optional<Client> clientOptional = clientRepository.findById(id);
        Client client = null;
        if(clientOptional.isPresent()) {
            client = clientOptional.get();
        }
        return client;
    }
}
