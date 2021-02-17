package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.repository.ProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService {

	@Autowired
	private ProduitRepository produitRepository;

	@Override
	public List<Produit> getAllProduits() {
		return produitRepository.findAll();
	}

	@Override
	public void saveProduit(Produit produit) {
		this.produitRepository.save(produit);
	}

	@Override
	public Produit getProduitById(long id) {
		Optional<Produit> optional = produitRepository.findById(id);
		Produit produit = null;
		if (optional.isPresent()) {
			produit = optional.get();
		} else {
			throw new RuntimeException(" produit not found for id :: " + id);
		}
		return produit;
	}

	@Override
	public void deleteProduitById(long id) {
		this.produitRepository.deleteById(id);
	}

	@Override
	public Page<Produit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.produitRepository.findAll(pageable);
	}
}
