package sg.redemption.rewardz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by manishandroid on 20/07/16.
 */

public class AllRewardsModel implements Serializable {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("next")
    @Expose
    private Object next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    /**
     * @return The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The next
     */
    public Object getNext() {
        return next;
    }

    /**
     * @param next The next
     */
    public void setNext(Object next) {
        this.next = next;
    }

    /**
     * @return The previous
     */
    public Object getPrevious() {
        return previous;
    }

    /**
     * @param previous The previous
     */
    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }


    public class Result implements Serializable {

        @SerializedName("pk")
        @Expose
        private Integer pk;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("merchant")
        @Expose
        private String merchant;
        @SerializedName("merchant_description")
        @Expose
        private String merchantDescription;
        @SerializedName("tier")
        @Expose
        private String tier;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("contact_email")
        @Expose
        private String contactEmail;
        @SerializedName("contact_phone")
        @Expose
        private String contactPhone;
        @SerializedName("img")
        @Expose
        private String img;
        @SerializedName("display_img")
        @Expose
        private String displayImg;
        @SerializedName("thumbnail_img")
        @Expose
        private String thumbnailImg;
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("terms_and_conditions")
        @Expose
        private String termsAndConditions;
        @SerializedName("cost")
        @Expose
        private String cost;
        @SerializedName("reward_type")
        @Expose
        private String rewardType;
        @SerializedName("valid_until")
        @Expose
        private String validUntil;
        @SerializedName("locations")
        @Expose
        private List<Location> locations = new ArrayList<Location>();
        @SerializedName("redemption")
        @Expose
        private Redemption redemption;
        @SerializedName("is_redeemable")
        @Expose
        private Boolean isRedeemable;
        @SerializedName("is_featured")
        @Expose
        private Boolean isFeatured;
        @SerializedName("brochure")
        @Expose
        private String brochure;

        /**
         * @return The pk
         */
        public Integer getPk() {
            return pk;
        }

        /**
         * @param pk The pk
         */
        public void setPk(Integer pk) {
            this.pk = pk;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return The slug
         */
        public String getSlug() {
            return slug;
        }

        /**
         * @param slug The slug
         */
        public void setSlug(String slug) {
            this.slug = slug;
        }

        /**
         * @return The merchant
         */
        public String getMerchant() {
            return merchant;
        }

        /**
         * @param merchant The merchant
         */
        public void setMerchant(String merchant) {
            this.merchant = merchant;
        }

        /**
         * @return The tier
         */
        public String getTier() {
            return tier;
        }

        /**
         * @param tier The tier
         */
        public void setTier(String tier) {
            this.tier = tier;
        }

        /**
         * @return The description
         */

        public String getMerchantDescription() {
            return merchantDescription;
        }

        public void setMerchantDescription(String merchantDescription) {
            this.merchantDescription = merchantDescription;
        }

        public String getDescription() {
            return description;
        }

        /**
         * @param description The description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * @return The contactEmail
         */
        public String getContactEmail() {
            return contactEmail;
        }

        /**
         * @param contactEmail The contact_email
         */
        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        /**
         * @return The contactPhone
         */
        public String getContactPhone() {
            return contactPhone;
        }

        /**
         * @param contactPhone The contact_phone
         */
        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        /**
         * @return The img
         */
        public String getImg() {
            return img;
        }

        /**
         * @param img The img
         */
        public void setImg(String img) {
            this.img = img;
        }

        /**
         * @return The displayImg
         */
        public String getDisplayImg() {
            return displayImg;
        }

        /**
         * @param displayImg The display_img
         */
        public void setDisplayImg(String displayImg) {
            this.displayImg = displayImg;
        }

        /**
         * @return The thumbnailImg
         */
        public String getThumbnailImg() {
            return thumbnailImg;
        }

        /**
         * @param thumbnailImg The thumbnail_img
         */
        public void setThumbnailImg(String thumbnailImg) {
            this.thumbnailImg = thumbnailImg;
        }

        /**
         * @return The website
         */
        public String getWebsite() {
            return website;
        }

        /**
         * @param website The website
         */
        public void setWebsite(String website) {
            this.website = website;
        }

        /**
         * @return The termsAndConditions
         */
        public String getTermsAndConditions() {
            return termsAndConditions;
        }

        /**
         * @param termsAndConditions The terms_and_conditions
         */
        public void setTermsAndConditions(String termsAndConditions) {
            this.termsAndConditions = termsAndConditions;
        }

        /**
         * @return The cost
         */
        public String getCost() {
            return cost;
        }

        /**
         * @param cost The cost
         */
        public void setCost(String cost) {
            this.cost = cost;
        }

        /**
         * @return The rewardType
         */
        public String getRewardType() {
            return rewardType;
        }

        /**
         * @param rewardType The reward_type
         */
        public void setRewardType(String rewardType) {
            this.rewardType = rewardType;
        }

        /**
         * @return The validUntil
         */
        public String getValidUntil() {
            return validUntil;
        }

        /**
         * @param validUntil The valid_until
         */
        public void setValidUntil(String validUntil) {
            this.validUntil = validUntil;
        }

        /**
         * @return The locations
         */
        public List<Location> getLocations() {
            return locations;
        }

        /**
         * @param locations The locations
         */
        public void setLocations(List<Location> locations) {
            this.locations = locations;
        }

        /**
         * @return The redemption
         */
        public Redemption getRedemption() {
            return redemption;
        }

        /**
         * @param redemption The redemption
         */
        public void setRedemption(Redemption redemption) {
            this.redemption = redemption;
        }

        /**
         * @return The isRedeemable
         */
        public Boolean getIsRedeemable() {
            return isRedeemable;
        }

        /**
         * @param isRedeemable The is_redeemable
         */
        public void setIsRedeemable(Boolean isRedeemable) {
            this.isRedeemable = isRedeemable;
        }

        /**
         * @return The isFeatured
         */
        public Boolean getIsFeatured() {
            return isFeatured;
        }

        /**
         * @param isFeatured The is_featured
         */
        public void setIsFeatured(Boolean isFeatured) {
            this.isFeatured = isFeatured;
        }

        public String getBrochure() {
            return brochure;
        }

        public void setBrochure(String brochure) {
            this.brochure = brochure;
        }


        public class Redemption implements Serializable {

            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("description")
            @Expose
            private String description;

            /**
             * @return The type
             */
            public String getType() {
                return type;
            }

            /**
             * @param type The type
             */
            public void setType(String type) {
                this.type = type;
            }

            /**
             * @return The description
             */
            public String getDescription() {
                return description;
            }

            /**
             * @param description The description
             */
            public void setDescription(String description) {
                this.description = description;
            }

        }


        public class Location implements Serializable {

            @SerializedName("pk")
            @Expose
            private Integer pk;
            @SerializedName("address1")
            @Expose
            private String address1;
            @SerializedName("address2")
            @Expose
            private String address2;
            @SerializedName("phone")
            @Expose
            private String phone;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("lat")
            @Expose
            private String lat;
            @SerializedName("lng")
            @Expose
            private String lng;
            @SerializedName("has_flash_password")
            @Expose
            private Boolean hasFlashPassword;

            /**
             * @return The pk
             */
            public Integer getPk() {
                return pk;
            }

            /**
             * @param pk The pk
             */
            public void setPk(Integer pk) {
                this.pk = pk;
            }

            /**
             * @return The address1
             */
            public String getAddress1() {
                return address1;
            }

            /**
             * @param address1 The address1
             */
            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            /**
             * @return The address2
             */
            public String getAddress2() {
                return address2;
            }

            /**
             * @param address2 The address2
             */
            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            /**
             * @return The phone
             */
            public String getPhone() {
                return phone;
            }

            /**
             * @param phone The phone
             */
            public void setPhone(String phone) {
                this.phone = phone;
            }

            /**
             * @return The email
             */
            public String getEmail() {
                return email;
            }

            /**
             * @param email The email
             */
            public void setEmail(String email) {
                this.email = email;
            }

            /**
             * @return The lat
             */
            public String getLat() {
                return lat;
            }

            /**
             * @param lat The lat
             */
            public void setLat(String lat) {
                this.lat = lat;
            }

            /**
             * @return The lng
             */
            public String getLng() {
                return lng;
            }

            /**
             * @param lng The lng
             */
            public void setLng(String lng) {
                this.lng = lng;
            }

            /**
             * @return The hasFlashPassword
             */
            public Boolean getHasFlashPassword() {
                return hasFlashPassword;
            }

            /**
             * @param hasFlashPassword The has_flash_password
             */
            public void setHasFlashPassword(Boolean hasFlashPassword) {
                this.hasFlashPassword = hasFlashPassword;
            }

        }

    }

}
