package cn.chef.appointment.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SourcesRequestParam  {
    Long doctorId;
    LocalDate date;
    Integer page;
    Integer size;
}
