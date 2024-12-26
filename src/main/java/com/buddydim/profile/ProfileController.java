package com.buddydim.profile;

import com.buddydim.profile.dto.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // userId로 프로필 조회
    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResponse> getProfileByUserId(@PathVariable("userId") Long userId) {
        return profileService.getProfileByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
