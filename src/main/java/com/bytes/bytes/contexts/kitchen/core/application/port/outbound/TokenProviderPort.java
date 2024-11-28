package com.bytes.bytes.contexts.kitchen.core.application.port.outbound;

public interface TokenProviderPort {

    String generate(String subject);
}
