package com.szlify.veterinaryclinic.configuration;

import com.szlify.veterinaryclinic.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class VetInfoDetailsService implements UserDetailsService {

    @Autowired
    private VetRepository repository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        return repository.findByDeletedFalseAndEmail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
