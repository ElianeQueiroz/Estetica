package br.edu.up.dao;

import java.util.List;

//Abstração (muito genérica)
//é apenas a especificação
public interface DAO<T> {
	
	T salvar(T entidade);
	T atualizar(T entidade);
	T buscarPorId(Integer id);
	List<T> listar();
	void apagar(Integer id);

}
