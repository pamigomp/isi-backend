package com.pwr.isi.project.service.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyPagination {
  public int totalPages;
  public int totalElements;
  public Boolean last;
  public int size;
  public int number;
  public String sort;
  public int numberOfElements;
  public Boolean first;
}
