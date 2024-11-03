package bk.rw.eventticketing.security.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bk.rw.eventticketing.model.User;
import bk.rw.eventticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EventTicketingDetails implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
return EventTicketing.buildUserDetails(user);
}

}
