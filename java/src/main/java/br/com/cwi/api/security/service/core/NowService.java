package br.com.cwi.api.security.service.core;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NowService {

    public LocalDate getDate() {
        return LocalDate.now();
    }
}
