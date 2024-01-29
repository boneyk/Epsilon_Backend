package com.example.finalfinalback3.Controller;

import com.example.finalfinalback3.DTO.PassportAddDTO;
import com.example.finalfinalback3.DTO.PersonalInfoAddDTO;
import com.example.finalfinalback3.Exceptions.AccessException;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService docService;
    private final ModelMapper modelMapper;
    public DocumentController(DocumentService docService, ModelMapper modelMapper) {
        this.docService = docService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity addEmptyDocument(@RequestParam String token){
        return new ResponseEntity(docService.addDocument(token), HttpStatus.CREATED);
    }

    @PostMapping("/personalInfo")
    public ResponseEntity addPersonalInfo(@RequestBody PersonalInfoAddDTO info,
                                          @RequestParam String doc_token){
        docService.addPersonalInfo(info, doc_token);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/passport")
    public ResponseEntity addPassport(@RequestBody PassportAddDTO passport,
                                      @RequestParam String doc_token)
            throws DataNotFoundException, DataAlreadyExistsException {
        docService.addPassport(passport, doc_token);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @PatchMapping("/passport")
    public ResponseEntity changePassport(@RequestBody PassportAddDTO passport,
                                         @RequestParam String doc_token)
            throws DataNotFoundException {
        return new ResponseEntity(docService.changePassport(passport, doc_token), HttpStatus.CREATED);
    }
    @GetMapping()//token - токен пользователя
    public ResponseEntity showDocuments(@RequestParam String token) {
        return new ResponseEntity(docService.showDocuments(token), HttpStatus.OK);
    }

    @GetMapping("/person")
    public ResponseEntity showPersonDocuments(@RequestParam String doc_token) {
        return new ResponseEntity(docService.showPersonDocuments(doc_token), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deletePersonDocuments(@RequestParam String doc_token,
                                                @RequestParam String token) throws AccessException {

        return new ResponseEntity(docService.deletePersonDocument(doc_token, token), HttpStatus.OK);
    }

}
