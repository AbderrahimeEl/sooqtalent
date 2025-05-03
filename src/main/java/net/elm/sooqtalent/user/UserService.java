package net.elm.sooqtalent.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.elm.sooqtalent.exception.ResourceNotFoundException;
import net.elm.sooqtalent.user.dto.UserCreateRequest;
import net.elm.sooqtalent.user.dto.UserDTO;
import org.springframework.stereotype.Service;


    @Service
    @RequiredArgsConstructor
    public class UserService {
        private final UserRepository userRepo;

        @Transactional
        public UserDTO updateUserProfile(UserDTO dto, Long userId) {
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setPhone(dto.getPhone());
            user.setLocation(dto.getLocation());
            user.setBio(dto.getBio());
            user.setProfilePictureUrl(dto.getProfilePictureUrl());

            return UserMapper.toDTO(userRepo.save(user));
        }

        public UserDTO getUserProfile(Long userId) {
            return userRepo.findById(userId)
                    .map(UserMapper::toDTO)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        }

        @Transactional
        public UserDTO createUser(UserCreateRequest request) {
            if(userRepo.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Email already registered");
            }

            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setRole(request.getRole());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPhone(request.getPhone());
            user.setLocation(request.getLocation());

            User savedUser = userRepo.save(user);
            return UserMapper.toDTO(savedUser);
        }

    }