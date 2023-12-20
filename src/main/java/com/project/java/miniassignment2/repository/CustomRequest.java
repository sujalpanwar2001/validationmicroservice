package com.project.java.miniassignment2.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Custom implementation of the Spring Data Pageable interface for handling pagination.
 */
public class CustomRequest implements Pageable {

    // Offset for pagination
    private int offset;

    // Limit for the number of items per page
    private int limit;

    // Sorting information (can be omitted if not needed)
    private Sort sort = Sort.unsorted();

    /**
     * Constructs a CustomRequest with the specified offset and limit.
     *
     * @param offset the offset for pagination
     * @param limit  the limit for the number of items per page
     */
    public CustomRequest(int offset, int limit) {
        // Validate that offset and limit are non-negative
        if (offset < 0)
            throw new IllegalArgumentException("Offset must not be less than zero!");

        if (limit < 0)
            throw new IllegalArgumentException("Limit must not be less than zero!");

        // Set offset and limit
        this.offset = offset;
        this.limit = limit;

        // Sorting information (can be omitted if not needed)
        if (sort != null) {
            this.sort = sort;
        }
    }

    /**
     * Gets the page number. In this custom implementation, always returns 0.
     *
     * @return the page number (always 0)
     */
    @Override
    public int getPageNumber() {
        return 0;
    }

    /**
     * Gets the number of items per page.
     *
     * @return the number of items per page
     */
    @Override
    public int getPageSize() {
        return limit;
    }

    /**
     * Gets the offset for pagination.
     *
     * @return the offset for pagination
     */
    @Override
    public long getOffset() {
        return offset;
    }

    /**
     * Gets the sorting information.
     *
     * @return the sorting information
     */
    @Override
    public Sort getSort() {
        return this.sort;
    }

    // Other methods of the Pageable interface, not implemented for simplicity

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return this;
    }

    @Override
    public Pageable first() {
        return this;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
