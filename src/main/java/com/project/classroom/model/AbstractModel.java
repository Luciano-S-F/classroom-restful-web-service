package com.project.classroom.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Abstract base class for all entity models to provide common identification,
 * version control, and timestamp tracking.
 */
@MappedSuperclass
public abstract class AbstractModel implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Version
  private Integer version;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private Date timestamp;

  @UpdateTimestamp
  @Column(nullable = false)
  private Date updateTimestamp;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public Date getUpdateTimestamp() {
    return updateTimestamp;
  }

  public void setUpdateTimestamp(Date updateTimestamp) {
    this.updateTimestamp = updateTimestamp;
  }

  @Override
  public String toString() {
    return "AbstractModel{" +
            "id=" + id +
            ", version=" + version +
            ", timestamp=" + timestamp +
            ", updateTimestamp=" + updateTimestamp +
            '}';
  }
}
