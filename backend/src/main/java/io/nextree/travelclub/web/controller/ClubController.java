package io.nextree.travelclub.web.controller;

import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.service.ClubService;
import io.nextree.travelclub.web.service.dto.TravelClubDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping // localhost:8080/club/
    public String register(@RequestBody TravelClubDto travelClubDto) {
        clubService.register(travelClubDto);

        return travelClubDto.getUsid();
    }

    @GetMapping("/{clubId}")
    public TravelClub find(@PathVariable String clubId) {
        return clubService.findClub(clubId).toTravelClub();
    }

    @GetMapping // localhost:8080/club?name=JavaClub
    public TravelClub findByName(@RequestParam String name) {
        return clubService.findClubByName(name).toTravelClub();
    }

    @PutMapping
    public void modify(@RequestBody TravelClubDto travelClubDto) {
        clubService.modify(travelClubDto);
    }

    @DeleteMapping("/{clubId}")
    public void delete(@PathVariable String clubId) {
        clubService.remove(clubId);
    }

}
