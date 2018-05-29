package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodBagType;
import io.cristaling.iss.reddrop.core.BloodRequest;
import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.core.BloodType;
import io.cristaling.iss.reddrop.repositories.BloodBagTypeRepository;
import io.cristaling.iss.reddrop.repositories.BloodRequestRepository;
import io.cristaling.iss.reddrop.repositories.BloodTypeRepository;
import io.cristaling.iss.reddrop.utils.StockUtils;
import io.cristaling.iss.reddrop.utils.enums.BloodRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BloodRequestService {

    BloodRequestRepository requestRepository;
    BloodTypeRepository bloodTypeRepository;
    BloodBagTypeRepository bloodBagTypeRepository;

    BloodBagService bloodBagService;

    @Autowired
    public BloodRequestService(BloodRequestRepository requestRepository, BloodTypeRepository bloodTypeRepository, BloodBagTypeRepository bloodBagTypeRepository, BloodBagService bloodBagService) {
        this.requestRepository = requestRepository;
        this.bloodTypeRepository = bloodTypeRepository;
        this.bloodBagTypeRepository = bloodBagTypeRepository;
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
    public List<BloodRequest> getAllUncompletedBloodRequest() {

        List<BloodRequest> result = requestRepository.getBloodRequestsByStatusIsNot(BloodRequestStatus.COMPLETED);

        result.sort((o1, o2) -> {
            if (o1.getImportance() == o2.getImportance()) {
                return o1.getDate().compareTo(o2.getDate());
            }
            return o2.getImportance().compareTo(o1.getImportance());
        });

        HashMap<BloodType, BloodStock> stock = bloodBagService.getBloodStockAsMap();

        for (BloodRequest bloodRequest : result) {
            BloodType bloodType = bloodTypeRepository.getOne(bloodRequest.getBloodType());
            BloodBagType bloodBagType = bloodBagTypeRepository.getOne(bloodRequest.getBloodBagType());
            if (StockUtils.hasInStock(stock, bloodType, bloodBagType)) {
                StockUtils.removeFromStock(stock, bloodType, bloodBagType, 1);
                bloodRequest.setStatus(BloodRequestStatus.AWAITING_CONFIRMATION);
            }
        }

        return result;
    }

}
