package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
    name = "likes",
    uniqueConstraints = {
        @UniqueConstraint(
            name="likes_uk", // 제약 조건 이름
            columnNames = {"imageId", "userId"} // 실제 디비 컬럼명 (JoinColumn)
        )
    }
)
public class Likes {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JoinColumn(name="imageId")
  @ManyToOne
  private Image image;

  @JsonIgnoreProperties({"images"})
  @JoinColumn(name="userId")
  @ManyToOne
  private User user;

  private LocalDateTime createDate;

  @PrePersist
  public void createDate() {
    this.createDate = LocalDateTime.now();
  }
}
