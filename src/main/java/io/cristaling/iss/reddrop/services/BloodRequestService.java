package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodRequest;
import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import io.cristaling.iss.reddrop.repositories.BloodRequestRepository;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import io.cristaling.iss.reddrop.utils.BloodRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BloodRequestService {

    BloodRequestRepository requestRepository;
    BloodBagService bloodBagService;

    @Autowired
    public BloodRequestService(BloodRequestRepository requestRepository, BloodBagService bloodBagService) {
        this.requestRepository = requestRepository;
        this.bloodBagService = bloodBagService;
    }

    public void deleteBloodRequest(UUID uuid) {
        requestRepository.deleteById(uuid);
    }

    public void registerBloodRequest(BloodRequest bloodRequest) {
        //TODO Validate Request
        if (bloodRequest.getUuid() == null) {
            bloodRequest.setUuid(UUID.randomUUID());
        }
        if (bloodRequest.getDate() == null) {
            bloodRequest.setDate(new Date());
        }
        if (bloodRequest.getStatus() == null) {
            bloodRequest.setStatus(BloodRequestStatus.UNRESOLVED);
        }
        requestRepository.save(bloodRequest);
    }

    public List<BloodRequest> getBloodRequestByDoctor(UUID doctorID) {
        return requestRepository.getBloodRequestsByDoctor(doctorID);
    }

    //TODO Check stocks and mark status
    public List<BloodRequest> getAllBloodRequest() {
        List<BloodRequest> result = requestRepository.findAll();
        result.sort(new Comparator<BloodRequest>() {
            @Override
            public int compare(BloodRequest o1, BloodRequest o2) {
                if (o1.getImportance() == o2.getImportance()) {
                    return o1.getDate().compareTo(o2.getDate());
                }
                return o2.getImportance().compareTo(o1.getImportance());
            }
        });

        List<BloodStock> stock = bloodBagService.getBloodStocks();

        return result;
    }

}
