package com.acme.studenthome;

import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.model.PropertiesSystem.PropertyComment;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.acme.studenthome.domain.service.PropertiesSystemService.PropertyCommentService;
import com.acme.studenthome.service.PropertiesSystemService.PropertyCommentServiceImpl;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PropertyCommentServiceImplTest {
    @MockBean
    private PropertyCommentService propertyCommentService;

    @Autowired
    private PropertyCommentService commentService;

    @TestConfiguration
    static class PropertyCommentServiceImplTestConfiguration {
        @Bean
        public PropertyCommentService commentService() {
            return new PropertyCommentServiceImpl();
        }
    }

    @Test
    @DisplayName("When CreatePropertyComment With Correct Data Then Returns the Comment")
    public void WhenCreatePropertyCommentWithCorrectDataThenReturnsTheComment() {
        PropertyComment propertyComment = new PropertyComment();
        propertyComment.setComment("Comment");
        propertyComment.setScore(5L);
        propertyComment.setStudent(new Student());
        propertyComment.setProperty(new Property());
        propertyComment.setId(1L);

        when(propertyCommentService.createPropertyComment(1L, 2L, propertyComment))
            .thenReturn(propertyComment);

        //Act

        PropertyComment comment = commentService.createPropertyComment(1L, 2L, propertyComment);

        //Assert
        assertThat(comment).isEqualTo(propertyComment);
    }

    @Test
    @DisplayName("When CreatePropertyComment With Incorrect Data Then Returns A Bad Message")
    public void WhenCreatePropertyCommentWithIncorrectDataThenReturnsABadMessage() {
        PropertyComment propertyComment = new PropertyComment();
        propertyComment.setComment("Comment");
        propertyComment.setScore(5L);
        propertyComment.setId(1L);

        when(propertyCommentService.createPropertyComment(5L, 2L, propertyComment))
                .thenThrow(new RuntimeException("Incorrect Data"));

        //Act


        Throwable exception = catchThrowable(() -> {
            PropertyComment comment = commentService.createPropertyComment(5L, 2L, propertyComment);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Incorrect Data");
    }

}