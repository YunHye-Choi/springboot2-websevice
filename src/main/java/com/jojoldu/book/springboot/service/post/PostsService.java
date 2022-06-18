/* SERVICE layer */
/*
 * 데이터베이스와 맞닿은 핵심 Entity 클래스.
 * 이 클래스는 절대 request, response 클래스로 사용하면 안됨!
 * Entity 클래스를 기분으로 테이블이 생성되고, 스키마가 변경되는데
 * 수많은 서비스 클래스, 비지니스 로직들이 Entity 클래스를 기준으로 동작하고, 이들이 변경되면
 * 여러 클래스에 영향을 끼치게 됨.
 * 반면 DTO는 View를 위한 클래스라 매우 잦은 변경이 필요함!
 * ViewLayer와 DBLayer는 역할 분리를 철저하게 해야함.
 * ☆Entity클래스와  controller에서 사용할 Dto는 꼭 분리해서 사용해야 함☆
 */

package com.jojoldu.book.springboot.service.post;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자로 받는 생성자 만듦, 어노테이션 사용 이유는 코드의 유지보수를 유용하게 하기 위해
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    // REPOSITORY layer를 불러와서, 아래와 같이 DTO를 통해 전달!

    // 직접 데이터를 받지 않고, Dto를 통하는 것을 알 수 있다!
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
