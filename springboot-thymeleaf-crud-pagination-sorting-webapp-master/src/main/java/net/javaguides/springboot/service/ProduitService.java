package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Produit;
import org.springframework.data.domain.Page;

public interface ProduitService {
	List<Produit> getAllProduits();
	void saveProduit(Produit produit);
	Produit getProduitById(long id);
	void deleteProduitById(long id);
	Page<Produit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
