package com.tarikc.ServiceBookingSystem.Controller;


import com.tarikc.ServiceBookingSystem.Services.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/ads")
    public ResponseEntity<?> getAllAds(){
        return ResponseEntity.ok(clientService.getAllAds());
    }
    @GetMapping("/search/{name}")
    public  ResponseEntity<?> searchAdByService(@PathVariable String name){
        return  ResponseEntity.ok(clientService.SearchAdByName(name));
    }


}
