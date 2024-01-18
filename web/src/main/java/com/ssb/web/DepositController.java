package com.ssb.web;

import com.ssb.data.model.*;
import com.ssb.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;

@Controller
@RequestMapping("/ssb")
public class DepositController {

    private final DepositService depositService;
    private final UserAccountService userAccountService;

    @Autowired
    public DepositController(DepositService depositService, UserAccountService userAccountService) {
        this.depositService = depositService;
        this.userAccountService = userAccountService;
    }

    @GetMapping("/ssb/deposits/{userId}")
    public String showDeposits(@PathVariable("userId") int userId, Model model) {
        UserAccountDto userAccountDto = userAccountService.getUserAccountById(userId);
        if (userAccountDto != null) {
            model.addAttribute("userAccount", userAccountDto);
            List<DepositsDto> deposits = depositService.getAllDeposits();
            model.addAttribute("deposits", deposits);
        }
        return "deposits";
    }

    @PostMapping("/addDeposit")
    public String addDeposit(@RequestParam int userId, @RequestParam int depositId, @RequestParam BigDecimal amount) {
        UserAccountDto userAccountDto = userAccountService.getUserAccountById(userId);
        if (userAccountDto != null) {
            depositService.addToDeposit(userAccountDto.getId(), depositId, amount);
        }
        return "redirect:/deposits";
    }

    @Scheduled(cron = "0 0 0 * * ?") // runs daily at midnight
    public void calculateInterest() {
        depositService.calculateInterest();
    }
}






