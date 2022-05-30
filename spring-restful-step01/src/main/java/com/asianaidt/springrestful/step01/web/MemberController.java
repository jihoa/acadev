package com.asianaidt.springrestful.step01.web;

import com.asianaidt.springrestful.step01.entity.Member;
import com.asianaidt.springrestful.step01.repository.MemberRepository;
import com.asianaidt.springrestful.step01.resolver.ClientIP;
import com.asianaidt.springrestful.step01.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MemberController {
    final MemberService memberService;
    final MemberRepository memberRepository;

    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping(value = "/members",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> getMembers(@ClientIP String clientIp) {
        log.info("Request Client IP: {}", clientIp);
        return ResponseEntity.ok().body(memberService.findByAll());

    }
    @GetMapping(value = "/members/{username}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getMember(@ClientIP String clientIp, @PathVariable String username) {
        log.info("Request Client IP: {}", clientIp);
        return ResponseEntity.ok().body(memberService.findByUsername(username));
        //return ResponseEntity.ok().body(memberRepository.findByCustom(username));

    }

    @PostMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> addMember(@Valid @RequestBody Member member) {
        memberService.add(member);
        return ResponseEntity.ok(memberService.findByUsername(member.getUsername()));
    }

    @PutMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> modifyMember(@Valid @RequestBody Member member) {
        memberService.update(member);
        return ResponseEntity.ok().body(member);
    }

    @DeleteMapping(value = "/members/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeMember(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.ok("Member is deleted");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions (MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
