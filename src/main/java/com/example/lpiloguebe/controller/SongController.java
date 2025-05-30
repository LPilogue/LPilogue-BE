package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.apiPayload.code.ApiResponse;
import com.example.lpiloguebe.dto.SongResponseDTO;
import com.example.lpiloguebe.entity.Song;
import com.example.lpiloguebe.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    // 대표곡 선정
    @PatchMapping("/main/{songId}")
    @Operation(summary = "대표곡 설정", description = "노래 ID를 통해 해당 곡을 대표곡으로 설정합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "대표곡 설정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "노래를 찾을 수 없음")
    })
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "songId", description = "대표곡으로 설정할 노래의 ID", required = true)
    })
    public ApiResponse<SongResponseDTO.updateMainSongResultDTO> updateMainSong(@PathVariable Long songId) {

        Song song = songService.updateMainSong(songId);
        return ApiResponse.onSuccess(
                SongResponseDTO.updateMainSongResultDTO.builder()
                        .songId(song.getId())
                        .title(song.getName())
                        .type(song.getType())
                        .build());
    }

    // 곡에 좋아요
    @PatchMapping("/like/{songId}")
    @Operation(summary = "좋아요 설정", description = "노래 ID를 통해 해당 곡에 좋아요를 설정합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "좋아요 설정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "노래를 찾을 수 없음")
    })
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "songId", description = "좋아요를 설정할 노래의 ID", required = true)
    })
    public ApiResponse<SongResponseDTO.updateLikeSongResultDTO> updateLikeSong(@PathVariable Long songId) {

        Song song = songService.updateLikeSong(songId);
        return ApiResponse.onSuccess(
                SongResponseDTO.updateLikeSongResultDTO.builder()
                        .songId(song.getId())
                        .title(song.getName())
                        .isLiked(song.getIsLiked())
                        .build());
    }

    // 싫어요한 곡 가져오기
    @GetMapping("/dislike")
    @Operation(summary = "싫어요한 곡 가져오기", description = "사용자가 싫어요한 곡 리스트를 가져옵니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "싫어요한 곡 리스트 가져오기 성공")
    })
    public ApiResponse<SongResponseDTO.getSongListResultDTO> getDislikeSong() {
        return ApiResponse.onSuccess(
                SongResponseDTO.getSongListResultDTO.builder()
                        .songList(songService.getDislikeSong().stream()
                                .map(song -> SongResponseDTO.getSongResultDTO.builder()
                                        .title(song.getName())
                                        .artist(song.getArtist())
                                        .build())
                                .toList())
                        .build());
    }
}
