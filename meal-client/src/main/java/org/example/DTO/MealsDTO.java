package org.example.DTO;

import java.util.List;

public record MealsDTO(List<meals> meals) {
        public record meals(
                String idMeal,
                String strMeal,
                String strCategory,
                String strArea,
                String strInstructions,
                String strMealThumb,
                String strTags,
                String strYoutube
        ) {}
}
