package com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveStudentOpinionResource {

    @NotNull
    private Long score;

    @NotNull
    @Size(max = 250)
    private String content;

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
