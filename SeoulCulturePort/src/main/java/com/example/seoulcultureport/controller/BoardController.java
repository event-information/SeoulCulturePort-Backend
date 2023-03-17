package com.example.seoulcultureport.controller;

import com.example.seoulcultureport.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;



}
