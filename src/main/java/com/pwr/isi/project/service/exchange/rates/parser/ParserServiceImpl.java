package com.pwr.isi.project.service.exchange.rates.parser;

import static com.pwr.isi.project.service.enums.BaseCurrency.PLN;

import com.pwr.isi.project.service.dto.ecb.ECBResponseDto;
import com.pwr.isi.project.service.dto.exchange.rates.CurrencyDataDto;
import com.pwr.isi.project.service.dto.exchange.rates.ExchangeRatesDto;
import com.pwr.isi.project.service.dto.exchange.rates.ExchangeRatesPageableDto;
import com.pwr.isi.project.service.dto.nbp.rates.NBPRateDto;
import com.pwr.isi.project.service.dto.nbp.rates.NBPResponseDto;
import com.pwr.isi.project.service.dto.pagination.CurrencyPagination;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ParserServiceImpl implements ParserService {

  @Override
  public ExchangeRatesDto getExchangeRatesFromNBP(NBPResponseDto nbpResponseDto) {
    return buildExchangeRates(PLN.toString(), nbpResponseDto.getCode(), buildCurrencyDataListFromNBP(nbpResponseDto.getRates()));
  }

  @Override
  public ExchangeRatesPageableDto getExchangeRatesFromNBP(NBPResponseDto nbpResponseDto, Pageable pageRequest) {
    return buildExchangeRatesPage(PLN.toString(),
        nbpResponseDto.getCode(),
        buildCurrencyDataListFromNBP(nbpResponseDto.getRates(), pageRequest),
        createNBPPaginationDetails(nbpResponseDto.getRates(), pageRequest));
  }

  @Override
  public ExchangeRatesDto getExchangeRatesFromECB(String ecbResponse) {
    List<ECBResponseDto> ecbResponseDtos = parseEcbResponse(ecbResponse);
    return buildExchangeRates(ecbResponseDtos.get(0).getCurrencyDenom(),
        ecbResponseDtos.get(0).getCurrencyDenom(),
        buildCurrencyDataListFromECB(ecbResponseDtos));
  }

  @Override
  public ExchangeRatesPageableDto getExchangeRatesFromECB(String ecbResponse, Pageable pageRequest) {
    List<ECBResponseDto> ecbResponseDtos = parseEcbResponse(ecbResponse);
    return buildExchangeRatesPage(ecbResponseDtos.get(0).getCurrencyDenom(),
        ecbResponseDtos.get(0).getCurrency(),
        buildCurrencyDataListFromECB(ecbResponseDtos, pageRequest),
        createECBPaginationDetails(ecbResponseDtos, pageRequest));
  }

  private List<ECBResponseDto> parseEcbResponse(String ecbResponse) {
    List<ECBResponseDto> ecbResponseDtos = new ArrayList<>();
    String[] responseRows = ecbResponse.split("\r\n");
    for (String row : responseRows) {
      if (row.contains("KEY")) continue;
      ecbResponseDtos.add(new ECBResponseDto(row.split(",")));
    }
    return ecbResponseDtos;
  }

  private List<CurrencyDataDto> buildCurrencyDataListFromECB(List<ECBResponseDto> ecbResponseDtos) {
    List<CurrencyDataDto> currenciesData = new LinkedList<>();
    for (ECBResponseDto ecbResponse : ecbResponseDtos) {
      currenciesData.add(CurrencyDataDto.builder()
          .effectiveDate(ecbResponse.getTimePeriod())
          .value(ecbResponse.getObsValue())
          .build());
    }
    return currenciesData;
  }

  private List<CurrencyDataDto> buildCurrencyDataListFromECB(List<ECBResponseDto> ecbResponseDtos, Pageable pageRequest) {
    List<CurrencyDataDto> currenciesData = new LinkedList<>();
    int pageOffset = pageRequest.getOffset();
    if (pageOffset <= ecbResponseDtos.size()) {
      for (int elementCounter = pageOffset; elementCounter < ecbResponseDtos.size() && elementCounter - pageOffset < pageRequest.getPageSize(); elementCounter++) {
        currenciesData.add(CurrencyDataDto.builder()
            .effectiveDate(ecbResponseDtos.get(elementCounter).getTimePeriod())
            .value(ecbResponseDtos.get(elementCounter).getObsValue())
            .build());
      }
    }
    return currenciesData;
  }

  private List<CurrencyDataDto> buildCurrencyDataListFromNBP(List<NBPRateDto> rates) {
    List<CurrencyDataDto> currenciesData = new LinkedList<>();
    for (NBPRateDto nbpRate : rates) {
      currenciesData.add(CurrencyDataDto.builder()
          .effectiveDate(nbpRate.getEffectiveDate())
          .value(Double.parseDouble(nbpRate.getMid()))
          .build());
    }
    return currenciesData;
  }

  private List<CurrencyDataDto> buildCurrencyDataListFromNBP(List<NBPRateDto> rates, Pageable pageRequest) {
    List<CurrencyDataDto> currenciesData = new LinkedList<>();
    int pageOffset = pageRequest.getOffset();
    if (pageOffset <= rates.size()) {
      for (int elementCounter = pageOffset; elementCounter < rates.size() && elementCounter - pageOffset < pageRequest.getPageSize(); elementCounter++) {
        currenciesData.add(CurrencyDataDto.builder()
            .effectiveDate(rates.get(elementCounter).getEffectiveDate())
            .value(Double.parseDouble(rates.get(elementCounter).getMid()))
            .build());
      }
    }
    return currenciesData;
  }

  private CurrencyPagination createNBPPaginationDetails(List<NBPRateDto> rates, Pageable pageRequest) {
    return CurrencyPagination.builder()
        .totalPages(rates.size() / pageRequest.getPageSize())
        .totalElements(rates.size())
        .last(pageRequest.getOffset() + pageRequest.getPageSize() >= rates.size())
        .size(pageRequest.getPageSize())
        .number(pageRequest.getPageNumber())
        .sort(null)
        .numberOfElements(rates.size() - pageRequest.getOffset() >= pageRequest.getPageSize() ? pageRequest.getPageSize() : rates.size() % pageRequest.getPageSize())
        .first(pageRequest.getPageNumber() == 0)
        .build();
  }

  private CurrencyPagination createECBPaginationDetails(List<ECBResponseDto> ecbResponseDtos, Pageable pageRequest) {
    return CurrencyPagination.builder()
        .totalPages(ecbResponseDtos.size() / pageRequest.getPageSize())
        .totalElements(ecbResponseDtos.size())
        .last(pageRequest.getOffset() + pageRequest.getPageSize() >= ecbResponseDtos.size())
        .size(pageRequest.getPageSize())
        .number(pageRequest.getPageNumber())
        .sort(null)
        .numberOfElements(ecbResponseDtos.size() - pageRequest.getOffset() >= pageRequest.getPageSize() ? pageRequest.getPageSize() : ecbResponseDtos.size() % pageRequest.getPageSize())
        .first(pageRequest.getPageNumber() == 0)
        .build();
  }

  private ExchangeRatesDto buildExchangeRates(String baseCurrency, String targetCurrency, List<CurrencyDataDto> currencyData) {
    return ExchangeRatesDto.builder()
        .baseCurrencyCode(baseCurrency)
        .targetCurrencyCode(targetCurrency)
        .currencyData(currencyData)
        .build();
  }

  private ExchangeRatesPageableDto buildExchangeRatesPage(String baseCurrency, String targetCurrency, List<CurrencyDataDto> currencyData, CurrencyPagination pagination) {
    return ExchangeRatesPageableDto.builder()
        .baseCurrencyCode(baseCurrency)
        .targetCurrencyCode(targetCurrency)
        .currencyData(currencyData)
        .totalPages(pagination.getTotalPages())
        .totalElements(pagination.getTotalElements())
        .last(pagination.getLast())
        .size(pagination.getSize())
        .number(pagination.getNumber())
        .sort(pagination.getSort())
        .numberOfElements(pagination.getNumberOfElements())
        .first(pagination.getFirst())
        .build();
  }

}
