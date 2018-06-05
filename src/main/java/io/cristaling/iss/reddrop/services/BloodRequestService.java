package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.BloodBagType;
import io.cristaling.iss.reddrop.core.BloodRequest;
import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.core.BloodType;
import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import io.cristaling.iss.reddrop.repositories.BloodBagTypeRepository;
import io.cristaling.iss.reddrop.repositories.BloodRequestRepository;
import io.cristaling.iss.reddrop.repositories.BloodTypeRepository;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import io.cristaling.iss.reddrop.utils.StockUtils;
import io.cristaling.iss.reddrop.utils.enums.BloodBagStatus;
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
    BloodBagRepository bloodBagRepository;
    DonatorRepository donatorRepository;

    BloodBagService bloodBagService;
    DonatorService donatorService;
    EmailSenderService emailSenderService;

    @Autowired
    public BloodRequestService(BloodRequestRepository requestRepository,
                               BloodTypeRepository bloodTypeRepository,
                               BloodBagTypeRepository bloodBagTypeRepository,
                               BloodBagRepository bloodBagRepository,
                               DonatorRepository donatorRepository,
                               BloodBagService bloodBagService,
                               DonatorService donatorService,
                               EmailSenderService emailSenderService) {
        this.requestRepository = requestRepository;
        this.bloodTypeRepository = bloodTypeRepository;
        this.bloodBagTypeRepository = bloodBagTypeRepository;
        this.bloodBagRepository = bloodBagRepository;
        this.donatorRepository = donatorRepository;
        this.bloodBagService = bloodBagService;
        this.donatorService = donatorService;
        this.emailSenderService = emailSenderService;
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

        HashMap<BloodType, BloodStock> stock = bloodBagService.getBloodStockAsMap();

        BloodType requestBloodType = bloodTypeRepository.getOne(bloodRequest.getBloodType());
        BloodBagType requestBagType = bloodBagTypeRepository.getOne(bloodRequest.getBloodBagType());

        if (!StockUtils.hasInStock(stock, requestBloodType, requestBagType) && false) {
            List<Donator> donators = donatorRepository.getDonatorsByBloodType(requestBloodType.getUuid());

            Date today = new Date();

            for (Donator donator : donators) {
                Date nextVisit = donatorService.getNextAvailableDate(donator.getUuid());
                if (nextVisit.after(today)) {
                    emailSenderService.askDonatorToDonate(donator.getUuid());
                }
            }
        }

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
            BloodBagType wholeBagType = bloodBagTypeRepository.getBloodBagTypeByName("Whole");
            if (StockUtils.hasInStock(stock, bloodType, bloodBagType)) {
                StockUtils.removeFromStock(stock, bloodType, bloodBagType, 1);
                bloodRequest.setStatus(BloodRequestStatus.AWAITING_CONFIRMATION);
                continue;
            }
            if (StockUtils.hasInStock(stock, bloodType, wholeBagType)) {
                StockUtils.removeFromStock(stock, bloodType, bloodBagType, 1);
                bloodRequest.setStatus(BloodRequestStatus.NEEDS_BREAKDOWN);
                continue;
            }
            bloodRequest.setStatus(BloodRequestStatus.UNRESOLVED);
        }

        return result;
    }

    public void solveBloodRequest(UUID actualUuid) {

        BloodRequest toSolve = requestRepository.getOne(actualUuid);

        BloodType toSolveBloodType = bloodTypeRepository.getOne(toSolve.getBloodType());
        BloodBagType toSolveBagType = bloodBagTypeRepository.getOne(toSolve.getBloodBagType());
        BloodBagType wholeBagType = bloodBagTypeRepository.getBloodBagTypeByName("Whole");

        HashMap<BloodType, BloodStock> stock = bloodBagService.getBloodStockAsMap();

        if (StockUtils.hasInStock(stock, toSolveBloodType, toSolveBagType)) {
            List<BloodBag> bloodBags = bloodBagRepository.getBloodBagsByBloodBagStatusAndBloodBagTypeAndBloodType(BloodBagStatus.DEPOSITED, toSolveBagType.getUuid(), toSolveBloodType.getUuid());
            if (bloodBags.size() < 1) {
                System.out.println("No blood bag");
                return;
            }
            BloodBag toUse = bloodBags.get(0);
            toUse.setBloodBagStatus(BloodBagStatus.USED);
            bloodBagRepository.save(toUse);
            toSolve.setStatus(BloodRequestStatus.COMPLETED);
            requestRepository.save(toSolve);
            return;
        }

        if (toSolve.getBloodBagType().equals(wholeBagType.getUuid())) {
            System.out.println("No blood bag");
            return;
        }

        List<BloodBag> bloodBags = bloodBagRepository.getBloodBagsByBloodBagStatusAndBloodBagTypeAndBloodType(BloodBagStatus.DEPOSITED, wholeBagType.getUuid(), toSolveBloodType.getUuid());

        if (bloodBags.size() < 1) {
            System.out.println("No whole blood bag");
            return;
        }

        BloodBag toUse = bloodBags.get(0);
        toUse.setBloodBagStatus(BloodBagStatus.USED);
        bloodBagRepository.save(toUse);
        toSolve.setStatus(BloodRequestStatus.COMPLETED);
        requestRepository.save(toSolve);

        List<BloodBagType> newBagTypes = bloodBagTypeRepository.findAll();
        newBagTypes.remove(wholeBagType);
        newBagTypes.remove(toSolveBagType);

        for (BloodBagType bloodBagType : newBagTypes) {
            BloodBag bloodBag = new BloodBag();

            bloodBag.setUuid(UUID.randomUUID());
            bloodBag.setBloodBagStatus(BloodBagStatus.DEPOSITED);

            bloodBag.setBloodType(toSolveBloodType.getUuid());
            bloodBag.setBloodBagType(bloodBagType.getUuid());

            Date date = new Date();
            date.setTime(date.getTime() + bloodBagType.getDaysToExpire() * 86400000);
            bloodBag.setExpireDate(date);

            bloodBagRepository.save(bloodBag);
        }

    }
}
