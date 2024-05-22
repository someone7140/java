package com.placeNote.placeNoteApi2024.service.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placeNote.placeNoteApi2024.repository.UserAccountRepository;

@Service
public class UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;


}
