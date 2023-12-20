package com.project.java.miniassignment2.model;

import java.util.List;

/**
 * Represents the response model for random user data.
 */
public class RandomResponse {

    // List of user data
    List<UserData> results;

    /**
     * Gets the list of user data.
     *
     * @return the list of user data
     */
    public List<UserData> getResults() {
        return results;
    }

    /**
     * Sets the list of user data.
     *
     * @param results the list of user data
     */
    public void setResults(List<UserData> results) {
        this.results = results;
    }

    /**
     * Returns a string representation of the RandomResponse object.
     *
     * @return a string representation of the RandomResponse object
     */
    @Override
    public String toString() {
        return "RandomResponse{" +
                "results=" + results +
                '}';
    }

    /**
     * Represents the user data including gender, name, date of birth, and nationality.
     */
    public static class UserData {

        // Gender of the user
        String gender;

        // Name data including first and last names
        NameData name;

        // Date of birth data including date and age
        DOB dob;

        // Nationality of the user
        String nat;

        /**
         * Gets the gender of the user.
         *
         * @return the gender of the user
         */
        public String getGender() {
            return gender;
        }

        /**
         * Sets the gender of the user.
         *
         * @param gender the gender of the user
         */
        public void setGender(String gender) {
            this.gender = gender;
        }

        /**
         * Gets the name data of the user.
         *
         * @return the name data of the user
         */
        public NameData getName() {
            return name;
        }

        /**
         * Sets the name data of the user.
         *
         * @param name the name data of the user
         */
        public void setName(NameData name) {
            this.name = name;
        }

        /**
         * Gets the date of birth data of the user.
         *
         * @return the date of birth data of the user
         */
        public DOB getDob() {
            return dob;
        }

        /**
         * Sets the date of birth data of the user.
         *
         * @param dob the date of birth data of the user
         */
        public void setDob(DOB dob) {
            this.dob = dob;
        }

        /**
         * Gets the nationality of the user.
         *
         * @return the nationality of the user
         */
        public String getNat() {
            return nat;
        }

        /**
         * Sets the nationality of the user.
         *
         * @param nat the nationality of the user
         */
        public void setNat(String nat) {
            this.nat = nat;
        }

        /**
         * Returns a string representation of the UserData object.
         *
         * @return a string representation of the UserData object
         */
        @Override
        public String toString() {
            return "UserData{" +
                    "gender='" + gender + '\'' +
                    ", name=" + name +
                    ", dob=" + dob +
                    ", nat='" + nat + '\'' +
                    '}';
        }

        /**
         * Represents the name data including first and last names.
         */
        public static class NameData {

            // First name of the user
            String first;

            // Last name of the user
            String last;

            /**
             * Gets the first name of the user.
             *
             * @return the first name of the user
             */
            public String getFirst() {
                return first;
            }

            /**
             * Sets the first name of the user.
             *
             * @param first the first name of the user
             */
            public void setFirst(String first) {
                this.first = first;
            }

            /**
             * Gets the last name of the user.
             *
             * @return the last name of the user
             */
            public String getLast() {
                return last;
            }

            /**
             * Sets the last name of the user.
             *
             * @param last the last name of the user
             */
            public void setLast(String last) {
                this.last = last;
            }

            /**
             * Returns a string representation of the NameData object.
             *
             * @return a string representation of the NameData object
             */
            @Override
            public String toString() {
                return "NameData{" +
                        "first='" + first + '\'' +
                        ", last='" + last + '\'' +
                        '}';
            }
        }

        /**
         * Represents the date of birth data including date and age.
         */
        public static class DOB {

            // Date of birth of the user
            String Date;

            // Age of the user
            int age;

            /**
             * Gets the date of birth of the user.
             *
             * @return the date of birth of the user
             */
            public String getDate() {
                return Date;
            }

            /**
             * Sets the date of birth of the user.
             *
             * @param date the date of birth of the user
             */
            public void setDate(String date) {
                Date = date;
            }

            /**
             * Gets the age of the user.
             *
             * @return the age of the user
             */
            public int getAge() {
                return age;
            }

            /**
             * Sets the age of the user.
             *
             * @param age the age of the user
             */
            public void setAge(int age) {
                this.age = age;
            }

            /**
             * Returns a string representation of the DOB object.
             *
             * @return a string representation of the DOB object
             */
            @Override
            public String toString() {
                return "DOB{" +
                        "Date='" + Date + '\'' +
                        ", age='" + age + '\'' +
                        '}';
            }
        }
    }
}
