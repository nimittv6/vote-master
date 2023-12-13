package com.nuchange.vote.services;

import com.nuchange.vote.dto.CandidateDto;
import com.nuchange.vote.entity.Candidate;
import com.nuchange.vote.mapper.CandidateMapper;
import com.nuchange.vote.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotingService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidateMapper candidateMapper;

    public CandidateDto save(String candidateName,Integer id){
         if(candidateRepository.findById(id).isEmpty()) {
             Candidate candidate = new Candidate();
             candidate.setCandidateName(candidateName);
             candidate.setVoteCount(0);
             candidate.setCandidateId(id);
             candidateRepository.save(candidate);
             return candidateMapper.convertToDto(candidate);
         }
         else throw new RuntimeException(" Duplicate ID");
    }


    public CandidateDto updateVote(Integer id) {
        Optional<Candidate> candidate  = candidateRepository.findById(id);
        if(candidate.isPresent()) {
            candidate.get().setVoteCount(candidate.get().getVoteCount()+1);
            candidateRepository.save(candidate.get());
            return candidateMapper.convertToDto(candidate.get());
        }
        else throw new RuntimeException(" Duplicate ID");
    }

    public CandidateDto countVote(Integer id) {
        Optional<Candidate> candidate  = candidateRepository.findById(id);
        if(candidate.isPresent()) {
            return candidateMapper.convertToDto(candidate.get());
        }
        else throw new RuntimeException("Exception");
    }

    public List<CandidateDto> findAllCandidates() {
        List<Candidate> candidateList = candidateRepository.findAll();
        List<CandidateDto> candidateDtoList = new ArrayList<>();
        for (Candidate candidate: candidateList){
            candidateDtoList.add(candidateMapper.convertToDto(candidate));

        }
        return candidateDtoList;
    }

    public CandidateDto findWinner() {
        List<Candidate> candidateList = candidateRepository.findAll();
        Optional<Candidate> candidate =candidateList.stream().max(Comparator.comparing(Candidate::getVoteCount));
        if(candidate.isPresent()){
           return  candidateMapper.convertToDto(candidate.get());
        }
        else throw new RuntimeException(" No Voting has been done");
    }
}
