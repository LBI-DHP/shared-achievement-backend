package at.lbg.dhp.sharedachievementbackend.service;

import at.lbg.dhp.sharedachievementbackend.data.dto.SubChallengeDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.SubChallengeIdDTO;
import at.lbg.dhp.sharedachievementbackend.data.models.Challenge;
import at.lbg.dhp.sharedachievementbackend.data.models.SubChallenge;
import at.lbg.dhp.sharedachievementbackend.data.models.SubChallengeId;
import at.lbg.dhp.sharedachievementbackend.data.repository.ChallengeRepository;
import at.lbg.dhp.sharedachievementbackend.data.repository.SubChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SubChallengeService {

    @Autowired
    SubChallengeRepository subChallengeRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    public void createSubChallenge(SubChallengeDTO subChallengeDTO) {

        SubChallenge subChallenge = new SubChallenge();

        subChallenge.setName(subChallengeDTO.getName());
        subChallenge.setSteps(subChallengeDTO.getSteps());

        Challenge challenge = challengeRepository.findById(subChallengeDTO.getChallengeName()).get();
        subChallenge.setChallenge(challenge);

        subChallengeRepository.save(subChallenge);
    }

    public void updateSubChallenge(SubChallengeDTO subChallengeDTO) {
        SubChallengeId id = new SubChallengeId();
        id.setChallenge(subChallengeDTO.getChallengeName());
        id.setName(subChallengeDTO.getName());

        Optional<SubChallenge> subChallenge = subChallengeRepository.findById(id);
        if (subChallenge.isEmpty()) {
            // exception
        } else {
            subChallenge.get().setSteps(subChallengeDTO.getSteps());
            subChallengeRepository.save(subChallenge.get());
        }
    }

    public void deleteSubChallenge(SubChallengeIdDTO subChallengeIdDTO) {
        SubChallengeId id = new SubChallengeId();
        id.setChallenge(subChallengeIdDTO.getChallengeName());
        id.setName(subChallengeIdDTO.getName());
        subChallengeRepository.deleteById(id);
    }

    public SubChallengeDTO getSubChallenge(SubChallengeIdDTO subChallengeIdDTO) {
        SubChallengeId id = new SubChallengeId();
        id.setChallenge(subChallengeIdDTO.getChallengeName());
        id.setName(subChallengeIdDTO.getName());

        SubChallenge subChallenge = subChallengeRepository.findById(id).get();

        SubChallengeDTO subChallengeDTO = new SubChallengeDTO();
        subChallengeDTO.setChallengeName(subChallenge.getChallenge().getName());
        subChallengeDTO.setSteps(subChallenge.getSteps());
        subChallengeDTO.setName(subChallenge.getName());

        return subChallengeDTO;
    }

    public List<SubChallengeDTO> getSubChallenges() {
        List<SubChallenge> subChallenges = subChallengeRepository.findAll();

        List<SubChallengeDTO> subChallengeDTOs = new LinkedList<>();
        for (SubChallenge subChallenge : subChallenges) {
            SubChallengeDTO subChallengeDTO = new SubChallengeDTO();
            subChallengeDTO.setChallengeName(subChallenge.getChallenge().getName());
            subChallengeDTO.setSteps(subChallenge.getSteps());
            subChallengeDTO.setName(subChallenge.getName());

            subChallengeDTOs.add(subChallengeDTO);
        }

        return subChallengeDTOs;
    }

    public List<SubChallengeDTO> getSubChallenges(String challengeName) {
        List<SubChallenge> subChallenges = subChallengeRepository.findAll();

        List<SubChallengeDTO> subChallengeDTOs = new LinkedList<>();
        for (SubChallenge subChallenge : subChallenges) {

            if (subChallenge.getChallenge().getName().equals(challengeName)) {
                SubChallengeDTO subChallengeDTO = new SubChallengeDTO();
                subChallengeDTO.setChallengeName(subChallenge.getChallenge().getName());
                subChallengeDTO.setSteps(subChallenge.getSteps());
                subChallengeDTO.setName(subChallenge.getName());

                subChallengeDTOs.add(subChallengeDTO);
            }
        }

        return subChallengeDTOs;
    }

}
