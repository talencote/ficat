package com.talencote.ficat.services.impl;

import com.talencote.ficat.models.User;
import com.talencote.ficat.models.UserProfile;
import com.talencote.ficat.repository.UserProfileRepository;
import com.talencote.ficat.repository.UserRepository;
import com.talencote.ficat.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public boolean deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow();
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId()).orElseThrow();
        userRepository.delete(user);
        userProfileRepository.delete(userProfile);
        return true;
    }
}
