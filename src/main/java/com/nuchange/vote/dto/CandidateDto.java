package com.nuchange.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto{

        private Integer candidateId;
        private String candidateName;
        private Integer voteCount;
}
