package com.jojostudio.recipe.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// https://medium.com/programmingmitra-com/spring-data-jpa-auditing-saving-createdby-createddate-lastmodifiedby-lastmodifieddate-c2d64c42998e
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {
  @CreatedBy
  protected U createdBy;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected Date creationDate;

  @LastModifiedBy
  protected U lastModifiedBy;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected Date lastModifiedDate;
}