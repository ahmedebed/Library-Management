package com.example.Library_Management.dto.response;

import com.example.Library_Management.entity.Publisher;
import lombok.Builder;

@Builder
public record PublisherResponse(
        Long id,
        String name,
        String address
) {
    public static PublisherResponse mapToPublisherResponse(Publisher publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .address(publisher.getAddress())
                .build();
    }
}
