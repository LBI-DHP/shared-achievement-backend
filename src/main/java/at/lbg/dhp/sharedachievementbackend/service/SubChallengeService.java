/*
 * Copyright (c) 2021-2022 Ludwig Boltzmann Institute for Digital Health and Prevention
 *
 * Licensed under the Apache License, Version 2.0 with the Commons Clause License
 * Condition v1.0 (the "License"); you may not use this file except in compliance
 * with the License. A copy of the License is distributed in the LICENSE file at
 * the root of this repository; the Apache License is also available at
 * http://www.apache.org/licenses/LICENSE-2.0 and the Commons Clause condition at
 * https://commonsclause.com/
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: LicenseRef-Apache-2.0-WITH-Commons-Clause
 */

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
