package io.travel.city.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.travel.city.model.dto.CityByMemberListResponse;
import io.travel.city.model.dto.CityCreateRequest;
import io.travel.city.model.dto.CityResponse;
import io.travel.city.model.dto.CityUpdateRequest;
import io.travel.city.service.CityService;
import io.travel.exception.invalidrequest.CannotDeleteCityException;
import io.travel.exception.notfound.CityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static io.travel.common.Constants.CITY_NAME;
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
@WebMvcTest(CityApi.class)
class CityApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CityService cityService;

    @Test
    void 도시_등록_성공_201() throws Exception {
        // given
        CityCreateRequest request = new CityCreateRequest(CITY_NAME);

        given(cityService.create(any())).willReturn(1L);

        // when
        // then
        mockMvc.perform(post("/api/v1/cities")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void 도시_조회_성공_200() throws Exception {
        // given
        Long id = 1L;

        given(cityService.getOne(id)).willReturn(new CityResponse());
        // when
        // then
        mockMvc.perform(get("/api/v1/cities/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void 존재_하지_않는_도시_조회_실패_404() throws Exception {
        // given
        Long id = 0L;

        given(cityService.getOne(id)).willThrow(new CityNotFoundException());

        // when
        // then
        mockMvc.perform(get("/api/v1/cities/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void 도시_수정_성공_204() throws Exception {
        // given
        Long id = 1L;
        CityUpdateRequest request = new CityUpdateRequest(CITY_NAME);

        willDoNothing()
                .given(cityService)
                .update(any(), any());


        // when
        // then
        mockMvc.perform(patch("/api/v1/cities/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void 존재_하지_않는_도시_수정_실패_404() throws Exception {
        // given
        Long id = 1L;
        CityUpdateRequest request = new CityUpdateRequest(CITY_NAME);

        willThrow(new CityNotFoundException())
                .given(cityService)
                .update(any(), any());


        // when
        // then
        mockMvc.perform(patch("/api/v1/cities/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void 도시_삭제_성공_204() throws Exception {
        // given
        Long id = 1L;

        willDoNothing()
                .given(cityService)
                .delete(any());

        // when
        // then
        mockMvc.perform(delete("/api/v1/cities/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void 존지_하지_않는_도시_삭제_실패_404() throws Exception {
        // given
        Long id = 1L;

        willThrow(new CityNotFoundException())
                .given(cityService)
                .delete(any());

        // when
        // then
        mockMvc.perform(delete("/api/v1/cities/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void 일정이_있는_도시_삭제_실패_404() throws Exception {
        // given
        Long id = 1L;

        willThrow(new CannotDeleteCityException())
                .given(cityService)
                .delete(any());

        // when
        // then
        mockMvc.perform(delete("/api/v1/cities/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void 사용자별_도시_목록_조회_200() throws Exception {
        // given
        Long memberId = 1L;

        given(cityService.getCitiesByMember(memberId)).willReturn(new CityByMemberListResponse());

        // when
        // then
        mockMvc.perform(get("/api/v1/cities?member={id}", memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}