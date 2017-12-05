package model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by yang on 2017/6/20.
 */
public class Burberry {

    private BannerBean banner;
    private FacetsBeanX facets;
    private SortOptionsBean sortOptions;
    private List<ShelvesBean> shelves;

    public BannerBean getBanner() {
        return banner;
    }

    public void setBanner(BannerBean banner) {
        this.banner = banner;
    }

    public FacetsBeanX getFacets() {
        return facets;
    }

    public void setFacets(FacetsBeanX facets) {
        this.facets = facets;
    }

    public SortOptionsBean getSortOptions() {
        return sortOptions;
    }

    public void setSortOptions(SortOptionsBean sortOptions) {
        this.sortOptions = sortOptions;
    }

    public List<ShelvesBean> getShelves() {
        return shelves;
    }

    public void setShelves(List<ShelvesBean> shelves) {
        this.shelves = shelves;
    }

    public static class BannerBean {
        /**
         * copy : {}
         * media : {}
	@FindBy(css="")
	private WebElement webElement;
	@FindBy(css="")
	private WebElement webElement;
	@FindBy(css="")
	private WebElement webElement;
	@FindBy(css="")
	private WebElement webElement;
	@FindBy(css="")
	private WebElement webElement;
	@FindBy(css="")
	private WebElement webElement;
	@FindBy(css="")
	private WebElement webElement;
	@FindBy(css="")
	private WebElement webElement;
         * hiddenClasses : []
         * cellType : hero-cell-3-3
         * labels : {"discover":"探索更多","viewAll":"查看全部"}
         * key : hero-text
         * isClickable : false
         * isH3Title : false
         * position : 1
         * countdownTimer : {"dropShadow":false,"textOverImage":false,"labels":{"days":{"singular":"日","plural":"天"},"hours":{"singular":"小时","plural":"小时"},"minutes":{"singular":"分","plural":"分"},"seconds":{"singular":"秒","plural":"秒"}}}
         * isH1Title : true
         */

        private CopyBean copy;
        private MediaBean media;
        private String cellType;
        private LabelsBean labels;
        private String key;
        private boolean isClickable;
        private boolean isH3Title;
        private int position;
        private CountdownTimerBean countdownTimer;
        private boolean isH1Title;
        private List<?> hiddenClasses;

        public CopyBean getCopy() {
            return copy;
        }

        public void setCopy(CopyBean copy) {
            this.copy = copy;
        }

        public MediaBean getMedia() {
            return media;
        }

        public void setMedia(MediaBean media) {
            this.media = media;
        }

        public String getCellType() {
            return cellType;
        }

        public void setCellType(String cellType) {
            this.cellType = cellType;
        }

        public LabelsBean getLabels() {
            return labels;
        }

        public void setLabels(LabelsBean labels) {
            this.labels = labels;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public boolean isIsClickable() {
            return isClickable;
        }

        public void setIsClickable(boolean isClickable) {
            this.isClickable = isClickable;
        }

        public boolean isIsH3Title() {
            return isH3Title;
        }

        public void setIsH3Title(boolean isH3Title) {
            this.isH3Title = isH3Title;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public CountdownTimerBean getCountdownTimer() {
            return countdownTimer;
        }

        public void setCountdownTimer(CountdownTimerBean countdownTimer) {
            this.countdownTimer = countdownTimer;
        }

        public boolean isIsH1Title() {
            return isH1Title;
        }

        public void setIsH1Title(boolean isH1Title) {
            this.isH1Title = isH1Title;
        }

        public List<?> getHiddenClasses() {
            return hiddenClasses;
        }

        public void setHiddenClasses(List<?> hiddenClasses) {
            this.hiddenClasses = hiddenClasses;
        }

        public static class CopyBean {
        }

        public static class MediaBean {
        }

        public static class LabelsBean {
            /**
             * discover : 探索更多
             * viewAll : 查看全部
             */

            private String discover;
            private String viewAll;

            public String getDiscover() {
                return discover;
            }

            public void setDiscover(String discover) {
                this.discover = discover;
            }

            public String getViewAll() {
                return viewAll;
            }

            public void setViewAll(String viewAll) {
                this.viewAll = viewAll;
            }
        }

        public static class CountdownTimerBean {
            /**
             * dropShadow : false
             * textOverImage : false
             * labels : {"days":{"singular":"日","plural":"天"},"hours":{"singular":"小时","plural":"小时"},"minutes":{"singular":"分","plural":"分"},"seconds":{"singular":"秒","plural":"秒"}}
             */

            private boolean dropShadow;
            private boolean textOverImage;
            private LabelsBeanX labels;

            public boolean isDropShadow() {
                return dropShadow;
            }

            public void setDropShadow(boolean dropShadow) {
                this.dropShadow = dropShadow;
            }

            public boolean isTextOverImage() {
                return textOverImage;
            }

            public void setTextOverImage(boolean textOverImage) {
                this.textOverImage = textOverImage;
            }

            public LabelsBeanX getLabels() {
                return labels;
            }

            public void setLabels(LabelsBeanX labels) {
                this.labels = labels;
            }

            public static class LabelsBeanX {
                /**
                 * days : {"singular":"日","plural":"天"}
                 * hours : {"singular":"小时","plural":"小时"}
                 * minutes : {"singular":"分","plural":"分"}
                 * seconds : {"singular":"秒","plural":"秒"}
                 */

                private DaysBean days;
                private HoursBean hours;
                private MinutesBean minutes;
                private SecondsBean seconds;

                public DaysBean getDays() {
                    return days;
                }

                public void setDays(DaysBean days) {
                    this.days = days;
                }

                public HoursBean getHours() {
                    return hours;
                }

                public void setHours(HoursBean hours) {
                    this.hours = hours;
                }

                public MinutesBean getMinutes() {
                    return minutes;
                }

                public void setMinutes(MinutesBean minutes) {
                    this.minutes = minutes;
                }

                public SecondsBean getSeconds() {
                    return seconds;
                }

                public void setSeconds(SecondsBean seconds) {
                    this.seconds = seconds;
                }

                public static class DaysBean {
                    /**
                     * singular : 日
                     * plural : 天
                     */

                    private String singular;
                    private String plural;

                    public String getSingular() {
                        return singular;
                    }

                    public void setSingular(String singular) {
                        this.singular = singular;
                    }

                    public String getPlural() {
                        return plural;
                    }

                    public void setPlural(String plural) {
                        this.plural = plural;
                    }
                }

                public static class HoursBean {
                    /**
                     * singular : 小时
                     * plural : 小时
                     */

                    private String singular;
                    private String plural;

                    public String getSingular() {
                        return singular;
                    }

                    public void setSingular(String singular) {
                        this.singular = singular;
                    }

                    public String getPlural() {
                        return plural;
                    }

                    public void setPlural(String plural) {
                        this.plural = plural;
                    }
                }

                public static class MinutesBean {
                    /**
                     * singular : 分
                     * plural : 分
                     */

                    private String singular;
                    private String plural;

                    public String getSingular() {
                        return singular;
                    }

                    public void setSingular(String singular) {
                        this.singular = singular;
                    }

                    public String getPlural() {
                        return plural;
                    }

                    public void setPlural(String plural) {
                        this.plural = plural;
                    }
                }

                public static class SecondsBean {
                    /**
                     * singular : 秒
                     * plural : 秒
                     */

                    private String singular;
                    private String plural;

                    public String getSingular() {
                        return singular;
                    }

                    public void setSingular(String singular) {
                        this.singular = singular;
                    }

                    public String getPlural() {
                        return plural;
                    }

                    public void setPlural(String plural) {
                        this.plural = plural;
                    }
                }
            }
        }
    }

    public static class FacetsBeanX {

        private String baseUrl;
        private int total;
        private LabelsBeanXX labels;
        private String filterText;
        private boolean isOnlyOneSelected;
        private List<FacetsBean> facets;
        private List<?> shortUrlFacetOptions;

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public LabelsBeanXX getLabels() {
            return labels;
        }

        public void setLabels(LabelsBeanXX labels) {
            this.labels = labels;
        }

        public String getFilterText() {
            return filterText;
        }

        public void setFilterText(String filterText) {
            this.filterText = filterText;
        }

        public boolean isIsOnlyOneSelected() {
            return isOnlyOneSelected;
        }

        public void setIsOnlyOneSelected(boolean isOnlyOneSelected) {
            this.isOnlyOneSelected = isOnlyOneSelected;
        }

        public List<FacetsBean> getFacets() {
            return facets;
        }

        public void setFacets(List<FacetsBean> facets) {
            this.facets = facets;
        }

        public List<?> getShortUrlFacetOptions() {
            return shortUrlFacetOptions;
        }

        public void setShortUrlFacetOptions(List<?> shortUrlFacetOptions) {
            this.shortUrlFacetOptions = shortUrlFacetOptions;
        }

        public static class LabelsBeanXX {
            /**
             * label : 筛选
             * summary : 件商品
             * clear : 清空
             * reset : 重置
             * applyFilter : 完成筛选
             * productCount : 500
             */

            private String label;
            private String summary;
            private String clear;
            private String reset;
            private String applyFilter;
            private String productCount;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getClear() {
                return clear;
            }

            public void setClear(String clear) {
                this.clear = clear;
            }

            public String getReset() {
                return reset;
            }

            public void setReset(String reset) {
                this.reset = reset;
            }

            public String getApplyFilter() {
                return applyFilter;
            }

            public void setApplyFilter(String applyFilter) {
                this.applyFilter = applyFilter;
            }

            public String getProductCount() {
                return productCount;
            }

            public void setProductCount(String productCount) {
                this.productCount = productCount;
            }
        }

        public static class FacetsBean {
            /**
             * id : 36036
             * name : 产品类别
             * type : MULTI
             * max_value_in_header : 1
             * size_guide_url : null
             * alias : category
             * options : [{"value":"Polo衫及T恤衫","label":"Polo衫及T恤衫","alias":"polo衫及t恤衫","selected":false,"extended_label":null,"short_url":null},{"value":"T 恤","label":"T 恤","alias":"t-恤","selected":false,"extended_label":null,"short_url":null},{"value":"Trench 风衣","label":"Trench 风衣","alias":"trench-风衣","selected":false,"extended_label":null,"short_url":null},{"value":"休闲西装外套","label":"休闲西装外套","alias":"休闲西装外套","selected":false,"extended_label":null,"short_url":null},{"value":"唇妆","label":"唇妆","alias":"唇妆","selected":false,"extended_label":null,"short_url":null},{"value":"外套","label":"外套","alias":"外套","selected":false,"extended_label":null,"short_url":null},{"value":"底妆","label":"底妆","alias":"底妆","selected":false,"extended_label":null,"short_url":null},{"value":"彩妆","label":"彩妆","alias":"彩妆","selected":false,"extended_label":null,"short_url":null},{"value":"披风","label":"披风","alias":"披风","selected":false,"extended_label":null,"short_url":null},{"value":"淡香水","label":"淡香水","alias":"淡香水","selected":false,"extended_label":null,"short_url":null},{"value":"牛仔裤","label":"牛仔裤","alias":"牛仔裤","selected":false,"extended_label":null,"short_url":null},{"value":"甲妆","label":"甲妆","alias":"甲妆","selected":false,"extended_label":null,"short_url":null},{"value":"眼妆","label":"眼妆","alias":"眼妆","selected":false,"extended_label":null,"short_url":null},{"value":"腮红","label":"腮红","alias":"腮红","selected":false,"extended_label":null,"short_url":null},{"value":"衬衫","label":"衬衫","alias":"衬衫","selected":false,"extended_label":null,"short_url":null},{"value":"衬衫及上衣","label":"衬衫及上衣","alias":"衬衫及上衣","selected":false,"extended_label":null,"short_url":null},{"value":"裙装","label":"裙装","alias":"裙装","selected":false,"extended_label":null,"short_url":null},{"value":"运动衫","label":"运动衫","alias":"运动衫","selected":false,"extended_label":null,"short_url":null},{"value":"连衣裙","label":"连衣裙","alias":"连衣裙","selected":false,"extended_label":null,"short_url":null},{"value":"连身套衫","label":"连身套衫","alias":"连身套衫","selected":false,"extended_label":null,"short_url":null},{"value":"针织外套","label":"针织外套","alias":"针织外套","selected":false,"extended_label":null,"short_url":null},{"value":"针织衫","label":"针织衫","alias":"针织衫","selected":false,"extended_label":null,"short_url":null},{"value":"针织衫及运动衫","label":"针织衫及运动衫","alias":"针织衫及运动衫","selected":false,"extended_label":null,"short_url":null},{"value":"长裤及短裤","label":"长裤及短裤","alias":"长裤及短裤","selected":false,"extended_label":null,"short_url":null},{"value":"香水","label":"香水","alias":"香水","selected":false,"extended_label":null,"short_url":null}]
             */

            private String id;
            private String name;
            private String type;
            private int max_value_in_header;
            private Object size_guide_url;
            private String alias;
            private List<OptionsBean> options;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getMax_value_in_header() {
                return max_value_in_header;
            }

            public void setMax_value_in_header(int max_value_in_header) {
                this.max_value_in_header = max_value_in_header;
            }

            public Object getSize_guide_url() {
                return size_guide_url;
            }

            public void setSize_guide_url(Object size_guide_url) {
                this.size_guide_url = size_guide_url;
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public List<OptionsBean> getOptions() {
                return options;
            }

            public void setOptions(List<OptionsBean> options) {
                this.options = options;
            }

            public static class OptionsBean {
                /**
                 * value : Polo衫及T恤衫
                 * label : Polo衫及T恤衫
                 * alias : polo衫及t恤衫
                 * selected : false
                 * extended_label : null
                 * short_url : null
                 */

                private String value;
                private String label;
                private String alias;
                private boolean selected;
                private Object extended_label;
                private Object short_url;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public boolean isSelected() {
                    return selected;
                }

                public void setSelected(boolean selected) {
                    this.selected = selected;
                }

                public Object getExtended_label() {
                    return extended_label;
                }

                public void setExtended_label(Object extended_label) {
                    this.extended_label = extended_label;
                }

                public Object getShort_url() {
                    return short_url;
                }

                public void setShort_url(Object short_url) {
                    this.short_url = short_url;
                }
            }
        }
    }

    public static class SortOptionsBean {
        /**
         * data : [{"label":"默认设置","property":"default","text":"按价格排序：<span>默认设置<\/span>"}]
         * labels : {"label":"按价格排序：{0}","default":"默认设置"}
         * queryOption : numprop:descending:price
         */

        private LabelsBeanXXX labels;
        private String queryOption;
        private List<DataBean> data;

        public LabelsBeanXXX getLabels() {
            return labels;
        }

        public void setLabels(LabelsBeanXXX labels) {
            this.labels = labels;
        }

        public String getQueryOption() {
            return queryOption;
        }

        public void setQueryOption(String queryOption) {
            this.queryOption = queryOption;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class LabelsBeanXXX {
            /**
             * label : 按价格排序：{0}
             * default : 默认设置
             */

            private String label;
            @JSONField(name = "default")
            private String defaultX;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDefaultX() {
                return defaultX;
            }

            public void setDefaultX(String defaultX) {
                this.defaultX = defaultX;
            }
        }

        public static class DataBean {
            /**
             * label : 默认设置
             * property : default
             * text : 按价格排序：<span>默认设置</span>
             */

            private String label;
            private String property;
            private String text;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }

    public static class ShelvesBean {

        private int firstBatchProductCount;
        private ViewAllBean viewAll;
        private String totalCount;
        private boolean hasBanner;
        private List<ShelfElementsBean> shelfElements;

        public int getFirstBatchProductCount() {
            return firstBatchProductCount;
        }

        public void setFirstBatchProductCount(int firstBatchProductCount) {
            this.firstBatchProductCount = firstBatchProductCount;
        }

        public ViewAllBean getViewAll() {
            return viewAll;
        }

        public void setViewAll(ViewAllBean viewAll) {
            this.viewAll = viewAll;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public boolean isHasBanner() {
            return hasBanner;
        }

        public void setHasBanner(boolean hasBanner) {
            this.hasBanner = hasBanner;
        }

        public List<ShelfElementsBean> getShelfElements() {
            return shelfElements;
        }

        public void setShelfElements(List<ShelfElementsBean> shelfElements) {
            this.shelfElements = shelfElements;
        }

        public static class ViewAllBean {
            /**
             * label : 加载更多
             */

            private String label;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }

        public static class ShelfElementsBean {

            private String itemNumber;
            private String label;
            private String link;
            private ImagesBean images;
            private RolloverImageBean rolloverImage;
            private int price;
            private String formattedPrice;
            private ColorsBean colors;
            private boolean showOnSmall;
            private boolean showOnMedium;
            private String oldPrice;

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

            public boolean isShowOnSmall() {
                return showOnSmall;
            }

            public void setShowOnSmall(boolean showOnSmall) {
                this.showOnSmall = showOnSmall;
            }

            public boolean isShowOnMedium() {
                return showOnMedium;
            }

            public void setShowOnMedium(boolean showOnMedium) {
                this.showOnMedium = showOnMedium;
            }

            public String getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(String oldPrice) {
                this.oldPrice = oldPrice;
            }

            public static class ImagesBean {
                /**
                 * sources : [{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":"//assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=324&hei=432 324w","sizes":"calc(25vw - 30px)"},{"srcset":"//assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=316&hei=421 316w","sizes":"calc(50vw - 27px)"}]
                 * isNew : true
                 * img : {"src":"//assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520"}
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
                     * src : //assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520
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
                     * srcset : //assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/bddc6cd312b4f4f7b1fec62db2591c3720fdb49f.jpg?$BBY_V2_ML_3X4$=&wid=520&hei=693 520w
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
                 * sources : [{"media":"(min-width: 1280px)","srcset":"//assets.burberry.com/is/image/Burberryltd/e9bd5b708885a3c94cd8e328d11430d3626f8a10.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/e9bd5b708885a3c94cd8e328d11430d3626f8a10.jpg?$BBY_V2_SL_3X4$=&wid=520&hei=693 520w","sizes":"390px"},{"media":"(min-width: 1024px)","srcset":"//assets.burberry.com/is/image/Burberryltd/e9bd5b708885a3c94cd8e328d11430d3626f8a10.jpg?$BBY_V2_SL_3X4$=&wid=316&hei=421 316w, //assets.burberry.com/is/image/Burberryltd/e9bd5b708885a3c94cd8e328d11430d3626f8a10.jpg?$BBY_V2_SL_3X4$=&wid=422&hei=562 422w","sizes":"calc(25vw - 45px)"},{"media":"(min-width: 768px)","srcset":null,"sizes":"calc(25vw - 30px)"},{"srcset":null,"sizes":"calc(50vw - 27px)"}]
                 * isNew : true
                 * img : {"src":"//assets.burberry.com/is/image/Burberryltd/e9bd5b708885a3c94cd8e328d11430d3626f8a10.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520"}
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
                     * src : //assets.burberry.com/is/image/Burberryltd/e9bd5b708885a3c94cd8e328d11430d3626f8a10.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520
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
                     * srcset : //assets.burberry.com/is/image/Burberryltd/e9bd5b708885a3c94cd8e328d11430d3626f8a10.jpg?$BBY_V2_SL_3X4$=&wid=390&hei=520 390w, //assets.burberry.com/is/image/Burberryltd/e9bd5b708885a3c94cd8e328d11430d3626f8a10.jpg?$BBY_V2_SL_3X4$=&wid=520&hei=693 520w
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

                private String more;
                private List<ItemsBean> items;

                public String getMore() {
                    return more;
                }

                public void setMore(String more) {
                    this.more = more;
                }

                public List<ItemsBean> getItems() {
                    return items;
                }

                public void setItems(List<ItemsBean> items) {
                    this.items = items;
                }

                public static class ItemsBean {
                    private String id;
                    private String label;
                    private ImageBean image;
                    private String link;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getLabel() {
                        return label;
                    }

                    public void setLabel(String label) {
                        this.label = label;
                    }

                    public ImageBean getImage() {
                        return image;
                    }

                    public void setImage(ImageBean image) {
                        this.image = image;
                    }

                    public String getLink() {
                        return link;
                    }

                    public void setLink(String link) {
                        this.link = link;
                    }

                    public static class ImageBean {

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

                            private String src;

                            public String getSrc() {
                                return src;
                            }

                            public void setSrc(String src) {
                                this.src = src;
                            }
                        }

                        public static class SourcesBeanXX {

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
        }
    }
}