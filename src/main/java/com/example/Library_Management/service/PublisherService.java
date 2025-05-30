package com.example.Library_Management.service;

import com.example.Library_Management.dto.request.PublisherRequest;
import com.example.Library_Management.dto.response.PublisherResponse;
import com.example.Library_Management.entity.Publisher;
import com.example.Library_Management.exception.PublisherNotFoundException;
import com.example.Library_Management.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherResponse getById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
        return PublisherResponse.mapToPublisherResponse(publisher);
    }

    public Page<PublisherResponse> getAll(Pageable pageable) {
        return publisherRepository.findAll(pageable)
                .map(PublisherResponse::mapToPublisherResponse);
    }

    public PublisherResponse create(PublisherRequest publisherRequest) {
        Publisher publisher = buildPublisher(publisherRequest);
        publisherRepository.save(publisher);
        return PublisherResponse.mapToPublisherResponse(publisher);
    }

    private Publisher buildPublisher(PublisherRequest request) {
        return Publisher.builder()
                .name(request.name())
                .address(request.address())
                .build();
    }

    public PublisherResponse update(PublisherRequest publisherRequest, Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
        updatePublisher(publisherRequest, publisher);
        publisherRepository.save(publisher);
        return PublisherResponse.mapToPublisherResponse(publisher);
    }

    private void updatePublisher(PublisherRequest request, Publisher publisher) {
        if (request.name() != null && !request.name().equals(publisher.getName())) {
            if (publisherRepository.existsByName(request.name())) {
                throw new IllegalStateException("Publisher name must be unique");
            }
            publisher.setName(request.name());
        }
        Optional.ofNullable(request.address()).ifPresent(publisher::setAddress);
    }

    public void deleteById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
        if (publisher.getBooks() != null && !publisher.getBooks().isEmpty()) {
            throw new IllegalStateException("Cannot delete publisher because they are assigned to one or more books");
        }
        publisherRepository.deleteById(id);
    }
}
