package com.example.demo.service.session;

public class SessionFactory {
    private static final Session session = new SessionMemoryImpl();

    public static Session getSession() {
        return session;
    }
}
