package com.example.sfs.security.auth;

import com.example.sfs.model.Member;
import com.example.sfs.model.MemberAuthority;
import com.example.sfs.repository.MemberAuthorityRepository;
import com.example.sfs.repository.MemberRepository;
import com.example.sfs.security.UserNotActivatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

//    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final MemberRepository memberRepository;
    private final MemberAuthorityRepository memberAuthorityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
//        log.debug("Authenticating user '{}'", login);
        System.out.println("Authenticating user '{" + username + "}'");

        return memberRepository.findByUsername(username)
                .map(member -> createSpringSecurityUser(member))
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));

    }

    private UserDetails createSpringSecurityUser(Member member) {
        if (!member.isActivated()) {
            throw new UserNotActivatedException("User " + member.getUsername() + " was not activated");
        }
        Long memberId = member.getId();
        Set<MemberAuthority> memberAuthorities = memberAuthorityRepository.findAllByMemberId(memberId);
        List<GrantedAuthority> grantedAuthorities = memberAuthorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getRole()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(member, grantedAuthorities);
    }
}
