package io.travel.travel.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.travel.exception.invalidrequest.InvalidDateException;
import io.travel.exception.notfound.TravelNotFoundException;
import io.travel.travel.model.dto.TravelCreateRequest;
import io.travel.travel.model.dto.TravelResponse;
import io.travel.travel.model.dto.TravelUpdateRequest;
import io.travel.travel.service.TravelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static io.travel.common.Constants.END_DATE;
import static io.travel.common.Constants.START_DATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@WebMvcTest(TravelApi.class)
class TravelApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TravelService travelService;

    @Test
    void 여행_등록_성공_201() throws Exception{
        // given
        TravelCreateRequest request = new TravelCreateRequest(1L, 1L, START_DATE, END_DATE);

        given(travelService.create(any())).willReturn(1L);

        // when
        // then
        mockMvc.perform(post("/api/v1/travel")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void 여행_조회_성공_200() throws Exception {
        // given
        Long id = 1L;

        given(travelService.get(id)).willReturn(new TravelResponse());
        // when
        // then
        mockMvc.perform(get("/api/v1/travel/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void 존재_하지_않는_여행_조회_실패_404() throws Exception {
        // given
        Long id = 0L;

        given(travelService.get(id)).willThrow(new TravelNotFoundException());

        // when
        // then
        mockMvc.perform(get("/api/v1/travel/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void 여행_수정_성공_204() throws Exception {
        // given
        Long id = 1L;
        TravelUpdateRequest request = new TravelUpdateRequest(1L, START_DATE, END_DATE);

        willDoNothing()
                .given(travelService)
                .update(any(), any());

        // when
        // then
        mockMvc.perform(patch("/api/v1/travel/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void 존재_하지_않는_여행_수정_실패_404() throws Exception {
        // given
        Long id = 1L;
        TravelUpdateRequest request = new TravelUpdateRequest(1L, START_DATE, END_DATE);

        willThrow(new TravelNotFoundException())
                .given(travelService)
                .update(any(), any());

        // when
        // then
        mockMvc.perform(patch("/api/v1/travel/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void 시작일_보다_빠른_종료일_여행_수정_실패_400() throws Exception {
        // given
        Long id = 1L;
        TravelUpdateRequest request = new TravelUpdateRequest(1L, START_DATE, START_DATE.minusDays(1));

        willThrow(new InvalidDateException())
                .given(travelService)
                .update(any(), any());

        // when
        // then
        mockMvc.perform(patch("/api/v1/travel/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void 미래가_아닌_종료일_여행_수정_실패_400() throws Exception {
        // given
        Long id = 1L;
        TravelUpdateRequest request = new TravelUpdateRequest(1L, START_DATE, LocalDateTime.now().minusSeconds(1));

        willThrow(new InvalidDateException())
                .given(travelService)
                .update(any(), any());

        // when
        // then
        mockMvc.perform(patch("/api/v1/travel/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void 여행_삭제_성공_204() throws Exception {
        // given
        Long id = 1L;

        willDoNothing()
                .given(travelService)
                .delete(any());

        // when
        // then
        mockMvc.perform(delete("/api/v1/travel/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void 존지_하지_않는_여행_삭제_실패_404() throws Exception {
        // given
        Long id = 1L;

        willThrow(new TravelNotFoundException())
                .given(travelService)
                .delete(any());

        // when
        // then
        mockMvc.perform(delete("/api/v1/travel/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}