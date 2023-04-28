package net.joagz.worldnewsback.dto;

public record NewsDto(
        String _id,
        String title,
        String release_date,
        String content,
        String short_description) {

}
