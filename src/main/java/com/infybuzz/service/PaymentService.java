package com.infybuzz.service;

import com.infybuzz.entity.UserBalance;
import com.infybuzz.repository.UserBalanceRepository;
import com.infybuzz.repository.UserTransactionRepository;
import com.infybuzz.request.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository balanceRepository;

    @Autowired
    private UserTransactionRepository transactionRepository;

    @PostConstruct
    public void initDB() {
        balanceRepository.saveAll(Stream.of(
                        new UserBalance(1, 100),
                        new UserBalance(2, 150),
                        new UserBalance(3, 5)
                ).collect(Collectors.toList())
        );
    }

    @Transactional
    public void deduct(PaymentDto paymentDto) throws Exception {
        Optional<UserBalance> userBalanceOpt = balanceRepository.findById(paymentDto.getUserId())
                .filter(ub -> ub.getBalance() >= paymentDto.getAmount());

        if (userBalanceOpt.isPresent()) {
            UserBalance userBalance = userBalanceOpt.get();
            userBalance.setBalance(userBalance.getBalance() - paymentDto.getAmount());
        } else {
            throw new Exception();
        }
    }
}
