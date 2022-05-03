package com.example.medicine.service;

import com.example.medicine.model.Medicine;
import com.example.medicine.model.Reactions;
import com.example.medicine.repository.MedicineRepository;
import com.example.medicine.service.ReactionsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private ReactionsService reactionsService;

    public Medicine createMedicine (Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public List<Medicine> listAllMedicine(){
        return medicineRepository.findAll();
    }

    public Optional<Medicine> findMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    public void updateMedicineId(Medicine medicine, Long id){
        Optional<Medicine> found = medicineRepository.findById(id);
        if(found.isEmpty())
            return ;
        BeanUtils.copyProperties(medicine,found);
        medicine.setId(id);
        medicine.setReactions(found.get().getReactions());
        medicineRepository.save(medicine);
    }

    public void addReactions(Long id, List<Long> reactionsIds) {
        var reactions = reactionsService.getFindById(reactionsIds);
        Optional<Medicine> found = medicineRepository.findById(id);
        found.get().getReactions().addAll(reactions);
        medicineRepository.save(found.get());
    }

    public void removeReactions(Long id, List<Long> reactionsIds) {
        var reactions = reactionsService.getFindById(reactionsIds);
        Optional<Medicine> found = medicineRepository.findById(id);
        found.get().getReactions().removeAll(reactions);
        medicineRepository.save(found.get());
    }


    public void deleteById (Long id) {
        medicineRepository.findById(id)
                .map(medicine -> {
                    medicineRepository.deleteById(id);
                    return null;
                });
    }
}
