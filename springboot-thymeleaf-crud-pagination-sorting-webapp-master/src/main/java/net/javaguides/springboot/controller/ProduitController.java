package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Produit;
import net.javaguides.springboot.service.ProduitService;

@Controller
public class ProduitController {

	@Autowired
	private ProduitService produitService;
	
	// display list of produits
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listProduit", produitService.getAllProduits());
		//return findPaginated(1, "firstName", "asc", model);
		return "index";
	}
	
	@GetMapping("/showNewProduitForm")
	public String showNewproduitForm(Model model) {
		// create model attribute to bind form data
		Produit produit = new Produit();
		model.addAttribute("produit", produit);
		return "new_produit";
	}
	
	@PostMapping("/saveProduit")
	public String saveProduit(@ModelAttribute("produit") Produit produit) {
		// save produit to database
		produitService.saveProduit(produit);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get produit from the service
		Produit produit = produitService.getProduitById(id);
		
		// set produit as a model attribute to pre-populate the form
		model.addAttribute("produit", produit);
		return "update_produit";
	}
	
	@GetMapping("/deleteProduit/{id}")
	public String deleteProduit(@PathVariable (value = "id") long id) {
		
		// call delete produit method
		this.produitService.deleteProduitById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Produit> page = produitService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Produit> listproduits = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listproduits", listproduits);
		return "index";
	}
}
