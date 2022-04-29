package com.example.medicine.service;

import com.example.medicine.model.Reactions;
import com.example.medicine.repository.ReactionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReactionsService {

    private ReactionsRepository reactionsRepository;

    public Reactions createReactions (Reactions reactions) {
        return reactionsRepository.save(reactions);
    }

    public List<Reactions> listAllReactions(){
        return reactionsRepository.findAll();
    }

    public Optional<Reactions> findReactionsById(Long id) {
        return reactionsRepository.findById(id);
    }

    public Optional<Reactions> updateReactinosId(Reactions reactions, Long id){
        return reactionsRepository.findById(id)
                .map(reactionsToUpdate -> {
                    reactionsToUpdate.setDescription(reactions.getDescription());
                    Reactions updated = reactionsRepository.save((reactionsToUpdate));
                    return updated;
                });
    }

    public void deleteById (Long id) {
         reactionsRepository.findById(id)
                .map(reactions -> {
                    reactionsRepository.deleteById(id);
                    return null;
                });
    }
}
