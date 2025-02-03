package com.example.springcoredemo.filter;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

public class LangFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Lấy lang từ header "lang" của yêu cầu
        String lang = request.getHeader("lang");

        // Nếu có header lang, thay đổi locale
        if (lang != null && !lang.isEmpty()) {
            LocaleContextHolder.setLocale(Locale.forLanguageTag(lang));
        } else {
            // Nếu không có header lang, thiết lập ngôn ngữ mặc định là tiếng Anh
            LocaleContextHolder.setLocale(Locale.of("vi", "VN"));
        }

        // Tiếp tục chuỗi xử lý yêu cầu
        filterChain.doFilter(request, response);
    }
}
