package com.nuchange.vote.controller;

import com.nuchange.vote.dto.CandidateDto;
import com.nuchange.vote.services.VotingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/votingSystem")
public class VotingController {

    @Autowired
    VotingService votingService;

@Operation(
        summary = "Register Candidate",
        description = "Saving Candidate into DB, and intializing the vote Count to 0")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Candidate Created")
})
@PostMapping("/entercandidate")
    public ResponseEntity<CandidateDto> saveCandidate(@RequestParam String candidateName, @RequestParam Integer id){
        CandidateDto candidateDto = votingService.save(candidateName,id);
        return new ResponseEntity<>(candidateDto, HttpStatus.CREATED);
}

    @Operation(
            summary = "updating  vote casted for each candidates",
            description = "Updating vote count  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted")
    })
    @PutMapping("/castvote")
    public ResponseEntity<CandidateDto> updateVoteCount(@RequestParam Integer id){
        CandidateDto candidateDto = votingService.updateVote(id);
        return new ResponseEntity<>(candidateDto, HttpStatus.ACCEPTED);
    }

    @Operation(
            summary = "Counting vote casted for individual candidate",
            description = "Counting Vote  ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @GetMapping("/countvote")
    public ResponseEntity<CandidateDto> countVote(@RequestParam Integer id){
        CandidateDto candidateDto = votingService.countVote(id);
        return new ResponseEntity<>(candidateDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Fetching details of all candidates",
            description = "Fetching details of all candidates ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @GetMapping("/listvote")
    public ResponseEntity<List<CandidateDto>> findAllCandidates(){
        List<CandidateDto> candidateDto = votingService.findAllCandidates();
        return new ResponseEntity<>(candidateDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Fetching details of the winner candidate",
            description = "Fetching details of winner candidate ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @GetMapping("/getwinner")
    public ResponseEntity<CandidateDto> findWinner(){
        CandidateDto candidateDto = votingService.findWinner();
        return new ResponseEntity<>(candidateDto, HttpStatus.OK);
    }



}
