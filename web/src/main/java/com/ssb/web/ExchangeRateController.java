package com.ssb.web;

import com.ssb.data.model.*;
import com.ssb.service.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;

@Controller
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    public String getAllExchangeRates(Model model) {
        List<ExchangeRateDto> exchangeRateList = exchangeRateService.getAllExchangeRates();
        model.addAttribute("exchangeRateList", exchangeRateList);
        return "exchange-rates";
    }
    @GetMapping("/convert-result")
    public String showConvertResult() {
        return "convert-result";
    }
    @PostMapping("/convert")
    public String convertCurrency(@RequestParam("fromCurrency") String fromCurrency,
                                  @RequestParam("toCurrency") String toCurrency,
                                  @RequestParam("amount") BigDecimal amount,
                                  Model model) {
        BigDecimal result = exchangeRateService.convertCurrency(fromCurrency, toCurrency, amount);
//        BigDecimal rate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
        if (result != null) {
            model.addAttribute("result", result);
//            model.addAttribute("rate", rate);
        }
        return "convert-result";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") int id, Model model, HttpServletRequest request) {
        ExchangeRateDto exchangeRateDto = exchangeRateService.getExchangeRate(id);
        model.addAttribute("exchangeRate", exchangeRateDto);
        model.addAttribute("contextPath", request.getContextPath());
        return "update-exchange-rate";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/update")
    public String updateExchangeRate(@ModelAttribute("exchangeRate") ExchangeRateDto exchangeRateDto, Model model) {
        exchangeRateService.updateExchangeRate(exchangeRateDto);
        List<ExchangeRateDto> exchangeRateList = exchangeRateService.getAllExchangeRates();
        model.addAttribute("exchangeRateList", exchangeRateList);
        return "redirect:/exchange-rates";
    }


}
