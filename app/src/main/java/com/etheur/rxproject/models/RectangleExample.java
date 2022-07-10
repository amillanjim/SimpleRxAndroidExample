package com.etheur.rxproject.models;

import android.graphics.Rect;

/**
 * Patron Builder
 * Cuando se tienen muchos parametros de entrada para el constructor de una clase, se recomienda
 * utilizar el patron Builder, esté nos permite acceder y construir solo los datos que
 * requerimos.
 */

public class RectangleExample {

    private int x;
    private int y;
    private int width;
    private int heigth;
    private int borderRadius;

    private RectangleExample(Builder builder){
        x = builder.x;
        y = builder.y;
        width = builder.width;
        heigth = builder.heigth;
        borderRadius = builder.borderRadius;
    }

    public static class Builder{

        private int x = 0;
        private int y = 0;
        private int width;
        private int heigth;
        private int borderRadius;

        public Builder(int width, int heigth){
            this.width = width;
            this.heigth = heigth;
        }

        public RectangleExample build(){
            return new RectangleExample(this);
        }

        public Builder x(int positionX){
            x = positionX;
            return this;
        }

        public Builder y(int positionY){
            y = positionY;
            return this;
        }

        public Builder width(int recWidth){
            width = recWidth;
            return this;
        }

        public Builder heigth(int recHeight){
            heigth = recHeight;
            return this;
        }

        public Builder borderRadius(int recBorderRadius){
            borderRadius = recBorderRadius;
            return this;
        }
    }
}
