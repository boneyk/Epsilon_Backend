package com.example.finalfinalback3.Service;

import com.example.finalfinalback3.DTO.PassportAddDTO;
import com.example.finalfinalback3.DTO.PersonalInfoAddDTO;
import com.example.finalfinalback3.Entity.DocumentEntity;
import com.example.finalfinalback3.Entity.PassportEntity;
import com.example.finalfinalback3.Entity.UserEntity;
import com.example.finalfinalback3.Exceptions.AccessException;
import com.example.finalfinalback3.Exceptions.DataAlreadyExistsException;
import com.example.finalfinalback3.Exceptions.DataNotFoundException;
import com.example.finalfinalback3.Model.DocPersonalInfo;
import com.example.finalfinalback3.Model.PersonalDocuments;
import com.example.finalfinalback3.Model.Token;
import com.example.finalfinalback3.Repository.DocumentRepository;
import com.example.finalfinalback3.Repository.PassportRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository docRepo;
    private final PassportRepository passportRepo;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public DocumentService(DocumentRepository docRepo, PassportRepository passportRepo, UserService userService, ModelMapper modelMapper) {
        this.docRepo = docRepo;
        this.passportRepo = passportRepo;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public DocumentEntity saveDoc(DocumentEntity doc){
        return docRepo.save(doc);
    }
    public DocumentEntity getPersonById(Integer id) throws DataNotFoundException{
        if (docRepo.findById(id).isEmpty()){
            throw new DataNotFoundException("Документов не найдено");
        }
        return docRepo.findById(id).get();
    }

    public DocumentEntity getPersonByToken(String token) throws DataNotFoundException{
        if (docRepo.findByToken(token) == null){
            throw new DataNotFoundException("Такая личность не найдена");
        }
        return docRepo.findByToken(token);
    }

    public Token addDocument(String token) throws DataNotFoundException{
        UserEntity user = userService.getUserByToken(token);
        DocumentEntity empty_doc = new DocumentEntity();
        //DocumentEntity new_doc = modelMapper.map(empty_doc, DocumentEntity.class);
        empty_doc.setUser(user);
        empty_doc.setToken(RandomString.make(16));
        DocumentEntity doc = docRepo.save(empty_doc);
        List<DocumentEntity> doc_list = user.getDoc();
        doc_list.add(doc);
        user.setDoc(doc_list);
        userService.saveUser(user);
        return new Token(doc.getToken());
    }

    public PersonalInfoAddDTO addPersonalInfo(PersonalInfoAddDTO info, String doc_token) throws DataNotFoundException{
        DocumentEntity doc = getPersonByToken(doc_token);
        doc.setFullname(info.getFullname());
        doc.setPhone_number(info.getPhone_number());
        return modelMapper.map(docRepo.save(doc), PersonalInfoAddDTO.class);
    }

    public PassportEntity addPassport(PassportAddDTO passport, String doc_token) throws DataNotFoundException,
            DataAlreadyExistsException {

        DocumentEntity doc = getPersonByToken(doc_token);
            if (doc == null){
                throw new DataNotFoundException("Прежде, чем добавлять паспорт, необходимо сохранить документ");
            }
            if (doc.getPassport() != null){
                throw new DataAlreadyExistsException("Данному пользователю уже был добавлен паспорт");
            }
            PassportEntity new_passport = new PassportEntity(passport);//modelMapper.map(passport, PassportEntity.class);
            new_passport.setDoc(doc);//Проверить, нужен ли этот пункт TODO
            PassportEntity new_passport_entity = passportRepo.save(new_passport);
            doc.setPassport(new_passport_entity);
            docRepo.save(doc);
        //userService.saveUser(user);

        return new_passport_entity;
    }

    public List<DocPersonalInfo> showDocuments(String token) throws DataNotFoundException{
        UserEntity user = userService.getUserByToken(token);
        List<DocumentEntity> doc_list = user.getDoc();
        if (doc_list.isEmpty()){
            throw new DataNotFoundException("Документов пока нет!");
        }
        List<DocPersonalInfo> new_doc_list =  Streamable.of(doc_list)
                .stream()
                .map(doc -> modelMapper.map(doc, DocPersonalInfo.class))
                .toList();
        return new_doc_list;
    }

    public PersonalDocuments showPersonDocuments(String doc_token) throws DataNotFoundException{
        DocumentEntity doc = getPersonByToken(doc_token);
        return modelMapper.map(doc, PersonalDocuments.class);
    }

    public List<DocPersonalInfo> deletePersonDocument(String doc_token, String token)
            throws DataNotFoundException,
                    AccessException{
        DocumentEntity doc = getPersonByToken(doc_token);
        UserEntity user = userService.getUserByToken(token);
        List<DocumentEntity> user_docs = user.getDoc();
        if (!user_docs.contains(doc)){
            throw new AccessException("Эти документы не принадлежат данному пользователю");
        }
        user_docs.remove(doc);
        user.setDoc(user_docs);
        userService.saveUser(user);
        docRepo.delete(doc);
        return Streamable.of(user_docs)
                .stream()
                .map(document -> modelMapper.map(document, DocPersonalInfo.class))
                .toList();
    }

    //Не надо меня пилить за это ржавой ножовкой. Лучше расскажите, как правильно
    public PassportEntity changePassport(PassportAddDTO passport, String doc_token)
            throws DataNotFoundException {
     DocumentEntity doc = getPersonByToken(doc_token);
     PassportEntity old_passport = doc.getPassport();

     if (passport.getDob() != null) old_passport.setDob(passport.getDob());
     if (passport.getSex() != null) old_passport.setSex(passport.getSex());
     if (passport.getWg() != null) old_passport.setWg(passport.getWg());
     if (passport.getDog() != null) old_passport.setDog(passport.getDog());
     if (passport.getCitizenship() != null) old_passport.setCitizenship(passport.getCitizenship());
     if (passport.getNumber() != null) old_passport.setNumber(passport.getNumber());
     if (passport.getSerial() != null) old_passport.setSerial(passport.getSerial());
     if (passport.getRegistration() != null) old_passport.setRegistration(passport.getRegistration());
     if (passport.getFullname() != null) old_passport.setFullname(passport.getFullname());

     doc.setPassport(old_passport);
     docRepo.save(doc);
     return old_passport;

    }

    @Transactional
    public void deletePassport(String doc_token){
        DocumentEntity doc = getPersonByToken(doc_token);
        PassportEntity passport = doc.getPassport();
        doc.setPassport(null);
        docRepo.save(doc);
        passportRepo.delete(passport);
    }
}
