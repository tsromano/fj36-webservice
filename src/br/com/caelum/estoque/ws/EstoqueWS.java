package br.com.caelum.estoque.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author soa6927
 *
 */
@WebService(targetNamespace="http://caelum.com.br/estoquews/v1")
//@WebService
@Stateless
public class EstoqueWS {
	// simulando um repositorio ou banco de dados
	private Map<String, ItemEstoque> repositorio = new HashMap<>();

	public EstoqueWS() {
		// populando alguns dados, mapeando codigo para quantidade
		repositorio.put("SOA", new ItemEstoque("SOA", 5));
		repositorio.put("TDD", new ItemEstoque("TDD", 1));
		repositorio.put("RES", new ItemEstoque("RES", 2));
		repositorio.put("LOG", new ItemEstoque("LOG", 4));
		repositorio.put("WEB", new ItemEstoque("WEB", 1));
		repositorio.put("ARQ", new ItemEstoque("ARQ", 8));
	}

	// @WebMethod
	// public ItemEstoque getQuantidade(String codigo) {
	// return repositorio.get(codigo);
	// }

	@WebMethod(operationName = "ItensPeloCodigo")
	@WebResult(name = "ItemEstoque")
	public List<ItemEstoque> getQuantidade(@WebParam(name = "codigo") List<String> codigos,
			@WebParam(name = "tokenUsuario", header = true) String token) {
		List<ItemEstoque> itens = new ArrayList<ItemEstoque>();

		if (token == null || !token.equals("TOKEN123")) {
			throw new AutorizacaoException("Nao	autorizado");// vamos gerar essa
																// classe
		}

		if (codigos == null || codigos.isEmpty()) {
			return itens;
		}

		for (String codigo : codigos) {
			itens.add(repositorio.get(codigo));
		}
		return itens;
	}

	//@WebMethod
	//public String addLivro(String codigo, int quantidade) {
	//	repositorio.put(codigo, new ItemEstoque(codigo, quantidade));
	//	System.out.println(codigo);
	//	System.out.println(quantidade);
	//	return ("ok");
	//}

}
