package com.java.ihmdemo.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.java.ihmdemo.dao.AdvertiserRepo;
import com.java.ihmdemo.model.Advertiser;

/*
 * 	ServiceLayer for API Demo
 * 	@author Pravesh Jaiswal <praveshjaiswal@gmail.com>
 * 	Date: 08/06/2018
 * 
 */

@Component
public class AdvertiserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		    
    String resp;
    
    @Autowired
	AdvertiserRepo repo;
	
	public String addAdvertiser(Advertiser advertiser){
		
		try{
			repo.save(advertiser);
		}catch(Exception ex)
		{
			logger.info("Error: "+ex.getMessage());
			throw new RuntimeException("Invalid data");
		}
		
		resp = "Record added successfully";
		return resp;
	}
	
	public String updateAdvertiser(String id, Advertiser advertiser){
		
		logger.info("Updating record...");
			
		Optional<Advertiser> advertiser1=repo.findById(id);

		if(!advertiser1.isPresent() || !id.equalsIgnoreCase(advertiser.getAdvContactName())){
			resp = "Error: Record not matched!";
			throw new RuntimeException("Record not matched!");
		}else{
			try{
				repo.save(advertiser);
			}catch(Exception ex)
			{
				logger.info("Error: "+ex.getMessage());
				throw new RuntimeException("Invalid data");
			}
			resp = "Record updated successfully";
		}
		
		return resp;
	}
	
	
	public Advertiser getAdvertiser(String id)	
	{
		logger.info("Searching record for: "+id);
			
		
		Advertiser advertiser=repo.findById(id).orElse(new Advertiser("","",(long)0));//or can use Optional also
		//Optional<Advertiser> advertiser=repo.findById(id);
		
		if(advertiser==null){
			logger.info("No Record found!");
			throw new RuntimeException("No record found!");
		}
		else{
			logger.info("Record found.");
			
		}
		
		return advertiser;
	}
	

	public String chkAdvertiser(String id){
			
		Optional<Advertiser> advertiser1=repo.findById(id);
		if(!advertiser1.isPresent())
			throw new RuntimeException("No record found!");
		else{
			
			Long lBal= advertiser1.get().getAdvCreditLimit();
			
			resp = lBal>0?"Credit Available":"Zero Balance Available!";
		}
		
	
		return resp;
	}
	
	public String processTransaction(String id){
		logger.info("Deducting credit...");
			
		Advertiser advertiser=repo.findById(id).orElse(null);
		
		if(advertiser!=null){
			try{
				if(advertiser.getAdvCreditLimit()-10 > 0){
					advertiser.setAdvCreditLimit(advertiser.getAdvCreditLimit()-10);
					repo.save(advertiser);
					resp = "Credit Deducted successfully";

				}else{
					throw new RuntimeException("Low Balance");
				}
		
			}catch(Exception ex)
			{

				logger.info("Error: "+ex.getMessage());
				throw new RuntimeException("Invalid data");
			}
		
		}else{

			resp = "Error: No record found!";
			throw new RuntimeException("No record found!");
		}

		return resp;
	}
	
	public String deleteAdvertiser(String id){
		logger.info("Deleting record...");
			
		Optional<Advertiser> advertiser1=repo.findById(id);
		if(!advertiser1.isPresent()){
			resp = "Error: No record found!";
			throw new RuntimeException("No record found!");
		}
		else{
			repo.deleteById(id);
			resp = "Record deleted successfully";
		}
		
		return resp;
	}
	
}
