package com.devsuperior.vinicius.dsvendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.vinicius.dsvendas.dto.SaleDTO;
import com.devsuperior.vinicius.dsvendas.entities.Sale;
import com.devsuperior.vinicius.dsvendas.repositories.SaleRepository;
import com.devsuperior.vinicius.dsvendas.repositories.SellerRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepo;
	
	@Autowired
	private SellerRepository sellerRepo;
	
	//Solução situacional por ter poucos vendedores no banco de dados
	@Transactional(readOnly = true)
	public Page<SaleDTO> findAll(Pageable pageable){
		sellerRepo.findAll();
		
		Page<Sale> result = saleRepo.findAll(pageable);
		
		return result.map(x -> new SaleDTO(x));
	}
}
