package com.bytes.bytes.contexts.kitchen.domain.port.outbound;

public interface TokenProviderPort {

    String generate(String subject);
}
