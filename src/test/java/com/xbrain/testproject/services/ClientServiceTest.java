package com.xbrain.testproject.services;

import com.xbrain.testproject.models.entities.Client;
import com.xbrain.testproject.repositories.ClientRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientServiceTest {
    private static ClientService clientService;

    private static ClientRepository clientRepositoryMock = Mockito.mock(ClientRepository.class);

    @BeforeClass
    public static void init() {
        clientService = new ClientService(clientRepositoryMock);
    }

    @Test
    public void isClientRegisteredShouldReturnFalse_when_ClientIdIsNotFound() {
        Long clientId = 100L;

        when(clientRepositoryMock.existsById(any())).thenReturn(false);

        boolean result = clientService.isClientRegistered(clientId);
        assertFalse(result);

        verify(clientRepositoryMock, Mockito.times(1)).existsById(clientId);
    }

    @Test
    public void isClientRegisteredShouldReturnFalse_when_ClientIdIsNull() {
        Long clientId = null;

        boolean result = clientService.isClientRegistered(clientId);
        assertFalse(result);
    }

    @Test
    public void isClientRegisteredShouldReturnTrue_when_ClientIdIsFound() {
        Long clientId = 1L;

        when(clientRepositoryMock.existsById(any())).thenReturn(true);

        boolean result = clientService.isClientRegistered(clientId);
        assertTrue(result);

        verify(clientRepositoryMock, Mockito.times(1)).existsById(clientId);
    }

    @Test
    public void findClientByIdShouldReturnProperResult_when_ClientIdIsFound() {
        Long clientId = 1L;
        Client expectedClient = new Client("Viola", "viola@gmail.com", "hello");
        expectedClient.setId(clientId);
        Optional<Client> clientOptional = Optional.of(expectedClient);

        when(clientRepositoryMock.findById(clientId)).thenReturn(clientOptional);

        Client resultClient = clientService.findClientById(clientId);
        assertEquals(expectedClient, resultClient);

        verify(clientRepositoryMock, Mockito.times(1)).findById(clientId);
    }

    @Test
    public void findClientByIdShouldReturnNull_when_ClientIdIsNotFound() {
        Long clientId = 100L;
        Client expectedClient = null;

        when(clientRepositoryMock.findById(clientId)).thenReturn(Optional.empty());

        Client resultClient = clientService.findClientById(clientId);
        assertEquals(expectedClient, resultClient);
        assertNull(resultClient);

        verify(clientRepositoryMock, Mockito.times(1)).findById(clientId);
    }

    @Test
    public void findClientByIdShouldReturnNull_when_ClientIdIsNull() {
        Long clientId = null;
        Client expectedClient = null;

        Client resultClient = clientService.findClientById(clientId);
        assertEquals(expectedClient, resultClient);
        assertNull(resultClient);
    }
}
