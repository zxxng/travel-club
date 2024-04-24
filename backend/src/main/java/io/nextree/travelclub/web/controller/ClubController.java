package io.nextree.travelclub.web.controller;

import io.nextree.travelclub.web.domain.club.TravelClub;
import io.nextree.travelclub.web.service.ClubService;
import io.nextree.travelclub.web.service.dto.TravelClubDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping // localhost:8080/club
    public Long register(@RequestBody TravelClubDto travelClubDto) {
        clubService.register(travelClubDto);

        return travelClubDto.getId();
    }

    @GetMapping("/{clubId}")
    public TravelClubDto find(@PathVariable Long clubId) {
        return clubService.findClub(clubId);
    }

    @GetMapping // localhost:8080/club?name=JavaClub
    public TravelClubDto findByName(@RequestParam String name) {
        return clubService.findClubByName(name);
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody TravelClubDto travelClubDto) {
        clubService.modify(travelClubDto);

        return ResponseEntity.ok("{\"message\":\"Club modified successfully\"}");
    }

    @DeleteMapping("/{clubId}")
    public ResponseEntity<String> delete(@PathVariable Long clubId) {
        clubService.remove(clubId);

        return ResponseEntity.ok("{\"message\":\"Club deleted successfully\"}");
    }
}
