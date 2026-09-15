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

import at.lbg.dhp.sharedachievementbackend.data.dto.SimpleStepCountDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.StepCountDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.StepCountIdDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.StepCountTodayDTO;
import at.lbg.dhp.sharedachievementbackend.data.models.Person;
import at.lbg.dhp.sharedachievementbackend.data.models.StepCount;
import at.lbg.dhp.sharedachievementbackend.data.models.StepCountId;
import at.lbg.dhp.sharedachievementbackend.data.repository.PersonRepository;
import at.lbg.dhp.sharedachievementbackend.data.repository.StepCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StepCountService {

    @Autowired
    StepCountRepository stepCountRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    NotificationService notificationService;

    public void createStepCount(StepCountDTO stepCountDTO) {

        StepCount stepCount = new StepCount();

        stepCount.setDay(stepCountDTO.getDay());
        stepCount.setSteps(stepCountDTO.getSteps());

        Person person = personRepository.findById(stepCountDTO.getPersonId()).get();
        stepCount.setPerson(person);

        stepCountRepository.save(stepCount);
    }

    public void updateStepCount(StepCountDTO stepCountDTO) {
        StepCountId id = new StepCountId();
        id.setDay(stepCountDTO.getDay());
        id.setPerson(stepCountDTO.getPersonId());
        Optional<StepCount> stepCount = stepCountRepository.findById(id);
        if (stepCount.isEmpty()) {
            // exception
        } else {
            stepCount.get().setSteps(stepCountDTO.getSteps());
            stepCountRepository.save(stepCount.get());
        }

        Person person = personRepository.findById(stepCountDTO.getPersonId()).get();
        notificationService.sendMessageToPerson(person.getId(), "pushed new steps", String.format("%s contributes %d steps.", person.getName(), stepCountDTO.getSteps()));
    }

    public void pushStepCountOfToday(StepCountTodayDTO stepCountTodayDTO) {
        StepCountId id = new StepCountId();
        id.setPerson(stepCountTodayDTO.getPersonId());
        id.setDay(LocalDate.now());

        Optional<StepCount> stepCount = stepCountRepository.findById(id);

        if (stepCount.isPresent()) {
            stepCount.get().setSteps(stepCountTodayDTO.getSteps());
            stepCountRepository.save(stepCount.get());
        } else {
            StepCount newStepCount = new StepCount();
            newStepCount.setDay(LocalDate.now());
            newStepCount.setSteps(stepCountTodayDTO.getSteps());

            Person person = personRepository.findById(stepCountTodayDTO.getPersonId()).get();
            newStepCount.setPerson(person);

            stepCountRepository.save(newStepCount);
        }
        Person person = personRepository.findById(stepCountTodayDTO.getPersonId()).get();
        notificationService.sendMessageToPerson(stepCountTodayDTO.getPersonId(), "pushed new steps", String.format("%s contributes %d steps.", person.getName(), stepCountTodayDTO.getSteps()));
        
    }

    public void deleteStepCount(StepCountIdDTO stepCountIdDTO) {
        StepCountId id = new StepCountId();
        id.setPerson(stepCountIdDTO.getPersonId());
        id.setDay(stepCountIdDTO.getDay());
        stepCountRepository.deleteById(id);
    }

    public StepCountDTO getStepCount(StepCountIdDTO stepCountIdDTO) {
        StepCountId id = new StepCountId();
        id.setPerson(stepCountIdDTO.getPersonId());
        id.setDay(stepCountIdDTO.getDay());

        StepCount stepCount = stepCountRepository.findById(id).get();

        StepCountDTO stepCountDTO = new StepCountDTO();
        stepCountDTO.setPersonId(stepCount.getPerson().getId());
        stepCountDTO.setDay(stepCount.getDay());
        stepCountDTO.setSteps(stepCount.getSteps());

        return stepCountDTO;
    }

    public List<StepCountDTO> getStepCounts() {
        List<StepCount> stepCounts = stepCountRepository.findAll();

        List<StepCountDTO> stepCountDTOs = new LinkedList<>();
        for (StepCount stepCount : stepCounts) {
            StepCountDTO stepCountDTO = new StepCountDTO();
            stepCountDTO.setPersonId(stepCount.getPerson().getId());
            stepCountDTO.setDay(stepCount.getDay());
            stepCountDTO.setSteps(stepCount.getSteps());

            stepCountDTOs.add(stepCountDTO);
        }

        return stepCountDTOs;
    }

    public List<StepCountDTO> getStepCounts(String personId) {
        List<StepCount> stepCounts = stepCountRepository.findAll();

        List<StepCountDTO> stepCountDTOs = new LinkedList<>();
        for (StepCount stepCount : stepCounts) {

            if (stepCount.getPerson().getId().equals(personId)) {
                StepCountDTO stepCountDTO = new StepCountDTO();
                stepCountDTO.setPersonId(stepCount.getPerson().getId());
                stepCountDTO.setDay(stepCount.getDay());
                stepCountDTO.setSteps(stepCount.getSteps());
                stepCountDTOs.add(stepCountDTO);
            }
        }

        return stepCountDTOs;
    }

    public SimpleStepCountDTO getStepCountByPersonIdForToday(String personId) {

        StepCountId id = new StepCountId();
        id.setPerson(personId);
        id.setDay(LocalDate.now());

        Optional<StepCount> stepCount = stepCountRepository.findById(id);

        SimpleStepCountDTO simpleStepCountDTO = new SimpleStepCountDTO();

        if (stepCount.isPresent()) {
            simpleStepCountDTO.setSteps(stepCount.get().getSteps());
        }

        return simpleStepCountDTO;
    }

}
