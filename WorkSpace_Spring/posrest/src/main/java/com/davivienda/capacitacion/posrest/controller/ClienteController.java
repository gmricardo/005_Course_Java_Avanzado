package com.davivienda.capacitacion.posrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davivienda.capacitacion.posrest.dto.ClienteDto;
import com.davivienda.capacitacion.posrest.modelo.ClientePK;
import com.davivienda.capacitacion.posrest.service.ClienteService;

@RestController
@RequestMapping("/client")
public class ClienteController {
	@Autowired
	private ClienteService clientSvc;
	
	
	@GetMapping("/find/{cedula}/{tipo}")
	public ClienteDto findByPrimaryKey(@PathVariable("cedula") String cedula, @PathVariable("tipo") String tipo) {
		ClientePK id = new ClientePK();
		id.setNroIdentificacion(cedula);
		id.setTipoIdentificacion(tipo);
		ClienteDto cliente = clientSvc.findByPrimaryKey(id);
		return cliente;
	}

	@GetMapping("/findAll")
	public List<ClienteDto> findAll() {
		List<ClienteDto> lstClientes = this.clientSvc.findAll(); 
		return lstClientes;
	}
	
	@PostMapping("/create")
	public void create(@RequestBody ClienteDto cliente) {
		this.clientSvc.create(cliente);
	}
	
	/*@PostMapping("/delete")
	public void delete(@RequestBody ClienteDto cliente) {
		this.clientSvc.delete(cliente.getId());
	}
	
	@PostMapping("/update")
	public void update(@RequestBody ClienteDto cliente) {
		this.clientSvc.update(cliente);
	}*/
}
