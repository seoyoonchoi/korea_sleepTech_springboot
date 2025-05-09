package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.request.PostCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.PostUpdateRequestDto;
import com.example.korea_sleepTech_springboot.dto.response.CommentResponseDto;
import com.example.korea_sleepTech_springboot.dto.response.PostDetailResponseDto;
import com.example.korea_sleepTech_springboot.dto.response.PostListResponseDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.entity.D_Post;
import com.example.korea_sleepTech_springboot.repository.PostRepository;
import com.example.korea_sleepTech_springboot.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public ResponseDto<PostDetailResponseDto> createPost(PostCreateRequestDto dto) {
        PostDetailResponseDto responseDto = null;

        D_Post newPost = D_Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .build();

        D_Post saved = postRepository.save(newPost);

        responseDto = PostDetailResponseDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .author(saved.getAuthor())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<PostDetailResponseDto> getPostById(Long id) {
        PostDetailResponseDto dto = null;

        D_Post post = postRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("post not found id"+id));
        List<CommentResponseDto> comments = post.getComments().stream()
                .map(comment -> CommentResponseDto.builder()
                        .id(comment.getId())
                        .postId(comment.getPost().getId())
                        .content(comment.getContent())
                        .commenter(comment.getCommenter())
                        .build())
                .collect(Collectors.toList());

        dto = PostDetailResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .comments(comments)
                .build();



        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, dto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<PostListResponseDto>> getAllPosts() {
        List<PostListResponseDto> responseDtos = null;

        List<D_Post> posts = postRepository.findAll();
        responseDtos = posts.stream()
                .map(post -> PostListResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .author(post.getAuthor())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
    }

    @Override
    @Transactional
    public ResponseDto<PostDetailResponseDto> updatePost(Long id, PostUpdateRequestDto dto) {
        PostDetailResponseDto responseDto = null;
        D_Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no id" +id));
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        D_Post updatedPost = postRepository.save(post);
        responseDto = PostDetailResponseDto.builder()
                .id(updatedPost.getId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .author(updatedPost.getAuthor())
                .comments(updatedPost.getComments().stream().map(comment-> CommentResponseDto.builder()
                        .id(comment.getId())
                                .postId(comment.getPost().getId())
                        .content(comment.getContent())
                        .commenter(comment.getCommenter())
                        .build())
                .collect(Collectors.toList()))


                .build();
        return null;
    }

    @Override
    public ResponseDto<Void> deletePost(Long id) {
//        if(!postRepository.existsById(id)){
//            throw new EntityNotFoundException("no id"+id);
//        }

        D_Post post = postRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST+id));
        post.getComments().forEach(post::removeComment);
        postRepository.deleteById(id);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
    }


}