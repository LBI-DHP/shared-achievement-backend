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

import at.lbg.dhp.sharedachievementbackend.data.dto.PersonDTO;
import at.lbg.dhp.sharedachievementbackend.data.models.Person;
import at.lbg.dhp.sharedachievementbackend.data.models.StepCount;
import at.lbg.dhp.sharedachievementbackend.data.repository.PersonRepository;
import at.lbg.dhp.sharedachievementbackend.data.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeamRepository teamRepository;

    public void createPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setExpoToken(personDTO.getExpoToken());
        if (personDTO.getTeamName() != null){
            person.setTeam(teamRepository.findById(personDTO.getTeamName()).get());
        }
            
        person.calculateBMI();
        personRepository.save(person);
    }

    public void updatePerson(PersonDTO personDTO) {
        Optional<Person> person = personRepository.findById(personDTO.getId());
        if (person.isEmpty()) {
            // exception
        } else {
            person.get().setName(personDTO.getName());
            if (personDTO.getTeamName() == null)
                person.get().setTeam(null);
            else {
                person.get().setTeam(teamRepository.findById(personDTO.getTeamName()).get());
            }
            person.get().setExpoToken(personDTO.getExpoToken());
            person.get().calculateBMI();

            personRepository.save(person.get());
        }
    }

    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }

    public PersonDTO getPerson(String id) {
        Person person = personRepository.findById(id).get();

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setExpoToken(person.getExpoToken());
        if (person.getTeam() != null) {
            personDTO.setTeamName(person.getTeam().getName());
        } else {
            personDTO.setTeamName(null);
        }

        return personDTO;
    }

    public List<PersonDTO> getPersons() {
        List<Person> persons = personRepository.findAll();

        List<PersonDTO> personDTOs = new LinkedList<>();
        for (Person person : persons) {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(person.getId());
            personDTO.setName(person.getName());
            personDTO.setExpoToken(person.getExpoToken());
            if (person.getTeam() != null) {
                personDTO.setTeamName(person.getTeam().getName());
            } else {
                personDTO.setTeamName(null);
            }
            personDTOs.add(personDTO);
        }

        return personDTOs;
    }

    public int getStepCountOfPerson(String id) {
        int steps = 0;

        for (StepCount stepCount : personRepository.getById(id).getStepCounts()) {
            steps = steps + stepCount.getSteps();
        }

        return steps;
    }

}
