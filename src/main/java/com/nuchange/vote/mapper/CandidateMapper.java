package com.nuchange.vote.mapper;

import com.nuchange.vote.dto.CandidateDto;
import com.nuchange.vote.entity.Candidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    @Autowired
    public ModelMapper modelMapper;

    public CandidateDto convertToDto(Candidate candidate){
        return modelMapper.map(candidate,CandidateDto.class);
    }

}
