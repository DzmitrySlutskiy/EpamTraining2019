package com.example.myapplication.gof.builder;

import com.example.myapplication.gof.IPizza;

public class Pizza implements IPizza {
    private long mId;
    private String mTitle;
    private String mIngridients;

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getIngridients() {
        return mIngridients;
    }

    public static class Builder {
        private String mTitle;
        private String mIngridients;

        public Builder setId(long pId) {
            mId = pId;

            return this;
        }

        private long mId;


        public Builder setTitle(String pTitle) {
            mTitle = pTitle;

            return this;
        }



        public Builder setIngridients(String pIngridients) {
            mIngridients = pIngridients;

            return this;
        }


        public IPizza build() {
            final Pizza pizza = new Pizza();

            pizza.mId = this.mId;
            pizza.mTitle = this.mTitle;
            pizza.mIngridients = this.mIngridients;

            return pizza;
        }
    }


}
