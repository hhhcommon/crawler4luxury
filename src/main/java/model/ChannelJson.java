package model;

import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/6.15:54
 * @Desc: to do?
 */
public class ChannelJson {


    /**
     * css : ["productsheet","product","looksheet","white"]
     * data : {"categoryAutoOpen":false,"collectionId":"18C","collectionLabel":"2017/18早春度假系列","details":{"functionalities":[],"information":[{"datas":[{"color":"米色、灰与乳白","data":[{"availability":true,"displayPrice":true,"ref":"P57900 V43835 MD528","refPrice":"P57900V43835"}],"isMulti":false,"material":"花织斜纹软呢","title":"连衣裙与腰带"}],"title":"高级成衣"},{"datas":[{"color":"金色、珍珠白、米色与黄","data":[{"availability":true,"displayPrice":false,"ref":"A99351 Y47056 Z5620","refPrice":"A99351Y47056"}],"isMulti":false,"material":"金属、淡水珍珠、水钻与名贵树脂","title":"发带"},{"color":"黑","data":[{"availability":true,"displayPrice":true,"ref":"A76357 X11729 94305","refPrice":"A76357X11729"}],"isMulti":false,"material":"真丝","title":"发带"},{"color":"金、蓝及绿","data":[{"availability":true,"displayPrice":true,"ref":"A99438 Y49180 Z4746","refPrice":"A99438Y49180"}],"isMulti":false,"material":"金属、名贵树脂与水钻","title":"发梳"},{"color":"金与蓝","data":[{"availability":true,"displayPrice":true,"ref":"A97930 Y50402 Z2638","refPrice":"A97930Y50402"}],"isMulti":false,"material":"金属与名贵树脂","title":"耳环"},{"color":"金","data":[{"availability":true,"displayPrice":true,"ref":"A96924 X01060 Z0000","refPrice":"A96924X01060"}],"isMulti":false,"material":"金属","title":"颈链"},{"color":"金色、蓝与珍珠白","data":[{"availability":true,"displayPrice":true,"ref":"A99365 Y47023 Z5617","refPrice":"A99365Y47023"}],"isMulti":false,"material":"金属、淡水珍珠、天然珍珠、水钻、琉璃与名贵树脂","title":"项链"},{"color":"金与珍珠白","data":[{"availability":true,"displayPrice":true,"ref":"A99208 Y47020 Z2048","refPrice":"A99208Y47020"}],"isMulti":false,"material":"金属、淡水珍珠与琉璃","title":"长项链"},{"color":"金、粉红与珍珠白","data":[{"availability":true,"displayPrice":true,"ref":"A96930 Y47059 Z3014","refPrice":"A96930Y47059"}],"isMulti":false,"material":"金属、小牛皮与名贵树脂","title":"手镯"},{"color":"金色、蓝与珍珠白","data":[{"availability":true,"displayPrice":true,"ref":"A99407 Y47078 Z5617","refPrice":"A99407Y47078"}],"isMulti":false,"material":"金属、天然珍珠、琉璃与名贵树脂","title":"手镯"},{"color":"金与淡绿","data":[{"availability":true,"displayPrice":true,"ref":"A96928 Y47058 Z4905","refPrice":"A96928Y47058"}],"isMulti":false,"material":"金属与小牛皮","title":"手镯"},{"color":"绿","data":[{"availability":true,"displayPrice":true,"ref":"G33608 X06008 0G817","refPrice":"G33608X06008"}],"isMulti":false,"material":"羊皮革","title":"凉鞋"}],"title":"配饰系列"}]},"gridhref":"/zh_CN/fashion/products/ready-to-wear/g.cruise-2017-18.c.18C.html","gridimgsrc":"/dam/fashion/catalog/collections/18C/RTW/looks/18C1.jpg.fashionImg.look-grid","height":500,"imgsrc":"/dam/fashion/catalog/collections/18C/RTW/looks/18C1.jpg.fashionImg.look-sheet","index":"01","moduleBarPosition":"under","modules":[{"imgsrc":"/dam/fashion/en/collections/18C/CAMPAIGN/VIGNETTES/Vignette_18C_01bis.jpg","name":"slideshow","title":"广告大片"},{"imgsrc":"/dam/fashion/catalog/collections/18C/HAT/products/A76357/A76357X1172994305/headband-look-accessories-push.jpg","name":"accessories","title":"配饰系列"}],"modulesJsonUrl":"/zh_CN/fashion/products/ready-to-wear/g/s/_jcr_content.ajax.modules.18C.18C1.json","title":"款式 1","twitterTextProposed":"欣赏香奈儿2017/18早春度假系列高级成衣所有款式，敬请前往香奈儿CHANEL官方网站。查看香奈儿2017/18早春度假系列高级成衣所有款式细节及价格等。","width":339,"zoom":{"imgsrc":"/dam/fashion/catalog/collections/18C/RTW/looks/18C1.jpg.fashionImg","title":"缩放"}}
     * images : {}
     * navItems : [{"href":"/zh_CN/fashion.html","title":"Chanel","type":"pages/menu-link"},{"action":"product-lines","title":"产品系列","type":"pages/menu-item"},{"href":"/zh_CN/fashion/products/ready-to-wear.html","title":"高级成衣","type":"pages/menu-link"},{"href":"/zh_CN/fashion/products/ready-to-wear/g.cruise-2017-18.c.18C.html","title":"2017/18早春度假系列","type":"pages/menu-link"},{"title":"款式 1","type":"pages/menu-text"}]
     * navPosition : bottom
     * template : looksheet
     * title : 香奈儿2017/18早春度假系列-高级成衣-款式 1-香奈儿CHANEL官网
     * webtrendsLoad : {"cat":"RTW","scat1":"LOOK INTRO","scat2":"18C"}
     */

    private DataBeanX data;
    private ImagesBean images;
    private String navPosition;
    private String template;
    private String title;
    private WebtrendsLoadBean webtrendsLoad;
    private List<String> css;
    private List<NavItemsBean> navItems;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getNavPosition() {
        return navPosition;
    }

    public void setNavPosition(String navPosition) {
        this.navPosition = navPosition;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WebtrendsLoadBean getWebtrendsLoad() {
        return webtrendsLoad;
    }

    public void setWebtrendsLoad(WebtrendsLoadBean webtrendsLoad) {
        this.webtrendsLoad = webtrendsLoad;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public List<NavItemsBean> getNavItems() {
        return navItems;
    }

    public void setNavItems(List<NavItemsBean> navItems) {
        this.navItems = navItems;
    }

    public static class DataBeanX {
        /**
         * categoryAutoOpen : false
         * collectionId : 18C
         * collectionLabel : 2017/18早春度假系列
         * details : {"functionalities":[],"information":[{"datas":[{"color":"米色、灰与乳白","data":[{"availability":true,"displayPrice":true,"ref":"P57900 V43835 MD528","refPrice":"P57900V43835"}],"isMulti":false,"material":"花织斜纹软呢","title":"连衣裙与腰带"}],"title":"高级成衣"},{"datas":[{"color":"金色、珍珠白、米色与黄","data":[{"availability":true,"displayPrice":false,"ref":"A99351 Y47056 Z5620","refPrice":"A99351Y47056"}],"isMulti":false,"material":"金属、淡水珍珠、水钻与名贵树脂","title":"发带"},{"color":"黑","data":[{"availability":true,"displayPrice":true,"ref":"A76357 X11729 94305","refPrice":"A76357X11729"}],"isMulti":false,"material":"真丝","title":"发带"},{"color":"金、蓝及绿","data":[{"availability":true,"displayPrice":true,"ref":"A99438 Y49180 Z4746","refPrice":"A99438Y49180"}],"isMulti":false,"material":"金属、名贵树脂与水钻","title":"发梳"},{"color":"金与蓝","data":[{"availability":true,"displayPrice":true,"ref":"A97930 Y50402 Z2638","refPrice":"A97930Y50402"}],"isMulti":false,"material":"金属与名贵树脂","title":"耳环"},{"color":"金","data":[{"availability":true,"displayPrice":true,"ref":"A96924 X01060 Z0000","refPrice":"A96924X01060"}],"isMulti":false,"material":"金属","title":"颈链"},{"color":"金色、蓝与珍珠白","data":[{"availability":true,"displayPrice":true,"ref":"A99365 Y47023 Z5617","refPrice":"A99365Y47023"}],"isMulti":false,"material":"金属、淡水珍珠、天然珍珠、水钻、琉璃与名贵树脂","title":"项链"},{"color":"金与珍珠白","data":[{"availability":true,"displayPrice":true,"ref":"A99208 Y47020 Z2048","refPrice":"A99208Y47020"}],"isMulti":false,"material":"金属、淡水珍珠与琉璃","title":"长项链"},{"color":"金、粉红与珍珠白","data":[{"availability":true,"displayPrice":true,"ref":"A96930 Y47059 Z3014","refPrice":"A96930Y47059"}],"isMulti":false,"material":"金属、小牛皮与名贵树脂","title":"手镯"},{"color":"金色、蓝与珍珠白","data":[{"availability":true,"displayPrice":true,"ref":"A99407 Y47078 Z5617","refPrice":"A99407Y47078"}],"isMulti":false,"material":"金属、天然珍珠、琉璃与名贵树脂","title":"手镯"},{"color":"金与淡绿","data":[{"availability":true,"displayPrice":true,"ref":"A96928 Y47058 Z4905","refPrice":"A96928Y47058"}],"isMulti":false,"material":"金属与小牛皮","title":"手镯"},{"color":"绿","data":[{"availability":true,"displayPrice":true,"ref":"G33608 X06008 0G817","refPrice":"G33608X06008"}],"isMulti":false,"material":"羊皮革","title":"凉鞋"}],"title":"配饰系列"}]}
         * gridhref : /zh_CN/fashion/products/ready-to-wear/g.cruise-2017-18.c.18C.html
         * gridimgsrc : /dam/fashion/catalog/collections/18C/RTW/looks/18C1.jpg.fashionImg.look-grid
         * height : 500
         * imgsrc : /dam/fashion/catalog/collections/18C/RTW/looks/18C1.jpg.fashionImg.look-sheet
         * index : 01
         * moduleBarPosition : under
         * modules : [{"imgsrc":"/dam/fashion/en/collections/18C/CAMPAIGN/VIGNETTES/Vignette_18C_01bis.jpg","name":"slideshow","title":"广告大片"},{"imgsrc":"/dam/fashion/catalog/collections/18C/HAT/products/A76357/A76357X1172994305/headband-look-accessories-push.jpg","name":"accessories","title":"配饰系列"}]
         * modulesJsonUrl : /zh_CN/fashion/products/ready-to-wear/g/s/_jcr_content.ajax.modules.18C.18C1.json
         * title : 款式 1
         * twitterTextProposed : 欣赏香奈儿2017/18早春度假系列高级成衣所有款式，敬请前往香奈儿CHANEL官方网站。查看香奈儿2017/18早春度假系列高级成衣所有款式细节及价格等。
         * width : 339
         * zoom : {"imgsrc":"/dam/fashion/catalog/collections/18C/RTW/looks/18C1.jpg.fashionImg","title":"缩放"}
         */

        private boolean categoryAutoOpen;
        private String collectionId;
        private String collectionLabel;
        private DetailsBean details;

        public boolean isCategoryAutoOpen() {
            return categoryAutoOpen;
        }

        public void setCategoryAutoOpen(boolean categoryAutoOpen) {
            this.categoryAutoOpen = categoryAutoOpen;
        }

        public String getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(String collectionId) {
            this.collectionId = collectionId;
        }

        public String getCollectionLabel() {
            return collectionLabel;
        }

        public void setCollectionLabel(String collectionLabel) {
            this.collectionLabel = collectionLabel;
        }

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public static class DetailsBean {
            private List<?> functionalities;
            private List<InformationBean> information;

            public List<?> getFunctionalities() {
                return functionalities;
            }

            public void setFunctionalities(List<?> functionalities) {
                this.functionalities = functionalities;
            }

            public List<InformationBean> getInformation() {
                return information;
            }

            public void setInformation(List<InformationBean> information) {
                this.information = information;
            }

            public static class InformationBean {
                /**
                 * datas : [{"color":"米色、灰与乳白","data":[{"availability":true,"displayPrice":true,"ref":"P57900 V43835 MD528","refPrice":"P57900V43835"}],"isMulti":false,"material":"花织斜纹软呢","title":"连衣裙与腰带"}]
                 * title : 高级成衣
                 */

                private String title;
                private List<DatasBean> datas;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<DatasBean> getDatas() {
                    return datas;
                }

                public void setDatas(List<DatasBean> datas) {
                    this.datas = datas;
                }

                public static class DatasBean {
                    /**
                     * color : 米色、灰与乳白
                     * data : [{"availability":true,"displayPrice":true,"ref":"P57900 V43835 MD528","refPrice":"P57900V43835"}]
                     * isMulti : false
                     * material : 花织斜纹软呢
                     * title : 连衣裙与腰带
                     */

                    private String color;
                    private boolean isMulti;
                    private String material;
                    private String title;
                    private List<DataBean> data;

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }

                    public boolean isIsMulti() {
                        return isMulti;
                    }

                    public void setIsMulti(boolean isMulti) {
                        this.isMulti = isMulti;
                    }

                    public String getMaterial() {
                        return material;
                    }

                    public void setMaterial(String material) {
                        this.material = material;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public List<DataBean> getData() {
                        return data;
                    }

                    public void setData(List<DataBean> data) {
                        this.data = data;
                    }

                    public static class DataBean {
                        /**
                         * availability : true
                         * displayPrice : true
                         * ref : P57900 V43835 MD528
                         * refPrice : P57900V43835
                         */

                        private boolean availability;
                        private boolean displayPrice;
                        private String ref;
                        private String refPrice;

                        public boolean isAvailability() {
                            return availability;
                        }

                        public void setAvailability(boolean availability) {
                            this.availability = availability;
                        }

                        public boolean isDisplayPrice() {
                            return displayPrice;
                        }

                        public void setDisplayPrice(boolean displayPrice) {
                            this.displayPrice = displayPrice;
                        }

                        public String getRef() {
                            return ref;
                        }

                        public void setRef(String ref) {
                            this.ref = ref;
                        }

                        public String getRefPrice() {
                            return refPrice;
                        }

                        public void setRefPrice(String refPrice) {
                            this.refPrice = refPrice;
                        }
                    }
                }
            }
        }

        public static class ZoomBean {
            /**
             * imgsrc : /dam/fashion/catalog/collections/18C/RTW/looks/18C1.jpg.fashionImg
             * title : 缩放
             */

            private String imgsrc;
            private String title;

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ModulesBean {
        }
    }

    public static class ImagesBean {
    }

    public static class WebtrendsLoadBean {
        /**
         * cat : RTW
         * scat1 : LOOK INTRO
         * scat2 : 18C
         */

        private String cat;
        private String scat1;
        private String scat2;

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }

        public String getScat1() {
            return scat1;
        }

        public void setScat1(String scat1) {
            this.scat1 = scat1;
        }

        public String getScat2() {
            return scat2;
        }

        public void setScat2(String scat2) {
            this.scat2 = scat2;
        }
    }

    public static class NavItemsBean {
        /**
         * href : /zh_CN/fashion.html
         * title : Chanel
         * type : pages/menu-link
         * action : product-lines
         */

        private String href;
        private String title;
        private String type;
        private String action;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }
}
