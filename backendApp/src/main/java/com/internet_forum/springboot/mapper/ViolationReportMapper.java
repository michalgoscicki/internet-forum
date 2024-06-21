package com.internet_forum.springboot.mapper;

import com.internet_forum.springboot.dto.PostRequestDto;
import com.internet_forum.springboot.dto.PostResponseDto;
import com.internet_forum.springboot.dto.ViolationReportRequestDto;
import com.internet_forum.springboot.model.Post;
import com.internet_forum.springboot.model.ViolationReport;
import org.mapstruct.Mapper;

@Mapper
public interface ViolationReportMapper {
    ViolationReport requestDtoToEntity(ViolationReportRequestDto violationReportRequestDto);
}
