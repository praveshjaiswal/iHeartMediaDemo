package com.java.ihmdemo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.ihmdemo.dao.AdvertiserRepo;
import com.java.ihmdemo.model.Advertiser;
import com.java.ihmdemo.service.AdvertiserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/*
 * 	Controller for API Demo
 * 	@author Pravesh Jaiswal <praveshjaiswal@gmail.com>
 * 	Date: 08/06/2018
 * 
 */


@Controller
@Api(value= "iHeartMedia Resources", description = "Show Advertiser info")
public class AdvertiserController {

	@Autowired
	private AdvertiserService advService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AdvertiserRepo repo;


	//To get Advertiser by id
	@ApiOperation(value ="Retrive Advertiser by id from database")
	@GetMapping("/advertiser/get")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Process"),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			
	})
	@ResponseBody
	public Optional<Advertiser> getAdvertiser(@RequestParam(value = "id", required =true, defaultValue="") String id){
		
		return advService.getAdvertiser(id);//getString();
		
	}
	
	//To check eligibility by id
	@ApiOperation(value ="Check Advertiser Eligibility")
	@GetMapping("/advertiser/check")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Process"),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			
	})
	@ResponseBody
	public String chkAdvertiser(@RequestParam(value = "id", required =true, defaultValue="") String id){
		
		return advService.chkAdvertiser(id);//getString();
		
	}

	//To update Advertiser to DB
	@ApiOperation(value ="Process Transaction")
	@PostMapping("/advertiser/processTransaction")
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
	@PostMapping("/advertiser/add")
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
	@PutMapping("/advertiser/update")
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
	@DeleteMapping("/advertiser/delete")
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

		
		
	
}
