package com.stefanini.hackaton.api;



import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.dto.LoginDto;
import com.stefanini.hackaton.rest.exceptions.NegocioException;
import com.stefanini.hackaton.service.BatalhaService;
import com.stefanini.hackaton.service.JogadorService;

@Path("/jogador")
//@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class JogadorApi {

	private Map<String, List<String>> resultado = new HashMap<>();
	
	@Inject
	private JogadorService service;
	
	@Inject
	private BatalhaService btService;
	
	@POST
	public Response save(LoginDto dto) throws NegocioException {
		if(dto.getPersonagem() == null) {throw new NegocioException("É necessário Selecionar ao menos 1 Héroi !!!!");}
		if (dto.getSenha().length() < 6 || dto.getSenha().length() > 8) {
			throw new NegocioException("A senha deve conter entre 6 a 8 caracteres");
		}else {
			//Base64
			String encodedString = Base64.getEncoder().encodeToString(dto.getSenha().getBytes());
			dto.setSenha(encodedString);
			service.save(dto);	
			dto.setNickname(dto.getNickname());			
			return Response.ok(dto).build();
		}
	}
	
	@GET
	public Response findAll() {	
		return Response.ok(service.findAll()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Integer id) throws NegocioException{
		
		return Response.ok(service.findById(id)).build();
		
	}
	
	
	@POST
	@Path("/autenticar")
	public Response login(LoginDto dto) throws NegocioException{
		return Response.ok(service.login(dto)).build();	
	}
	
	
	@POST
	@Path("/batalhar/{player1}/{player2}")
	public Response batalha( @PathParam("player1") Integer player1, @PathParam("player2") Integer player2 ) {
		Random idAleatorio = new Random();
		String result;
	
		
		result = btService.batalhar(player1, player2);
		resultado.put(result, btService.getLog());
		
		return Response.ok(resultado).build();
	}
	
	
}
