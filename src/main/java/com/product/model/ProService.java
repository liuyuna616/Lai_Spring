package com.product.model;

import java.sql.Timestamp;
import java.util.List;

public class ProService {
	
	private ProDAO_interface dao;
	
	public ProService() {
		dao = new ProDAO();
	}

	public ProVO addPro(Integer productId,Integer supplierId,String productName,String productContent,
			Integer price,String productSpec,Integer stock,Integer productType,Timestamp createdTime,
			Timestamp updatedTime,Timestamp updatedAt) {
		
		ProVO proVO = new ProVO();
		
		proVO.setProductId(productId);
		proVO.setSupplierId(supplierId);
		proVO.setProductName(productName);
		proVO.setProductContent(productContent);
		proVO.setPrice(price);
		proVO.setProductSpec(productSpec);
		proVO.setStock(stock);
		proVO.setCreatedTime(createdTime);
		proVO.setUpdatedTime(updatedTime);
		proVO.setProductType(productType);
		proVO.setUpdatedAt(updatedAt);
		dao.insert(proVO);
		
		return proVO;
	}
    
	public ProVO updatePro(Integer productId,Integer supplierId,String productName,String productContent,
			Integer price,String productSpec,Integer stock,Integer productType,Timestamp createdTime,
			Timestamp updatedTime,Timestamp updatedAt,byte[] picture) {
		
		ProVO proVO = new ProVO();

		proVO.setProductId(productId);
		proVO.setSupplierId(supplierId);
		proVO.setProductName(productName);
		proVO.setProductContent(productContent);
		proVO.setPrice(price);
		proVO.setProductSpec(productSpec);
		proVO.setStock(stock);
		proVO.setCreatedTime(createdTime);
		proVO.setUpdatedTime(updatedTime);
		proVO.setProductType(productType);
		proVO.setUpdatedAt(updatedAt);
		proVO.setPicture(picture);
		
		dao.insert(proVO);
		
		return proVO;
		
	}
	
	public void deletePro(Integer productId) {
		dao.delete(productId);
	}
	
	public ProVO getProductById(Integer productId) {
		return dao.getProductById(productId);
	}
	
	public List<ProVO> getAll(){
		return dao.getAll();
	}
	public List<ProVO> getPopular(){
		return dao.getPopular();
	}
	public List<ProVO> getPrice_LtoH(){
		return dao.getPrice_LtoH();
	}
	public List<ProVO> getNew(){
		return dao.getNew();
	}
	
	public List<ProVO> getSearchnam(String productName){
		return dao.getSearchnam(productName);
	}
		

	public List<ProVO> getOverviewPicture(Integer productId){
		return dao.getOverviewPicture(productId);
	}
	
	public List<ProVO> getAd(){
		return dao.getAd();
	}
}
