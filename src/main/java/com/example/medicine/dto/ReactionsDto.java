package com.example.medicine.dto;

import com.example.medicine.model.Reactions;
import com.sun.istack.NotNull;

public class ReactionsDto {

    public static class Create {
        @NotNull
        public String description;
        public Reactions toModel() {
            return new Reactions(description = this.description);
        }
    }
    public static class Update {
        @NotNull
        public String description;
        public Reactions toModel() {
            return new Reactions(description = this.description);
        }
    }
}
