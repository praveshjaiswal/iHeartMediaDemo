package com.java.ihmdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.java.ihmdemo.model.Advertiser;
import com.java.ihmdemo.service.AdvertiserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/advertiser")
public class AdvertiserController {

	@Autowired
    private AdvertiserService advService;

	//###################Resources##############
	
	
	//To get Advertiser by id
	//@ApiOperation(value ="Retrive Advertiser by id from database")
	@GetMapping(value= "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public Advertiser getAdvertiser(@RequestParam(value = "id", required =true, defaultValue="") String id){
		Advertiser adv= advService.getAdvertiser(id);
		return adv;
		
	}
	
	
	//To check eligibility by id
	@ApiOperation(value ="Check Advertiser Eligibility")
	@GetMapping("/check")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Process"),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			
	})
	public String chkAdvertiser(@RequestParam(value = "id", required =true, defaultValue="") String id){
		String resp=advService.chkAdvertiser(id);
		return resp; 
	}

	//To update Advertiser to DB
	@ApiOperation(value ="Process Transaction")
	@PostMapping("/processTransaction")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Process"),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			
	})
	@ResponseBody
	public String processTransaction(@RequestParam(value = "id", required =true, defaultValue="") String id){
		return advService.processTransaction(id);
	}
	
	//To add Advertiser to DB
	@ApiOperation(value ="Adds new Advertiser to database")
	@PostMapping("/add")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Process"),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			
	})
	@ResponseBody
	public String addAdvertiser(@RequestBody Advertiser advertiser){
		return advService.addAdvertiser(advertiser);
	}

	//To update Advertiser to DB
	@ApiOperation(value ="Updates Advertiser to database")
	@PutMapping("/update")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Process"),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			
	})
	@ResponseBody
	public String updateAdvertiser(@RequestParam(value = "id", required =true, defaultValue="") String id, @RequestBody Advertiser advertiser){
		return advService.updateAdvertiser(id, advertiser);
	}
	
	//To delete Advertiser from DB
	@ApiOperation(value ="Deletes Advertiser from database")
	@DeleteMapping("/delete")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Process"),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			
	})
	@ResponseBody
	public String deleteAdvertiser(@RequestParam(value = "id", required =true, defaultValue="") String id){
		return advService.deleteAdvertiser(id);
	}
	//###################Resources##############
    
   
}
