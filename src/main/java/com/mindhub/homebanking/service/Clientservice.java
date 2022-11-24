package com.mindhub.homebanking.service;

import com.mindhub.homebanking.DTO.ClientDTO;
import com.mindhub.homebanking.models.Client;

import java.util.List;

public interface Clientservice {

    public List<ClientDTO> getListClientsDTO();

    public ClientDTO getClients(Long id);

    public Client findByEmail(String email);

    public void saveClient(Client client);
}
