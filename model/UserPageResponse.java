package com.project.java.validationmicroservice.model;

import com.project.java.validationmicroservice.entity.User;

import java.util.List;

/**
 * Represents the response model for paginated user data.
 */
public class UserPageResponse {

    // List of user data
    private List<User> data;

    // Information about the pagination
    private PageInfo pageInfo;

    /**
     * Constructs a new UserPageResponse with the specified data and pageInfo.
     *
     * @param data     the list of user data
     * @param pageInfo information about the pagination
     */
    public UserPageResponse(List<User> data, PageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }

    /**
     * Gets the list of user data.
     *
     * @return the list of user data
     */
    public List<User> getData() {
        return data;
    }

    /**
     * Sets the list of user data.
     *
     * @param data the list of user data
     */
    public void setData(List<User> data) {
        this.data = data;
    }

    /**
     * Gets information about the pagination.
     *
     * @return information about the pagination
     */
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    /**
     * Sets information about the pagination.
     *
     * @param pageInfo information about the pagination
     */
    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * Represents information about pagination including hasNextPage, hasPreviousPage, and total.
     */
    public static class PageInfo {

        // Indicates whether there is a next page
        private boolean hasNextPage;

        // Indicates whether there is a previous page
        private boolean hasPreviousPage;

        // Total number of items across all pages
        private long total;

        /**
         * Constructs a new PageInfo with the specified parameters.
         *
         * @param hasNextPage     indicates whether there is a next page
         * @param hasPreviousPage indicates whether there is a previous page
         * @param total           total number of items across all pages
         */
        public PageInfo(boolean hasNextPage, boolean hasPreviousPage, long total) {
            this.hasNextPage = hasNextPage;
            this.hasPreviousPage = hasPreviousPage;
            this.total = total;
        }

        /**
         * Checks whether there is a next page.
         *
         * @return true if there is a next page, false otherwise
         */
        public boolean isHasNextPage() {
            return hasNextPage;
        }

        /**
         * Sets whether there is a next page.
         *
         * @param hasNextPage indicates whether there is a next page
         */
        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        /**
         * Checks whether there is a previous page.
         *
         * @return true if there is a previous page, false otherwise
         */
        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        /**
         * Sets whether there is a previous page.
         *
         * @param hasPreviousPage indicates whether there is a previous page
         */
        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        /**
         * Gets the total number of items across all pages.
         *
         * @return the total number of items across all pages
         */
        public long getTotal() {
            return total;
        }

        /**
         * Sets the total number of items across all pages.
         *
         * @param total the total number of items across all pages
         */
        public void setTotal(long total) {
            this.total = total;
        }
    }
}
