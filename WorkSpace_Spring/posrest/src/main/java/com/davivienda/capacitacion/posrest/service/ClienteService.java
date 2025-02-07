package com.davivienda.capacitacion.posrest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.davivienda.capacitacion.posrest.dto.ClienteDto;
import com.davivienda.capacitacion.posrest.dto.ClienteDtoPK;
import com.davivienda.capacitacion.posrest.error.PosRestError;
import com.davivienda.capacitacion.posrest.modelo.Cliente;
import com.davivienda.capacitacion.posrest.modelo.ClientePK;
import com.davivienda.capacitacion.posrest.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteDto findByPrimaryKey(ClientePK id) {
		Optional<Cliente> optCliente = clienteDao.findById(id);
		if(optCliente.isPresent()) {
			Cliente cliente = optCliente.get();
			ClienteDto clienteDto = this.modelMapper.map(cliente, ClienteDto.class);
			return clienteDto;
		}
		throw new PosRestError("Cliente no encontrado", HttpStatus.NOT_FOUND);
	}
	
	public List<ClienteDto> findAll(){
		List<ClienteDto> lstResultado = new ArrayList<ClienteDto>();
		List<Cliente> lstClientes = this.clienteDao.findAll();
		
		for(Cliente cliente : lstClientes) {
			ClienteDto clienteDto = this.modelMapper.map(cliente, ClienteDto.class);
			lstResultado.add(clienteDto);
		}
		
		return lstResultado;
	}
	
	public void create(Cliente cliente) {
		this.clienteDao.save(cliente);
	}
	
	public void create(ClienteDto clienteDto) {
		Cliente clienteEnt = this.modelMapper.map(clienteDto, Cliente.class);
		this.clienteDao.save(clienteEnt);
	}
	
	/*public void delete(ClienteDtoPK clienteDtoPK) {
		Optional<Cliente> optCliente = clienteDao.findById(clienteDtoPK);
		if(optCliente.isPresent()) {
			Cliente cliente = optCliente.get();
			this.clienteDao.delete(cliente);
		}else {
			throw new PosRestError("Cliente no existe en la base de datos", HttpStatus.NOT_FOUND);
		}
	}
	
	public void update(ClienteDto clienteDto) {
			
		Optional<Cliente> optCliente = this.clienteDao.findById(clienteDto.getId());
		
		if (optCliente.isPresent()) {
			
			Cliente clienteEntidad = optCliente.get();
			
			clienteEntidad.setCelular(clienteDto.getCelular());
			clienteEntidad.setCorreoElectronico(clienteDto.getCorreoElectronico());
			clienteEntidad.setFechaNacimiento(clienteDto.getFechaNacimiento());
			clienteEntidad.setNombre(clienteDto.getNombre());
			clienteEntidad.setNumeroHijos(clienteDto.getNumeroHijos());
			
			
			this.clienteDao.save(clienteEntidad);
			
		} else {
		
			throw new PosRestError("Cliente no existe en la base de datos", HttpStatus.NOT_FOUND);
		}
			
	}*/

}
