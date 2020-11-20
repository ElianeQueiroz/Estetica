package br.edu.up.dao;

import java.util.List;

//Abstra��o (muito gen�rica)
//� apenas a especifica��o
public interface DAO<T> {
	
	T salvar(T entidade);
	T atualizar(T entidade);
	T buscarPorId(Integer id);
	List<T> listar();
	void apagar(Integer id);

}
