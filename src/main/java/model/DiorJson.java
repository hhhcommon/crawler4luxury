package model;

import java.util.List;

/**
 * Created by yang on 2017/6/20.
 */
public class DiorJson {


    /**
     * event : productClick
     * ecommerce : {"click":{"products":[{"id":"51971","name":"定位印花，白色纯棉","price":"550.0000","brand":"CDC","category":"men/ready-to-wear shirts","variant":"","dimension25":"","code":"433C529K0580_C089","position":"1"}]}}
     */

    private String event;
    private EcommerceBean ecommerce;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public EcommerceBean getEcommerce() {
        return ecommerce;
    }

    public void setEcommerce(EcommerceBean ecommerce) {
        this.ecommerce = ecommerce;
    }

    public static class EcommerceBean {
        /**
         * click : {"products":[{"id":"51971","name":"定位印花，白色纯棉","price":"550.0000","brand":"CDC","category":"men/ready-to-wear shirts","variant":"","dimension25":"","code":"433C529K0580_C089","position":"1"}]}
         */

        private ClickBean click;

        public ClickBean getClick() {
            return click;
        }

        public void setClick(ClickBean click) {
            this.click = click;
        }

        public static class ClickBean {
            private List<ProductsBean> products;

            public List<ProductsBean> getProducts() {
                return products;
            }

            public void setProducts(List<ProductsBean> products) {
                this.products = products;
            }

            public static class ProductsBean {
                /**
                 * id : 51971
                 * name : 定位印花，白色纯棉
                 * price : 550.0000
                 * brand : CDC
                 * category : men/ready-to-wear shirts
                 * variant :
                 * dimension25 :
                 * code : 433C529K0580_C089
                 * position : 1
                 */

                private String id;
                private String name;
                private String price;
                private String brand;
                private String category;
                private String variant;
                private String dimension25;
                private String code;
                private String position;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getBrand() {
                    return brand;
                }

                public void setBrand(String brand) {
                    this.brand = brand;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getVariant() {
                    return variant;
                }

                public void setVariant(String variant) {
                    this.variant = variant;
                }

                public String getDimension25() {
                    return dimension25;
                }

                public void setDimension25(String dimension25) {
                    this.dimension25 = dimension25;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getPosition() {
                    return position;
                }

                public void setPosition(String position) {
                    this.position = position;
                }
            }
        }
    }
}
