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

package at.lbg.dhp.sharedachievementbackend.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {


    public enum PhysicalActivity {
        PhysicallyActive,
        ActiveButSedentary,
        SlightlyActive,
        ExtremelySedentary
    }

    public enum Gender {
        Male, Female, Diverse, NoAnswer;
    }


    @Id
    private String id;

    @Column(unique = true)
    private String name;


    @Column
    private String expoToken;



    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<StepCount> stepCounts;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NoAnswer;
    private int age; // age in years
    private int bodyWeight = 80; // weight in kilogtamm
    private int bodyHeight = 170; // height in centimeters
    private float bodyMassIndex;
    
    @Enumerated(EnumType.STRING)
    private PhysicalActivity physicalActivity;

    public void calculateBMI() {
        float height = (float)this.bodyHeight / 100.0f;
        this.bodyMassIndex = bodyWeight / (height*height);
    }

}
