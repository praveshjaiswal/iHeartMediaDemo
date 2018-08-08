package com.java.ihmdemo.controller;


import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.java.ihmdemo.model.Advertiser;
import com.java.ihmdemo.service.AdvertiserService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class AdvertiserControllerTest {


    private MockMvc mockMvc;

    @Mock
    private AdvertiserService advService;

    
    @InjectMocks
    private AdvertiserController advController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(advController)
                .build();
    }

    
	//--->getAdvertiser ---Optional<Advertiser> comflict with Advertiser   	
  	@Test
	public void testGetAdvertiser() throws Exception {
  		
          when(advService.getAdvertiser("Jai")).thenReturn(new Advertiser("Jai", "Pravesh", (long) 100));

          mockMvc.perform(get("/advertiser/get?id=Jai"))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.advContactName", Matchers.is("Jai")))
                  .andExpect(jsonPath("$.advName", Matchers.is("Pravesh")))
                  .andExpect(jsonPath("$.advCreditLimit", Matchers.is(100)))
                  .andExpect(jsonPath("$.*", Matchers.hasSize(3)));
      }

  	//--->chkAdvertiser 
   	
  	@Test
	public void testChkAdvertiser() throws Exception {
  		
          when(advService.chkAdvertiser("Jai")).thenReturn("Credit Available");

          mockMvc.perform(get("/advertiser/check?id=Jai"))
          .andExpect(status().isOk())
          .andExpect(content().string("Credit Available"));
      }
  	
  	
  	//--->chkAdvertiser    	
  	@Test
	public void testProcessTransaction() throws Exception {
  		
          when(advService.processTransaction("Jai")).thenReturn("Credit Deducted successfully");

          mockMvc.perform(post("/advertiser/processTransaction?id=Jai"))
          .andExpect(status().isOk())
          .andExpect(content().string("Credit Deducted successfully"));
      }
  	
  	
  	//--->deleteAdvertiser    	
  	@Test
	public void testDeleteAdvertiser() throws Exception {
  		
          when(advService.deleteAdvertiser("Jai")).thenReturn("Record Deleted successfully");

          mockMvc.perform(delete("/advertiser/delete?id=Jai"))
          .andExpect(status().isOk())
          .andExpect(content().string("Record Deleted successfully"));
      }
  	
  	
  	//--->deleteAdvertiser --last chk
  	@Test
    public void testAddAdvertiser() throws Exception {

         when(advService.addAdvertiser(new Advertiser("Modi","Smita",(long) 107))).thenReturn("Record added successfully");
         
         String json= "{\"advContactName\":\"Modi\",\"advName\":\"Smita\",\"advCreditlimit\":107}";
         mockMvc.perform(post("/advertiser/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
		        .andExpect(status().isOk());
    }
  	
  	 

   	//working modifing  --->deleteAdvertiser 
   	 @Test
     public void testUpdateAdvertiser() throws Exception {

          when(advService.updateAdvertiser("Modi", new Advertiser("Modi","Smita",(long) 107))).thenReturn("Record added successfully");
          
          
         String json= "{\"advContactName\":\"Modi\",\"advName\":\"Smita\",\"advCreditlimit\":107}";
         mockMvc.perform(put("/advertiser/update?id=Modi")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(json))
 		        .andExpect(status().isOk());
     }    
    
}