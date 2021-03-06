package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

//Diferente do @Controller essa anotação serve para o que o spring conheça
//o objeto só que entendendo que é para acesso a dados

@Repository
@Transactional //Indicando ao Spring é um objeto transacional
public class ProdutoDAO {
	
	// Anotação que serve para que o Spring gerencie o objeto de acesso a dados
	
	@PersistenceContext 
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}
	
	public List<Produto> listar() {
		return manager.createQuery("Select t from Produto t", Produto.class).getResultList();
	}
	
	public Produto obterPorId(Integer id) {
		TypedQuery<Produto> query = manager.createQuery("Select distinct(p) from Produto p "
				+ " join fetch p.precos preco"
				+ " where p.id = :id", Produto.class);
		
		query.setParameter("id", id);
		
		return query.getSingleResult();
		
	}
}
