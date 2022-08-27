package dev.mamekun.freelecspringboot2webservice.service.posts;

import dev.mamekun.freelecspringboot2webservice.domain.posts.Posts;
import dev.mamekun.freelecspringboot2webservice.domain.posts.PostsRepository;
import dev.mamekun.freelecspringboot2webservice.web.dto.PostsResponseDto;
import dev.mamekun.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import dev.mamekun.freelecspringboot2webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }
}