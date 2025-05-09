package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.request.CommentCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentUpdateRequestDto;
import com.example.korea_sleepTech_springboot.dto.response.CommentResponseDto;
import com.example.korea_sleepTech_springboot.entity.D_Comment;
import com.example.korea_sleepTech_springboot.entity.D_Post;
import com.example.korea_sleepTech_springboot.repository.CommentRepository;
import com.example.korea_sleepTech_springboot.repository.PostRepository;
import com.example.korea_sleepTech_springboot.service.CommentService;
import com.example.korea_sleepTech_springboot.시험.dto.response.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = false)
    public ResponseDto<CommentResponseDto> createComment(Long postId, CommentCreateRequestDto dto) {
        CommentResponseDto responseDto = null;

        D_Post post = postRepository.findById(postId)
                .orElseThrow(() -> new
                        EntityNotFoundException("no ID"+postId));

        D_Comment newComment = D_Comment.builder()
                .content(dto.getContent())
                .commenter(dto.getCommenter())
                .post(post)
                .build();
        D_Comment savedComments = commentRepository.save(newComment);
        responseDto = CommentResponseDto.builder()
                .id(savedComments.getId())
                .postId(savedComments.getPost().getId())
                .content(savedComments.getContent())
                .commenter(savedComments.getCommenter())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS,responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<CommentResponseDto> deleteComment(Long postId, Long commentId) {
        D_Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_COMMENT+commentId));
        if(!comment.getPost().getId().equals(postId)){
            throw new IllegalArgumentException("comment does not belong");

        }
        //연관관계를 해제하고
        comment.getPost().removeComment(comment);
        //db에서 삭제한다/
        commentRepository.delete(comment);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS,null);
    }

    @Override
    @Transactional
    public ResponseDto<CommentResponseDto> updateComment(Long postId, Long commentId, CommentUpdateRequestDto dto) {
        CommentResponseDto responseDto = null;

        if(!commentRepository.existsById(postId)){
            throw new EntityNotFoundException(ResponseMessage.NOT_EXISTS_COMMENT+commentId);

        }

        D_Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_COMMENT + commentId));
        if(!comment.getPost().getId().equals(postId)) {
            throw new IllegalArgumentException("Comment does not belong to the specified post");
        }

        comment.setContent(dto.getContent());
        D_Comment updatedComment = commentRepository.save(comment);

        responseDto = CommentResponseDto.builder()
                .id(updatedComment.getId())
                .postId(updatedComment.getPost().getId())
                .content(updatedComment.getContent())
                .commenter(updatedComment.getCommenter())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

}
