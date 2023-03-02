package com.lab6.client.entities;


public class CollectionData {

        private String type;
        private long initialisationDate;
        private int size;
        private boolean isEmpty;

        public CollectionData(String type, long initialisationDate, int size, boolean isEmpty) {
            this.type = type;
            this.initialisationDate = initialisationDate;
            this.size = size;
            this.isEmpty = isEmpty;
        }

        public String getType() {
            return type;
        }

        public long getInitialisationDate() {
            return initialisationDate;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return isEmpty;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setInitialisationDate(long initialisationDate) {
            this.initialisationDate = initialisationDate;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setEmpty(boolean empty) {
            isEmpty = empty;
        }

    }
