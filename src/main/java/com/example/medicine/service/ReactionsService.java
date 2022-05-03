package com.example.medicine.service;

import com.example.medicine.model.Reactions;
import com.example.medicine.repository.ReactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReactionsService {
    @Autowired
    private ReactionsRepository reactionsRepository;

    public Reactions createReactions (Reactions reactions) {
        System.out.println(reactions);
        System.out.println("AQUI");
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

    public List<Reactions> getFindById(List<Long> reactionsIds) {
        return reactionsIds
                .stream()
                .map(this::findReactionsById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
