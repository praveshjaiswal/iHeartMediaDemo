package com.java.ihmdemo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.ihmdemo.dao.AdvertiserRepo;
import com.java.ihmdemo.model.Advertiser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value= "iHeartMedia Resources", description = "Show Advertiser info")
public class AdvertiserController {

	@Autowired
	AdvertiserRepo repo;

	/*
	@RequestMapping("/")
	@ResponseBody
	public String home(){
		System.out.println("Home");
		return "Home";
	}*/

	
	//To add Advertiser to DB
	@ApiOperation(value ="Adds new Advertiser to database")
	@PostMapping("/advertiser/add")
	@ResponseBody
	public String addAdvertiser(@RequestBody Advertiser advertiser){
		repo.save(advertiser);
		return "Recode added successfully";
	}

	//To update Advertiser to DB
	@ApiOperation(value ="Updates Advertiser to database")
	@PutMapping("/advertiser/update/{id}")
	@ResponseBody
	public String updateAdvertiser(@PathVariable("id") String id, @RequestBody Advertiser advertiser){
		
		String resp;
		System.out.println("Updating record...");
			
		Optional<Advertiser> advertiser1=repo.findById(id);
		if(!advertiser1.isPresent())
			resp = "Error: No record found!";
		else{
			repo.save(advertiser);
			resp = "Recode updated successfully";
		}
		
		return resp;
	}
	
	//To delete Advertiser from DB
	@ApiOperation(value ="Deletes Advertiser from database")
	@DeleteMapping("/advertiser/delete/{id}")
	@ResponseBody
	public String deleteAdvertiser(@PathVariable("id") String id){
		
		String resp;
		System.out.println("Deleting record...");
			
		Optional<Advertiser> advertiser1=repo.findById(id);
		if(!advertiser1.isPresent())
			resp = "Error: No record found!";
		else{
			repo.deleteById(id);
			resp = "Recode deleted successfully";
		}
		
		return resp;
	}

	//To get Advertiser by id
	@ApiOperation(value ="Retrive Advertiser by id from database")
	@GetMapping("/advertiser/get/{id}")
	@ResponseBody
	public Advertiser getAdvertiser(@PathVariable("id") String id){
		
		String resp;
		System.out.println("Searching record...");
			
		Advertiser advertiser=repo.findById(id).orElse(new Advertiser());//or can use Optional also
		return advertiser;
	}


	//To check eligibility by id
	@ApiOperation(value ="Check Advertiser Eligibility")
	@GetMapping("/advertiser/check/{id}")
	@ResponseBody
	public String chkAdvertiser(@PathVariable("id") String id){
		
		String resp;
			
		Optional<Advertiser> advertiser1=repo.findById(id);
		if(!advertiser1.isPresent())
			resp = "Error: No record found!";
		else{
			Long lBal= advertiser1.get().getAdvCreditLimit();
			
			resp = lBal>0?"Credit Available":"Zero Balance Available!";
		}
		return resp;
	}

	
}
