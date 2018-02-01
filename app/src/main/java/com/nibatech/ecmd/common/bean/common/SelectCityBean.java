package com.nibatech.ecmd.common.bean.common;


import java.util.ArrayList;

public class SelectCityBean {
    private ArrayList<ProvincesBean> provinces;

    public ArrayList<ProvincesBean> getProvinces() {
        return provinces;
    }

    public void setProvinces(ArrayList<ProvincesBean> provinces) {
        this.provinces = provinces;
    }

    public static class ProvincesBean {
        /**
         * cities : [{"name":"西安","regions":[{"name":"北郊","code":"110000","id":1},{"name":"南郊","code":"110000","id":1}]},{"name":"安康"}]
         * name : 陕西
         */

        private String name;
        private ArrayList<CitiesBean> cities;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<CitiesBean> getCities() {
            return cities;
        }

        public void setCities(ArrayList<CitiesBean> cities) {
            this.cities = cities;
        }

        public static class CitiesBean {
            /**
             * name : 西安
             * regions : [{"name":"北郊","code":"110000","id":1},{"name":"南郊","code":"110000","id":1}]
             */

            private String name;
            private ArrayList<RegionsBean> regions;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ArrayList<RegionsBean> getRegions() {
                return regions;
            }

            public void setRegions(ArrayList<RegionsBean> regions) {
                this.regions = regions;
            }

            public static class RegionsBean {
                /**
                 * name : 北郊
                 * code : 110000
                 * id : 1
                 */

                private String name;
                private String code;
                private int id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
