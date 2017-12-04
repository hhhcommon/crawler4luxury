package core.model;

import java.util.List;

/**
 * Created by yang on 2017/6/20.
 */
public class ColorAlexander {
    /**
     * Colors : [{"ColorId":15412,"Description":"黑色","Rgb":"000000","Code10":"34680056KR","ModelFabricColor":"461923Q5KQL1000","Link":"http://www.alexandermcqueen.cn/cn/alexandermcqueen/夹克_cod34680056kr.html"}]
     * ColorsFull : [{"ColorId":15412,"Description":"黑色","Rgb":"000000","Code10":"34680056KR","ModelFabricColor":"461923Q5KQL1000","Link":"http://www.alexandermcqueen.cn/cn/alexandermcqueen/夹克_cod34680056kr.html"}]
     * ColorsRetail : []
     * Sizes : [{"Size":4,"SortOrder":5,"Description":"170/92A","ClassFamily":"CN","Alternative":{"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false},{"Size":5,"SortOrder":6,"Description":"175/96A","ClassFamily":"CN","Alternative":{"Size":5,"SortOrder":6,"Description":"44","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false}]
     * SizesFull : [{"Size":4,"SortOrder":5,"Description":"170/92A","ClassFamily":"CN","Alternative":{"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false},{"Size":5,"SortOrder":6,"Description":"175/96A","ClassFamily":"CN","Alternative":{"Size":5,"SortOrder":6,"Description":"44","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false}]
     * SizesRetail : []
     * ModelColorSizes : [{"Color":{"ColorId":15412,"Description":"黑色","Rgb":"000000","Code10":"34680056KR","ModelFabricColor":"461923Q5KQL1000","Link":"http://www.alexandermcqueen.cn/cn/alexandermcqueen/夹克_cod34680056kr.html"},"Size":{"Size":4,"SortOrder":5,"Description":"170/92A","ClassFamily":"CN","Alternative":{"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false},"Quantity":1,"IsFastDelivery":false,"QuantityInCart":0},{"Color":{"ColorId":15412,"Description":"黑色","Rgb":"000000","Code10":"34680056KR","ModelFabricColor":"461923Q5KQL1000","Link":"http://www.alexandermcqueen.cn/cn/alexandermcqueen/夹克_cod34680056kr.html"},"Size":{"Size":5,"SortOrder":6,"Description":"175/96A","ClassFamily":"CN","Alternative":{"Size":5,"SortOrder":6,"Description":"44","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false},"Quantity":1,"IsFastDelivery":false,"QuantityInCart":0}]
     * ModelColorSizesRetail : []
     * SizesByCode10 : [{"Code10":"34680056KR","Sizes":[{"Size":4,"SortOrder":5,"Description":"170/92A","ClassFamily":"CN","Alternative":{"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false},{"Size":5,"SortOrder":6,"Description":"175/96A","ClassFamily":"CN","Alternative":{"Size":5,"SortOrder":6,"Description":"44","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false}]}]
     * ItemsAvailabilityInfo : [{"Code8":"34680056","Code10":"34680056KR","IsSoldOut":false,"IsUnsellable":false,"IsPreorder":false,"ExpectedShippingDate":""},{"Code8":"34680056","Code10":"34680056KR","IsSoldOut":false,"IsUnsellable":false,"IsPreorder":false,"ExpectedShippingDate":""}]
     * ItemAvailabilityLimitQuantity : 3
     * IsSoldOut : false
     * IsSoldoutInNavigationCountryBoutiques : true
     */

    private int ItemAvailabilityLimitQuantity;
    private boolean IsSoldOut;
    private boolean IsSoldoutInNavigationCountryBoutiques;
    private List<ColorsBean> Colors;
    private List<ColorsFullBean> ColorsFull;
    private List<?> ColorsRetail;
    private List<SizesBean> Sizes;
    private List<SizesFullBean> SizesFull;
    private List<?> SizesRetail;
    private List<ModelColorSizesBean> ModelColorSizes;
    private List<?> ModelColorSizesRetail;
    private List<SizesByCode10Bean> SizesByCode10;
    private List<ItemsAvailabilityInfoBean> ItemsAvailabilityInfo;

    public int getItemAvailabilityLimitQuantity() {
        return ItemAvailabilityLimitQuantity;
    }

    public void setItemAvailabilityLimitQuantity(int ItemAvailabilityLimitQuantity) {
        this.ItemAvailabilityLimitQuantity = ItemAvailabilityLimitQuantity;
    }

    public boolean isIsSoldOut() {
        return IsSoldOut;
    }

    public void setIsSoldOut(boolean IsSoldOut) {
        this.IsSoldOut = IsSoldOut;
    }

    public boolean isIsSoldoutInNavigationCountryBoutiques() {
        return IsSoldoutInNavigationCountryBoutiques;
    }

    public void setIsSoldoutInNavigationCountryBoutiques(boolean IsSoldoutInNavigationCountryBoutiques) {
        this.IsSoldoutInNavigationCountryBoutiques = IsSoldoutInNavigationCountryBoutiques;
    }

    public List<ColorsBean> getColors() {
        return Colors;
    }

    public void setColors(List<ColorsBean> Colors) {
        this.Colors = Colors;
    }

    public List<ColorsFullBean> getColorsFull() {
        return ColorsFull;
    }

    public void setColorsFull(List<ColorsFullBean> ColorsFull) {
        this.ColorsFull = ColorsFull;
    }

    public List<?> getColorsRetail() {
        return ColorsRetail;
    }

    public void setColorsRetail(List<?> ColorsRetail) {
        this.ColorsRetail = ColorsRetail;
    }

    public List<SizesBean> getSizes() {
        return Sizes;
    }

    public void setSizes(List<SizesBean> Sizes) {
        this.Sizes = Sizes;
    }

    public List<SizesFullBean> getSizesFull() {
        return SizesFull;
    }

    public void setSizesFull(List<SizesFullBean> SizesFull) {
        this.SizesFull = SizesFull;
    }

    public List<?> getSizesRetail() {
        return SizesRetail;
    }

    public void setSizesRetail(List<?> SizesRetail) {
        this.SizesRetail = SizesRetail;
    }

    public List<ModelColorSizesBean> getModelColorSizes() {
        return ModelColorSizes;
    }

    public void setModelColorSizes(List<ModelColorSizesBean> ModelColorSizes) {
        this.ModelColorSizes = ModelColorSizes;
    }

    public List<?> getModelColorSizesRetail() {
        return ModelColorSizesRetail;
    }

    public void setModelColorSizesRetail(List<?> ModelColorSizesRetail) {
        this.ModelColorSizesRetail = ModelColorSizesRetail;
    }

    public List<SizesByCode10Bean> getSizesByCode10() {
        return SizesByCode10;
    }

    public void setSizesByCode10(List<SizesByCode10Bean> SizesByCode10) {
        this.SizesByCode10 = SizesByCode10;
    }

    public List<ItemsAvailabilityInfoBean> getItemsAvailabilityInfo() {
        return ItemsAvailabilityInfo;
    }

    public void setItemsAvailabilityInfo(List<ItemsAvailabilityInfoBean> ItemsAvailabilityInfo) {
        this.ItemsAvailabilityInfo = ItemsAvailabilityInfo;
    }

    public static class ColorsBean {
        /**
         * ColorId : 15412
         * Description : 黑色
         * Rgb : 000000
         * Code10 : 34680056KR
         * ModelFabricColor : 461923Q5KQL1000
         * Link : http://www.alexandermcqueen.cn/cn/alexandermcqueen/夹克_cod34680056kr.html
         */

        private int ColorId;
        private String Description;
        private String Rgb;
        private String Code10;
        private String ModelFabricColor;
        private String Link;

        public int getColorId() {
            return ColorId;
        }

        public void setColorId(int ColorId) {
            this.ColorId = ColorId;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getRgb() {
            return Rgb;
        }

        public void setRgb(String Rgb) {
            this.Rgb = Rgb;
        }

        public String getCode10() {
            return Code10;
        }

        public void setCode10(String Code10) {
            this.Code10 = Code10;
        }

        public String getModelFabricColor() {
            return ModelFabricColor;
        }

        public void setModelFabricColor(String ModelFabricColor) {
            this.ModelFabricColor = ModelFabricColor;
        }

        public String getLink() {
            return Link;
        }

        public void setLink(String Link) {
            this.Link = Link;
        }
    }

    public static class ColorsFullBean {
        /**
         * ColorId : 15412
         * Description : 黑色
         * Rgb : 000000
         * Code10 : 34680056KR
         * ModelFabricColor : 461923Q5KQL1000
         * Link : http://www.alexandermcqueen.cn/cn/alexandermcqueen/夹克_cod34680056kr.html
         */

        private int ColorId;
        private String Description;
        private String Rgb;
        private String Code10;
        private String ModelFabricColor;
        private String Link;

        public int getColorId() {
            return ColorId;
        }

        public void setColorId(int ColorId) {
            this.ColorId = ColorId;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getRgb() {
            return Rgb;
        }

        public void setRgb(String Rgb) {
            this.Rgb = Rgb;
        }

        public String getCode10() {
            return Code10;
        }

        public void setCode10(String Code10) {
            this.Code10 = Code10;
        }

        public String getModelFabricColor() {
            return ModelFabricColor;
        }

        public void setModelFabricColor(String ModelFabricColor) {
            this.ModelFabricColor = ModelFabricColor;
        }

        public String getLink() {
            return Link;
        }

        public void setLink(String Link) {
            this.Link = Link;
        }
    }

    public static class SizesBean {
        /**
         * Size : 4
         * SortOrder : 5
         * Description : 170/92A
         * ClassFamily : CN
         * Alternative : {"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true}
         * Labeled : false
         */

        private int Size;
        private int SortOrder;
        private String Description;
        private String ClassFamily;
        private AlternativeBean Alternative;
        private boolean Labeled;

        public int getSize() {
            return Size;
        }

        public void setSize(int Size) {
            this.Size = Size;
        }

        public int getSortOrder() {
            return SortOrder;
        }

        public void setSortOrder(int SortOrder) {
            this.SortOrder = SortOrder;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getClassFamily() {
            return ClassFamily;
        }

        public void setClassFamily(String ClassFamily) {
            this.ClassFamily = ClassFamily;
        }

        public AlternativeBean getAlternative() {
            return Alternative;
        }

        public void setAlternative(AlternativeBean Alternative) {
            this.Alternative = Alternative;
        }

        public boolean isLabeled() {
            return Labeled;
        }

        public void setLabeled(boolean Labeled) {
            this.Labeled = Labeled;
        }

        public static class AlternativeBean {
            /**
             * Size : 4
             * SortOrder : 5
             * Description : 42
             * ClassFamily : IT
             * Alternative : null
             * Labeled : true
             */

            private int Size;
            private int SortOrder;
            private String Description;
            private String ClassFamily;
            private Object Alternative;
            private boolean Labeled;

            public int getSize() {
                return Size;
            }

            public void setSize(int Size) {
                this.Size = Size;
            }

            public int getSortOrder() {
                return SortOrder;
            }

            public void setSortOrder(int SortOrder) {
                this.SortOrder = SortOrder;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getClassFamily() {
                return ClassFamily;
            }

            public void setClassFamily(String ClassFamily) {
                this.ClassFamily = ClassFamily;
            }

            public Object getAlternative() {
                return Alternative;
            }

            public void setAlternative(Object Alternative) {
                this.Alternative = Alternative;
            }

            public boolean isLabeled() {
                return Labeled;
            }

            public void setLabeled(boolean Labeled) {
                this.Labeled = Labeled;
            }
        }
    }

    public static class SizesFullBean {
        /**
         * Size : 4
         * SortOrder : 5
         * Description : 170/92A
         * ClassFamily : CN
         * Alternative : {"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true}
         * Labeled : false
         */

        private int Size;
        private int SortOrder;
        private String Description;
        private String ClassFamily;
        private AlternativeBeanX Alternative;
        private boolean Labeled;

        public int getSize() {
            return Size;
        }

        public void setSize(int Size) {
            this.Size = Size;
        }

        public int getSortOrder() {
            return SortOrder;
        }

        public void setSortOrder(int SortOrder) {
            this.SortOrder = SortOrder;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getClassFamily() {
            return ClassFamily;
        }

        public void setClassFamily(String ClassFamily) {
            this.ClassFamily = ClassFamily;
        }

        public AlternativeBeanX getAlternative() {
            return Alternative;
        }

        public void setAlternative(AlternativeBeanX Alternative) {
            this.Alternative = Alternative;
        }

        public boolean isLabeled() {
            return Labeled;
        }

        public void setLabeled(boolean Labeled) {
            this.Labeled = Labeled;
        }

        public static class AlternativeBeanX {
            /**
             * Size : 4
             * SortOrder : 5
             * Description : 42
             * ClassFamily : IT
             * Alternative : null
             * Labeled : true
             */

            private int Size;
            private int SortOrder;
            private String Description;
            private String ClassFamily;
            private Object Alternative;
            private boolean Labeled;

            public int getSize() {
                return Size;
            }

            public void setSize(int Size) {
                this.Size = Size;
            }

            public int getSortOrder() {
                return SortOrder;
            }

            public void setSortOrder(int SortOrder) {
                this.SortOrder = SortOrder;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getClassFamily() {
                return ClassFamily;
            }

            public void setClassFamily(String ClassFamily) {
                this.ClassFamily = ClassFamily;
            }

            public Object getAlternative() {
                return Alternative;
            }

            public void setAlternative(Object Alternative) {
                this.Alternative = Alternative;
            }

            public boolean isLabeled() {
                return Labeled;
            }

            public void setLabeled(boolean Labeled) {
                this.Labeled = Labeled;
            }
        }
    }

    public static class ModelColorSizesBean {
        /**
         * Color : {"ColorId":15412,"Description":"黑色","Rgb":"000000","Code10":"34680056KR","ModelFabricColor":"461923Q5KQL1000","Link":"http://www.alexandermcqueen.cn/cn/alexandermcqueen/夹克_cod34680056kr.html"}
         * Size : {"Size":4,"SortOrder":5,"Description":"170/92A","ClassFamily":"CN","Alternative":{"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false}
         * Quantity : 1
         * IsFastDelivery : false
         * QuantityInCart : 0
         */

        private ColorBean Color;
        private SizeBean Size;
        private int Quantity;
        private boolean IsFastDelivery;
        private int QuantityInCart;

        public ColorBean getColor() {
            return Color;
        }

        public void setColor(ColorBean Color) {
            this.Color = Color;
        }

        public SizeBean getSize() {
            return Size;
        }

        public void setSize(SizeBean Size) {
            this.Size = Size;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int Quantity) {
            this.Quantity = Quantity;
        }

        public boolean isIsFastDelivery() {
            return IsFastDelivery;
        }

        public void setIsFastDelivery(boolean IsFastDelivery) {
            this.IsFastDelivery = IsFastDelivery;
        }

        public int getQuantityInCart() {
            return QuantityInCart;
        }

        public void setQuantityInCart(int QuantityInCart) {
            this.QuantityInCart = QuantityInCart;
        }

        public static class ColorBean {
            /**
             * ColorId : 15412
             * Description : 黑色
             * Rgb : 000000
             * Code10 : 34680056KR
             * ModelFabricColor : 461923Q5KQL1000
             * Link : http://www.alexandermcqueen.cn/cn/alexandermcqueen/夹克_cod34680056kr.html
             */

            private int ColorId;
            private String Description;
            private String Rgb;
            private String Code10;
            private String ModelFabricColor;
            private String Link;

            public int getColorId() {
                return ColorId;
            }

            public void setColorId(int ColorId) {
                this.ColorId = ColorId;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getRgb() {
                return Rgb;
            }

            public void setRgb(String Rgb) {
                this.Rgb = Rgb;
            }

            public String getCode10() {
                return Code10;
            }

            public void setCode10(String Code10) {
                this.Code10 = Code10;
            }

            public String getModelFabricColor() {
                return ModelFabricColor;
            }

            public void setModelFabricColor(String ModelFabricColor) {
                this.ModelFabricColor = ModelFabricColor;
            }

            public String getLink() {
                return Link;
            }

            public void setLink(String Link) {
                this.Link = Link;
            }
        }

        public static class SizeBean {
            /**
             * Size : 4
             * SortOrder : 5
             * Description : 170/92A
             * ClassFamily : CN
             * Alternative : {"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true}
             * Labeled : false
             */

            private int Size;
            private int SortOrder;
            private String Description;
            private String ClassFamily;
            private AlternativeBeanXX Alternative;
            private boolean Labeled;

            public int getSize() {
                return Size;
            }

            public void setSize(int Size) {
                this.Size = Size;
            }

            public int getSortOrder() {
                return SortOrder;
            }

            public void setSortOrder(int SortOrder) {
                this.SortOrder = SortOrder;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getClassFamily() {
                return ClassFamily;
            }

            public void setClassFamily(String ClassFamily) {
                this.ClassFamily = ClassFamily;
            }

            public AlternativeBeanXX getAlternative() {
                return Alternative;
            }

            public void setAlternative(AlternativeBeanXX Alternative) {
                this.Alternative = Alternative;
            }

            public boolean isLabeled() {
                return Labeled;
            }

            public void setLabeled(boolean Labeled) {
                this.Labeled = Labeled;
            }

            public static class AlternativeBeanXX {
                /**
                 * Size : 4
                 * SortOrder : 5
                 * Description : 42
                 * ClassFamily : IT
                 * Alternative : null
                 * Labeled : true
                 */

                private int Size;
                private int SortOrder;
                private String Description;
                private String ClassFamily;
                private Object Alternative;
                private boolean Labeled;

                public int getSize() {
                    return Size;
                }

                public void setSize(int Size) {
                    this.Size = Size;
                }

                public int getSortOrder() {
                    return SortOrder;
                }

                public void setSortOrder(int SortOrder) {
                    this.SortOrder = SortOrder;
                }

                public String getDescription() {
                    return Description;
                }

                public void setDescription(String Description) {
                    this.Description = Description;
                }

                public String getClassFamily() {
                    return ClassFamily;
                }

                public void setClassFamily(String ClassFamily) {
                    this.ClassFamily = ClassFamily;
                }

                public Object getAlternative() {
                    return Alternative;
                }

                public void setAlternative(Object Alternative) {
                    this.Alternative = Alternative;
                }

                public boolean isLabeled() {
                    return Labeled;
                }

                public void setLabeled(boolean Labeled) {
                    this.Labeled = Labeled;
                }
            }
        }
    }

    public static class SizesByCode10Bean {
        /**
         * Code10 : 34680056KR
         * Sizes : [{"Size":4,"SortOrder":5,"Description":"170/92A","ClassFamily":"CN","Alternative":{"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false},{"Size":5,"SortOrder":6,"Description":"175/96A","ClassFamily":"CN","Alternative":{"Size":5,"SortOrder":6,"Description":"44","ClassFamily":"IT","Alternative":null,"Labeled":true},"Labeled":false}]
         */

        private String Code10;
        private List<SizesBeanX> Sizes;

        public String getCode10() {
            return Code10;
        }

        public void setCode10(String Code10) {
            this.Code10 = Code10;
        }

        public List<SizesBeanX> getSizes() {
            return Sizes;
        }

        public void setSizes(List<SizesBeanX> Sizes) {
            this.Sizes = Sizes;
        }

        public static class SizesBeanX {
            /**
             * Size : 4
             * SortOrder : 5
             * Description : 170/92A
             * ClassFamily : CN
             * Alternative : {"Size":4,"SortOrder":5,"Description":"42","ClassFamily":"IT","Alternative":null,"Labeled":true}
             * Labeled : false
             */

            private int Size;
            private int SortOrder;
            private String Description;
            private String ClassFamily;
            private AlternativeBeanXXX Alternative;
            private boolean Labeled;

            public int getSize() {
                return Size;
            }

            public void setSize(int Size) {
                this.Size = Size;
            }

            public int getSortOrder() {
                return SortOrder;
            }

            public void setSortOrder(int SortOrder) {
                this.SortOrder = SortOrder;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getClassFamily() {
                return ClassFamily;
            }

            public void setClassFamily(String ClassFamily) {
                this.ClassFamily = ClassFamily;
            }

            public AlternativeBeanXXX getAlternative() {
                return Alternative;
            }

            public void setAlternative(AlternativeBeanXXX Alternative) {
                this.Alternative = Alternative;
            }

            public boolean isLabeled() {
                return Labeled;
            }

            public void setLabeled(boolean Labeled) {
                this.Labeled = Labeled;
            }

            public static class AlternativeBeanXXX {
                /**
                 * Size : 4
                 * SortOrder : 5
                 * Description : 42
                 * ClassFamily : IT
                 * Alternative : null
                 * Labeled : true
                 */

                private int Size;
                private int SortOrder;
                private String Description;
                private String ClassFamily;
                private Object Alternative;
                private boolean Labeled;

                public int getSize() {
                    return Size;
                }

                public void setSize(int Size) {
                    this.Size = Size;
                }

                public int getSortOrder() {
                    return SortOrder;
                }

                public void setSortOrder(int SortOrder) {
                    this.SortOrder = SortOrder;
                }

                public String getDescription() {
                    return Description;
                }

                public void setDescription(String Description) {
                    this.Description = Description;
                }

                public String getClassFamily() {
                    return ClassFamily;
                }

                public void setClassFamily(String ClassFamily) {
                    this.ClassFamily = ClassFamily;
                }

                public Object getAlternative() {
                    return Alternative;
                }

                public void setAlternative(Object Alternative) {
                    this.Alternative = Alternative;
                }

                public boolean isLabeled() {
                    return Labeled;
                }

                public void setLabeled(boolean Labeled) {
                    this.Labeled = Labeled;
                }
            }
        }
    }

    public static class ItemsAvailabilityInfoBean {
        /**
         * Code8 : 34680056
         * Code10 : 34680056KR
         * IsSoldOut : false
         * IsUnsellable : false
         * IsPreorder : false
         * ExpectedShippingDate :
         */

        private String Code8;
        private String Code10;
        private boolean IsSoldOut;
        private boolean IsUnsellable;
        private boolean IsPreorder;
        private String ExpectedShippingDate;

        public String getCode8() {
            return Code8;
        }

        public void setCode8(String Code8) {
            this.Code8 = Code8;
        }

        public String getCode10() {
            return Code10;
        }

        public void setCode10(String Code10) {
            this.Code10 = Code10;
        }

        public boolean isIsSoldOut() {
            return IsSoldOut;
        }

        public void setIsSoldOut(boolean IsSoldOut) {
            this.IsSoldOut = IsSoldOut;
        }

        public boolean isIsUnsellable() {
            return IsUnsellable;
        }

        public void setIsUnsellable(boolean IsUnsellable) {
            this.IsUnsellable = IsUnsellable;
        }

        public boolean isIsPreorder() {
            return IsPreorder;
        }

        public void setIsPreorder(boolean IsPreorder) {
            this.IsPreorder = IsPreorder;
        }

        public String getExpectedShippingDate() {
            return ExpectedShippingDate;
        }

        public void setExpectedShippingDate(String ExpectedShippingDate) {
            this.ExpectedShippingDate = ExpectedShippingDate;
        }
    }
}
