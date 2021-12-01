package at.lbg.dhp.sharedachievementbackend.service;

import at.lbg.dhp.sharedachievementbackend.data.dto.ChallengeDTO;
import at.lbg.dhp.sharedachievementbackend.data.models.Challenge;
import at.lbg.dhp.sharedachievementbackend.data.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;

    public void createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setName(challengeDTO.getName());
        challenge.setSteps(challengeDTO.getSteps());
        challengeRepository.save(challenge);
    }

    public void updateChallenge(ChallengeDTO challengeDTO) {
        Optional<Challenge> challenge = challengeRepository.findById(challengeDTO.getName());
        if (challenge.isEmpty()) {
            // exception
        } else {
            challenge.get().setSteps(challengeDTO.getSteps());
            challengeRepository.save(challenge.get());
        }
    }

    public void deleteChallenge(String name) {
        challengeRepository.deleteById(name);
    }

    public ChallengeDTO getChallenge(String name) {
        Challenge challenge = challengeRepository.findById(name).get();

        ChallengeDTO challengeDTO = new ChallengeDTO();
        challengeDTO.setName(challenge.getName());
        challengeDTO.setSteps(challenge.getSteps());

        return challengeDTO;
    }

    public List<ChallengeDTO> getChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();

        List<ChallengeDTO> challengeDTOs = new LinkedList<>();
        for (Challenge challenge : challenges) {
            ChallengeDTO challengeDTO = new ChallengeDTO();
            challengeDTO.setName(challenge.getName());
            challengeDTO.setSteps(challenge.getSteps());
            challengeDTOs.add(challengeDTO);
        }

        return challengeDTOs;
    }
}
