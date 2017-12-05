package model;

import java.util.List;

/**
 * Created by yang on 2017/6/21.
 */
public class BurFinal {
    private String itemLabel;
    private String outOfStockMessage;
    private String addedToBagMessage;
    private String addedToBagFailure;
    private String fiveItemMessage;
    private String shippingUnavailableMessage;
    private String registerInterest;
    private String preorder;
    private String soldOutMessage;
    private String comingSoonLabel;
    private int price;
    private String formattedPrice;
    private String defaultUrl;
    private String itemNumber;
    private String name;
    private String currency;
    private AddToBagBean addToBag;
    private CheckoutBean checkout;
    private CustomerServiceBean customerService;
    private ImageInBagBean imageInBag;
    private String imageAltText;
    private Object sku;
    private String gaProductSku;
    private String gaDeliveryType;
    private RecommendationsBean recommendations;
    private boolean recentViewed;
    private SocialShareBean socialShare;
    private DescriptionBean description;
    private ShippingMessagesBean shippingMessages;
    private boolean isOutOfStock;
    private boolean isShippingAvailable;
    private boolean isRunwayMadeToOrder;
    private boolean isAvailable;
    private boolean isSoldOut;
    private String productPreOrderable;
    private boolean isMonogrammable;
    private StoreStockLookupBeanX storeStockLookup;
    private FindInStoreBean findInStore;
    private boolean isComingSoon;
    private List<CarouselBean> carousel;
    private List<HdCarouselBean> hdCarousel;
    private List<GalleryBean> gallery;
    private List<OptionsBean> options;
    private List<FamilyBean> family;

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public String getOutOfStockMessage() {
        return outOfStockMessage;
    }

    public void setOutOfStockMessage(String outOfStockMessage) {
        this.outOfStockMessage = outOfStockMessage;
    }

    public String getAddedToBagMessage() {
        return addedToBagMessage;
    }

    public void setAddedToBagMessage(String addedToBagMessage) {
        this.addedToBagMessage = addedToBagMessage;
    }

    public String getAddedToBagFailure() {
        return addedToBagFailure;
    }

    public void setAddedToBagFailure(String addedToBagFailure) {
        this.addedToBagFailure = addedToBagFailure;
    }

    public String getFiveItemMessage() {
        return fiveItemMessage;
    }

    public void setFiveItemMessage(String fiveItemMessage) {
        this.fiveItemMessage = fiveItemMessage;
    }

    public String getShippingUnavailableMessage() {
        return shippingUnavailableMessage;
    }

    public void setShippingUnavailableMessage(String shippingUnavailableMessage) {
        this.shippingUnavailableMessage = shippingUnavailableMessage;
    }

    public String getRegisterInterest() {
        return registerInterest;
    }

    public void setRegisterInterest(String registerInterest) {
        this.registerInterest = registerInterest;
    }

    public String getPreorder() {
        return preorder;
    }

    public void setPreorder(String preorder) {
        this.preorder = preorder;
    }

    public String getSoldOutMessage() {
        return soldOutMessage;
    }

    public void setSoldOutMessage(String soldOutMessage) {
        this.soldOutMessage = soldOutMessage;
    }

    public String getComingSoonLabel() {
        return comingSoonLabel;
    }

    public void setComingSoonLabel(String comingSoonLabel) {
        this.comingSoonLabel = comingSoonLabel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AddToBagBean getAddToBag() {
        return addToBag;
    }

    public void setAddToBag(AddToBagBean addToBag) {
        this.addToBag = addToBag;
    }

    public CheckoutBean getCheckout() {
        return checkout;
    }

    public void setCheckout(CheckoutBean checkout) {
        this.checkout = checkout;
    }

    public CustomerServiceBean getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerServiceBean customerService) {
        this.customerService = customerService;
    }

    public ImageInBagBean getImageInBag() {
        return imageInBag;
    }

    public void setImageInBag(ImageInBagBean imageInBag) {
        this.imageInBag = imageInBag;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public void setImageAltText(String imageAltText) {
        this.imageAltText = imageAltText;
    }

    public Object getSku() {
        return sku;
    }

    public void setSku(Object sku) {
        this.sku = sku;
    }

    public String getGaProductSku() {
        return gaProductSku;
    }

    public void setGaProductSku(String gaProductSku) {
        this.gaProductSku = gaProductSku;
    }

    public String getGaDeliveryType() {
        return gaDeliveryType;
    }

    public void setGaDeliveryType(String gaDeliveryType) {
        this.gaDeliveryType = gaDeliveryType;
    }

    public RecommendationsBean getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(RecommendationsBean recommendations) {
        this.recommendations = recommendations;
    }

    public boolean isRecentViewed() {
        return recentViewed;
    }

    public void setRecentViewed(boolean recentViewed) {
        this.recentViewed = recentViewed;
    }

    public SocialShareBean getSocialShare() {
        return socialShare;
    }

    public void setSocialShare(SocialShareBean socialShare) {
        this.socialShare = socialShare;
    }

    public DescriptionBean getDescription() {
        return description;
    }

    public void setDescription(DescriptionBean description) {
        this.description = description;
    }

    public ShippingMessagesBean getShippingMessages() {
        return shippingMessages;
    }

    public void setShippingMessages(ShippingMessagesBean shippingMessages) {
        this.shippingMessages = shippingMessages;
    }

    public boolean isIsOutOfStock() {
        return isOutOfStock;
    }

    public void setIsOutOfStock(boolean isOutOfStock) {
        this.isOutOfStock = isOutOfStock;
    }

    public boolean isIsShippingAvailable() {
        return isShippingAvailable;
    }

    public void setIsShippingAvailable(boolean isShippingAvailable) {
        this.isShippingAvailable = isShippingAvailable;
    }

    public boolean isIsRunwayMadeToOrder() {
        return isRunwayMadeToOrder;
    }

    public void setIsRunwayMadeToOrder(boolean isRunwayMadeToOrder) {
        this.isRunwayMadeToOrder = isRunwayMadeToOrder;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isIsSoldOut() {
        return isSoldOut;
    }

    public void setIsSoldOut(boolean isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public String getProductPreOrderable() {
        return productPreOrderable;
    }

    public void setProductPreOrderable(String productPreOrderable) {
        this.productPreOrderable = productPreOrderable;
    }

    public boolean isIsMonogrammable() {
        return isMonogrammable;
    }

    public void setIsMonogrammable(boolean isMonogrammable) {
        this.isMonogrammable = isMonogrammable;
    }

    public StoreStockLookupBeanX getStoreStockLookup() {
        return storeStockLookup;
    }

    public void setStoreStockLookup(StoreStockLookupBeanX storeStockLookup) {
        this.storeStockLookup = storeStockLookup;
    }

    public FindInStoreBean getFindInStore() {
        return findInStore;
    }

    public void setFindInStore(FindInStoreBean findInStore) {
        this.findInStore = findInStore;
    }

    public boolean isIsComingSoon() {
        return isComingSoon;
    }

    public void setIsComingSoon(boolean isComingSoon) {
        this.isComingSoon = isComingSoon;
    }

    public List<CarouselBean> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<CarouselBean> carousel) {
        this.carousel = carousel;
    }

    public List<HdCarouselBean> getHdCarousel() {
        return hdCarousel;
    }

    public void setHdCarousel(List<HdCarouselBean> hdCarousel) {
        this.hdCarousel = hdCarousel;
    }

    public List<GalleryBean> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryBean> gallery) {
        this.gallery = gallery;
    }

    public List<OptionsBean> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsBean> options) {
        this.options = options;
    }

    public List<FamilyBean> getFamily() {
        return family;
    }

    public void setFamily(List<FamilyBean> family) {
        this.family = family;
    }

    public static class AddToBagBean {
        /**
         * label : ���빺���
         * link : /service/bag/
         * action : add-to-bag
         */

        private String label;
        private String link;
        private String action;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }

    public static class CheckoutBean {
        /**
         * label : ����
         * link : /service/bag/
         * action : check-out
         */

        private String label;
        private String link;
        private String action;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }

    public static class CustomerServiceBean {
        /**
         * label : ��Ҫ�����������µ�:
         * number : 4001 201 154
         * hours : ��ͨ������09:00 - 19:00 ����ʱ�䣩
         */

        private String label;
        private String number;
        private String hours;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getHours() {
            return hours;
        }

        public void setHours(String hours) {
            this.hours = hours;
        }
    }

    public static class ImageInBagBean {
        /**
         * sources : [{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w","sizes":"316px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w","sizes":"90px"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w","sizes":"90px"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w","sizes":"90px"}]
         * isNew : true
         * img : {"src":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421"}
         */

        private boolean isNew;
        private ImgBean img;
        private List<SourcesBean> sources;

        public boolean isIsNew() {
            return isNew;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public ImgBean getImg() {
            return img;
        }

        public void setImg(ImgBean img) {
            this.img = img;
        }

        public List<SourcesBean> getSources() {
            return sources;
        }

        public void setSources(List<SourcesBean> sources) {
            this.sources = sources;
        }

        public static class ImgBean {
            /**
             * src : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421
             */

            private String src;

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }
        }

        public static class SourcesBean {
            /**
             * media : (min-width: 1280px)
             * srcset : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w
             * sizes : 316px
             */

            private String media;
            private String srcset;
            private String sizes;

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }

            public String getSrcset() {
                return srcset;
            }

            public void setSrcset(String srcset) {
                this.srcset = srcset;
            }

            public String getSizes() {
                return sizes;
            }

            public void setSizes(String sizes) {
                this.sizes = sizes;
            }
        }
    }

    public static class RecommendationsBean {
        /**
         * title : ������������
         * products : [{"itemNumber":"45456551","label":"�޷��λ�װ�����ʳ���ʽ����ȹ","link":"/cotton-shirt-dress-with-pintucksmacrame-trim-p45456551","images":{"sources":[{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=324&hei=432 324w","sizes":"calc(25vw - 30px)"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w","sizes":"calc(50vw - 27px)"}],"isNew":true,"img":{"src":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520"}},"rolloverImage":{"sources":[{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":null,"sizes":"calc(25vw - 30px)"},{"srcset":null,"sizes":"calc(50vw - 27px)"}],"isNew":true,"img":{"src":"//assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520"}},"price":8700,"formattedPrice":"?8,700.00","colors":{"items":[],"more":"����"}},{"itemNumber":"40542731","label":"The DK88 - �������","link":"/the-dk88-bowling-bag-p40542731","images":{"sources":[{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d4bec778730e936e93f190da3f064c9eccdd7626.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/d4bec778730e936e93f190da3f064c9eccdd7626.jpg?$BBY_V2_SL_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d4bec778730e936e93f190da3f064c9eccdd7626.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d4bec778730e936e93f190da3f064c9eccdd7626.jpg?$BBY_V2_SL_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d4bec778730e936e93f190da3f064c9eccdd7626.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d4bec778730e936e93f190da3f064c9eccdd7626.jpg?$BBY_V2_SL_3X4$=&wid=324&hei=432 324w","sizes":"calc(25vw - 30px)"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/d4bec778730e936e93f190da3f064c9eccdd7626.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d4bec778730e936e93f190da3f064c9eccdd7626.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w","sizes":"calc(50vw - 27px)"}],"isNew":true,"img":{"src":"//assets.burberry.com/is/image/Burberryltd/d4bec778730e936e93f190da3f064c9eccdd7626.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520"}},"rolloverImage":{"sources":[{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/e9bd67b0170a8e629212d480b5c91169c6dfe946.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/e9bd67b0170a8e629212d480b5c91169c6dfe946.jpg?$BBY_V2_SL_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/e9bd67b0170a8e629212d480b5c91169c6dfe946.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/e9bd67b0170a8e629212d480b5c91169c6dfe946.jpg?$BBY_V2_SL_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":null,"sizes":"calc(25vw - 30px)"},{"srcset":null,"sizes":"calc(50vw - 27px)"}],"isNew":true,"img":{"src":"//assets.burberry.com/is/image/Burberryltd/e9bd67b0170a8e629212d480b5c91169c6dfe946.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520"}},"price":27500,"formattedPrice":"?27,500.00","colors":{"items":[],"more":"����"}},{"itemNumber":"40540881","label":"���ܸ߸���֯��Ͳѥ","link":"/mid-calf-knitted-boots-with-sculpted-heel-p40540881","images":{"sources":[{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/7a3782c450c486e21c1cb1c71e5aeee6bca9dae4.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/7a3782c450c486e21c1cb1c71e5aeee6bca9dae4.jpg?$BBY_V2_SL_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/7a3782c450c486e21c1cb1c71e5aeee6bca9dae4.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/7a3782c450c486e21c1cb1c71e5aeee6bca9dae4.jpg?$BBY_V2_SL_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/7a3782c450c486e21c1cb1c71e5aeee6bca9dae4.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/7a3782c450c486e21c1cb1c71e5aeee6bca9dae4.jpg?$BBY_V2_SL_3X4$=&wid=324&hei=432 324w","sizes":"calc(25vw - 30px)"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/7a3782c450c486e21c1cb1c71e5aeee6bca9dae4.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/7a3782c450c486e21c1cb1c71e5aeee6bca9dae4.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w","sizes":"calc(50vw - 27px)"}],"isNew":true,"img":{"src":"//assets.burberry.com/is/image/Burberryltd/7a3782c450c486e21c1cb1c71e5aeee6bca9dae4.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520"}},"rolloverImage":{"sources":[{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/42d10d4ad5f44fa5766843cd188f99f5e14d32ea.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/42d10d4ad5f44fa5766843cd188f99f5e14d32ea.jpg?$BBY_V2_ML_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/42d10d4ad5f44fa5766843cd188f99f5e14d32ea.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/42d10d4ad5f44fa5766843cd188f99f5e14d32ea.jpg?$BBY_V2_ML_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":null,"sizes":"calc(25vw - 30px)"},{"srcset":null,"sizes":"calc(50vw - 27px)"}],"isNew":true,"img":{"src":"//assets.burberry.com/is/image/Burberryltd/42d10d4ad5f44fa5766843cd188f99f5e14d32ea.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520"}},"price":8100,"formattedPrice":"?8,100.00","colors":{"items":[],"more":"����"}}]
         */

        private String title;
        private List<ProductsBean> products;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            /**
             * itemNumber : 45456551
             * label : �޷��λ�װ�����ʳ���ʽ����ȹ
             * link : /cotton-shirt-dress-with-pintucksmacrame-trim-p45456551
             * images : {"sources":[{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=324&hei=432 324w","sizes":"calc(25vw - 30px)"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w","sizes":"calc(50vw - 27px)"}],"isNew":true,"img":{"src":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520"}}
             * rolloverImage : {"sources":[{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":null,"sizes":"calc(25vw - 30px)"},{"srcset":null,"sizes":"calc(50vw - 27px)"}],"isNew":true,"img":{"src":"//assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520"}}
             * price : 8700
             * formattedPrice : ?8,700.00
             * colors : {"items":[],"more":"����"}
             */

            private String itemNumber;
            private String label;
            private String link;
            private ImagesBean images;
            private RolloverImageBean rolloverImage;
            private int price;
            private String formattedPrice;
            private ColorsBean colors;

            public String getItemNumber() {
                return itemNumber;
            }

            public void setItemNumber(String itemNumber) {
                this.itemNumber = itemNumber;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public ImagesBean getImages() {
                return images;
            }

            public void setImages(ImagesBean images) {
                this.images = images;
            }

            public RolloverImageBean getRolloverImage() {
                return rolloverImage;
            }

            public void setRolloverImage(RolloverImageBean rolloverImage) {
                this.rolloverImage = rolloverImage;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getFormattedPrice() {
                return formattedPrice;
            }

            public void setFormattedPrice(String formattedPrice) {
                this.formattedPrice = formattedPrice;
            }

            public ColorsBean getColors() {
                return colors;
            }

            public void setColors(ColorsBean colors) {
                this.colors = colors;
            }

            public static class ImagesBean {
                /**
                 * sources : [{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=324&hei=432 324w","sizes":"calc(25vw - 30px)"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w","sizes":"calc(50vw - 27px)"}]
                 * isNew : true
                 * img : {"src":"//assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520"}
                 */

                private boolean isNew;
                private ImgBeanX img;
                private List<SourcesBeanX> sources;

                public boolean isIsNew() {
                    return isNew;
                }

                public void setIsNew(boolean isNew) {
                    this.isNew = isNew;
                }

                public ImgBeanX getImg() {
                    return img;
                }

                public void setImg(ImgBeanX img) {
                    this.img = img;
                }

                public List<SourcesBeanX> getSources() {
                    return sources;
                }

                public void setSources(List<SourcesBeanX> sources) {
                    this.sources = sources;
                }

                public static class ImgBeanX {
                    /**
                     * src : //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520
                     */

                    private String src;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }
                }

                public static class SourcesBeanX {
                    /**
                     * media : (min-width: 1280px)
                     * srcset : //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/d33efa14ff96e18406a91695d1cc2eb8be8bd9e2.jpg?$BBY_V2_ML_3X4$=&wid=520&hei=693 520w
                     * sizes : 390px
                     */

                    private String media;
                    private String srcset;
                    private String sizes;

                    public String getMedia() {
                        return media;
                    }

                    public void setMedia(String media) {
                        this.media = media;
                    }

                    public String getSrcset() {
                        return srcset;
                    }

                    public void setSrcset(String srcset) {
                        this.srcset = srcset;
                    }

                    public String getSizes() {
                        return sizes;
                    }

                    public void setSizes(String sizes) {
                        this.sizes = sizes;
                    }
                }
            }

            public static class RolloverImageBean {
                /**
                 * sources : [{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":null,"sizes":"calc(25vw - 30px)"},{"srcset":null,"sizes":"calc(50vw - 27px)"}]
                 * isNew : true
                 * img : {"src":"//assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520"}
                 */

                private boolean isNew;
                private ImgBeanXX img;
                private List<SourcesBeanXX> sources;

                public boolean isIsNew() {
                    return isNew;
                }

                public void setIsNew(boolean isNew) {
                    this.isNew = isNew;
                }

                public ImgBeanXX getImg() {
                    return img;
                }

                public void setImg(ImgBeanXX img) {
                    this.img = img;
                }

                public List<SourcesBeanXX> getSources() {
                    return sources;
                }

                public void setSources(List<SourcesBeanXX> sources) {
                    this.sources = sources;
                }

                public static class ImgBeanXX {
                    /**
                     * src : //assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520
                     */

                    private String src;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }
                }

                public static class SourcesBeanXX {
                    /**
                     * media : (min-width: 1280px)
                     * srcset : //assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/a8e650090f327651c028076eb5eb73bfe531395a.jpg?$BBY_V2_SL_3X4$=&wid=520&hei=693 520w
                     * sizes : 390px
                     */

                    private String media;
                    private String srcset;
                    private String sizes;

                    public String getMedia() {
                        return media;
                    }

                    public void setMedia(String media) {
                        this.media = media;
                    }

                    public String getSrcset() {
                        return srcset;
                    }

                    public void setSrcset(String srcset) {
                        this.srcset = srcset;
                    }

                    public String getSizes() {
                        return sizes;
                    }

                    public void setSizes(String sizes) {
                        this.sizes = sizes;
                    }
                }
            }

            public static class ColorsBean {
                /**
                 * items : []
                 * more : ����
                 */

                private String more;
                private List<?> items;

                public String getMore() {
                    return more;
                }

                public void setMore(String more) {
                    this.more = more;
                }

                public List<?> getItems() {
                    return items;
                }

                public void setItems(List<?> items) {
                    this.items = items;
                }
            }
        }
    }

    public static class SocialShareBean {
        /**
         * title : �������Ʒ
         * productName : ���Գ��˶���ʽ����ȹ
         * image : {"sources":[{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600 450w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=600&hei=800 600w","sizes":"450px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600 450w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=600&hei=800 600w","sizes":"300px"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600 450w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=600&hei=800 600w","sizes":"300px"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_4X3$=&wid=1152&hei=864 1152w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_4X3$=&wid=1536&hei=1152 1536w","sizes":"768px"}],"isNew":true,"img":{"src":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600"}}
         * wechat : {"title":"΢�ŷ���","description":"���΢�ţ�ʹ��[ɨһɨ]����ɨ�� QR ���룬Ȼ��ҳ�淢�͸����ѣ������������Ȧ��"}
         */

        private String title;
        private String productName;
        private ImageBean image;
        private WechatBean wechat;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public WechatBean getWechat() {
            return wechat;
        }

        public void setWechat(WechatBean wechat) {
            this.wechat = wechat;
        }

        public static class ImageBean {
            /**
             * sources : [{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600 450w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=600&hei=800 600w","sizes":"450px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600 450w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=600&hei=800 600w","sizes":"300px"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600 450w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=600&hei=800 600w","sizes":"300px"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_4X3$=&wid=1152&hei=864 1152w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_4X3$=&wid=1536&hei=1152 1536w","sizes":"768px"}]
             * isNew : true
             * img : {"src":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600"}
             */

            private boolean isNew;
            private ImgBeanXXX img;
            private List<SourcesBeanXXX> sources;

            public boolean isIsNew() {
                return isNew;
            }

            public void setIsNew(boolean isNew) {
                this.isNew = isNew;
            }

            public ImgBeanXXX getImg() {
                return img;
            }

            public void setImg(ImgBeanXXX img) {
                this.img = img;
            }

            public List<SourcesBeanXXX> getSources() {
                return sources;
            }

            public void setSources(List<SourcesBeanXXX> sources) {
                this.sources = sources;
            }

            public static class ImgBeanXXX {
                /**
                 * src : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600
                 */

                private String src;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }
            }

            public static class SourcesBeanXXX {
                /**
                 * media : (min-width: 1280px)
                 * srcset : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=450&hei=600 450w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=600&hei=800 600w
                 * sizes : 450px
                 */

                private String media;
                private String srcset;
                private String sizes;

                public String getMedia() {
                    return media;
                }

                public void setMedia(String media) {
                    this.media = media;
                }

                public String getSrcset() {
                    return srcset;
                }

                public void setSrcset(String srcset) {
                    this.srcset = srcset;
                }

                public String getSizes() {
                    return sizes;
                }

                public void setSizes(String sizes) {
                    this.sizes = sizes;
                }
            }
        }

        public static class WechatBean {
            /**
             * title : ΢�ŷ���
             * description : ���΢�ţ�ʹ��[ɨһɨ]����ɨ�� QR ���룬Ȼ��ҳ�淢�͸����ѣ������������Ȧ��
             */

            private String title;
            private String description;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }

    public static class DescriptionBean {
        /**
         * label : ��Ʒ����
         * content : <p>���ԳƼ����˶���ʽ����ȹ�����������ƽ֯���������ϡ��������߿�����ƣ�ʹ���Ÿ����������硣��������ǰ��ּ���¾�Ӣ�����ܴ�ʦ Henry Moore ��Ʒ�еı�־�Ի����������͡�</p>
         * subItem : {"label":"��Ʒ����","content":"<ul><li>����ȹ���ȣ�70cm����ܰ��ʾ���˳�����UK 8��Ϊ������׼�����ݳ���Ĳ�ͬ���ᰴ�������б仯��<\/li><li>81%�ޣ�19%������ά<\/li><li>װ�Σ�100%��<\/li><li>б���ʽ<\/li><li>����ʽ�����������<\/li><li>��ϴ<\/li><li>�������<\/li><\/ul>"}
         * promotionalText : null
         * monogramPromoText : null
         */

        private String label;
        private String content;
        private SubItemBean subItem;
        private Object promotionalText;
        private Object monogramPromoText;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public SubItemBean getSubItem() {
            return subItem;
        }

        public void setSubItem(SubItemBean subItem) {
            this.subItem = subItem;
        }

        public Object getPromotionalText() {
            return promotionalText;
        }

        public void setPromotionalText(Object promotionalText) {
            this.promotionalText = promotionalText;
        }

        public Object getMonogramPromoText() {
            return monogramPromoText;
        }

        public void setMonogramPromoText(Object monogramPromoText) {
            this.monogramPromoText = monogramPromoText;
        }

        public static class SubItemBean {
            /**
             * label : ��Ʒ����
             * content : <ul><li>����ȹ���ȣ�70cm����ܰ��ʾ���˳�����UK 8��Ϊ������׼�����ݳ���Ĳ�ͬ���ᰴ�������б仯��</li><li>81%�ޣ�19%������ά</li><li>װ�Σ�100%��</li><li>б���ʽ</li><li>����ʽ�����������</li><li>��ϴ</li><li>�������</li></ul>
             */

            private String label;
            private String content;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }

    public static class ShippingMessagesBean {
        /**
         * label : ���ͼ��˻�
         * content : [{"icon":"van","label":"����ͻ�����","description":"������ͷ��������ʱ��Ϊ��������5-9��"},{"icon":"returns","label":"����˻�","description":"��������˻�����"},{"icon":"bow","label":"�����Ʒ��װ","description":"������Ҿ�����Ʒ��װ����貽��ڱ�����Բ�ɫ�д���ϵ"}]
         */

        private String label;
        private List<ContentBean> content;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * icon : van
             * label : ����ͻ�����
             * description : ������ͷ��������ʱ��Ϊ��������5-9��
             */

            private String icon;
            private String label;
            private String description;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }

    public static class StoreStockLookupBeanX {
        /**
         * storeStockLookup : {"modalTitle":"��Ʒ����","editLocation":"�޸ĵص�"}
         * searchBox : {"searchCtaLabel":"����","nearestStoreCtaLabel":"���������������","selectCountryLabel":"ѡ����� / ����","selectStateLabel":"ѡ��ʡ��","selectCityLabel":"ѡ�����","geoLocationDisabled":"��Ǹ���������������λ�ã��Ա����Ƕ�λ�������ڵص㡣"}
         * listView : {"detailsLabel":"��ϸ��Ϣ","hideDetailsLabel":"��������","showInMapLabel":"�鿴��ͼ","showDirectionsLabel":"��λָ��","cityStoresLabel":"([x] �侫Ʒ��)","mapLabel":"��ͼ","storeListTitle":"��Ʒ��","storesLabel":"���������ڵظ����ҵ� [x] �Ҿ�Ʒ���п��","storesSearchLabel":"��[y]�����ҵ� [x] �Ҿ�Ʒ���п��","nearbyStoresLabel":"�������ڵظ����ҵ� [x] �Ҿ�Ʒ��","noStocksNearby":"����������Ʒ�ھ��������ڵ� 50 Ӣ�ﷶΧ�����޿��","noStocksLocation":"����������Ʒ���޿��","limitedAvailability":"�Ϳ��","outOfStock":"���޿��","available":"�п��","showStoresOutOfStock":"��ʾȫ����Ʒ��","openToday":"����Ӫҵ"}
         * mapView : {"detailsLabel":"��ϸ��Ϣ","hideDetailsLabel":"��������","unableToDisplayTitle":"�޷���ʾ��ͼ","unableToDisplayDescription":"���ڰٶ��޷��ṩѡ�����ҵĵ�ͼ����������޷�Ϊ����ʾ�õ���λ�õĸ�����ϸ��Ϣ��\n����������б��Ի�ȡ���������Ϣ��"}
         * baiduMapKey : KXQEcPBg2mIm3dNaTYwdpYxL
         */

        private StoreStockLookupBean storeStockLookup;
        private SearchBoxBean searchBox;
        private ListViewBean listView;
        private MapViewBean mapView;
        private String baiduMapKey;

        public StoreStockLookupBean getStoreStockLookup() {
            return storeStockLookup;
        }

        public void setStoreStockLookup(StoreStockLookupBean storeStockLookup) {
            this.storeStockLookup = storeStockLookup;
        }

        public SearchBoxBean getSearchBox() {
            return searchBox;
        }

        public void setSearchBox(SearchBoxBean searchBox) {
            this.searchBox = searchBox;
        }

        public ListViewBean getListView() {
            return listView;
        }

        public void setListView(ListViewBean listView) {
            this.listView = listView;
        }

        public MapViewBean getMapView() {
            return mapView;
        }

        public void setMapView(MapViewBean mapView) {
            this.mapView = mapView;
        }

        public String getBaiduMapKey() {
            return baiduMapKey;
        }

        public void setBaiduMapKey(String baiduMapKey) {
            this.baiduMapKey = baiduMapKey;
        }

        public static class StoreStockLookupBean {
            /**
             * modalTitle : ��Ʒ����
             * editLocation : �޸ĵص�
             */

            private String modalTitle;
            private String editLocation;

            public String getModalTitle() {
                return modalTitle;
            }

            public void setModalTitle(String modalTitle) {
                this.modalTitle = modalTitle;
            }

            public String getEditLocation() {
                return editLocation;
            }

            public void setEditLocation(String editLocation) {
                this.editLocation = editLocation;
            }
        }

        public static class SearchBoxBean {
            /**
             * searchCtaLabel : ����
             * nearestStoreCtaLabel : ���������������
             * selectCountryLabel : ѡ����� / ����
             * selectStateLabel : ѡ��ʡ��
             * selectCityLabel : ѡ�����
             * geoLocationDisabled : ��Ǹ���������������λ�ã��Ա����Ƕ�λ�������ڵص㡣
             */

            private String searchCtaLabel;
            private String nearestStoreCtaLabel;
            private String selectCountryLabel;
            private String selectStateLabel;
            private String selectCityLabel;
            private String geoLocationDisabled;

            public String getSearchCtaLabel() {
                return searchCtaLabel;
            }

            public void setSearchCtaLabel(String searchCtaLabel) {
                this.searchCtaLabel = searchCtaLabel;
            }

            public String getNearestStoreCtaLabel() {
                return nearestStoreCtaLabel;
            }

            public void setNearestStoreCtaLabel(String nearestStoreCtaLabel) {
                this.nearestStoreCtaLabel = nearestStoreCtaLabel;
            }

            public String getSelectCountryLabel() {
                return selectCountryLabel;
            }

            public void setSelectCountryLabel(String selectCountryLabel) {
                this.selectCountryLabel = selectCountryLabel;
            }

            public String getSelectStateLabel() {
                return selectStateLabel;
            }

            public void setSelectStateLabel(String selectStateLabel) {
                this.selectStateLabel = selectStateLabel;
            }

            public String getSelectCityLabel() {
                return selectCityLabel;
            }

            public void setSelectCityLabel(String selectCityLabel) {
                this.selectCityLabel = selectCityLabel;
            }

            public String getGeoLocationDisabled() {
                return geoLocationDisabled;
            }

            public void setGeoLocationDisabled(String geoLocationDisabled) {
                this.geoLocationDisabled = geoLocationDisabled;
            }
        }

        public static class ListViewBean {
            /**
             * detailsLabel : ��ϸ��Ϣ
             * hideDetailsLabel : ��������
             * showInMapLabel : �鿴��ͼ
             * showDirectionsLabel : ��λָ��
             * cityStoresLabel : ([x] �侫Ʒ��)
             * mapLabel : ��ͼ
             * storeListTitle : ��Ʒ��
             * storesLabel : ���������ڵظ����ҵ� [x] �Ҿ�Ʒ���п��
             * storesSearchLabel : ��[y]�����ҵ� [x] �Ҿ�Ʒ���п��
             * nearbyStoresLabel : �������ڵظ����ҵ� [x] �Ҿ�Ʒ��
             * noStocksNearby : ����������Ʒ�ھ��������ڵ� 50 Ӣ�ﷶΧ�����޿��
             * noStocksLocation : ����������Ʒ���޿��
             * limitedAvailability : �Ϳ��
             * outOfStock : ���޿��
             * available : �п��
             * showStoresOutOfStock : ��ʾȫ����Ʒ��
             * openToday : ����Ӫҵ
             */

            private String detailsLabel;
            private String hideDetailsLabel;
            private String showInMapLabel;
            private String showDirectionsLabel;
            private String cityStoresLabel;
            private String mapLabel;
            private String storeListTitle;
            private String storesLabel;
            private String storesSearchLabel;
            private String nearbyStoresLabel;
            private String noStocksNearby;
            private String noStocksLocation;
            private String limitedAvailability;
            private String outOfStock;
            private String available;
            private String showStoresOutOfStock;
            private String openToday;

            public String getDetailsLabel() {
                return detailsLabel;
            }

            public void setDetailsLabel(String detailsLabel) {
                this.detailsLabel = detailsLabel;
            }

            public String getHideDetailsLabel() {
                return hideDetailsLabel;
            }

            public void setHideDetailsLabel(String hideDetailsLabel) {
                this.hideDetailsLabel = hideDetailsLabel;
            }

            public String getShowInMapLabel() {
                return showInMapLabel;
            }

            public void setShowInMapLabel(String showInMapLabel) {
                this.showInMapLabel = showInMapLabel;
            }

            public String getShowDirectionsLabel() {
                return showDirectionsLabel;
            }

            public void setShowDirectionsLabel(String showDirectionsLabel) {
                this.showDirectionsLabel = showDirectionsLabel;
            }

            public String getCityStoresLabel() {
                return cityStoresLabel;
            }

            public void setCityStoresLabel(String cityStoresLabel) {
                this.cityStoresLabel = cityStoresLabel;
            }

            public String getMapLabel() {
                return mapLabel;
            }

            public void setMapLabel(String mapLabel) {
                this.mapLabel = mapLabel;
            }

            public String getStoreListTitle() {
                return storeListTitle;
            }

            public void setStoreListTitle(String storeListTitle) {
                this.storeListTitle = storeListTitle;
            }

            public String getStoresLabel() {
                return storesLabel;
            }

            public void setStoresLabel(String storesLabel) {
                this.storesLabel = storesLabel;
            }

            public String getStoresSearchLabel() {
                return storesSearchLabel;
            }

            public void setStoresSearchLabel(String storesSearchLabel) {
                this.storesSearchLabel = storesSearchLabel;
            }

            public String getNearbyStoresLabel() {
                return nearbyStoresLabel;
            }

            public void setNearbyStoresLabel(String nearbyStoresLabel) {
                this.nearbyStoresLabel = nearbyStoresLabel;
            }

            public String getNoStocksNearby() {
                return noStocksNearby;
            }

            public void setNoStocksNearby(String noStocksNearby) {
                this.noStocksNearby = noStocksNearby;
            }

            public String getNoStocksLocation() {
                return noStocksLocation;
            }

            public void setNoStocksLocation(String noStocksLocation) {
                this.noStocksLocation = noStocksLocation;
            }

            public String getLimitedAvailability() {
                return limitedAvailability;
            }

            public void setLimitedAvailability(String limitedAvailability) {
                this.limitedAvailability = limitedAvailability;
            }

            public String getOutOfStock() {
                return outOfStock;
            }

            public void setOutOfStock(String outOfStock) {
                this.outOfStock = outOfStock;
            }

            public String getAvailable() {
                return available;
            }

            public void setAvailable(String available) {
                this.available = available;
            }

            public String getShowStoresOutOfStock() {
                return showStoresOutOfStock;
            }

            public void setShowStoresOutOfStock(String showStoresOutOfStock) {
                this.showStoresOutOfStock = showStoresOutOfStock;
            }

            public String getOpenToday() {
                return openToday;
            }

            public void setOpenToday(String openToday) {
                this.openToday = openToday;
            }
        }

        public static class MapViewBean {
            /**
             * detailsLabel : ��ϸ��Ϣ
             * hideDetailsLabel : ��������
             * unableToDisplayTitle : �޷���ʾ��ͼ
             * unableToDisplayDescription : ���ڰٶ��޷��ṩѡ�����ҵĵ�ͼ����������޷�Ϊ����ʾ�õ���λ�õĸ�����ϸ��Ϣ��
             ����������б��Ի�ȡ���������Ϣ��
             */

            private String detailsLabel;
            private String hideDetailsLabel;
            private String unableToDisplayTitle;
            private String unableToDisplayDescription;

            public String getDetailsLabel() {
                return detailsLabel;
            }

            public void setDetailsLabel(String detailsLabel) {
                this.detailsLabel = detailsLabel;
            }

            public String getHideDetailsLabel() {
                return hideDetailsLabel;
            }

            public void setHideDetailsLabel(String hideDetailsLabel) {
                this.hideDetailsLabel = hideDetailsLabel;
            }

            public String getUnableToDisplayTitle() {
                return unableToDisplayTitle;
            }

            public void setUnableToDisplayTitle(String unableToDisplayTitle) {
                this.unableToDisplayTitle = unableToDisplayTitle;
            }

            public String getUnableToDisplayDescription() {
                return unableToDisplayDescription;
            }

            public void setUnableToDisplayDescription(String unableToDisplayDescription) {
                this.unableToDisplayDescription = unableToDisplayDescription;
            }
        }
    }

    public static class FindInStoreBean {
        /**
         * label : �鿴��Ʒ����
         * findInStoreEnable : true
         * imageUrlSets : {"large":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421","small":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_9X16$=&wid=316&hei=561"}
         * colour : {"key":"��ɫ","value":"��ɫ"}
         * size : {"sizeLabel":"����","label":"ѡ�����","renderingType":"default","description":"ѡ�����","standardSleeveSize":"��׼�䳤��ʽ","shorterSleeveSize":"�϶��䳤��ʽ���� 3cm��","hasShorterSleeveSizes":false,"errorLabel":"��ѡ�����","currentValue":null,"type":"size","items":[{"label":"02","value":"45459581001","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"04","value":"45459581002","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"06","value":"45459581003","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"08","value":"45459581004","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"10","value":"45459581005","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"12","value":"45459581006","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"14","value":"45459581007","isAvailable":false,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"16","value":"45459581008","isAvailable":false,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false}],"link":"����ָ��"}
         */

        private String label;
        private boolean findInStoreEnable;
        private ImageUrlSetsBean imageUrlSets;
        private ColourBean colour;
        private SizeBean size;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public boolean isFindInStoreEnable() {
            return findInStoreEnable;
        }

        public void setFindInStoreEnable(boolean findInStoreEnable) {
            this.findInStoreEnable = findInStoreEnable;
        }

        public ImageUrlSetsBean getImageUrlSets() {
            return imageUrlSets;
        }

        public void setImageUrlSets(ImageUrlSetsBean imageUrlSets) {
            this.imageUrlSets = imageUrlSets;
        }

        public ColourBean getColour() {
            return colour;
        }

        public void setColour(ColourBean colour) {
            this.colour = colour;
        }

        public SizeBean getSize() {
            return size;
        }

        public void setSize(SizeBean size) {
            this.size = size;
        }

        public static class ImageUrlSetsBean {
            /**
             * large : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421
             * small : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_9X16$=&wid=316&hei=561
             */

            private String large;
            private String small;

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }
        }

        public static class ColourBean {
            /**
             * key : ��ɫ
             * value : ��ɫ
             */

            private String key;
            private String value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class SizeBean {
            /**
             * sizeLabel : ����
             * label : ѡ�����
             * renderingType : default
             * description : ѡ�����
             * standardSleeveSize : ��׼�䳤��ʽ
             * shorterSleeveSize : �϶��䳤��ʽ���� 3cm��
             * hasShorterSleeveSizes : false
             * errorLabel : ��ѡ�����
             * currentValue : null
             * type : size
             * items : [{"label":"02","value":"45459581001","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"04","value":"45459581002","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"06","value":"45459581003","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"08","value":"45459581004","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"10","value":"45459581005","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"12","value":"45459581006","isAvailable":true,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"14","value":"45459581007","isAvailable":false,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false},{"label":"16","value":"45459581008","isAvailable":false,"assignSleeveSizeClass":"standard-sleeve","isPreOrderProduct":false}]
             * link : ����ָ��
             */

            private String sizeLabel;
            private String label;
            private String renderingType;
            private String description;
            private String standardSleeveSize;
            private String shorterSleeveSize;
            private boolean hasShorterSleeveSizes;
            private String errorLabel;
            private Object currentValue;
            private String type;
            private String link;
            private List<ItemsBean> items;

            public String getSizeLabel() {
                return sizeLabel;
            }

            public void setSizeLabel(String sizeLabel) {
                this.sizeLabel = sizeLabel;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getRenderingType() {
                return renderingType;
            }

            public void setRenderingType(String renderingType) {
                this.renderingType = renderingType;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getStandardSleeveSize() {
                return standardSleeveSize;
            }

            public void setStandardSleeveSize(String standardSleeveSize) {
                this.standardSleeveSize = standardSleeveSize;
            }

            public String getShorterSleeveSize() {
                return shorterSleeveSize;
            }

            public void setShorterSleeveSize(String shorterSleeveSize) {
                this.shorterSleeveSize = shorterSleeveSize;
            }

            public boolean isHasShorterSleeveSizes() {
                return hasShorterSleeveSizes;
            }

            public void setHasShorterSleeveSizes(boolean hasShorterSleeveSizes) {
                this.hasShorterSleeveSizes = hasShorterSleeveSizes;
            }

            public String getErrorLabel() {
                return errorLabel;
            }

            public void setErrorLabel(String errorLabel) {
                this.errorLabel = errorLabel;
            }

            public Object getCurrentValue() {
                return currentValue;
            }

            public void setCurrentValue(Object currentValue) {
                this.currentValue = currentValue;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * label : 02
                 * value : 45459581001
                 * isAvailable : true
                 * assignSleeveSizeClass : standard-sleeve
                 * isPreOrderProduct : false
                 */

                private String label;
                private String value;
                private boolean isAvailable;
                private String assignSleeveSizeClass;
                private boolean isPreOrderProduct;

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public boolean isIsAvailable() {
                    return isAvailable;
                }

                public void setIsAvailable(boolean isAvailable) {
                    this.isAvailable = isAvailable;
                }

                public String getAssignSleeveSizeClass() {
                    return assignSleeveSizeClass;
                }

                public void setAssignSleeveSizeClass(String assignSleeveSizeClass) {
                    this.assignSleeveSizeClass = assignSleeveSizeClass;
                }

                public boolean isIsPreOrderProduct() {
                    return isPreOrderProduct;
                }

                public void setIsPreOrderProduct(boolean isPreOrderProduct) {
                    this.isPreOrderProduct = isPreOrderProduct;
                }
            }
        }
    }

    public static class CarouselBean {
        /**
         * sources : [{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1006&hei=1006 1006w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1341&hei=1341 1341w","sizes":"1006px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=810&hei=810 810w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1080&hei=1080 1080w","sizes":"calc(58.333333333333336vw - 57px)"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1152&hei=1152 1152w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1536&hei=1536 1536w","sizes":"100vw"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=480&hei=640 480w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=640&hei=853 640w","sizes":"100vw"}]
         * isNew : true
         * img : {"src":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1006&hei=1006"}
         */

        private boolean isNew;
        private ImgBeanXXXX img;
        private List<SourcesBeanXXXX> sources;

        public boolean isIsNew() {
            return isNew;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public ImgBeanXXXX getImg() {
            return img;
        }

        public void setImg(ImgBeanXXXX img) {
            this.img = img;
        }

        public List<SourcesBeanXXXX> getSources() {
            return sources;
        }

        public void setSources(List<SourcesBeanXXXX> sources) {
            this.sources = sources;
        }

        public static class ImgBeanXXXX {
            /**
             * src : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1006&hei=1006
             */

            private String src;

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }
        }

        public static class SourcesBeanXXXX {
            /**
             * media : (min-width: 1280px)
             * srcset : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1006&hei=1006 1006w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1341&hei=1341 1341w
             * sizes : 1006px
             */

            private String media;
            private String srcset;
            private String sizes;

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }

            public String getSrcset() {
                return srcset;
            }

            public void setSrcset(String srcset) {
                this.srcset = srcset;
            }

            public String getSizes() {
                return sizes;
            }

            public void setSizes(String sizes) {
                this.sizes = sizes;
            }
        }
    }

    public static class HdCarouselBean {
        /**
         * sources : [{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650 1650w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650 1650w","sizes":"1650px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650 1650w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650 1650w","sizes":"150vw"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650 1650w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650 1650w","sizes":"150vw"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=720&hei=960 720w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_3X4$=&wid=960&hei=1280 960w","sizes":"150vw"}]
         * isNew : true
         * img : {"src":"//assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650"}
         */

        private boolean isNew;
        private ImgBeanXXXXX img;
        private List<SourcesBeanXXXXX> sources;

        public boolean isIsNew() {
            return isNew;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public ImgBeanXXXXX getImg() {
            return img;
        }

        public void setImg(ImgBeanXXXXX img) {
            this.img = img;
        }

        public List<SourcesBeanXXXXX> getSources() {
            return sources;
        }

        public void setSources(List<SourcesBeanXXXXX> sources) {
            this.sources = sources;
        }

        public static class ImgBeanXXXXX {
            /**
             * src : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650
             */

            private String src;

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }
        }

        public static class SourcesBeanXXXXX {
            /**
             * media : (min-width: 1280px)
             * srcset : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650 1650w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1650&hei=1650 1650w
             * sizes : 1650px
             */

            private String media;
            private String srcset;
            private String sizes;

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }

            public String getSrcset() {
                return srcset;
            }

            public void setSrcset(String srcset) {
                this.srcset = srcset;
            }

            public String getSizes() {
                return sizes;
            }

            public void setSizes(String sizes) {
                this.sizes = sizes;
            }
        }
    }

    public static class GalleryBean {
        /**
         * sources : [{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1650&hei=1237 1650w, //assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1650&hei=1237 1650w","sizes":"1650px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1428&hei=1071 1428w, //assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1650&hei=1237 1650w","sizes":"calc(100vw - 72px)"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1080&hei=810 1080w, //assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1440&hei=1080 1440w","sizes":"calc(100vw - 48px)"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_3X4$=&wid=426&hei=568 426w, //assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_3X4$=&wid=568&hei=757 568w","sizes":"calc(100vw - 36px)"}]
         * isNew : true
         * img : {"src":"//assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1650&hei=1237"}
         */

        private boolean isNew;
        private ImgBeanXXXXXX img;
        private List<SourcesBeanXXXXXX> sources;

        public boolean isIsNew() {
            return isNew;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public ImgBeanXXXXXX getImg() {
            return img;
        }

        public void setImg(ImgBeanXXXXXX img) {
            this.img = img;
        }

        public List<SourcesBeanXXXXXX> getSources() {
            return sources;
        }

        public void setSources(List<SourcesBeanXXXXXX> sources) {
            this.sources = sources;
        }

        public static class ImgBeanXXXXXX {
            /**
             * src : //assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1650&hei=1237
             */

            private String src;

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }
        }

        public static class SourcesBeanXXXXXX {
            /**
             * media : (min-width: 1280px)
             * srcset : //assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1650&hei=1237 1650w, //assets.burberry.com/is/image/Burberryltd/eee27434dc6c7fd656b9828dda9a62c82e01bb22.jpg?$BBY_V2_SL_4X3$=&wid=1650&hei=1237 1650w
             * sizes : 1650px
             */

            private String media;
            private String srcset;
            private String sizes;

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }

            public String getSrcset() {
                return srcset;
            }

            public void setSrcset(String srcset) {
                this.srcset = srcset;
            }

            public String getSizes() {
                return sizes;
            }

            public void setSizes(String sizes) {
                this.sizes = sizes;
            }
        }
    }

    public static class OptionsBean {
        /**
         * label : ��ɫ
         * type : colour
         * currentValue : 45459581
         * renderingType : swatch
         * items : [{"label":"��ɫ","value":"45459581","isAvailable":true,"link":"/asymmetric-sweatshirt-dress-p45459581","imageLink":"//assets.burberry.com/is/image/Burberryltd/1467c2750992e70fa001c45881dff1f33dff71b0.jpg"}]
         * description : ѡ�����
         * standardSleeveSize : ��׼�䳤��ʽ
         * shorterSleeveSize : �϶��䳤��ʽ���� 3cm��
         * hasShorterSleeveSizes : false
         * errorLabel : ��ѡ�����
         * link : ����ָ��
         */

        private String label;
        private String type;
        private String currentValue;
        private String renderingType;
        private String description;
        private String standardSleeveSize;
        private String shorterSleeveSize;
        private boolean hasShorterSleeveSizes;
        private String errorLabel;
        private String link;
        private List<ItemsBeanX> items;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCurrentValue() {
            return currentValue;
        }

        public void setCurrentValue(String currentValue) {
            this.currentValue = currentValue;
        }

        public String getRenderingType() {
            return renderingType;
        }

        public void setRenderingType(String renderingType) {
            this.renderingType = renderingType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStandardSleeveSize() {
            return standardSleeveSize;
        }

        public void setStandardSleeveSize(String standardSleeveSize) {
            this.standardSleeveSize = standardSleeveSize;
        }

        public String getShorterSleeveSize() {
            return shorterSleeveSize;
        }

        public void setShorterSleeveSize(String shorterSleeveSize) {
            this.shorterSleeveSize = shorterSleeveSize;
        }

        public boolean isHasShorterSleeveSizes() {
            return hasShorterSleeveSizes;
        }

        public void setHasShorterSleeveSizes(boolean hasShorterSleeveSizes) {
            this.hasShorterSleeveSizes = hasShorterSleeveSizes;
        }

        public String getErrorLabel() {
            return errorLabel;
        }

        public void setErrorLabel(String errorLabel) {
            this.errorLabel = errorLabel;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public List<ItemsBeanX> getItems() {
            return items;
        }

        public void setItems(List<ItemsBeanX> items) {
            this.items = items;
        }

        public static class ItemsBeanX {
            /**
             * label : ��ɫ
             * value : 45459581
             * isAvailable : true
             * link : /asymmetric-sweatshirt-dress-p45459581
             * imageLink : //assets.burberry.com/is/image/Burberryltd/1467c2750992e70fa001c45881dff1f33dff71b0.jpg
             */

            private String label;
            private String value;
            private boolean isAvailable;
            private String link;
            private String imageLink;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public boolean isIsAvailable() {
                return isAvailable;
            }

            public void setIsAvailable(boolean isAvailable) {
                this.isAvailable = isAvailable;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getImageLink() {
                return imageLink;
            }

            public void setImageLink(String imageLink) {
                this.imageLink = imageLink;
            }
        }
    }

    public static class FamilyBean {


        private String id;
        private ImageBeanX image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ImageBeanX getImage() {
            return image;
        }

        public void setImage(ImageBeanX image) {
            this.image = image;
        }

        public static class ImageBeanX {


            private boolean isNew;
            private ImgBeanXXXXXXX img;
            private List<SourcesBeanXXXXXXX> sources;

            public boolean isIsNew() {
                return isNew;
            }

            public void setIsNew(boolean isNew) {
                this.isNew = isNew;
            }

            public ImgBeanXXXXXXX getImg() {
                return img;
            }

            public void setImg(ImgBeanXXXXXXX img) {
                this.img = img;
            }

            public List<SourcesBeanXXXXXXX> getSources() {
                return sources;
            }

            public void setSources(List<SourcesBeanXXXXXXX> sources) {
                this.sources = sources;
            }

            public static class ImgBeanXXXXXXX {
                /**
                 * src : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1006&hei=1006
                 */

                private String src;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }
            }

            public static class SourcesBeanXXXXXXX {
                /**
                 * media : (min-width: 1280px)
                 * srcset : //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1006&hei=1006 1006w, //assets.burberry.com/is/image/Burberryltd/2b3af7bd4f4b1485fffef01f87ce59b5c5d7e03e.jpg?$BBY_V2_ML_1X1$=&wid=1341&hei=1341 1341w
                 * sizes : 1006px
                 */

                private String media;
                private String srcset;
                private String sizes;

                public String getMedia() {
                    return media;
                }

                public void setMedia(String media) {
                    this.media = media;
                }

                public String getSrcset() {
                    return srcset;
                }

                public void setSrcset(String srcset) {
                    this.srcset = srcset;
                }

                public String getSizes() {
                    return sizes;
                }

                public void setSizes(String sizes) {
                    this.sizes = sizes;
                }
            }
        }
    }
}
