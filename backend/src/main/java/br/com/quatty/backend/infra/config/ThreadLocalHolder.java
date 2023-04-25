package br.com.quatty.backend.infra.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalHolder {
    private static final ThreadLocal<Authentication> AUTHENTICATION_THREAD_LOCAL = new ThreadLocal<>();

    private ThreadLocalHolder() {
    }

    public static void setAuthentication(Authentication authentication) {
        AUTHENTICATION_THREAD_LOCAL.set(authentication);
    }

    public static Authentication getAuthentication() {
        return AUTHENTICATION_THREAD_LOCAL.get();
    }

    public static void clear() {
        AUTHENTICATION_THREAD_LOCAL.remove();
    }
}


