package com.xbrain.testproject.services;

import com.xbrain.testproject.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
