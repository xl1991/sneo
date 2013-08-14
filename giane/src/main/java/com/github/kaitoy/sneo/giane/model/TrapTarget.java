/*_##########################################################################
  _##
  _##  Copyright (C) 2012 Kaito Yamada
  _##
  _##########################################################################
*/

package com.github.kaitoy.sneo.giane.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.github.kaitoy.sneo.network.dto.TrapTargetDto;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

@Entity
@Table(name = "TRAP_TARGET")
public class TrapTarget implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 4278317055245966206L;

  private Integer id;
  private String name;
  private String address;
  private Integer port;
  private List<TrapTargetGroup> trapTargetGroups;

  @Id
  @GeneratedValue(generator = "SequenceStyleGenerator")
  @GenericGenerator(
    name = "SequenceStyleGenerator",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
      @Parameter(name = "sequence_name", value = "TRAP_TARGET_SEQUENCE"),
      @Parameter(name = "initial_value", value = "1"),
      @Parameter(name = "increment_size", value = "1")
    }
  )
  @Column(name = "ID")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "NAME", nullable = false, length = 50, unique = true)
  public String getName() {
    return name;
  }

  @RequiredStringValidator(
    key = "RequiredStringValidator.error",
    trim = true,
    shortCircuit = true // Stops checking if detects error
  )
  @StringLengthFieldValidator(
    key = "StringLengthFieldValidator.error.max",
    trim = true,
    maxLength = "50",
    shortCircuit = true
  )
  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "ADDRESS", nullable = false)
  public String getAddress() {
    return address;
  }

  @RequiredStringValidator(
    key = "RequiredStringValidator.error",
    trim = true,
    shortCircuit = true // Stops checking if detects error
  )
  @RegexFieldValidator(
    key = "RegexFieldValidator.error",
    expression = "[0-9]{1,3}(\\.[0-9]{1,3}){3}",
    shortCircuit = true
  )
  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "PORT", nullable = false)
  public Integer getPort() {
    return port;
  }

  @RequiredFieldValidator(
    key = "RequiredFieldValidator.error",
    shortCircuit = true
  )
  @IntRangeFieldValidator(
    key = "IntRangeFieldValidator.error.min.max",
    min = "1",
    max = "65535",
    shortCircuit = true
  )
  public void setPort(Integer port) {
    this.port = port;
  }

  @ManyToMany(mappedBy = "trapTargets", fetch = FetchType.LAZY)
  //@LazyCollection(LazyCollectionOption.TRUE)
  public List<TrapTargetGroup> getTrapTargetGroups() {
    return trapTargetGroups;
  }

  public void setTrapTargetGroups(List<TrapTargetGroup> trapTargetGroups) {
    this.trapTargetGroups = trapTargetGroups;
  }

  public TrapTargetDto toDto() {
    TrapTargetDto dto = new TrapTargetDto();
    dto.setId(id);
    dto.setName(name);
    dto.setAddress(address);
    dto.setPort(port);
    return dto;
  }

}