package com.example.seoulcultureport.service;

import com.example.seoulcultureport.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional
public class BoardService {
    private final BoardRepository boardRepository;
}
