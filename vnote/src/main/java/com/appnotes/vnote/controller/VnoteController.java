package com.appnotes.vnote.controller;

import com.appnotes.vnote.DTO.ContentDTO;
import com.appnotes.vnote.DTO.VnotedataDTO;
import com.appnotes.vnote.model.Vnotedata;
import com.appnotes.vnote.service.Vnoteservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VnoteController {

    @Autowired
    private Vnoteservice vnoteservice;

    @GetMapping("/getnote")
    public List<Vnotedata> getnote(){
        return vnoteservice.getnote();
    }

    @GetMapping("/getnotebyid/{id}")
    public ResponseEntity<Vnotedata> getnotebyid (@PathVariable("id") long id){
        return new ResponseEntity<Vnotedata>(vnoteservice.getnotebyid(id), HttpStatus.OK);
    }

    @PostMapping("/savenote")
    public ResponseEntity<Vnotedata> savenotes (@RequestBody Vnotedata vnotedata){
        return new ResponseEntity<Vnotedata>(vnoteservice.savenote(vnotedata),HttpStatus.CREATED);
    }

    @PutMapping("/updatenotebyid/{id}")
    public ResponseEntity<Vnotedata> updatenotebyid(@PathVariable("id") long id, VnotedataDTO vnotedataDTO){
        return new ResponseEntity<Vnotedata> (vnoteservice.updatenotebyid(vnotedataDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("/deletenotebyid/{id}")
    public ResponseEntity<String> deletenotebyid(@PathVariable("id") long id){
        vnoteservice.deletenotebyid(id);
        return new ResponseEntity<String>(id +" deleted",HttpStatus.OK);
    }

    @GetMapping("/getnote/{topic}")
    public ResponseEntity<?> getAllEmployeesInCsv(@PathVariable ("topic") String topic,
                                                  HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\""+topic+".csv\"");
        vnoteservice.getbytopic(topic,servletResponse.getWriter());
        return null;
    }

    @PutMapping("/editcontent/{id}")
    public  ResponseEntity<?> editcontent (@PathVariable("id") long id, ContentDTO contentDTO){
        return  new ResponseEntity<>(vnoteservice.editcontent(contentDTO,id),HttpStatus.OK);
    }

    @GetMapping("/getnoteresponse/{topic}")
    public ResponseEntity<?> getbytopicres(@PathVariable ("topic") String topic){
        return new ResponseEntity<>(vnoteservice.getbytopicres(topic),HttpStatus.OK);
    }
    
//    @GetMapping("/index")
//    public String showUserList(Model model) {
//        String message="You just create Spring Boot Thymeleaf Example successfully";
//        return "index";
//    }

}
